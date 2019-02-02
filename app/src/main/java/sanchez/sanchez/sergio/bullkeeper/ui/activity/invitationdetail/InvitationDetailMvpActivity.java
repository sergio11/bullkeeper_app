package sanchez.sanchez.sergio.bullkeeper.ui.activity.invitationdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.ImageView;
import android.widget.TextView;
import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerInvitationsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.InvitationsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;

/**
 * Invitation Detail
 */
public class InvitationDetailMvpActivity extends SupportMvpActivity<InvitationDetailPresenter, IInvitationDetailView>
        implements HasComponent<InvitationsComponent>, IInvitationDetailActivityHandler
        , IInvitationDetailView {

    private final String CONTENT_FULL_NAME = "INVITATION_DETAIL";
    private final String CONTENT_TYPE_NAME = "INVITATIONS";

    /**
     * Args
     */
    public static String INVITATION_ID_ARG = "INVITATION_ID_ARG";

    /**
     * Invitation Component
     */
    private InvitationsComponent invitationsComponent;

    /**
     * Views
     * ====================
     */

    /**
     * Kid Name Text View
     */
    @BindView(R.id.kidName)
    protected TextView kidNameTextView;

    /**
     * School Name Text View
     */
    @BindView(R.id.schoolName)
    protected TextView schoolNameTextView;

    /**
     * Kid Detail Background
     */
    @BindView(R.id.kidDetailBackground)
    protected ImageView kidDetailBackgroundImageView;

    /**
     * Role Name Text View
     */
    @BindView(R.id.roleName)
    protected TextView roleNameTextView;

    /**
     * Role Image View
     */
    @BindView(R.id.roleImageView)
    protected ImageView roleImageView;

    /**
     * Role Description
     */
    @BindView(R.id.roleDescription)
    protected TextView roleDescriptionTextView;

    /**
     * State
     * =============
     */

    @State
    protected String invitationId;


    /**
     * Dependencies
     * =================
     */

    @Inject
    protected Picasso picasso;


    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String invitationId) {
        final Intent intent = new Intent(context, InvitationDetailMvpActivity.class);
        intent.putExtra(INVITATION_ID_ARG, invitationId);
        return intent;
    }


    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        invitationsComponent = DaggerInvitationsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        invitationsComponent.inject(this);

    }

    /**
     *
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_invitation_detail;
    }


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(final Bundle savedInstanceState) {
        if(savedInstanceState == null) {
            if (!getIntent().hasExtra(INVITATION_ID_ARG) ||
                    !appUtils.isValidString(getIntent().getStringExtra(INVITATION_ID_ARG)))
                throw new IllegalArgumentException("You must provide invitation id arg");

            invitationId = getIntent().getStringExtra(INVITATION_ID_ARG);
        }
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        final Bundle args = new Bundle();
        args.putString(INVITATION_ID_ARG, invitationId);
        return args;
    }

    /**
     * On Create Content View Event
     * @return
     */
    @Override
    protected ContentViewEvent onCreateContentViewEvent() {
        return new ContentViewEvent()
                .putContentName(CONTENT_FULL_NAME)
                .putContentType(CONTENT_TYPE_NAME);

    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public InvitationDetailPresenter providePresenter() {
        return invitationsComponent.invitationDetailPresenter();
    }

    /**
     * Get component
     * @return
     */
    @Override
    public InvitationsComponent getComponent() {
        return invitationsComponent;
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.background_cyan_5;
    }

    /**
     * On Supervised Children Entity Loaded
     * @param supervisedChildrenEntity
     */
    @Override
    public void onSupervisedChildrenEntityLoaded(final SupervisedChildrenEntity supervisedChildrenEntity) {
        Preconditions.checkNotNull(supervisedChildrenEntity, "Supervised Children Entity can not be null");

        final KidEntity kidEntity = supervisedChildrenEntity.getKid();

        if(kidEntity.getProfileImage() != null &&
                appUtils.isValidString(kidEntity.getProfileImage()))
            picasso.load(kidEntity.getProfileImage())
                .error(R.drawable.kid_default_image)
                .placeholder(R.drawable.kid_default_image)
                .into(kidDetailBackgroundImageView);
        else
            kidDetailBackgroundImageView.setImageResource(R.drawable.kid_default_image);
        // Set Kid Name
        kidNameTextView.setText(kidEntity.getFullName());
        // Set School Name
        schoolNameTextView.setText(kidEntity.getSchool().getName());

        switch (supervisedChildrenEntity.getGuardianRolesEnum()) {
            case ADMIN:
                roleNameTextView.setText(R.string.invitation_admin_role_title);
                roleImageView.setImageResource(R.drawable.crown_solid_cyan);
                roleDescriptionTextView.setText(getText(R.string.invitation_admin_role_description));
                break;
            case DATA_VIEWER:
                roleNameTextView.setText(R.string.invitation_data_viewer_title);
                roleImageView.setImageResource(R.drawable.eye_solid_cyan);
                roleDescriptionTextView.setText(getText(R.string.invitation_data_viewer_description));
                break;
            case PARENTAL_CONTROL_RULE_EDITOR:
                roleNameTextView.setText(R.string.invitation_parental_control_rule_editor_title);
                roleImageView.setImageResource(R.drawable.child_solid_cyan);
                roleDescriptionTextView.setText(getText(R.string.invitation_parental_control_rule_editor_description));
                break;
        }

    }

    /**
     * On Invitation Accepted
     */
    @Override
    public void onInvitationAccepted() {
        showNoticeDialog(R.string.invitation_was_accepted_successfully, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                closeActivity();
            }
        });
    }

    /**
     * On Invitation Deleted
     */
    @Override
    public void onInvitationDeleted() {
        showNoticeDialog(R.string.invitation_item_removed, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                closeActivity();
            }
        });
    }

    /**
     * On Accept Invitation Clicked
     */
    @OnClick(R.id.acceptInvitation)
    protected void onAcceptInvitationClicked(){
        getPresenter().acceptInvitation();
    }


    /**
     * On Delete Invitation Clicked
     */
    @OnClick(R.id.deleteInvitation)
    protected void onDeleteInvitationClicked(){
        getPresenter().deleteInvitation();
    }
}
