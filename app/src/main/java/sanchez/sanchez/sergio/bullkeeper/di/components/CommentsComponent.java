package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.CommentsModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.commentdetail.CommentDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.commentdetail.CommentDetailPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.comments.CommentsMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.comments.CommentsMvpPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.relationdetail.RelationDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.relationdetail.RelationDetailPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.relations.RelationsMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.relations.RelationsMvpPresenter;

/**
 * User Profile Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class, CommentsModule.class })
public interface CommentsComponent extends ActivityComponent {

    /**
     * Inject on Comments Activity
     * @param commentsActivity
     */
    void inject(final CommentsMvpActivity commentsActivity);

    /**
     * Comment Detail Activity
     * @param commentDetailActivity
     */
    void inject(final CommentDetailMvpActivity commentDetailActivity);

    /**
     * Relations Activity
     * @param relationsMvpActivity
     */
    void inject(final RelationsMvpActivity relationsMvpActivity);

    /**
     * Relation Detail Mvp Activity
     * @param relationDetailMvpActivity
     */
    void inject(final RelationDetailMvpActivity relationDetailMvpActivity);

    /**
     * Comments Presenter
     * @return
     */
    CommentsMvpPresenter commentsPresenter();
    CommentDetailPresenter commentDetailPresenter();
    RelationsMvpPresenter relationsMvpPresenter();
    RelationDetailPresenter relationDetailPresenter();


}
