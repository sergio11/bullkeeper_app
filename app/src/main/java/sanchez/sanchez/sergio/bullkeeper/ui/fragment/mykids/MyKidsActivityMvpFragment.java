package sanchez.sanchez.sergio.bullkeeper.ui.fragment.mykids;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import javax.inject.Inject;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEFragment;
import sanchez.sanchez.sergio.domain.models.GuardianRolesEnum;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykids.IMyKidsActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.MyKidsAdapter;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;
import timber.log.Timber;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.TOOLBAR_WITH_MENU;

/**
 * My Kids Activity Fragment
 */
public class MyKidsActivityMvpFragment extends SupportMvpLCEFragment<MyKidsFragmentPresenter,
        IMyKidsView, IMyKidsActivityHandler, MyKidsComponent, SupervisedChildrenEntity> implements IMyKidsView,
        MyKidsAdapter.OnMyKidsListener {

    public static String TAG = "MY_KIDS_ACTIVITY_FRAGMENT";

    /**
     * Picasso
     */
    @Inject
    protected Picasso picasso;

    /**
     * Activity
     */
    @Inject
    protected Activity activity;


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
    protected SupportRecyclerViewAdapter<SupervisedChildrenEntity> getAdapter() {
        final MyKidsAdapter myKidsAdapter = new MyKidsAdapter(activity, new ArrayList<SupervisedChildrenEntity>(), picasso);
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
     * @param supervisedChildrenEntity
     */
    @Override
    public void onItemClick(final SupervisedChildrenEntity supervisedChildrenEntity) {
        final KidEntity kidEntity = supervisedChildrenEntity.getKid();
        activityHandler.navigateToMyKidDetail(kidEntity.getIdentity());
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
     * @param kidEntity
     * @param role
     */
    @Override
    public void onDetailActionClicked(final KidEntity kidEntity, final GuardianRolesEnum role) {
        Timber.d("On Detail for -> %s ", kidEntity.getIdentity());
        activityHandler.navigateToMyKidDetail(kidEntity.getIdentity());
    }

    /**
     * On Results Action Clicked
     * @param kidEntity
     * @param role
     */
    @Override
    public void onResultsActionClicked(final KidEntity kidEntity, final GuardianRolesEnum role) {
        Timber.d("On Results for -> %s ", kidEntity.getIdentity());
        activityHandler.navigateToKidsResults(kidEntity.getIdentity());
    }

    /**
     * On Alerts Action Clicked
     * @param kidEntity
     * @param role
     */
    @Override
    public void onAlertsActionClicked(final KidEntity kidEntity, final GuardianRolesEnum role) {
        Timber.d("On Alerts for -> %s ", kidEntity.getIdentity());
        activityHandler.navigateToSonAlerts(kidEntity.getIdentity());
    }

    /**
     * On Relations Action Clicked
     * @param kidEntity
     * @param role
     */
    @Override
    public void onRelationsActionClicked(final KidEntity kidEntity, final GuardianRolesEnum role) {
        Timber.d("On Relations for -> %s ", kidEntity.getIdentity());
        activityHandler.navigateToRelations(kidEntity.getIdentity());
    }

    /**
     * On Profile Action Clicked
     * @param kidEntity
     * @param role
     */
    @Override
    public void onProfileActionClicked(final KidEntity kidEntity, final GuardianRolesEnum role) {
        Timber.d("On Profile for -> %s ", kidEntity.getIdentity());
        activityHandler.navigateToMyKidsProfile(kidEntity.getIdentity());

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
