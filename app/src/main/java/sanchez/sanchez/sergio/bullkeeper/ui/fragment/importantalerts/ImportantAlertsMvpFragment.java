package sanchez.sanchez.sergio.bullkeeper.ui.fragment.importantalerts;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.fernandocejas.arrow.checks.Preconditions;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import javax.inject.Inject;

import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.LastAlertsAdapter;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.IMyKidsDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import timber.log.Timber;

/**
 * Important Alerts Fragment
 */
public class ImportantAlertsMvpFragment extends SupportMvpLCEFragment<ImportantAlertsFragmentPresenter,
        IImportantAlertsFragmentView, IMyKidsDetailActivityHandler, MyKidsComponent, AlertEntity> implements IImportantAlertsFragmentView {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * App Context
     */
    @Inject
    protected Context appContext;



    /**
     * Kid Identity
     */
    private String kidIdentity;


    /**
     *
     */
    public ImportantAlertsMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static ImportantAlertsMvpFragment newInstance(final String kidIdentity) {
        ImportantAlertsMvpFragment fragment = new ImportantAlertsMvpFragment();
        Bundle args = new Bundle();
        args.putString(KID_IDENTITY_ARG, kidIdentity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() == null ||
                !getArguments().containsKey(KID_IDENTITY_ARG))
            throw new IllegalStateException("You must provide son identity - Illegal State");
        kidIdentity = getArguments().getString(KID_IDENTITY_ARG);
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        Timber.d("Get Args");
        final Bundle args = new Bundle();
        args.putString(ImportantAlertsFragmentPresenter.SON_IDENTITY_ARG, kidIdentity);
        return args;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_important_alerts;
    }

    /**
     * Initialize Injector
     * @param component
     */
    @Override
    protected void initializeInjector(MyKidsComponent component) {
        component.inject(this);
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {

    }

    /**
     * On Item Click
     * @param alertEntity
     */
    @Override
    public void onItemClick(AlertEntity alertEntity) {
        Preconditions.checkNotNull(alertEntity, "Alert Entity can not be null");
        activityHandler.navigateToAlertDetail(alertEntity.getIdentity(), alertEntity.getSon().getIdentity());
    }

    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() {}

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<AlertEntity> getAdapter() {
        final LastAlertsAdapter lastAlertsAdapter = new LastAlertsAdapter(appContext, new ArrayList<AlertEntity>());
        lastAlertsAdapter.setOnSupportRecyclerViewListener(this);
        return lastAlertsAdapter;
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public ImportantAlertsFragmentPresenter providePresenter() {
        return component.importantAlertsFragmentPresenter();
    }

    /**
     * On Show Alerts
     */
    @OnClick(R.id.showAlerts)
    protected void onShowAlerts(){
        activityHandler.navigateToWarningAlerts(kidIdentity);
    }

}

