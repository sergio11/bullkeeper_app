package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.timeallowance;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import javax.inject.Inject;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.IMyKidsDetailActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.TimeAllowancePerDayAdapter;
import sanchez.sanchez.sergio.domain.models.ScreenTimeAllowanceEntity;

/**
 * Time ALlowance Fragment
 */
public class TimeAllowanceMvpFragment extends SupportMvpLCEFragment<TimeAllowanceFragmentPresenter,
        ITimeAllowanceFragmentView, IMyKidsDetailActivityHandler, MyKidsComponent, ScreenTimeAllowanceEntity.TimeAllowancePerDayEntity>
        implements ITimeAllowanceFragmentView {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Dependencies
     * =================
     */

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
     * State
     * ===================
     */

    /**
     * Kid Identity
     */
    @State
    protected String kidIdentity;


    /**
     *
     */
    public TimeAllowanceMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static TimeAllowanceMvpFragment newInstance(final String kidIdentity) {
        TimeAllowanceMvpFragment fragment = new TimeAllowanceMvpFragment();
        Bundle args = new Bundle();
        args.putString(KID_IDENTITY_ARG, kidIdentity);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() == null ||
                !getArguments().containsKey(KID_IDENTITY_ARG))
            throw new IllegalStateException("You must provide son identity - Illegal State");

        kidIdentity = getArguments().getString(KID_IDENTITY_ARG);

        // Enable Nested Scrolling on Recycler View
        ViewCompat.setNestedScrollingEnabled(recyclerView, true);
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<ScreenTimeAllowanceEntity.TimeAllowancePerDayEntity> getAdapter() {
        final TimeAllowancePerDayAdapter allowancePerDayAdapter =
                new TimeAllowancePerDayAdapter(activity, new ArrayList<ScreenTimeAllowanceEntity.TimeAllowancePerDayEntity>());
        allowancePerDayAdapter.setOnSupportRecyclerViewListener(this);
        return allowancePerDayAdapter;
    }


    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        args.putString(TimeAllowanceFragmentPresenter.SON_IDENTITY_ARG, kidIdentity);
        return args;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_time_allowance;
    }

    /**
     * Initialize Injector
     * @param component
     */
    @Override
    protected void initializeInjector(MyKidsComponent component) {
        component.inject(this);
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {}

    /**
     * On Item Click
     * @param item
     */
    @Override
    public void onItemClick(final ScreenTimeAllowanceEntity.TimeAllowancePerDayEntity item) {
        Preconditions.checkNotNull(item, "Item can not be null");
    }

    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() {}

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public TimeAllowanceFragmentPresenter providePresenter() {
        return component.timeAllowanceFragmentPresenter();
    }

    /**
     * Set Remaining Time
     * @param remainingTime
     */
    @Override
    public void setRemainingTime(int remainingTime) {

    }
}

