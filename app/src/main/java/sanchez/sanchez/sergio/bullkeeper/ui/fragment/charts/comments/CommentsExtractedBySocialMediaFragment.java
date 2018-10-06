package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.comments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fernandocejas.arrow.checks.Preconditions;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.StatsComponent;
import sanchez.sanchez.sergio.bullkeeper.core.ui.chart.SupportBarChartMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;
import sanchez.sanchez.sergio.domain.models.CommentsStatisticsBySocialMediaEntity;

/**
 * Comments Extracted By Social MVP Fragment
 */
public class CommentsExtractedBySocialMediaFragment
        extends SupportBarChartMvpFragment<CommentsExtractedBySocialMediaFragmentPresenter,
        ICommentsExtractedBySocialMediaFragmentView, IBasicActivityHandler, StatsComponent,
        CommentsStatisticsBySocialMediaEntity>
        implements ICommentsExtractedBySocialMediaFragmentView {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";

    public enum SocialMediaEnum { INSTAGRAM, FACEBOOK, YOUTUBE }

    /**
     * Kid Identity
     */
    @State
    protected String kidIdentity;

    /**
     * Show All Comments Extracted View
     */
    @BindView(R.id.showAllCommentsExtracted)
    protected View showAllCommentsExtractedView;

    /**
     * Social Media Labels
     */
    private String[] socialMediaLabels = new String[SocialMediaEnum.values().length];


    public CommentsExtractedBySocialMediaFragment() {
        // Required empty public constructor
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static CommentsExtractedBySocialMediaFragment newInstance(final String kidIdentity) {
        CommentsExtractedBySocialMediaFragment fragment = new CommentsExtractedBySocialMediaFragment();
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

        if (getArguments() == null ||
                !getArguments().containsKey(KID_IDENTITY_ARG))
            throw new IllegalArgumentException("You must provide kid identity");

        kidIdentity = getArguments().getString(KID_IDENTITY_ARG);

    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        if(getArguments() != null &&
                getArguments().containsKey(KID_IDENTITY_ARG))
            args.putString(CommentsExtractedBySocialMediaFragmentPresenter.KID_IDENTITY_ARG,
                    kidIdentity);
        return args;
    }

    /**
     * Get Legend Label Color
     * @return
     */
    @Override
    protected int[] getLegendLabelColor() {
        return new int[] {
                ContextCompat.getColor(appContext, R.color.instagram_color),
                ContextCompat.getColor(appContext, R.color.facebook_color),
                ContextCompat.getColor(appContext, R.color.youtube_color)
        };
    }

    /**
     * Get Legend Labels
     * @return
     */
    @Override
    protected String[] getLegendLabels() {
        return new String[] {
                getString(R.string.instagram_brand_title),
                getString(R.string.facebook_brand_title),
                getString(R.string.youtube_brand_title)
        };
    }

    /**
     * Get Value Formatter
     * @return
     */
    @Override
    protected IValueFormatter getValueFormatter() {
        return new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex,
                                            ViewPortHandler viewPortHandler) {
                return String.format(Locale.getDefault(), "%d/%d", (int)value, 17);
            }
        };
    }

    /**
     * On Load Data
     */
    @Override
    protected void onLoadData() {
        getPresenter().loadData(kidIdentity);
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_comments_extracted;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(StatsComponent component) {
        component.inject(this);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public CommentsExtractedBySocialMediaFragmentPresenter providePresenter() {
        return component.commentsExtractedFragmentPresenter();
    }


    /**
     * On Value Selected
     * @param e
     * @param h
     */
    @Override
    public void onValueSelected(Entry e, Highlight h) {
        final int selectedIdx = (int)e.getX();
        navigator.showCommentsExtractedDialog((AppCompatActivity)getActivity(),
                selectedIdx, socialMediaLabels[selectedIdx], kidIdentity);
    }

    /**
     * On Nothing Selected
     */
    @Override
    public void onNothingSelected() {}

    /**
     * On Data Avaliable
     * @param chartData
     */
    @Override
    public void onDataAvaliable(CommentsStatisticsBySocialMediaEntity chartData) {
        super.onDataAvaliable(chartData);
        Preconditions.checkNotNull(chartData, "Chart Data can not be null");

        List<BarEntry> entries = new ArrayList<>();
        for(int i = 0; i < SocialMediaEnum.values().length; i++ ) {
            final SocialMediaEnum socialMediaEnum = SocialMediaEnum.values()[i];
            int j = 0;
            for(; j < chartData.getCommentsBySocialMediaEntities().size(); j++) {
                final CommentsStatisticsBySocialMediaEntity.CommentsBySocialMediaEntity commentsBySocialMediaEntity
                        = chartData.getCommentsBySocialMediaEntities().get(j);
                if(commentsBySocialMediaEntity.getSocialMediaTypeEnum().name().equals(socialMediaEnum.name())) {
                    entries.add(new BarEntry(i, commentsBySocialMediaEntity.getTotal()));
                    socialMediaLabels[i] = commentsBySocialMediaEntity.getLabel();
                    break;
                }
            }
            if(j == chartData.getCommentsBySocialMediaEntities().size()){
                entries.add(new BarEntry(i, 0));
                socialMediaLabels[i] = "0";
            }
        }

        // Set Chart Data
        setChartData(entries);
    }


    /**
     * On Show All Comments Extracted Clicked
     */
    @OnClick(R.id.showAllCommentsExtracted)
    protected void onShowAllCommentsExtractedClicked(){
        navigator.navigateToComments(kidIdentity);
    }

    /**
     * On Refresh Data
     */
    @OnClick(R.id.refreshData)
    protected void onRefreshData(){
        refreshChart();
    }

}
