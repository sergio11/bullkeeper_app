package sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpLCEActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerGeofenceComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerKidRequestComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.GeofenceComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportItemTouchHelper;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.KidRequestAdapter;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * Geofences List Activity
 */
public class GeofencesListMvpActivity extends SupportMvpLCEActivity<GeofencesListPresenter,
        IGeofencesListView, GeofenceEntity>
        implements HasComponent<GeofenceComponent>, IGeofencesListActivityHandler
        , IGeofencesListView,
        SupportItemTouchHelper.ItemTouchHelperListener {

    /**
     *
     */
    private final String CONTENT_FULL_NAME = "GEOFENCES_LIST";
    private final String CONTENT_TYPE_NAME = "GEOFENCES";

    /**
     * Args
     */
    private final static String KID_ID_ARG = "KID_ID_ARG";

    /**
     * Geofence Component
     */
    private GeofenceComponent geofenceComponent;

    /**
     * State
     * ================
     *
     */

    /**
     * Kid
     */
    @State
    protected String kid;

    /**
     * Views
     * =============
     */


    /**
     * Get Calling Intent
     * @param context
     * @param kid
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String kid) {
        final Intent intent = new Intent(context, GeofencesListMvpActivity.class);
        intent.putExtra(KID_ID_ARG, kid);
        return intent;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        this.geofenceComponent = DaggerGeofenceComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.geofenceComponent.inject(this);
    }


    /**
     * On Create Content View Event
     * @return
     */
    @Override
    protected ContentViewEvent onCreateContentViewEvent() {
        return new ContentViewEvent().putContentName(CONTENT_FULL_NAME)
                .putContentType(CONTENT_TYPE_NAME);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public GeofencesListPresenter providePresenter() {
        return geofenceComponent.geofencesListPresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public GeofenceComponent getComponent() {
        return geofenceComponent;
    }


    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        super.getArgs();
        final Bundle args = new Bundle();
        args.putString(GeofencesListPresenter.KID_ID_ARG, kid);
        return args;
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() {}

    @Override
    public void onItemClick(GeofenceEntity item) {

    }


    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() {}

    /**
     * On Swiped
     * @param viewHolder
     * @param direction
     * @param position
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof KidRequestAdapter.KidRequestViewHolder) {


        }
    }

    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return RETURN_TOOLBAR;
    }

    /**
     * Get Layout Res
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_kid_request_list;
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        if (getIntent().getExtras() != null) {

            final Bundle extras = getIntent().getExtras();

            if (!extras.containsKey(KID_ID_ARG))
                throw new IllegalArgumentException("You must provide kid id");

            kid = extras.getString(KID_ID_ARG);

        } else {

            throw new IllegalArgumentException("You must provide args");
        }

        // adding item touch helper
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
                new SupportItemTouchHelper<KidRequestAdapter.KidRequestViewHolder>(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<GeofenceEntity> getAdapter() {
        return null;
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.background_cyan_4;
    }

}
