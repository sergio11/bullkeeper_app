package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.AlertsModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.AppRulesModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.CallModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ChildrenProfileModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.CommentsModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ContactsModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.GuardianModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.KidRequestModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ScheduledBlockModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ScreenTimeAllowanceModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.SmsModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.SocialMediaModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.TerminalsModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykids.MyKidsMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykids.MyKidsActivityPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.MyKidsDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.MyKidsDetailPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile.MyKidsProfileMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile.MyKidsProfilePresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.apprules.AppRulesFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.apprules.AppRulesMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.callslist.CallListFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.callslist.CallsListMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.contactlist.ContactFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.contactlist.ContactListMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.familylocator.FamilyLocatorFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.familylocator.FamilyLocatorMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.importantalerts.ImportantAlertsMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.importantalerts.ImportantAlertsFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.kidrequest.KidRequestListFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.kidrequest.KidRequestListMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.smslist.SmsListFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.smslist.SmsListMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kidguardians.KidGuardiansFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kidguardians.KidGuardiansMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.mykids.MyKidsActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.mykids.MyKidsFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.scheduledblock.ScheduledBlocksFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.scheduledblock.ScheduledBlocksMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.terminals.TerminalsFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.terminals.TerminalsMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.timeallowance.TimeAllowanceFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.timeallowance.TimeAllowanceMvpFragment;

/**
 * My Kids Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class,
                 GuardianModule.class, ChildrenProfileModule.class, AlertsModule.class,
                SocialMediaModule.class, CommentsModule.class, ScheduledBlockModule.class,
                AppRulesModule.class, ScreenTimeAllowanceModule.class, TerminalsModule.class,
                SmsModule.class, CallModule.class, ContactsModule.class, KidRequestModule.class})
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

    /**
     * Inject into Time Allowance Mvp Fragment
     * @param timeAllowanceMvpFragment
     */
    void inject(final TimeAllowanceMvpFragment timeAllowanceMvpFragment);

    /**
     * Inject into Family Locator Fragment
     * @param familyLocatorMvpFragment
     */
    void inject(final FamilyLocatorMvpFragment familyLocatorMvpFragment);

    /**
     * Injet into Terminals Mvp Fragment
     * @param terminalsMvpFragment
     */
    void inject(final TerminalsMvpFragment terminalsMvpFragment);

    /**
     * Inject into Kid Guardian Mvp Fragment
     * @param kidGuardiansMvpFragment
     */
    void inject(final KidGuardiansMvpFragment kidGuardiansMvpFragment);

    /**
     * Inject into Sms List Mvp Fragment
     * @param smsListMvpFragment
     */
    void inject(final SmsListMvpFragment smsListMvpFragment);

    /**
     * Inject into Calls List Mvp Fragment
     * @param callsListMvpFragment
     */
    void inject(final CallsListMvpFragment callsListMvpFragment);

    /**
     * Inject into Contact List Mvp Fragment
     * @param contactListMvpFragment
     */
    void inject(final ContactListMvpFragment contactListMvpFragment);

    /**
     * Inject into Kid Request List Mvp Fragment
     * @param kidRequestListMvpFragment
     */
    void inject(final KidRequestListMvpFragment kidRequestListMvpFragment);


    MyKidsActivityPresenter myKidsActivityPresenter();
    MyKidsFragmentPresenter myKidsFragmentPresenter();
    MyKidsProfilePresenter myKidsProfilePresenter();
    MyKidsDetailPresenter myKidsDetailPresenter();
    ImportantAlertsFragmentPresenter importantAlertsFragmentPresenter();
    ScheduledBlocksFragmentPresenter scheduledBlocksFragmentPresenter();
    AppRulesFragmentPresenter appRulesFragmentPresenter();
    TimeAllowanceFragmentPresenter timeAllowanceFragmentPresenter();
    FamilyLocatorFragmentPresenter familyLocatorFragmentPresenter();
    TerminalsFragmentPresenter terminalsFragmentPresenter();
    KidGuardiansFragmentPresenter kidGuardiansFragmentPresenter();
    SmsListFragmentPresenter smsListFragmentPresenter();
    CallListFragmentPresenter callListFragmentPresenter();
    ContactFragmentPresenter  contactFragmentPresenter();
    KidRequestListFragmentPresenter kidRequestListFragmentPresenter();
}
