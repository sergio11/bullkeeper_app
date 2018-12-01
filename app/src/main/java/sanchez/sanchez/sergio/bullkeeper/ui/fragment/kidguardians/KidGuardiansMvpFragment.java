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
import java.util.List;

import javax.inject.Inject;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile.IMyKidsProfileActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.KidGuardiansAdapter;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.models.GuardianRolesEnum;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import timber.log.Timber;

/**
 * Kid Guardians Fragment
 */
public class KidGuardiansMvpFragment extends SupportMvpLCEFragment<KidGuardiansFragmentPresenter,
        IKidGuardiansFragmentView, IMyKidsProfileActivityHandler, MyKidsComponent, KidGuardianEntity>
        implements IKidGuardiansFragmentView {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    /**
     * Dependencies
     * ==================
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
     * Preferences Repository
     */
    @Inject
    protected IPreferenceRepository preferenceRepository;


    /**
     * State
     * ================
     */

    /**
     * Kid Identity
     */
    @State
    protected String kidIdentity;

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
        return new KidGuardiansMvpFragment();
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
        final String activeUserIdentity = preferenceRepository.getPrefCurrentUserIdentity();
        final KidGuardiansAdapter kidGuardiansAdapter = new KidGuardiansAdapter(activity, new ArrayList<KidGuardianEntity>(),
                picasso, activeUserIdentity);
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
    public List<KidGuardianEntity> getKidGuardianEntities() {
        return recyclerViewAdapter.getData();
    }


    /**
     * On Add Guardians
     */
    @OnClick(R.id.addGuardians)
    protected void onAddGuardians(){
        activityHandler.navigateToSearchGuardians();
    }

    /**
     * On Data Loaded
     * @param dataLoaded
     */
    @Override
    public void onDataLoaded(List<KidGuardianEntity> dataLoaded) {
        final List<KidGuardianEntity> currentKidGuardians = recyclerViewAdapter.getData();
        for(final KidGuardianEntity kidGuardianEntity: dataLoaded) {
            if(!currentKidGuardians.contains(kidGuardianEntity))
                currentKidGuardians.add(kidGuardianEntity);
        }
        super.onDataLoaded(currentKidGuardians);
    }

    /**
     * On Guardian Selected
     * @param guardianEntity
     */
    public void onGuardianSelected(final GuardianEntity guardianEntity) {
        Preconditions.checkNotNull(guardianEntity, "Guardian Entity can not be null");
        Preconditions.checkNotNull(guardianEntity.getIdentity(), "Identity can not be null");
        Preconditions.checkState(!guardianEntity.getIdentity().isEmpty(), "Identity can not be null");


        boolean alreadyAdded = false;

        final List<KidGuardianEntity> kidGuardianEntities = recyclerViewAdapter.getData();

        for(final KidGuardianEntity kidGuardianEntity: kidGuardianEntities) {
            if (kidGuardianEntity.getGuardian().getIdentity().equals(guardianEntity.getIdentity())) {
                alreadyAdded = true;
                break;
            }
        }

        if (!alreadyAdded) {
            final KidGuardianEntity kidGuardianEntity = new KidGuardianEntity();
            kidGuardianEntity.setRole(GuardianRolesEnum.DATA_VIEWER);
            kidGuardianEntity.setGuardian(guardianEntity);
            kidGuardianEntity.setKid(kidGuardianEntities.get(0).getKid());
            kidGuardianEntities.add(kidGuardianEntity);
            recyclerViewAdapter.notifyDataSetChanged();
        } else {
            showNoticeDialog(R.string.search_guardian_already_selected);
        }

    }
}

