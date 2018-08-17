package sanchez.sanchez.sergio.masom_app.ui.fragment.relations;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import sanchez.sanchez.sergio.domain.models.SocialMediaFriendEntity;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.masom_app.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.masom_app.ui.adapter.impl.SocialMediaFriendAdapter;
import sanchez.sanchez.sergio.masom_app.ui.support.IBasicActivityHandler;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportMvpFragment;

/**
 * Kid Relations Fragment
 */
public class KidRelationsMvpFragment extends SupportMvpFragment<KidRelationFragmentPresenter,
        IKidRelationsFragmentView, IBasicActivityHandler, MyKidsComponent>
        implements IKidRelationsFragmentView, SupportRecyclerViewAdapter.OnSupportRecyclerViewListener<SocialMediaFriendEntity> {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    @Inject
    protected Context appContext;


    /**
     * Kid Identity
     */
    private String kidIdentity;

    /**
     * Kid Relations List
     */
    @BindView(R.id.kidRelationsList)
    protected RecyclerView kidRelationsList;

    /**
     * Social Media Friend Adapter
     */
    private SocialMediaFriendAdapter socialMediaFriendAdapter;

    public KidRelationsMvpFragment() {
        // Required empty public constructor
    }


    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static KidRelationsMvpFragment newInstance(final String kidIdentity) {
        KidRelationsMvpFragment fragment = new KidRelationsMvpFragment();
        Bundle args = new Bundle();
        args.putString(KID_IDENTITY_ARG, kidIdentity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_kid_relations;
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
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public KidRelationFragmentPresenter providePresenter() {
        return component.kidRelationFragmentPresenter();
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewCompat.setNestedScrollingEnabled(kidRelationsList, false);
        kidRelationsList.setLayoutManager(new LinearLayoutManager(appContext));
        socialMediaFriendAdapter = new SocialMediaFriendAdapter(appContext, new ArrayList<SocialMediaFriendEntity>());
        socialMediaFriendAdapter.setOnSupportRecyclerViewListener(this);
        // Set Animator
        kidRelationsList.setItemAnimator(new DefaultItemAnimator());
        kidRelationsList.setAdapter(socialMediaFriendAdapter);

    }

    /**
     * On Kid Relations Loaded
     * @param socialMediaFriendEntityList
     */
    @Override
    public void onKidRelationsLoaded(List<SocialMediaFriendEntity> socialMediaFriendEntityList) {
        socialMediaFriendAdapter.setData(new ArrayList<>(socialMediaFriendEntityList));
        socialMediaFriendAdapter.notifyDataSetChanged();
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {

    }

    /**
     * Social Media Friend Entity
     * @param socialMediaFriendEntity
     */
    @Override
    public void onItemClick(SocialMediaFriendEntity socialMediaFriendEntity) {
        showShortMessage("Social Media Friend Clicked!!!");
    }

    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() {

    }
}
