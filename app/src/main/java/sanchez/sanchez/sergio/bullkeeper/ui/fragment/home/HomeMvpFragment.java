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
import android.widget.ImageButton;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.HomeComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.home.IHomeActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.LastAlertsAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportItemTouchHelper;
import sanchez.sanchez.sergio.bullkeeper.ui.images.CircleTransform;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportMvpFragment;

import static sanchez.sanchez.sergio.bullkeeper.ui.support.SupportToolbarApp.TOOLBAR_WITH_MENU;

/**
 * Home Fragment
 */
public class HomeMvpFragment extends SupportMvpFragment<HomeFragmentPresenter,
        IHomeView, IHomeActivityHandler, HomeComponent> implements IHomeView,
        SupportRecyclerViewAdapter.OnSupportRecyclerViewListener<AlertEntity>,
        SupportItemTouchHelper.ItemTouchHelperListener,
        SwipeRefreshLayout.OnRefreshListener {

    public static String TAG = "HOME_FRAGMENT";


    @Inject
    protected Context appContext;

    @BindView(R.id.userProfileImage)
    protected ImageView userProfileImage;

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
     * Alerts List
     */
    @BindView(R.id.alertsList)
    protected RecyclerView alertsList;

    /**
     * First Child Image
     */
    @BindView(R.id.firstChildImage)
    protected ImageView firstChildImage;

    /**
     * Second Child Image
     */
    @BindView(R.id.secondChildImage)
    protected ImageView secondChildImage;

    /**
     * Third Child Image
     */
    @BindView(R.id.thirdChildImage)
    protected ImageView thirdChildImage;

    /**
     * Last Alerts Adapter
     */
    private LastAlertsAdapter lastAlertsAdapter;


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


        Picasso.with(appContext).load("https://avatars3.githubusercontent.com/u/831538?s=460&v=4")
                .placeholder(R.drawable.user_default)
                .error(R.drawable.user_default)
                .transform(new CircleTransform())
                .into(userProfileImage);


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


        Picasso.with(appContext).load("https://avatars3.githubusercontent.com/u/831538?s=460&v=4")
                .placeholder(R.drawable.user_default)
                .error(R.drawable.user_default)
                .transform(new CircleTransform(
                        ContextCompat.getColor(appContext, R.color.greenSuccess)
                ))
                .into(firstChildImage);


        Picasso.with(appContext).load("https://avatars3.githubusercontent.com/u/831538?s=460&v=4")
                .placeholder(R.drawable.user_default)
                .error(R.drawable.user_default)
                .transform(new CircleTransform(
                        ContextCompat.getColor(appContext, R.color.redDanger)
                ))
                .into(secondChildImage);

        Picasso.with(appContext).load("https://avatars3.githubusercontent.com/u/831538?s=460&v=4")
                .placeholder(R.drawable.user_default)
                .error(R.drawable.user_default)
                .transform(new CircleTransform(
                        ContextCompat.getColor(appContext, R.color.yellowWarning)
                ))
                .into(thirdChildImage);


        swipeRefreshLayout.setColorSchemeResources(R.color.commonWhite);
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.cyanBrilliant);

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
        showShortMessage("Add Child ...");
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
     * On Last Alerts Loaded
     * @param lastAlerts
     */
    @Override
    public void onLastAlertsLoaded(List<AlertEntity> lastAlerts) {
        lastAlertsAdapter.setData(new ArrayList<>(lastAlerts));
        lastAlertsAdapter.notifyDataSetChanged();
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
}
