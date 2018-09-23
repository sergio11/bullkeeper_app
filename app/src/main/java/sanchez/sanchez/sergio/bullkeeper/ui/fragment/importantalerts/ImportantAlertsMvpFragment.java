package sanchez.sanchez.sergio.bullkeeper.ui.fragment.importantalerts;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.IMyKidsDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.LastAlertsAdapter;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;

/**
 * Important Alerts Fragment
 */
public class ImportantAlertsMvpFragment extends SupportMvpFragment<ImportantAlertsFragmentPresenter,
        IImportantAlertsFragmentView, IMyKidsDetailActivityHandler, MyKidsComponent>
        implements IImportantAlertsFragmentView,
        SupportRecyclerViewAdapter.OnSupportRecyclerViewListener<AlertEntity> {

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
     * Important Alerts
     */
    @BindView(R.id.alertsList)
    protected RecyclerView alertsList;

    /**
     * Last Alerts Adapter
     */
    private LastAlertsAdapter lastAlertsAdapter;


    public ImportantAlertsMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     *
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
    protected int getLayoutRes() {
        return R.layout.fragment_important_alerts;
    }

    /**
     * Initialize Injector
     *
     * @param component
     */
    @Override
    protected void initializeInjector(MyKidsComponent component) {
        component.inject(this);
    }

    /**
     * Provide Presenter
     *
     * @return
     */
    @NonNull
    @Override
    public ImportantAlertsFragmentPresenter providePresenter() {
        return component.importantAlertsFragmentPresenter();
    }

    /**
     * On View Created
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() == null || !getArguments().containsKey(KID_IDENTITY_ARG))
            throw new IllegalArgumentException("You must provide a child identifier");

        kidIdentity = getArguments().getString(KID_IDENTITY_ARG);

        ViewCompat.setNestedScrollingEnabled(alertsList, false);
        alertsList.setLayoutManager(new LinearLayoutManager(appContext));
        /*lastAlertsAdapter = new LastAlertsAdapter(appContext, new ArrayList<AlertEntity>());
        lastAlertsAdapter.setOnSupportRecyclerViewListener(this);
        // Set Animator
        alertsList.setItemAnimator(new DefaultItemAnimator());
        alertsList.setAdapter(lastAlertsAdapter);*/
    }

    /**
     * On Alerts Loaded
     *
     * @param alertEntityList
     */
    @Override
    public void onAlertsLoaded(List<AlertEntity> alertEntityList) {
        lastAlertsAdapter.setData(new ArrayList<>(alertEntityList));
        lastAlertsAdapter.notifyDataSetChanged();
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {
        activityHandler.navigateToAlerts();
    }

    /**
     * On Item Click
     *
     * @param alertEntity
     */
    @Override
    public void onItemClick(AlertEntity alertEntity) {
        Preconditions.checkNotNull(alertEntity, "Alert Entity can not be null");
        activityHandler.navigateToAlertDetail(alertEntity.getIdentity(), alertEntity.getSon().getIdentity());
    }

    @Override
    public void onFooterClick() {

    }
}

