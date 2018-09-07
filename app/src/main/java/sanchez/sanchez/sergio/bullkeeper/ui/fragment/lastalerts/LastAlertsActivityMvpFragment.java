package sanchez.sanchez.sergio.bullkeeper.ui.fragment.lastalerts;

import android.support.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.HomeComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykids.IMyKidsActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.LastAlertsAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.MyKidsAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportMvpLCEFragment;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.models.AlertsPageEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import timber.log.Timber;

import static sanchez.sanchez.sergio.bullkeeper.ui.support.SupportToolbarApp.TOOLBAR_WITH_MENU;

/**
 * Last Alerts Activity Fragment
 */
public class LastAlertsActivityMvpFragment extends SupportMvpLCEFragment<LastAlertsFragmentPresenter,
        ILastAlertsView, IMyKidsActivityHandler, HomeComponent, AlertEntity> implements ILastAlertsView,
        LastAlertsAdapter.OnSupportRecyclerViewListener<AlertEntity> {

    public static String TAG = "LAST_ALERTS_ACTIVITY_MVP";


    public LastAlertsActivityMvpFragment() {}

    /**
     * New Instance
     * @return
     */
    public static LastAlertsActivityMvpFragment newInstance() {
        LastAlertsActivityMvpFragment fragment = new LastAlertsActivityMvpFragment();
        return fragment;
    }



    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public LastAlertsFragmentPresenter providePresenter() {
        return component.lastAlertsFragmentPresenter();
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() { }


    /**
     * On Item Clicked
     * @param alertEntity
     */
    @Override
    public void onItemClick(AlertEntity alertEntity) {
    }

    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() { }


    /**
     * Get Layout Res
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_last_alerts;
    }


    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(HomeComponent component) {
        component.inject(this);
    }



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
}
