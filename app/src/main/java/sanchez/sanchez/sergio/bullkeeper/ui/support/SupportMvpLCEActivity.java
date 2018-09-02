package sanchez.sanchez.sergio.bullkeeper.ui.support;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.fernandocejas.arrow.checks.Preconditions;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.decoration.ItemOffsetDecoration;
import timber.log.Timber;

/**
 * Support MVP LCE Activity
 * @param <T>
 * @param <E>
 */
public abstract class SupportMvpLCEActivity<T extends SupportLCEPresenter<E>, E extends ISupportLCEView, F>
        extends SupportMvpActivity<T, E> implements SwipeRefreshLayout.OnRefreshListener, View.OnClickListener,
        SupportRecyclerViewAdapter.OnSupportRecyclerViewListener<F> , ISupportLCEView<F>{

    public static String TAG = "SUPPORT_MVP_LCE_ACTIVITY";

    /**
     * App Context
     */
    @Inject
    protected Context appContext;

    /**
     * Swipe Refresh Layout
     */
    @BindView(R.id.content)
    protected SwipeRefreshLayout content;

    /**
     * Recycler View
     */
    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;


    /**
     * Error Ocurred Layout
     */
    @BindView(R.id.errorOccurred)
    protected View errorOccurredView;

    /**
     * No Data Found
     */
    @BindView(R.id.noDataFound)
    protected View noDataFoundView;

    /**
     * Error Occurred Layout
     */
    private ErrorOccurredLayout errorOccurredLayout;

    /**
     * No Data Found Layout
     */
    private NotDataFoundLayout notDataFoundLayout;

    /**
     * Recycler View Adapter
     */
    protected SupportRecyclerViewAdapter<F> recyclerViewAdapter;

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        super.onViewReady(savedInstanceState);

        // Config Swipe Refresh Layout
        content.setColorSchemeResources(R.color.commonWhite);
        content.setProgressBackgroundColorSchemeResource(R.color.cyanBrilliant);
        content.setOnRefreshListener(this);

        // Config Recycler View
        recyclerView.setLayoutManager(new LinearLayoutManager(appContext));
        recyclerView.setNestedScrollingEnabled(false);

        ItemOffsetDecoration itemOffsetDecoration = new ItemOffsetDecoration(appContext, R.dimen.item_offset);
        recyclerView.addItemDecoration(itemOffsetDecoration);
        // Set Animator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Error Occurred Layout
        errorOccurredLayout = new ErrorOccurredLayout();
        errorOccurredLayout.bind(errorOccurredView);
        errorOccurredLayout.hide();

        // Not Data Found Layout
        notDataFoundLayout = new NotDataFoundLayout();
        notDataFoundLayout.bind(noDataFoundView);
        notDataFoundLayout.hide();

        noDataFoundView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPresenter().loadData();
            }
        });

        recyclerViewAdapter = getAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setOnSupportRecyclerViewListener(this);

        errorOccurredLayout.getRetryAgain().setOnClickListener(this);

    }

    /**
     * On Destroy
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(errorOccurredLayout != null)
            errorOccurredLayout.unbind();
        if(notDataFoundLayout != null)
            notDataFoundLayout.unbind();
    }

    /**
     * Get Adapter
     * @return
     */
    @NotNull
    protected abstract SupportRecyclerViewAdapter<F> getAdapter();

    /**
     * On Network Error
     */
    @Override
    public void onNetworkError() {
        content.setVisibility(View.GONE);
        notDataFoundLayout.hide();
        errorOccurredLayout.show(getString(R.string.network_error_ocurred));
    }

    /**
     * On Other Exception
     */
    @Override
    public void onOtherException() {
        content.setVisibility(View.GONE);
        notDataFoundLayout.hide();
        errorOccurredLayout.show(getString(R.string.unexpected_error_ocurred));
    }

    /**
     * On Refresh
     */
    @Override
    public void onRefresh() {
        getPresenter().loadData();
    }

    /**
     * On No Data Found
     */
    @Override
    public void onNoDataFound() {
        Timber.d("On No Data Found");
        content.setVisibility(View.GONE);
        errorOccurredLayout.hide();
        notDataFoundLayout.show(getString(R.string.no_data_found));
    }


    /**
     * On Data Loaded
     * @param dataLoaded
     */
    @Override
    public void onDataLoaded(final List<F> dataLoaded) {
        Preconditions.checkNotNull(dataLoaded, "Data Loaded can not be null");
        Preconditions.checkState(!dataLoaded.isEmpty(), "Data Loaded can not be empty");

        Timber.d("Data Loaded -> %d", dataLoaded.size());

        content.setVisibility(View.VISIBLE);
        content.setRefreshing(false);

        recyclerViewAdapter.setData(dataLoaded);
        recyclerViewAdapter.notifyDataSetChanged();

        final Context context = recyclerView.getContext();
        final LayoutAnimationController controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
    }

    /**
     * On Click
     * @param view
     */
    @Override
    public void onClick(View view) {
        content.setVisibility(View.VISIBLE);
        getPresenter().loadData();
    }

    /**
     * Error Occurred Layout
     */
    public class ErrorOccurredLayout {

        /**
         * Retry Again
         */
        @BindView(R.id.retryAgain)
        protected Button retryAgain;

        /**
         * Error Message
         */
        @BindView(R.id.errorMessage)
        protected TextView errorMessage;

        /**
         * UnBinder
         */
        private Unbinder unbinder;

        private View source;

        /**
         * Bind
         */
        public void bind(final View source) {
            Preconditions.checkNotNull(source, "Source can not be null");
            this.source = source;
            unbinder = ButterKnife.bind(this, source);

        }

        /**
         * Show
         * @param errorText
         */
        public void show(final String errorText) {
            errorMessage.setText(errorText);
            source.setVisibility(View.VISIBLE);
        }

        /**
         * Hide
         */
        public void hide() {
            errorMessage.setText("-");
            source.setVisibility(View.GONE);
        }


        /**
         * Un Bind
         */
        public void unbind(){

            if(unbinder != null)
                unbinder.unbind();
        }

        /**
         *
         * @return
         */
        public Button getRetryAgain() {
            return retryAgain;
        }
    }

    /**
     * Support Not Data Found
     */
    public class NotDataFoundLayout {

        /**
         * No Data Found Icon
         */
        @BindView(R.id.noDataFoundIcon)
        protected ImageView noDataFoundIcon;

        /**
         * Error Message
         */
        @BindView(R.id.errorMessage)
        protected TextView errorMessage;

        /**
         * UnBinder
         */
        private Unbinder unbinder;

        private View source;

        /**
         * Bind
         */
        public void bind(final View source) {
            Preconditions.checkNotNull(source, "Source can not be null");
            this.source = source;
            unbinder = ButterKnife.bind(this, source);

        }

        /**
         * Show
         * @param errorText
         */
        public void show(final String errorText) {
            errorMessage.setText(errorText);
            source.setVisibility(View.VISIBLE);
        }

        /**
         * Hide
         */
        public void hide() {
            errorMessage.setText("-");
            source.setVisibility(View.GONE);
        }


        /**
         * Un Bind
         */
        public void unbind(){

            if(unbinder != null)
                unbinder.unbind();
        }

        /**
         * Get No Data Found Icon
         * @return
         */
        public ImageView getNoDataFoundIcon() {
            return noDataFoundIcon;
        }

        /**
         * Get Error Message
         * @return
         */
        public TextView getErrorMessage() {
            return errorMessage;
        }
    }

}
