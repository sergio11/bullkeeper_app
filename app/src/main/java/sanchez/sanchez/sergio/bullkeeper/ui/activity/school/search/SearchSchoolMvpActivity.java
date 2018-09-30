package sanchez.sanchez.sergio.bullkeeper.ui.activity.school.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;

import com.fernandocejas.arrow.checks.Preconditions;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerSchoolComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.SchoolComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl.SchoolAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpSearchLCEActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import timber.log.Timber;

import static sanchez.sanchez.sergio.bullkeeper.ui.activity.school.create.AddSchoolMvpActivity.SCHOOL_ADDED_ARG;

/**
 * Search School Activity
 */
public class SearchSchoolMvpActivity extends SupportMvpSearchLCEActivity<SearchSchoolActivityPresenter,
        ISearchSchoolActivityView, SchoolEntity>
        implements HasComponent<SchoolComponent>, ISearchSchoolActivityView, SchoolAdapter.OnSchoolListener {

    public final static String SCHOOL_SELECTED_ARG = "SCHOOL_SELECTED_ARG";

    private final static int ADD_SCHOOL_REQUEST_CODE = 237;

    private SchoolComponent schoolComponent;

    /**
     * Add School Image View
     */
    @BindView(R.id.addSchool)
    protected ImageView addSchoolImageView;


    /**
     * Get Calling Intent
     *
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context){
        return new Intent(context, SearchSchoolMvpActivity.class);
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(){

        schoolComponent = DaggerSchoolComponent.builder()
            .applicationComponent(getApplicationComponent())
            .activityModule(getActivityModule())
            .build();

        schoolComponent.inject(this);
    }


    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        noDataFoundView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Timber.d("On Not Data Founded");
            }
        });

        // Set Search Layout Message
        initSearchLayout.getSearchMessage().setText(R.string.search_school_message);
        loadingLayout.getMessage().setText(R.string.search_school_loading_message);

    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes(){
        return R.layout.activity_search_school;
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public SearchSchoolActivityPresenter providePresenter(){
        return schoolComponent.searchSchoolActivityPresenter();
    }


    /**
     * Get Component
     * @return
     */
    @Override
    public SchoolComponent getComponent(){
        return schoolComponent;
    }


    /**
     * Get Adapter
     * @return
     */
    @NotNull
    @Override
    protected SupportRecyclerViewAdapter<SchoolEntity> getAdapter() {
        final SchoolAdapter schoolAdapter = new SchoolAdapter(appContext, new ArrayList<SchoolEntity>());
        schoolAdapter.setSchoolListener(this);
        return schoolAdapter;
    }

    /**
     * On Header Click
     */
    @Override
    public void onHeaderClick() { }

    /**
     * On Item Click
     * @param schoolEntity
     */
    @Override
    public void onItemClick(final SchoolEntity schoolEntity) {
        Timber.d("School Entity Selected -> %s", schoolEntity.getIdentity());
        final Intent schoolSelectedIntent = new Intent();
        schoolSelectedIntent.putExtra(SCHOOL_SELECTED_ARG, schoolEntity);
        onResultOk(schoolSelectedIntent);
    }

    /**
     * On Footer Click
     */
    @Override
    public void onFooterClick() {}


    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return SupportToolbarApp.RETURN_TOOLBAR;
    }

    /**
     * On Show School Detail
     * @param schoolEntity
     */
    @Override
    public void onShowSchoolDetail(SchoolEntity schoolEntity) {
        Preconditions.checkNotNull(schoolEntity, "School Entity can not be null");
        navigatorImpl.showSchoolDetail(this, schoolEntity);
    }

    /**
     * On Activity Result
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_SCHOOL_REQUEST_CODE) {

            if(Activity.RESULT_OK == resultCode) {

                final Bundle args = data.getExtras();

                if(args != null && args.containsKey(SCHOOL_ADDED_ARG)) {

                    final Intent schoolSelectedIntent = new Intent();
                    schoolSelectedIntent.putExtra(SCHOOL_SELECTED_ARG, args.getSerializable(SCHOOL_ADDED_ARG));
                    onResultOk(schoolSelectedIntent);
                }

            }

        }

    }

    /**
     * No Registered School
     */
    @Override
    public void noRegisteredSchool() {

        showConfirmationDialog(R.string.no_registered_school, new ConfirmationDialogFragment.ConfirmationDialogListener() {

            /**
             * On Accept
             * @param dialog
             */
            @Override
            public void onAccepted(DialogFragment dialog) {
                navigatorImpl.showAddSchool(SearchSchoolMvpActivity.this, ADD_SCHOOL_REQUEST_CODE);
            }

            /**
             * On Rejected
             * @param dialog
             */
            @Override
            public void onRejected(DialogFragment dialog) {
                closeActivity();
            }
        });
    }


    /**
     * On Add School
     */
    @OnClick(R.id.addSchool)
    protected void onAddSchool(){
        navigatorImpl.showAddSchool(SearchSchoolMvpActivity.this, ADD_SCHOOL_REQUEST_CODE);
    }
}
