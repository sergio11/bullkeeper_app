package sanchez.sanchez.sergio.bullkeeper.ui.activity.terminaldetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.crashlytics.android.answers.ContentViewEvent;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerTerminalComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.TerminalComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.terminaldetail.TerminalDetailActivityMvpFragment;

/**
 * Terminal Detail
 */
public class TerminalDetailMvpActivity extends SupportMvpActivity<TerminalDetailPresenter, ITerminalDetailView>
        implements HasComponent<TerminalComponent>
        , ITerminalDetailView {

    private final String CONTENT_FULL_NAME = "TERMINAL_DETAIL";
    private final String CONTENT_TYPE_NAME = "TERMINALS";

    /**
     * Args
     */
    public static String TERMINAL_ID_ARG = "TERMINAL_ID_ARG";
    public static String SON_ID_ARG = "SON_ID_ARG";

    /**
     * Terminal Component
     */
    private TerminalComponent terminalComponent;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String sonId, final String terminalId) {
        final Intent intent = new Intent(context, TerminalDetailMvpActivity.class);
        intent.putExtra(SON_ID_ARG, sonId);
        intent.putExtra(TERMINAL_ID_ARG, terminalId);
        return intent;
    }


    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        terminalComponent = DaggerTerminalComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        terminalComponent.inject(this);

    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_fragment_container;
    }


    @Override
    protected void onViewReady(final Bundle savedInstanceState) {
        if(savedInstanceState == null) {


            if(!getIntent().hasExtra(TERMINAL_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an terminal identifier");

            if (!getIntent().hasExtra(SON_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an son identifier");

            final String alertId = getIntent().getStringExtra(TERMINAL_ID_ARG);
            final String sonId = getIntent().getStringExtra(SON_ID_ARG);

            addFragment(R.id.mainContainer,
                    TerminalDetailActivityMvpFragment.newInstance(alertId, sonId), false);
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
    public TerminalDetailPresenter providePresenter() {
        return terminalComponent.terminalDetailPresenter();
    }

    /**
     * Get component
     * @return
     */
    @Override
    public TerminalComponent getComponent() {
        return terminalComponent;
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
