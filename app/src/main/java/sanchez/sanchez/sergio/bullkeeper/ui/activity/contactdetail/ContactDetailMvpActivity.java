package sanchez.sanchez.sergio.bullkeeper.ui.activity.contactdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.crashlytics.android.answers.ContentViewEvent;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.ContactDetailComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerContactDetailComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.contactdetail.ContactDetailActivityMvpFragment;

/**
 * Contact Detail
 */
public class ContactDetailMvpActivity extends SupportMvpActivity<ContactDetailPresenter, IContactDetailView>
        implements HasComponent<ContactDetailComponent>
        , IContactDetailView, IContactDetailActivityHandler {

    private final String CONTENT_FULL_NAME = "CONTACT_DETAIL";
    private final String CONTENT_TYPE_NAME = "CONTACTS";

    /**
     * Args
     */
    public static String TERMINAL_ID_ARG = "TERMINAL_ID_ARG";
    public static String KID_ID_ARG = "KID_ID_ARG";
    public static String CONTACT_ID_ARG = "CONTACT_ID_ARG";

    /**
     * Contact Detail Component
     */
    private ContactDetailComponent contactDetailComponent;

    /**
     * Get Calling Intent
     * @param context
     * @param kid
     * @param terminal
     * @param contact
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String kid,
                                          final String terminal, final String contact) {
        final Intent intent = new Intent(context, ContactDetailMvpActivity.class);
        intent.putExtra(KID_ID_ARG, kid);
        intent.putExtra(TERMINAL_ID_ARG, terminal);
        intent.putExtra(CONTACT_ID_ARG, contact);
        return intent;
    }


    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        contactDetailComponent = DaggerContactDetailComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        contactDetailComponent.inject(this);

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

            if (!getIntent().hasExtra(CONTACT_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an contact identifier");

            final String terminal = getIntent().getStringExtra(TERMINAL_ID_ARG);
            final String kid = getIntent().getStringExtra(KID_ID_ARG);
            final String contact = getIntent().getStringExtra(CONTACT_ID_ARG);

            addFragment(R.id.mainContainer,
                    ContactDetailActivityMvpFragment.newInstance(terminal, kid, contact), false);
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
    public ContactDetailPresenter providePresenter() {
        return contactDetailComponent.contactDetailPresenter();
    }

    /**
     * Get component
     * @return
     */
    @Override
    public ContactDetailComponent getComponent() {
        return contactDetailComponent;
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
