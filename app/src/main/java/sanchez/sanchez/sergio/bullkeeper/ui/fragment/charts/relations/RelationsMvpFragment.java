package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.relations;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.github.mikephil.charting.data.PieEntry;
import java.util.List;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.StatsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.SupportPieChartMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.support.IBasicActivityHandler;

/**
 * Relations Mvp Fragment
 */
public class RelationsMvpFragment
        extends SupportPieChartMvpFragment<RelationsFragmentPresenter,
        IRelationsFragmentView, IBasicActivityHandler, StatsComponent>
        implements IRelationsFragmentView {

    private static final String KID_IDENTITY_ARG = "KID_IDENTITY_ARG";


    /**
     * Kid Identity
     */
    private String kidIdentity;


    public RelationsMvpFragment() {
        // Required empty public constructor
    }

    /**
     * Get Chart Title
     * @return
     */
    @Override
    protected int getChartTitle() {
        return R.string.relations_title;
    }

    /**
     * New Instance
     * @param kidIdentity
     * @return
     */
    public static RelationsMvpFragment newInstance(final String kidIdentity) {
        RelationsMvpFragment fragment = new RelationsMvpFragment();
        Bundle args = new Bundle();
        args.putString(KID_IDENTITY_ARG, kidIdentity);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_relations;
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
    public RelationsFragmentPresenter providePresenter() {
        return component.relationsFragmentPresenter();
    }

    /**
     * On Relations Loaded
     * @param entries
     */
    @Override
    public void onRelationsLoaded(final List<PieEntry> entries) {
        setChartData(entries, new int[] {
                R.color.softOrange,
                R.color.cyanBrilliant,
                R.color.darkModerateBlue,
                R.color.moderateRed
        });
    }
}
