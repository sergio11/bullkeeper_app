package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.InvitationsModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.invitationdetail.InvitationDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.invitationdetail.InvitationDetailPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.invitations.InvitationsListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.invitations.InvitationsListPresenter;

/**
 * Invitations Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class,
                InvitationsModule.class })
public interface InvitationsComponent extends ActivityComponent {

    /**
     * Inject into Invitations List Mvp Activity
     * @param invitationsListMvpActivity
     */
    void inject(final InvitationsListMvpActivity invitationsListMvpActivity);

    /**
     * Inject into Invitation Detail Activity
     * @param invitationDetailMvpActivity
     */
    void inject(final InvitationDetailMvpActivity invitationDetailMvpActivity);

    /**
     *
     * @return
     */
    InvitationsListPresenter invitationsListPresenter();

    /**
     *
     * @return
     */
    InvitationDetailPresenter invitationDetailPresenter();
}
