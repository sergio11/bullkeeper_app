package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kidguardians;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.KidGuardiansAdapter;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;
import timber.log.Timber;

/**
 * Kid Guardians Fragment
 */
public class KidGuardiansMvpFragment extends SupportMvpLCEFragment<KidGuardiansFragmentPresenter,
        IKidGuardiansFragmentView, IMyKidsDetailActivityHandler, MyKidsComponent, KidGuardianEntity>
        implements IKidGuardiansFragmentView {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

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
     * Kid Identity
     */
    @State
    protected String kidIdentity;

    /**
     * Kid Guardians
     */
    @State
    protected ArrayList<KidGuardianEntity> kidGuardianEntities =
            new ArrayList<>();


    /**
     *
     */
    public KidGuardiansMvpFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @return
     */
    public static KidGuardiansMvpFragment newInstance() {
        KidGuardiansMvpFragment fragment = new KidGuardiansMvpFragment();
        return fragment;
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static KidGuardiansMvpFragment newInstance(final String kidIdentity) {
        KidGuardiansMvpFragment fragment = new KidGuardiansMvpFragment();
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
            throw new IllegalArgumentException("You must provide Kid identity");

        kidIdentity = getArguments().getString(KID_IDENTITY_ARG);

    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        Timber.d("Get Args");
        final Bundle args = new Bundle();
        args.putString(KidGuardiansFragmentPresenter.KID_IDENTITY_ARG, kidIdentity);
        return args;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_kid_guardians;
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
    public void onHeaderClick() {

    }

    /**
     * On Item Click
     * @param item
     */
    @Override
    public void onItemClick(KidGuardianEntity item) {
        Preconditions.checkNotNull(item, "Item can not be null");
    }

    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() {}

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<KidGuardianEntity> getAdapter() {
        final KidGuardiansAdapter kidGuardiansAdapter = new KidGuardiansAdapter(activity, new ArrayList<KidGuardianEntity>(),
                picasso);
        kidGuardiansAdapter.setOnSupportRecyclerViewListener(this);
        return kidGuardiansAdapter;
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public KidGuardiansFragmentPresenter providePresenter() {
        return component.kidGuardiansFragmentPresenter();
    }

    /**
     * Get Kid Guardian Entities
     * @return
     */
    public ArrayList<KidGuardianEntity> getKidGuardianEntities() {
        return kidGuardianEntities;
    }

    public void setKidGuardianEntities(ArrayList<KidGuardianEntity> kidGuardianEntities) {
        this.kidGuardianEntities = kidGuardianEntities;
    }
}

