package sanchez.sanchez.sergio.masom_app.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.masom_app.di.modules.ActivityModule;
import sanchez.sanchez.sergio.masom_app.di.scopes.PerActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.mykids.MyKidsMvpActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.mykids.MyKidsActivityPresenter;
import sanchez.sanchez.sergio.masom_app.ui.activity.mykidsdetail.MyKidsDetailMvpActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.mykidsdetail.MyKidsDetailPresenter;
import sanchez.sanchez.sergio.masom_app.ui.activity.mykidsprofile.MyKidsProfileMvpActivityMvp;
import sanchez.sanchez.sergio.masom_app.ui.activity.mykidsprofile.MyKidsProfilePresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.dimensions.FourDimensionsMvpFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.dimensions.FourDimensionsFragmentPresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.importantalerts.ImportantAlertsMvpFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.importantalerts.ImportantAlertsFragmentPresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.mykids.MyKidsActivityMvpFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.mykids.MyKidsFragmentPresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.relations.KidRelationFragmentPresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.relations.KidRelationsMvpFragment;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class })
public interface MyKidsComponent extends ActivityComponent {

    /**
     * My Kids Activity
     * @param myKidsActivity
     */
    void inject(final MyKidsMvpActivity myKidsActivity);

    /**
     * My Kids Activity Fragment
     * @param myKidsActivityFragment
     */
    void inject(final MyKidsActivityMvpFragment myKidsActivityFragment);

    /**
     * My Kids Profile Activity
     * @param myKidsProfileActivity
     */
    void inject(final MyKidsProfileMvpActivityMvp myKidsProfileActivity);

    /**
     * Inject on My Kids Detail Activity
     * @param myKidsDetailActivity
     */
    void inject(final MyKidsDetailMvpActivity myKidsDetailActivity);

    /**
     * Inject on Four Dimensions
     * @param fourDimensionsFragment
     */
    void inject(final FourDimensionsMvpFragment fourDimensionsFragment);

    /**
     * Inject on Important Alerts Fragment
     * @param importantAlertsFragment
     */
    void inject(final ImportantAlertsMvpFragment importantAlertsFragment);

    /**
     * Inject on Kid Relations Fragment
     * @param kidRelationsFragment
     */
    void inject(final KidRelationsMvpFragment kidRelationsFragment);


    MyKidsActivityPresenter myKidsActivityPresenter();
    MyKidsFragmentPresenter myKidsFragmentPresenter();
    MyKidsProfilePresenter myKidsProfilePresenter();
    MyKidsDetailPresenter myKidsDetailPresenter();
    FourDimensionsFragmentPresenter fourDimensionsFragmentPresenter();
    ImportantAlertsFragmentPresenter importantAlertsFragmentPresenter();
    KidRelationFragmentPresenter kidRelationFragmentPresenter();
}
