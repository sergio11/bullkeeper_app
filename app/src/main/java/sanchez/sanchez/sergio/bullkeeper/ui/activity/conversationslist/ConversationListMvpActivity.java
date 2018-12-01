package sanchez.sanchez.sergio.bullkeeper.ui.activity.conversationslist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.crashlytics.android.answers.ContentViewEvent;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.ConversationComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerConversationComponent;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * Conversation Activity
 */
public class ConversationListMvpActivity extends SupportMvpActivity<ConversationListPresenter, IConversationListView>
        implements HasComponent<ConversationComponent>, IConversationListActivityHandler
        , IConversationListView {

    private final String CONTENT_FULL_NAME = "CONVERSATION_LIST";
    private final String CONTENT_TYPE_NAME = "CONVERSATON";


    /**
     * Conversation Component
     */
    private ConversationComponent conversationComponent;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, ConversationListMvpActivity.class);
    }


    /**
     * initialize Injector
     */
    @Override
    protected void initializeInjector() {
        conversationComponent = DaggerConversationComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        conversationComponent.inject(this);
    }


    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_home;
    }


    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public ConversationListPresenter providePresenter() {
        return conversationComponent.conversationPresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public ConversationComponent getComponent() {
        return conversationComponent;
    }

    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return RETURN_TOOLBAR;
    }



    /**
     * On Create Content View Event
     * @return
     */
    @Override
    protected ContentViewEvent onCreateContentViewEvent() {
        return new ContentViewEvent().putContentName(CONTENT_FULL_NAME)
                .putContentType(CONTENT_TYPE_NAME);
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.intro_background_cyan;
    }


}
