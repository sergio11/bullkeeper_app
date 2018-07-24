package sanchez.sanchez.sergio.masom_app.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.masom_app.di.modules.ActivityModule;
import sanchez.sanchez.sergio.masom_app.di.scopes.PerActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.intro.IntroActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.intro.IntroPresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.intro.IntroFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.intro.IntroFragmentPresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.signin.SigninFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.signin.SigninFragmentPresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.signup.SignupFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.signup.SignupFragmentPresenter;

@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class })
public interface IntroComponent extends ActivityComponent {

    /**
     * Inject into Intro Activity
     * @param introActivity
     */
    void inject(IntroActivity introActivity);

    /**
     * Inject Intro Fragment
     * @param introFragment
     */
    void inject(final IntroFragment introFragment);

    /**
     * Inject Signin Fragment
     * @param signinFragment
     */
    void inject(final SigninFragment signinFragment);

    /**
     * Signup FragmentS
     * @param signupFragment
     */
    void inject(final SignupFragment signupFragment);


    IntroPresenter introPresenter();
    IntroFragmentPresenter introFragmentPresenter();
    SigninFragmentPresenter signinFragmentPresenter();
    SignupFragmentPresenter signupFragmentPresenter();
}
