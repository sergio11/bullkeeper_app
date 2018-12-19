package sanchez.sanchez.sergio.bullkeeper.ui.activity.calldetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.crashlytics.android.answers.ContentViewEvent;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.CallDetailComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerCallDetailComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.calldetail.CallDetailActivityMvpFragment;

/**
 * Call Detail
 */
public class CallDetailMvpActivity extends SupportMvpActivity<CallDetailPresenter, ICallDetailView>
        implements HasComponent<CallDetailComponent>
        , ICallDetailView, ICallDetailActivityHandler {

    private final String CONTENT_FULL_NAME = "CALL_DETAIL";
    private final String CONTENT_TYPE_NAME = "CALLS";

    /**
     * Args
     */
    public static String TERMINAL_ID_ARG = "TERMINAL_ID_ARG";
    public static String KID_ID_ARG = "KID_ID_ARG";
    public static String CALL_ID_ARG = "CALL_ID_ARG";

    /**
     * Call Detail Component
     */
    private CallDetailComponent callDetailComponent;

    /**
     * Get Calling Intent
     * @param context
     * @param kid
     * @param terminal
     * @param call
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String kid,
                                          final String terminal, final String call) {
        final Intent intent = new Intent(context, CallDetailMvpActivity.class);
        intent.putExtra(KID_ID_ARG, kid);
        intent.putExtra(TERMINAL_ID_ARG, terminal);
        intent.putExtra(CALL_ID_ARG, call);
        return intent;
    }


    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        callDetailComponent = DaggerCallDetailComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        callDetailComponent.inject(this);

    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_fragment_container;
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(final Bundle savedInstanceState) {
        if(savedInstanceState == null) {


            if(!getIntent().hasExtra(TERMINAL_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an terminal identifier");

            if (!getIntent().hasExtra(KID_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an kid identifier");

            if (!getIntent().hasExtra(CALL_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an call identifier");

            final String terminal = getIntent().getStringExtra(TERMINAL_ID_ARG);
            final String kid = getIntent().getStringExtra(KID_ID_ARG);
            final String call = getIntent().getStringExtra(CALL_ID_ARG);

            addFragment(R.id.mainContainer,
                    CallDetailActivityMvpFragment.newInstance(terminal, kid, call), false);
        }
    }

    /**
     * On Create Content View Event
     * @return
     */
    @Override
    protected ContentViewEvent onCreateContentViewEvent() {
        return new ContentViewEvent()
                .putContentName(CONTENT_FULL_NAME)
                .putContentType(CONTENT_TYPE_NAME);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public CallDetailPresenter providePresenter() {
        return callDetailComponent.callDetailPresenter();
    }

    /**
     * Get component
     * @return
     */
    @Override
    public CallDetailComponent getComponent() {
        return callDetailComponent;
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.background_cyan_5;
    }

}
