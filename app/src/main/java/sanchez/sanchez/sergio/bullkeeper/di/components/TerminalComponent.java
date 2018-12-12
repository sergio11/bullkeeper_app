package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.TerminalsModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.terminaldetail.TerminalDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.terminaldetail.TerminalDetailPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.terminaldetail.TerminalDetailActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.terminaldetail.TerminalDetailFragmentPresenter;

/**
 * Terminal Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class, TerminalsModule.class})
public interface TerminalComponent extends ActivityComponent {

    /**
     * Inject into Terminal Detail Activity
     * @param terminalDetailMvpActivity
     */
    void inject(final TerminalDetailMvpActivity terminalDetailMvpActivity);

    /**
     * Inject into Terminal Detail Activity Mvp Fragment
     * @param terminalDetailActivityMvpFragment
     */
    void inject(final TerminalDetailActivityMvpFragment terminalDetailActivityMvpFragment);

    /**
     * Presenters
     * @return
     */
    TerminalDetailPresenter terminalDetailPresenter();
    TerminalDetailFragmentPresenter terminalDetailFragmentPresenter();
}
