package sanchez.sanchez.sergio.masom_app.ui.fragment.home;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.HomeComponent;
import sanchez.sanchez.sergio.masom_app.ui.activity.home.IHomeActivityHandler;
import sanchez.sanchez.sergio.masom_app.ui.images.CircleTransform;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportFragment;

/**
 * Home Fragment
 */
public class HomeFragment extends SupportFragment<HomeFragmentPresenter,
        IHomeView, IHomeActivityHandler>  implements IHomeView{

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


        Picasso.with(appContext).load("https://avatars3.githubusercontent.com/u/6996211?s=460&v=4")
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
}
