package sanchez.sanchez.sergio.bullkeeper.sse.impl;

import android.content.Context;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernandocejas.arrow.checks.Preconditions;
import com.here.oksse.OkSse;
import com.here.oksse.ServerSentEvent;
import java.util.Locale;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.events.ILocalSystemNotification;
import sanchez.sanchez.sergio.bullkeeper.core.notification.INotificationHelper;
import sanchez.sanchez.sergio.bullkeeper.events.impl.KidLocationUpdatedEvent;
import sanchez.sanchez.sergio.bullkeeper.sse.ISseEventHandler;
import sanchez.sanchez.sergio.bullkeeper.sse.SseEventTypeEnum;
import sanchez.sanchez.sergio.bullkeeper.sse.models.CurrentLocationUpdateDTO;
import sanchez.sanchez.sergio.bullkeeper.sse.models.KidRequestCreatedDTO;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.kidrequestdetail.KidRequestDetailMvpActivity;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.models.RequestTypeEnum;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import timber.log.Timber;

/**
 * SSE Event Handler Impl
 */
public final class SseEventHandlerImpl implements ISseEventHandler,
            ServerSentEvent.Listener, SseEventTypeEnum.ISseEventsVisitor {


    private final static String EVENT_TYPE_NAME = "event_type";

    /**
     * Context
     */
    private Context context;

    /**
     * Api End Point Helper
     */
    private ApiEndPointsHelper apiEndPointsHelper;

    /**
     * Ok Http Client
     */
    private OkHttpClient okHttpClient;

    /**
     * Preference Repository
     */
    private IPreferenceRepository preferenceRepository;

    /**
     * Object Mapper
     */
    private ObjectMapper objectMapper;

    /**
     * Notification Helper
     */
    private INotificationHelper notificationHelper;

    /**
     * Local System Notification
     */
    private ILocalSystemNotification localSystemNotification;

    /**
     * Ok Sse
     */
    private final OkSse okSse;
    /**
     * State
     * ==============
     */

    private ServerSentEvent serverSentEvent = null;

    /**
     * @param context
     * @param apiEndPointsHelper
     * @param okHttpClient
     * @param preferenceRepository
     * @param objectMapper
     * @param notificationHelper
     */
    public SseEventHandlerImpl(
            final Context context,
            final ApiEndPointsHelper apiEndPointsHelper,
            final OkHttpClient okHttpClient,
            final IPreferenceRepository preferenceRepository,
            final ObjectMapper objectMapper,
            final INotificationHelper notificationHelper,
            final ILocalSystemNotification localSystemNotification) {
        this.context = context;
        this.apiEndPointsHelper = apiEndPointsHelper;
        this.okHttpClient = okHttpClient;
        this.preferenceRepository = preferenceRepository;
        this.objectMapper = objectMapper;
        this.notificationHelper = notificationHelper;
        this.localSystemNotification = localSystemNotification;
        this.okSse = new OkSse(okHttpClient);
    }

    /**
     * Open
     */
    @Override
    public void open() {

        if(!preferenceRepository.getPrefCurrentUserIdentity()
                .equals(IPreferenceRepository.CURRENT_USER_IDENTITY_DEFAULT_VALUE)) {
            final Request eventSubscriptionRequest = new Request.Builder()
                    .url(apiEndPointsHelper.getEventSubscriptionUrl(preferenceRepository
                            .getPrefCurrentUserIdentity()))
                    .build();
            serverSentEvent = okSse.newServerSentEvent(eventSubscriptionRequest, this);
        }
    }

    /**
     * Is Opened
     * @return
     */
    @Override
    public boolean isOpened() {
        return serverSentEvent != null;
    }

    /**
     * Close
     */
    @Override
    public void close() {
        if(serverSentEvent != null) {
            serverSentEvent.close();
            serverSentEvent = null;
        }
    }


    /**
     *
     * @param sse
     * @param response
     */
    @Override
    public void onOpen(ServerSentEvent sse, Response response) {
        Timber.d("SSE: On Open");
    }

    /**
     *
     * @param sse
     * @param id
     * @param event
     * @param message
     */
    @Override
    public void onMessage(ServerSentEvent sse, String id, String event, String message) {
        Timber.d("SSE: On Message -> %s", message);
        try {
            final JsonNode jsonNode = objectMapper.readTree(message);
            if(jsonNode.has(EVENT_TYPE_NAME)) {
                final String eventType = jsonNode.get(EVENT_TYPE_NAME).asText();
                final SseEventTypeEnum sseEventTypeEnum = SseEventTypeEnum.valueOf(eventType);
                sseEventTypeEnum.accept(this, message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param sse
     * @param comment
     */
    @Override
    public void onComment(ServerSentEvent sse, String comment) {
        Timber.d("SSE: On Comment -> %s", comment);
    }

    /**
     *
     * @param sse
     * @param milliseconds
     * @return
     */
    @Override
    public boolean onRetryTime(ServerSentEvent sse, long milliseconds) {
        Timber.d("SSE: On Retry Time");
        return false;
    }

    /**
     *
     * @param sse
     * @param throwable
     * @param response
     * @return
     */
    @Override
    public boolean onRetryError(ServerSentEvent sse, Throwable throwable, Response response) {
        Timber.d("SSE: On Retry Error");
        return false;
    }

    /**
     *
     * @param sse
     */
    @Override
    public void onClosed(ServerSentEvent sse) {
        Timber.d("SSE: On Closed");
        if(!preferenceRepository.getPrefCurrentUserIdentity()
                .equals(IPreferenceRepository.CURRENT_USER_IDENTITY_DEFAULT_VALUE))
            open();
    }

    /**
     *
     * @param sse
     * @param originalRequest
     * @return
     */
    @Override
    public Request onPreRetry(ServerSentEvent sse, Request originalRequest) {
        Timber.d("SSE: On Pre Retry");
        return null;
    }

    /**
     * Visit Kid Request Created Event
     * @param sseEventTypeEnum
     * @param message
     */
    @Override
    public void visitKidRequestCreatedEvent(
            final SseEventTypeEnum sseEventTypeEnum,
            final String message) {
        Preconditions.checkNotNull(sseEventTypeEnum, "SSE Event Type can not be null");
        Preconditions.checkNotNull(message, "Message can not be null");
        Preconditions.checkState(!message.isEmpty(), "Message can not be empty");

        try {

            final KidRequestCreatedDTO kidRequestCreatedDTO =
                    objectMapper.readValue(message, KidRequestCreatedDTO.class);

            final String kidName = kidRequestCreatedDTO.getKid().getFirstName() + " " +
                    kidRequestCreatedDTO.getKid().getLastName();

            String title = null, body = null;

            switch (RequestTypeEnum.valueOf(kidRequestCreatedDTO.getType())) {
                case PICKMEUP:

                    title = String.format(Locale.getDefault(), context.getString(R.string.kid_request_notification_pickmeup_title),
                                    kidName);
                    body = context.getString(R.string.kid_request_notification_pickmeup_description);
                    break;
                case SOS:
                    title = String.format(Locale.getDefault(), context.getString(R.string.kid_request_notification_sos_title),
                            kidName);
                    body = context.getString(R.string.kid_request_notification_sos_description);
                    break;
            }

            notificationHelper.showImportantNotification(title, body,
                    KidRequestDetailMvpActivity.getCallingIntent(context,
                            kidRequestCreatedDTO.getKid().getIdentity(), kidRequestCreatedDTO.getIdentity()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Visit Current Location Update Event
     * @param sseEventTypeEnum
     * @param message
     */
    @Override
    public void visitCurrentLocationUpdateEvent(
            final SseEventTypeEnum sseEventTypeEnum, final String message) {
        Preconditions.checkNotNull(sseEventTypeEnum, "SSE Event Type can not be null");
        Preconditions.checkNotNull(message, "Message can not be null");

        try {

            final CurrentLocationUpdateDTO currentLocationUpdateDTO =
                    objectMapper.readValue(message, CurrentLocationUpdateDTO.class);

            // Send Notification
            localSystemNotification.sendNotification(
                    new KidLocationUpdatedEvent(
                            currentLocationUpdateDTO.getKid(),
                            currentLocationUpdateDTO.getAddress(),
                            currentLocationUpdateDTO.getLat(),
                            currentLocationUpdateDTO.getLog()
                    )
            );

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
