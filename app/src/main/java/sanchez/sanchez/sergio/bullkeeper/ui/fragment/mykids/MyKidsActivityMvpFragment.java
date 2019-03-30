package sanchez.sanchez.sergio.bullkeeper.ui.fragment.mykids;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Locale;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.FocusShape;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpSearchLCEFragment;
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
public class MyKidsActivityMvpFragment extends SupportMvpSearchLCEFragment<MyKidsFragmentPresenter,
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


    /**
     * Views
     * ============
     */

    /**
     * See Invitations Container
     */
    @BindView(R.id.seeInvitationsContainer)
    protected FrameLayout seeInvitationsContainer;

    /**
     * See Invitations Image View
     */
    @BindView(R.id.seeInvitations)
    protected ImageView seeInvitationsImageView;

    /**
     * Invitations Count Text View
     */
    @BindView(R.id.invitationsCount)
    protected TextView invitationsCountTextView;


    public MyKidsActivityMvpFragment() {}

    /**
     * New Instance
     * @return
     */
    public static MyKidsActivityMvpFragment newInstance() {
        return  new MyKidsActivityMvpFragment();
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
        if(!supervisedChildrenEntity.getGuardianRolesEnum().equals(GuardianRolesEnum.DATA_VIEWER))
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
        if(!role.equals(GuardianRolesEnum.DATA_VIEWER))
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
        activityHandler.navigateToKidAlerts(kidEntity.getIdentity());
    }

    /**
     * On Chats Action Clicked
     * @param kidEntity
     * @param role
     */
    @Override
    public void onChatsActionClicked(final KidEntity kidEntity, final GuardianRolesEnum role) {
        Timber.d("On Chats for -> %s ", kidEntity.getIdentity());
        activityHandler.navigateToConversationMessagesList(kidEntity.getIdentity());
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

    /**
     * On See Invitations
     */
    @OnClick(R.id.seeInvitations)
    protected void onSeeInvitations(){
        activityHandler.navigateToInvitations();
    }

    /**
     * On Pending Request Avaliable
     */
    @Override
    public void onPendingRequestsAvailable(final long count) {

        seeInvitationsContainer.setVisibility(View.VISIBLE);
        invitationsCountTextView.setText(String.valueOf(count));

        // See Invitations Show Case
        new FancyShowCaseView.Builder(activity)
                .focusOn(seeInvitationsContainer)
                .title(String.format(Locale.getDefault(),
                        getString(R.string.invitations_pending_confirmed_show_case), count))
                .focusShape(FocusShape.CIRCLE)
                .enableAutoTextPosition()
                .focusBorderColor(R.color.commonWhite)
                .backgroundColor(R.color.cyanBrilliant)
                .build()
                .show();
    }

    /**
     * On No pending Request Avaliable
     */
    @Override
    public void onNoPendingRequestsAvailable() {
        seeInvitationsContainer.setVisibility(View.GONE);
        invitationsCountTextView.setText("-");
    }

    /**
     * Get Search Hint Res
     * @return
     */
    @Override
    protected int getSearchHintRes() {
        return R.string.search_kids_hint;
    }
}
