package sanchez.sanchez.sergio.masom_app.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.masom_app.di.modules.ActivityModule;
import sanchez.sanchez.sergio.masom_app.di.scopes.PerActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.comments.CommentsActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.comments.CommentsPresenter;

/**
 * User Profile Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class })
public interface CommentsComponent extends ActivityComponent {

    /**
     * Inject on Comments Activity
     * @param commentsActivity
     */
    void inject(final CommentsActivity commentsActivity);

    /**
     * Comments Presenter
     * @return
     */
    CommentsPresenter commentsPresenter();
}
