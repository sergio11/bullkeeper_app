package sanchez.sanchez.sergio.bullkeeper.ui.fragment.home;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.MyKidsStatusAdapter;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.HomeComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.home.IHomeActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.LastAlertsAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportItemTouchHelper;
import sanchez.sanchez.sergio.bullkeeper.ui.images.CircleTransform;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportMvpFragment;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import timber.log.Timber;

import static sanchez.sanchez.sergio.bullkeeper.ui.support.SupportToolbarApp.TOOLBAR_WITH_MENU;

/**
 * Home Fragment
 */
public class HomeMvpFragment extends SupportMvpFragment<HomeFragmentPresenter,
        IHomeView, IHomeActivityHandler, HomeComponent> implements IHomeView,
        SupportRecyclerViewAdapter.OnSupportRecyclerViewListener<AlertEntity>,
        SupportItemTouchHelper.ItemTouchHelperListener,
        SwipeRefreshLayout.OnRefreshListener, MyKidsStatusAdapter.OnMyKidsListener {

    public static String TAG = "HOME_FRAGMENT";
    private final static Integer MIN_KIDS_COUNT = 3;


    @Inject
    protected Context appContext;

    /**
     * Picasso
     */
    @Inject
    protected Picasso picasso;

    /**
     * User Profile Image
     */
    @BindView(R.id.userProfileImage)
    protected ImageView userProfileImage;

    /**
     * User Profile Text
     */
    @BindView(R.id.userProfileText)
    protected TextView userProfileText;

    /**
     * Main Container
     */
    @BindView(R.id.mainContainer)
    protected ViewGroup mainContainer;

    /**
     * Swipe Refresh Layout
     */
    @BindView(R.id.swipeContainer)
    protected SwipeRefreshLayout swipeRefreshLayout;


    /**
     * Results Action
     */
    @BindView(R.id.resultsAction)
    protected ImageButton resultsAction;


    /**
     * Alerts Action
     */
    @BindView(R.id.alertsAction)
    protected ImageButton alertsAction;

    /**
     * Children Action
     */
    @BindView(R.id.childrenAction)
    protected ImageButton childrenAction;

    /**
     * Add Children Btn
     */
    @BindView(R.id.addChildBtn)
    protected ImageButton addChildBtn;

    /**
     * My Children List
     */
    @BindView(R.id.myKidsList)
    protected RecyclerView myChildList;

    /**
     * Alerts List
     */
    @BindView(R.id.alertsList)
    protected RecyclerView alertsList;

    /**
     * Info Child Btn
     */
    @BindView(R.id.infoChildBtn)
    protected ImageButton infoChildBtn;


    /**
     * Last Alerts Adapter
     */
    private LastAlertsAdapter lastAlertsAdapter;

    /**
     * My Kids Home Adapter
     */
    private MyKidsStatusAdapter myKidsStatusAdapter;


    public HomeMvpFragment() { }

    /**
     * New Instance
     * @return
     */
    public static HomeMvpFragment newInstance() {
        HomeMvpFragment fragment = new HomeMvpFragment();
        return fragment;
    }




    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        resultsAction.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        resultsAction.setImageResource(R.drawable.chart_bar_white);
                        break;
                    default:
                        resultsAction.setImageResource(R.drawable.chart_bar_cyan);
                }
                return false;
            }
        });

        alertsAction.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        alertsAction.setImageResource(R.drawable.alert_white);
                        break;
                    default:
                        alertsAction.setImageResource(R.drawable.alert_cyan);
                }
                return false;
            }
        });

        childrenAction.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        childrenAction.setImageResource(R.drawable.child_white);
                        break;
                    default:
                        childrenAction.setImageResource(R.drawable.child_cyan);
                }
                return false;
            }
        });


        swipeRefreshLayout.setColorSchemeResources(R.color.commonWhite);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.cyanBrilliant);


        /**
         * My Kids Status Adapter
         */

        myChildList.setLayoutManager(new LinearLayoutManager(appContext,
                LinearLayoutManager.HORIZONTAL, false));
        myKidsStatusAdapter = new MyKidsStatusAdapter(appContext, new ArrayList<SonEntity>());
        myKidsStatusAdapter.setOnMyKidsListenerListener(this);

        myChildList.setAdapter(myKidsStatusAdapter);

        /**
         * Last Alerts Adapter
         */
        ViewCompat.setNestedScrollingEnabled(alertsList, false);
        alertsList.setLayoutManager(new LinearLayoutManager(appContext));
        alertsList.setNestedScrollingEnabled(false);
        lastAlertsAdapter = new LastAlertsAdapter(appContext, new ArrayList<AlertEntity>());
        lastAlertsAdapter.setOnSupportRecyclerViewListener(this);
        // Set Animator
        alertsList.setItemAnimator(new DefaultItemAnimator());
        alertsList.setAdapter(lastAlertsAdapter);

        // adding item touch helper
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new SupportItemTouchHelper<LastAlertsAdapter.LastAlertsViewHolder>(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(alertsList);

        getPresenter().loadProfileInformation();

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    /**
     * Initialize Injector
     * @param component
     */
    @Override
    protected void initializeInjector(HomeComponent component) {
        component.inject(this);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public HomeFragmentPresenter providePresenter() {
        return component.homeFragmentPresenter();
    }

    /**
     * On Add Child
     */
    @OnClick(R.id.addChildBtn)
    protected void onAddChild(){
        activityHandler.goToAddChild();
    }

    /**
     * On Info Child
     */
    @OnClick(R.id.infoChildBtn)
    protected void onInfoChildBtn(){
        activityHandler.showHowAddChildHelpDialog();
    }


    /**
     * On Children Action
     */
    @OnClick(R.id.childrenAction)
    protected void onChildrenAction(){
        activityHandler.goToMyKids();
    }

    /**
     * On Alerts Action
     */
    @OnClick(R.id.alertsAction)
    protected void onAlertsAction(){
        activityHandler.goToAlerts();
    }


    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {
        showShortMessage("Header Clicked ...");
        activityHandler.goToAlerts();

    }

    /**
     * On Item Click
     * @param alertEntity
     */
    @Override
    public void onItemClick(final AlertEntity alertEntity) {
        showShortMessage(String.format(Locale.getDefault(), "Alert %s clicked ", alertEntity.getTitle()));
        activityHandler.goToAlertDetail("123456");
    }



    @Override
    public void onFooterClick() { }

    /**
     * On User Profile Loaded
     * @param parentEntity
     */
    @Override
    public void onUserProfileLoaded(ParentEntity parentEntity) {

        userProfileText.setText(parentEntity.getFullName());

        picasso.load(parentEntity.getProfileImage()).placeholder(R.drawable.parent_default)
                .error(R.drawable.parent_default)
                .into(userProfileImage);
    }

    /**
     * On Last Alerts Loaded
     * @param lastAlerts
     */
    @Override
    public void onLastAlertsLoaded(List<AlertEntity> lastAlerts) {
        lastAlertsAdapter.setData(new ArrayList<>(lastAlerts));
        lastAlertsAdapter.notifyDataSetChanged();
    }

    /**
     * On Children Loaded
     * @param children
     */
    @Override
    public void onChildrenLoaded(List<SonEntity> children) {
        Preconditions.checkNotNull(children, "Children can not be null");
        Preconditions.checkState(!children.isEmpty(), "Children can not be empty");

        if(children.size() >= MIN_KIDS_COUNT) {
            addChildBtn.setVisibility(View.VISIBLE);
            infoChildBtn.setVisibility(View.GONE);
        } else {
            addChildBtn.setVisibility(View.GONE);
            infoChildBtn.setVisibility(View.VISIBLE);
        }

        myKidsStatusAdapter.setData(children);
        myKidsStatusAdapter.notifyDataSetChanged();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(appContext, R.anim.layout_animation_fall_down);
        myChildList.setLayoutAnimation(controller);
        myChildList.scheduleLayoutAnimation();
    }

    /**
     * On No Children Founded
     */
    @Override
    public void onNoChildrenFounded() {
        addChildBtn.setVisibility(View.GONE);
        infoChildBtn.setVisibility(View.VISIBLE);
    }

    /**
     * On Swiped
     * @param viewHolder
     * @param direction
     * @param position
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof LastAlertsAdapter.LastAlertsViewHolder) {

            final Integer deletedIndex = viewHolder.getAdapterPosition();
            final AlertEntity alertEntity = lastAlertsAdapter.getItemByAdapterPosition(deletedIndex);

            // Delete item from adapter
            lastAlertsAdapter.removeItem(deletedIndex);

            showLongSimpleSnackbar(mainContainer, getString(R.string.alert_item_removed), getString(R.string.undo_list_menu_item), new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastAlertsAdapter.restoreItem(alertEntity, deletedIndex);
                }
            });

        }
    }

    /**
     * On Refresh
     */
    @Override
    public void onRefresh() {
        showShortMessage("Refresh Alerts!!");
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
     * On User Profile Image Clicked
     */
    @OnClick(R.id.userProfileImage)
    protected void onUserProfileImageClicked(){
        activityHandler.goToUserProfile();
    }

    /**
     * On Detail Action Clicked
     * @param sonEntity
     */
    @Override
    public void onDetailActionClicked(SonEntity sonEntity) {
        activityHandler.goToChildDetail(sonEntity.getIdentity());
    }

    /**
     * On Default Item Clicked
     */
    @Override
    public void onDefaultItemClicked() {
        activityHandler.goToAddChild();
    }
}
