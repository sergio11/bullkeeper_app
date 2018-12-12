package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.smsdetail;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.sms.GetSmsDetailInteract;
import sanchez.sanchez.sergio.domain.models.SmsEntity;

/**
 * Sms Detail Presenter
 */
public final class SmsDetailFragmentPresenter extends SupportPresenter<ISmsDetailView> {

    /**
     * Get SMS Detail Interact
     */
    private final GetSmsDetailInteract getSmsDetailInteract;

    /**
     *
     * @param getSmsDetailInteract
     */
    @Inject
    public SmsDetailFragmentPresenter(
            final GetSmsDetailInteract getSmsDetailInteract
    ){
        this.getSmsDetailInteract = getSmsDetailInteract;
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

        final String kid = args.getString(SmsDetailActivityMvpFragment.KID_ID_ARG);
        final String terminal = args.getString(SmsDetailActivityMvpFragment.TERMINAL_ID_ARG);
        final String sms = args.getString(SmsDetailActivityMvpFragment.SMS_ID_ARG);


        getSmsDetailInteract.execute(new GetSmsDetailObserver(),
                GetSmsDetailInteract.Params.create(kid, terminal, sms));

    }

    /**
     * Get Sms Detail Observer
     */
    public class GetSmsDetailObserver extends BasicCommandCallBackWrapper<SmsEntity> {

        /**
         * On Success
         * @param smsEntity
         */
        @Override
        protected void onSuccess(SmsEntity smsEntity) {
            Preconditions.checkNotNull(smsEntity, "Sms can not be null");
            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onSmsDetailLoaded(smsEntity);
            }
        }
    }


}
