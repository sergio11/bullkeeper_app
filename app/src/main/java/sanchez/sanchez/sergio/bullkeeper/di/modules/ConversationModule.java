package sanchez.sanchez.sergio.bullkeeper.di.modules;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ConversationDTO;
import sanchez.sanchez.sergio.data.net.models.response.MessageDTO;
import sanchez.sanchez.sergio.data.net.services.IConversationsService;
import sanchez.sanchez.sergio.data.repository.ConversationRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.conversation.AddMessageByConversationIdInteract;
import sanchez.sanchez.sergio.domain.interactor.conversation.AddMessageInteract;
import sanchez.sanchez.sergio.domain.interactor.conversation.DeleteConversationByIdInteract;
import sanchez.sanchez.sergio.domain.interactor.conversation.DeleteConversationInteract;
import sanchez.sanchez.sergio.domain.interactor.conversation.DeleteConversationMessagesInteract;
import sanchez.sanchez.sergio.domain.interactor.conversation.DeleteMessagesByCoversationIdInteract;
import sanchez.sanchez.sergio.domain.interactor.conversation.GetConversationByIdInteract;
import sanchez.sanchez.sergio.domain.interactor.conversation.GetConversationMessagesInteract;
import sanchez.sanchez.sergio.domain.interactor.conversation.GetMessagesByConversationIdInteract;
import sanchez.sanchez.sergio.domain.models.ConversationEntity;
import sanchez.sanchez.sergio.domain.models.MessageEntity;
import sanchez.sanchez.sergio.domain.repository.IConversationRepository;

/**
 * Conversation Module
 */
@Module
public class ConversationModule {

    /**
     * Provide Image Loader
     * @param picasso
     * @return
     */
    @Provides
    @PerActivity
    public ImageLoader provideImageLoader(final Picasso picasso){
        return new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url, Object payload) {
                picasso.load(url).into(imageView);
            }
        };
    }

    /**
     * Provide Conversation Service
     * @param retrofit
     * @return
     */
    @Provides @PerActivity
    public IConversationsService provideConversationService(final Retrofit retrofit) {
        return retrofit.create(IConversationsService.class);
    }

    /**
     * Provide Conversation Repository
     * @param conversationEntityAbstractDataMapper
     * @param messageEntityAbstractDataMapper
     * @param conversationsService
     * @return
     */
    @Provides @PerActivity
    public IConversationRepository provideConversationRepository(
            final AbstractDataMapper<ConversationDTO, ConversationEntity> conversationEntityAbstractDataMapper,
            final AbstractDataMapper<MessageDTO, MessageEntity> messageEntityAbstractDataMapper,
            final IConversationsService conversationsService) {
        return new ConversationRepositoryImpl(conversationEntityAbstractDataMapper,
                messageEntityAbstractDataMapper, conversationsService);
    }

    /**
     * Provide Add Message By Conversation Id Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param conversationRepository
     * @return
     */
    @Provides @PerActivity
    public AddMessageByConversationIdInteract provideAddMessageByConversationIdInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IConversationRepository conversationRepository
            ){
        return new AddMessageByConversationIdInteract(threadExecutor, postExecutionThread, conversationRepository);
    }

    /**
     * Provide Add Message Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param conversationRepository
     * @return
     */
    @Provides @PerActivity
    public AddMessageInteract provideAddMessageInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IConversationRepository conversationRepository
    ){
        return new AddMessageInteract(threadExecutor, postExecutionThread, conversationRepository);
    }


    /**
     * Provide Delete Conversation By Id Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @return
     */
    @Provides @PerActivity
    public DeleteConversationByIdInteract provideDeleteConversationByIdInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IConversationRepository conversationRepository
    ){
        return new DeleteConversationByIdInteract(threadExecutor, postExecutionThread, conversationRepository);
    }

    /**
     * Provide Delete Conversation Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param conversationRepository
     * @return
     */
    @Provides @PerActivity
    public DeleteConversationInteract provideDeleteConversationInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IConversationRepository conversationRepository
    ){
        return new DeleteConversationInteract(threadExecutor, postExecutionThread, conversationRepository);
    }

    /**
     * Provide Delete Conversation Message Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @return
     */
    @Provides @PerActivity
    public DeleteConversationMessagesInteract provideDeleteConversationMessagesInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IConversationRepository conversationRepository
    ){
        return new DeleteConversationMessagesInteract(threadExecutor, postExecutionThread, conversationRepository);
    }

    /**
     * Delete Messages By Conversation Id Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @return
     */
    @Provides @PerActivity
    public DeleteMessagesByCoversationIdInteract provideDeleteMessagesByCoversationIdInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IConversationRepository conversationRepository
    ){
        return new DeleteMessagesByCoversationIdInteract(threadExecutor, postExecutionThread, conversationRepository);
    }

    /**
     * Get Conversation By Id Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @return
     */
    @Provides @PerActivity
    public GetConversationByIdInteract provideGetConversationByIdInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IConversationRepository conversationRepository
    ){
        return new GetConversationByIdInteract(threadExecutor, postExecutionThread, conversationRepository);
    }

    /**
     * Get Conversation Messages Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @return
     */
    @Provides @PerActivity
    public GetConversationMessagesInteract provideGetConversationMessagesInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IConversationRepository conversationRepository
    ){
        return new GetConversationMessagesInteract(threadExecutor, postExecutionThread, conversationRepository);
    }

    /**
     * Get Messages By Conversation Id Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @return
     */
    @Provides @PerActivity
    public GetMessagesByConversationIdInteract provideGetMessagesByConversationIdInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IConversationRepository conversationRepository
    ){
        return new GetMessagesByConversationIdInteract(threadExecutor, postExecutionThread, conversationRepository);
    }

}
