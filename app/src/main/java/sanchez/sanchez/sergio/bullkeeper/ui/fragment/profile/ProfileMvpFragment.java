package sanchez.sanchez.sergio.bullkeeper.ui.fragment.profile;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.MyKidsStatusAdapter;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.HomeComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.home.IHomeActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportMvpFragment;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;


/**
 * Home Fragment
 */
public class ProfileMvpFragment extends SupportMvpFragment<ProfileFragmentPresenter,
        IProfileView, IHomeActivityHandler, HomeComponent> implements IProfileView,
        MyKidsStatusAdapter.OnMyKidsListener {

    public static String TAG = "HOME_FRAGMENT";
    private final static Integer MIN_KIDS_COUNT = 3;

    /**
     * App Context
     */
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
     * Info Child Btn
     */
    @BindView(R.id.infoChildBtn)
    protected ImageButton infoChildBtn;

    /**
     * My Kids Home Adapter
     */
    private MyKidsStatusAdapter myKidsStatusAdapter;


    public ProfileMvpFragment() { }

    /**
     * New Instance
     * @return
     */
    public static ProfileMvpFragment newInstance() {
        ProfileMvpFragment fragment = new ProfileMvpFragment();
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


        /**
         * My Kids Status Adapter
         */

        myChildList.setLayoutManager(new LinearLayoutManager(appContext,
                LinearLayoutManager.HORIZONTAL, false));
        myKidsStatusAdapter = new MyKidsStatusAdapter(appContext, new ArrayList<SonEntity>());
        myKidsStatusAdapter.setOnMyKidsListenerListener(this);

        myChildList.setAdapter(myKidsStatusAdapter);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_profile;
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
    public ProfileFragmentPresenter providePresenter() {
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
    protected void onAlertsAction() {
        activityHandler.goToAlerts();
    }

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
