package sanchez.sanchez.sergio.bullkeeper.ui.fragment.profile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import me.toptas.fancyshowcase.FancyShowCaseQueue;
import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.FocusShape;
import me.toptas.fancyshowcase.listener.OnCompleteListener;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.MyKidsStatusAdapter;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.HomeComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.home.IHomeActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import sanchez.sanchez.sergio.domain.models.ChildrenOfSelfGuardianEntity;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.models.GuardianRolesEnum;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import timber.log.Timber;


/**
 * Home Fragment
 */
public class ProfileMvpFragment extends SupportMvpFragment<ProfileFragmentPresenter,
        IProfileView, IHomeActivityHandler, HomeComponent> implements IProfileView,
        MyKidsStatusAdapter.OnMyKidsListener, OnCompleteListener {

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
     * Activity
     */
    @Inject
    protected Activity activity;

    /**
     * Preference Repository
     */
    @Inject
    protected IPreferenceRepository preferenceRepository;

    /**
     * User Menu View
     */
    @BindView(R.id.userMenu)
    protected View userMenuView;

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
        return new ProfileMvpFragment();
    }

    /**
     * Toggle All Components
     * @param isEnable
     */
    private void toggleUserProfileAllComponents(final boolean isEnable) {
        userProfileImage.setEnabled(isEnable);
        userProfileText.setEnabled(isEnable);
        resultsAction.setEnabled(isEnable);
        alertsAction.setEnabled(isEnable);
        childrenAction.setEnabled(isEnable);
    }

    /**
     * Toggle Kids All Components
     * @param isEnable
     */
    private void toggleKidsAllComponents(final boolean isEnable){
        addChildBtn.setEnabled(isEnable);
        myChildList.setEnabled(isEnable);
        infoChildBtn.setEnabled(isEnable);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toggleUserProfileAllComponents(false);
        toggleKidsAllComponents(false);

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
        myKidsStatusAdapter = new MyKidsStatusAdapter(activity, new ArrayList<SupervisedChildrenEntity>(), picasso);
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
     * On Results Action
     */
    @OnClick(R.id.resultsAction)
    protected void onResultsAction(){
        activityHandler.goToSummaryMyKidsResults();
    }

    /**
     * On User Profile Loaded
     * @param guardianEntity
     */
    @Override
    public void onUserProfileLoaded(GuardianEntity guardianEntity) {
        Preconditions.checkNotNull(guardianEntity, "Parent Entity can not be null");

        preferencesRepositoryImpl.setPrefCurrentUserIdentity(guardianEntity.getIdentity());

        userProfileText.setText(guardianEntity.getFullName());

        if(appUtils.isValidString(guardianEntity.getProfileImage()))
            picasso.load(guardianEntity.getProfileImage()).placeholder(R.drawable.parent_default)
                .error(R.drawable.parent_default)
                .into(userProfileImage);
        else
            userProfileImage.setImageResource(R.drawable.parent_default);

        toggleUserProfileAllComponents(true);
    }

    /**
     * On Children Loaded
     * @param children
     */
    @Override
    public void onChildrenLoaded(final ChildrenOfSelfGuardianEntity children) {
        Preconditions.checkNotNull(children, "Children can not be null");
        Preconditions.checkState(children.getConfirmed() > 0,
                "Children can not be empty");

        if(children.getConfirmed() >= MIN_KIDS_COUNT) {
            addChildBtn.setVisibility(View.VISIBLE);
            infoChildBtn.setVisibility(View.GONE);
        } else {
            addChildBtn.setVisibility(View.GONE);
            infoChildBtn.setVisibility(View.VISIBLE);
        }

        myKidsStatusAdapter.setData(children.getSupervisedChildrenEntities());
        myKidsStatusAdapter.notifyDataSetChanged();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(appContext, R.anim.layout_animation_fall_down);
        myChildList.setLayoutAnimation(controller);
        myChildList.scheduleLayoutAnimation();

        toggleKidsAllComponents(true);

        if (!preferenceRepository.isHomeShowCaseCompleted())
            launchShowCase();
        else
            activityHandler.onShowcaseCompleted();

    }

    /**
     * On No Children Founded
     */
    @Override
    public void onNoChildrenFounded() {
        addChildBtn.setVisibility(View.GONE);
        infoChildBtn.setVisibility(View.VISIBLE);
        toggleKidsAllComponents(true);
        if (!preferenceRepository.isHomeShowCaseCompleted())
            launchShowCase();
        else
            activityHandler.onShowcaseCompleted();
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
     * @param kidEntity
     */
    @Override
    public void onDetailActionClicked(final KidEntity kidEntity, final GuardianRolesEnum role) {
        if(role.equals(GuardianRolesEnum.ADMIN) ||
                role.equals(GuardianRolesEnum.PARENTAL_CONTROL_RULE_EDITOR))
            activityHandler.goToChildDetail(kidEntity.getIdentity());
        else
            activityHandler.gotToKidAlerts(kidEntity.getIdentity());
    }

    /**
     * On Default Item Clicked
     */
    @Override
    public void onDefaultItemClicked() {
        activityHandler.goToAddChild();
    }

    /**
     * On Show Alerts Detail
     * @param alertLevelEnum
     * @param alertValue
     * @param childId
     */
    @Override
    public void onShowAlertsDetail(final AlertLevelEnum alertLevelEnum, final String alertValue,
                                   final String childId) {
        Preconditions.checkNotNull(alertLevelEnum, "Alert Level Enum can not be null");
        Preconditions.checkNotNull(alertValue, "Alert Value can not be null");
        Preconditions.checkState(!alertValue.isEmpty(), "Alert Value can not be empty");
        Preconditions.checkNotNull(childId, "Child id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child Id can not be empty");

        activityHandler.showChildAlertsDetailDialog(alertLevelEnum, alertValue, childId);

    }


    /**
     * Launch Show Case
     */
    private void launchShowCase(){

        Timber.d("Launch Show Case");

        // User Menu Show Case
        final FancyShowCaseView userMenuShowCase = new FancyShowCaseView.Builder(activity)
                .focusOn(userMenuView)
                .title(getString(R.string.user_menu_showcase_description))
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .roundRectRadius(90)
                .enableAutoTextPosition()
                .focusBorderColor(R.color.commonWhite)
                .backgroundColor(R.color.cyanBrilliant)
                .build();


        // My Children Show Case
        final FancyShowCaseView myChildrenShowCase = new FancyShowCaseView.Builder(activity)
                .focusOn(myChildList)
                .title(getString(R.string.my_childs_showcase_description))
                .focusShape(FocusShape.ROUNDED_RECTANGLE)
                .roundRectRadius(90)
                .enableAutoTextPosition()
                .focusBorderColor(R.color.commonWhite)
                .backgroundColor(R.color.cyanBrilliant)
                .build();

        final FancyShowCaseQueue queue =  new FancyShowCaseQueue()
                .add(userMenuShowCase)
                .add(myChildrenShowCase);

        queue.setCompleteListener(this);

        queue.show();

    }


    @Override
    public void onComplete() {
        preferenceRepository.setHomeShowcaseCompleted(true);
        activityHandler.onShowcaseCompleted();
    }

    /**
     * Load Profile Information
     */
    public void loadProfileInformation(){
        getPresenter().loadProfileInformation();
    }
}
