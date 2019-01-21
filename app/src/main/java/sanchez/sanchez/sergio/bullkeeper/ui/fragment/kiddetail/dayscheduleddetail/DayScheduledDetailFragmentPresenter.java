package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.dayscheduleddetail;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.funtime.GetFunTimeDayScheduledInteract;
import sanchez.sanchez.sergio.domain.interactor.funtime.SaveFunTimeDayScheduledInteract;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;

/**
 * Terminal Detail Presenter
 */
public final class DayScheduledDetailFragmentPresenter
        extends SupportPresenter<IDayScheduledDetailView> {


    /**
     * Get Fun Time Day Scheduled Interact
     */
    private final GetFunTimeDayScheduledInteract getFunTimeDayScheduledInteract;

    /**
     * Save Fun Time Day Scheduled Interact
     */
    private final SaveFunTimeDayScheduledInteract saveFunTimeDayScheduledInteract;


    /**
     *
     * @param getFunTimeDayScheduledInteract
     * @param saveFunTimeDayScheduledInteract
     */
    @Inject
    public DayScheduledDetailFragmentPresenter(
            final GetFunTimeDayScheduledInteract getFunTimeDayScheduledInteract,
            final SaveFunTimeDayScheduledInteract saveFunTimeDayScheduledInteract) {
        this.getFunTimeDayScheduledInteract = getFunTimeDayScheduledInteract;
        this.saveFunTimeDayScheduledInteract = saveFunTimeDayScheduledInteract;
    }

    /**
     * On Init
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        final String kid = args.getString(DayScheduledDetailActivityMvpFragment.KID_ID_ARG);
        final String terminal = args.getString(DayScheduledDetailActivityMvpFragment.TERMINAL_ID_ARG);
        final String day = args.getString(DayScheduledDetailActivityMvpFragment.DAY_SCHEDULED_ARG);

        getFunTimeDayScheduledInteract.execute(new GetFunTimeDayScheduledObserver(),
                GetFunTimeDayScheduledInteract.Params.create(kid, terminal, day));
    }


    /**
     *
     * @param kid
     * @param terminal
     * @param day
     * @param enabled
     * @param totalHours
     */
    public void saveDayScheduled(final String kid, final String terminal, final String day,
                                 final Boolean enabled, final Integer totalHours) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkNotNull(day, "Day can not be null");

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        saveFunTimeDayScheduledInteract.execute(new SaveFunTimeDayScheduledObserver(),
                SaveFunTimeDayScheduledInteract.Params.create(kid, terminal, day,
                        enabled, totalHours));

    }

    /**
     * Save Fun Time Day Scheduled Observer
     */
    public class SaveFunTimeDayScheduledObserver extends BasicCommandCallBackWrapper<DayScheduledEntity> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(final DayScheduledEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().showNoticeDialog(R.string.fun_time_saved_successfully);
            }

        }
    }


    /**
     * Get Fun Time Day Scheduled Observer
     */
    public class GetFunTimeDayScheduledObserver extends BasicCommandCallBackWrapper<DayScheduledEntity> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(final DayScheduledEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDayScheduledLoaded(response);
            }

        }
    }

}
