package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.AlertsModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.AppRulesModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ChildrenProfileModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.CommentsModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ParentModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ScheduledBlockModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.SocialMediaModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykids.MyKidsMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykids.MyKidsActivityPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.MyKidsDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.MyKidsDetailPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile.MyKidsProfileMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile.MyKidsProfilePresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.apprules.AppRulesFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.apprules.AppRulesMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.importantalerts.ImportantAlertsMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.importantalerts.ImportantAlertsFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.mykids.MyKidsActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.mykids.MyKidsFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.scheduledblock.ScheduledBlocksFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.scheduledblock.ScheduledBlocksMvpFragment;

@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class,
                 ParentModule.class, ChildrenProfileModule.class, AlertsModule.class,
                SocialMediaModule.class, CommentsModule.class, ScheduledBlockModule.class,
                AppRulesModule.class })
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
     * Inject on Schedule Blocks Mvp Fragment
     * @param scheduledBlocksMvpFragment
     */
    void inject(final ScheduledBlocksMvpFragment scheduledBlocksMvpFragment);

    /**
     * Inject into App Rules Mvp Fragment
     * @param appRulesMvpFragment
     */
    void inject(final AppRulesMvpFragment appRulesMvpFragment);


    MyKidsActivityPresenter myKidsActivityPresenter();
    MyKidsFragmentPresenter myKidsFragmentPresenter();
    MyKidsProfilePresenter myKidsProfilePresenter();
    MyKidsDetailPresenter myKidsDetailPresenter();
    ImportantAlertsFragmentPresenter importantAlertsFragmentPresenter();
    ScheduledBlocksFragmentPresenter scheduledBlocksFragmentPresenter();
    AppRulesFragmentPresenter appRulesFragmentPresenter();
}
