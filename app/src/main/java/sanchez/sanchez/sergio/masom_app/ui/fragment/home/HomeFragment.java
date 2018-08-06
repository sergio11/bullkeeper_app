package sanchez.sanchez.sergio.masom_app.ui.fragment.home;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
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
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.HomeComponent;
import sanchez.sanchez.sergio.masom_app.ui.activity.home.IHomeActivityHandler;
import sanchez.sanchez.sergio.masom_app.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.masom_app.ui.adapter.impl.LastAlertsAdapter;
import sanchez.sanchez.sergio.masom_app.ui.images.CircleTransform;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportFragment;

/**
 * Home Fragment
 */
public class HomeFragment extends SupportFragment<HomeFragmentPresenter,
        IHomeView, IHomeActivityHandler>  implements IHomeView, SupportRecyclerViewAdapter.OnItemClickListener<AlertEntity> {

    public static String TAG = "HOME_FRAGMENT";

    private HomeComponent homeComponent;

    @Inject
    protected Context appContext;

    @BindView(R.id.userProfileImage)
    protected ImageView userProfileImage;

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


    @BindView(R.id.firstChildImage)
    protected ImageView firstChildImage;

    @BindView(R.id.secondChildImage)
    protected ImageView secondChildImage;

    @BindView(R.id.thirdChildImage)
    protected ImageView thirdChildImage;

    /**
     * Last Alerts Adapter
     */
    private LastAlertsAdapter lastAlertsAdapter;


    public HomeFragment() { }

    /**
     * New Instance
     * @return
     */
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        homeComponent = HomeComponent.class
                .cast(((HasComponent<HomeComponent>) getActivity())
                        .getComponent());

        homeComponent.inject(this);
    }

    /**
     * On Create View
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_home, container, false);
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


        alertsList.setLayoutManager(new LinearLayoutManager(appContext));
        alertsList.setNestedScrollingEnabled(false);
        lastAlertsAdapter = new LastAlertsAdapter(appContext, new ArrayList<AlertEntity>());
        lastAlertsAdapter.setOnItemClickListener(this);
        alertsList.setAdapter(lastAlertsAdapter);

    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public HomeFragmentPresenter providePresenter() {
        return homeComponent.homeFragmentPresenter();
    }

    /**
     * On Add Child
     */
    @OnClick(R.id.addChildBtn)
    protected void onAddChild(){
        showShortMessage("Add Child ...");
    }


    /**
     * On Item Click
     * @param alertEntity
     */
    @Override
    public void onItemClick(final AlertEntity alertEntity) {
        showShortMessage(String.format(Locale.getDefault(), "Alert %s clicked ", alertEntity.getTitle()));
    }

    /**
     * On Last Alerts Loaded
     * @param lastAlerts
     */
    @Override
    public void onLastAlertsLoaded(List<AlertEntity> lastAlerts) {
        lastAlertsAdapter.setData(lastAlerts);
        lastAlertsAdapter.notifyDataSetChanged();
    }
}
