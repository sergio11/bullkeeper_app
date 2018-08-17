package sanchez.sanchez.sergio.masom_app.ui.fragment.mykids;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.components.MyKidsComponent;
import sanchez.sanchez.sergio.masom_app.ui.activity.mykids.IMyKidsActivityHandler;
import sanchez.sanchez.sergio.masom_app.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.masom_app.ui.adapter.decoration.ItemOffsetDecoration;
import sanchez.sanchez.sergio.masom_app.ui.adapter.impl.MyKidsAdapter;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportMvpFragment;

import static sanchez.sanchez.sergio.masom_app.ui.support.SupportToolbarApp.TOOLBAR_WITH_MENU;

/**
 * My Kids Activity Fragment
 */
public class MyKidsActivityMvpFragment extends SupportMvpFragment<MyKidsFragmentPresenter,
        IMyKidsView, IMyKidsActivityHandler, MyKidsComponent> implements IMyKidsView,
        SupportRecyclerViewAdapter.OnSupportRecyclerViewListener<SonEntity>,
        MyKidsAdapter.OnMyKidsListener {

    public static String TAG = "MY_KIDS_ACTIVITY_FRAGMENT";

    @Inject
    protected Context appContext;

    /**
     * My Kids Adapter
     */
    private MyKidsAdapter myKidsAdapter;

    /**
     * My Kids List
     */
    @BindView(R.id.myKidsList)
    protected RecyclerView myKidsList;

    public MyKidsActivityMvpFragment() {}

    /**
     * New Instance
     * @return
     */
    public static MyKidsActivityMvpFragment newInstance() {
        MyKidsActivityMvpFragment fragment = new MyKidsActivityMvpFragment();
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

        myKidsList.setLayoutManager(new LinearLayoutManager(appContext));
        myKidsList.setNestedScrollingEnabled(false);

        myKidsAdapter = new MyKidsAdapter(appContext, new ArrayList<SonEntity>());
        myKidsAdapter.setOnSupportRecyclerViewListener(this);
        myKidsAdapter.setOnMyKidsListenerListener(this);

        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(appContext, R.dimen.item_offset);
        myKidsList.addItemDecoration(itemOffsetDecoration);
        // Set Animator
        myKidsList.setItemAnimator(new DefaultItemAnimator());
        myKidsList.setAdapter(myKidsAdapter);

    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public MyKidsFragmentPresenter providePresenter() {
        return component.myKidsFragmentPresenter();
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() { }

    /**
     * ON Item Click
     * @param sonEntity
     */
    @Override
    public void onItemClick(SonEntity sonEntity) {
        activityHandler.navigateToMyKidDetail(sonEntity.getIdentity());
    }

    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() { }

    /**
     * On My Kids Loaded
     * @param myKids
     */
    @Override
    public void onMyKidsLoaded(List<SonEntity> myKids) {
        myKidsAdapter.setData(new ArrayList<>(myKids));
        myKidsAdapter.notifyDataSetChanged();
    }

    /**
     * Get Layout Res
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_my_kids;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(MyKidsComponent component) {
        component.inject(this);
    }


    /**
     * On Detail Action CLicked
     * @param sonEntity
     */
    @Override
    public void onDetailActionClicked(SonEntity sonEntity) {
        activityHandler.navigateToMyKidDetail(sonEntity.getIdentity());
    }

    /**
     * On Results Action Clicked
     * @param sonEntity
     */
    @Override
    public void onResultsActionClicked(final SonEntity sonEntity) {
        showLongMessage("On Results Action");
    }

    /**
     * On Alerts Action Clicked
     * @param sonEntity
     */
    @Override
    public void onAlertsActionClicked(final SonEntity sonEntity) {
        showLongMessage("On Alerts Action");
    }

    /**
     * On Relations Action Clicked
     * @param sonEntity
     */
    @Override
    public void onRelationsActionClicked(final SonEntity sonEntity) {
        activityHandler.navigateToComments(sonEntity.getIdentity());
    }

    /**
     * On Profile Action Clicked
     * @param sonEntity
     */
    @Override
    public void onProfileActionClicked(final SonEntity sonEntity) {
        activityHandler.navigateToMyKidsProfile(sonEntity.getIdentity());

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
