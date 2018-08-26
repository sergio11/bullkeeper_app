package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.AccountsModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.intro.IntroMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.intro.IntroPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.intro.IntroMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.intro.IntroFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.password.ForgotPasswordMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.password.ForgotPasswordFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.signin.SigninMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.signin.SigninFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.signup.SignupMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.signup.SignupFragmentPresenter;

@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, AccountsModule.class })
public interface IntroComponent extends ActivityComponent {

    /**
     * Inject into Intro Activity
     * @param introActivity
     */
    void inject(IntroMvpActivity introActivity);

    /**
     * Inject Intro Fragment
     * @param introFragment
     */
    void inject(final IntroMvpFragment introFragment);

    /**
     * Inject Signin Fragment
     * @param signinFragment
     */
    void inject(final SigninMvpFragment signinFragment);

    /**
     * Signup Fragment
     * @param signupFragment
     */
    void inject(final SignupMvpFragment signupFragment);

    /**
     * Inject on Forgot Password Fragment
     * @param forgotPasswordFragment
     */
    void inject(final ForgotPasswordMvpFragment forgotPasswordFragment);


    IntroPresenter introPresenter();
    IntroFragmentPresenter introFragmentPresenter();
    SigninFragmentPresenter signinFragmentPresenter();
    SignupFragmentPresenter signupFragmentPresenter();
    ForgotPasswordFragmentPresenter forgotPasswordFragmentPresenter();
}
