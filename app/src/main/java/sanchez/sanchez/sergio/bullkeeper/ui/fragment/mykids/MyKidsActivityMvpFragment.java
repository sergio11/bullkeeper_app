package sanchez.sanchez.sergio.bullkeeper.ui.fragment.mykids;

import android.support.annotation.NonNull;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import javax.inject.Inject;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEFragment;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykids.IMyKidsActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.MyKidsAdapter;
import timber.log.Timber;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.TOOLBAR_WITH_MENU;

/**
 * My Kids Activity Fragment
 */
public class MyKidsActivityMvpFragment extends SupportMvpLCEFragment<MyKidsFragmentPresenter,
        IMyKidsView, IMyKidsActivityHandler, MyKidsComponent, SonEntity> implements IMyKidsView,
        MyKidsAdapter.OnMyKidsListener {

    public static String TAG = "MY_KIDS_ACTIVITY_FRAGMENT";

    /**
     * Picasso
     */
    @Inject
    protected Picasso picasso;


    public MyKidsActivityMvpFragment() {}

    /**
     * New Instance
     * @return
     */
    public static MyKidsActivityMvpFragment newInstance() {
        MyKidsActivityMvpFragment fragment = new MyKidsActivityMvpFragment();
        return fragment;
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<SonEntity> getAdapter() {
        final MyKidsAdapter myKidsAdapter = new MyKidsAdapter(appContext, new ArrayList<SonEntity>(), picasso);
        myKidsAdapter.setOnSupportRecyclerViewListener(this);
        myKidsAdapter.setOnMyKidsListenerListener(this);
        return myKidsAdapter;
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public MyKidsFragmentPresenter providePresenter() {
        return component.myKidsFragmentPresenter();
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() { }

    /**
     * ON Item Click
     * @param sonEntity
     */
    @Override
    public void onItemClick(SonEntity sonEntity) {
        activityHandler.navigateToMyKidDetail(sonEntity.getIdentity());
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
        return R.layout.fragment_my_kids;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(MyKidsComponent component) {
        component.inject(this);
    }


    /**
     * On Detail Action CLicked
     * @param sonEntity
     */
    @Override
    public void onDetailActionClicked(SonEntity sonEntity) {
        Timber.d("On Detail for -> %s ", sonEntity.getIdentity());
        activityHandler.navigateToMyKidDetail(sonEntity.getIdentity());
    }

    /**
     * On Results Action Clicked
     * @param sonEntity
     */
    @Override
    public void onResultsActionClicked(final SonEntity sonEntity) {
        Timber.d("On Results for -> %s ", sonEntity.getIdentity());
        activityHandler.navigateToKidsResults(sonEntity.getIdentity());
    }

    /**
     * On Alerts Action Clicked
     * @param sonEntity
     */
    @Override
    public void onAlertsActionClicked(final SonEntity sonEntity) {
        Timber.d("On Alerts for -> %s ", sonEntity.getIdentity());
        activityHandler.navigateToSonAlerts(sonEntity.getIdentity());
    }

    /**
     * On Relations Action Clicked
     * @param sonEntity
     */
    @Override
    public void onRelationsActionClicked(final SonEntity sonEntity) {
        Timber.d("On Relations for -> %s ", sonEntity.getIdentity());
        activityHandler.navigateToComments(sonEntity.getIdentity());
    }

    /**
     * On Profile Action Clicked
     * @param sonEntity
     */
    @Override
    public void onProfileActionClicked(final SonEntity sonEntity) {
        Timber.d("On Profile for -> %s ", sonEntity.getIdentity());
        activityHandler.navigateToMyKidsProfile(sonEntity.getIdentity());

    }

    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return TOOLBAR_WITH_MENU;
    }

    /**
     * Add Child
     */
    @OnClick(R.id.addChild)
    protected void addChild() {
        activityHandler.navigateToAddChild();
    }
}
