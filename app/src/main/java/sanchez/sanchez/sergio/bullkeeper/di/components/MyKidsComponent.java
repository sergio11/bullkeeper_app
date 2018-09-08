package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ChildrenModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ParentModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykids.MyKidsMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykids.MyKidsActivityPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.MyKidsDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.MyKidsDetailPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile.MyKidsProfileMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile.MyKidsProfilePresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.importantalerts.ImportantAlertsMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.importantalerts.ImportantAlertsFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.mykids.MyKidsActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.mykids.MyKidsFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.relations.KidRelationFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.relations.KidRelationsMvpFragment;

@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class,
                ParentModule.class, ChildrenModule.class})
public interface MyKidsComponent extends StatsComponent {

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
    void inject(final MyKidsProfileMvpActivity myKidsProfileActivity);

    /**
     * Inject on My Kids Detail Activity
     * @param myKidsDetailActivity
     */
    void inject(final MyKidsDetailMvpActivity myKidsDetailActivity);


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
    ImportantAlertsFragmentPresenter importantAlertsFragmentPresenter();
    KidRelationFragmentPresenter kidRelationFragmentPresenter();
}
