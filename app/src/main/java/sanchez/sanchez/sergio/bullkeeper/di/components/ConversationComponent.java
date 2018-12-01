package sanchez.sanchez.sergio.bullkeeper.di.components;


import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ConversationModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.conversationmessages.ConversationMessageListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.conversationmessages.ConversationMessageListPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.conversationslist.ConversationListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.conversationslist.ConversationListPresenter;

@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class, ConversationModule.class })
public interface ConversationComponent extends ActivityComponent {

    /**
     * Inject into Conversation Activity
     * @param conversationListMvpActivity
     */
    void inject(final ConversationListMvpActivity conversationListMvpActivity);

    /**
     * Inject into Conversation Message List Activity
     * @param conversationMessageListMvpActivity
     */
    void inject(final ConversationMessageListMvpActivity conversationMessageListMvpActivity);

    /**
     * Conversation Presenter
     * @return
     */
    ConversationListPresenter conversationPresenter();

    /**
     * Conversation Message List Presenter
     * @return
     */
    ConversationMessageListPresenter conversationMessageListPresenter();


}
