package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.AddMessageDTO;
import sanchez.sanchez.sergio.data.net.models.response.ConversationDTO;
import sanchez.sanchez.sergio.data.net.models.response.MessageDTO;
import sanchez.sanchez.sergio.data.net.services.IConversationsService;
import sanchez.sanchez.sergio.domain.models.ConversationEntity;
import sanchez.sanchez.sergio.domain.models.MessageEntity;
import sanchez.sanchez.sergio.domain.repository.IConversationRepository;

/**
 * Conversation Repository Impl
 */
public final class ConversationRepositoryImpl implements IConversationRepository {

    /**
     * Conversation Entity Mapper
     */
    private final AbstractDataMapper<ConversationDTO, ConversationEntity> conversationEntityAbstractDataMapper;

    /**
     * Message Entity Mapper
     */
    private final AbstractDataMapper<MessageDTO, MessageEntity> messageEntityAbstractDataMapper;

    /**
     * Conversation Service
     */
    private final IConversationsService conversationsService;

    /**
     *
     * @param conversationEntityAbstractDataMapper
     * @param messageEntityAbstractDataMapper
     * @param conversationsService
     */
    public ConversationRepositoryImpl(final AbstractDataMapper<ConversationDTO, ConversationEntity> conversationEntityAbstractDataMapper,
                                      final AbstractDataMapper<MessageDTO, MessageEntity> messageEntityAbstractDataMapper,
                                      final IConversationsService conversationsService) {
        this.conversationEntityAbstractDataMapper = conversationEntityAbstractDataMapper;
        this.messageEntityAbstractDataMapper = messageEntityAbstractDataMapper;
        this.conversationsService = conversationsService;
    }

    /**
     * Delete Conversation
     * @param kid
     * @return
     */
    @Override
    public Observable<String> deleteConversation(final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        // Delete Conversation By Id
        return conversationsService.deleteConversation(kid)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null);
    }

    /**
     * Get Conversation
     * @param kid
     * @return
     */
    @Override
    public Observable<ConversationEntity> getConversation(final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        return conversationsService.getConversation(kid)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null)
                .map(conversationEntityAbstractDataMapper::transform);
    }

    /**
     * Delete Conversation Messages
     * @param kid
     * @return
     */
    @Override
    public Observable<String> deleteConversationMessages(final String kid, final List<String> messageIds) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(messageIds, "Message Ids can not be empty");

        return conversationsService.deleteConversationMessages(kid, messageIds)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null);
    }

    /**
     * Get Conversation Message
     * @param kid
     * @return
     */
    @Override
    public Observable<List<MessageEntity>> getConversationMessages(final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        return conversationsService.getConversationMessages(kid)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null)
                .map(messageEntityAbstractDataMapper::transform);
    }

    /**
     * Delete Conversation By id
     * @param id
     * @return
     */
    @Override
    public Observable<String> deleteConversationById(final String id) {
        Preconditions.checkNotNull(id, "Id can not be null");
        Preconditions.checkState(!id.isEmpty(), "Id can not be null");

        return conversationsService.deleteConversationById(id)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null);
    }

    /**
     * Get Conversation By Id
     * @param id
     * @return
     */
    @Override
    public Observable<ConversationEntity> getConversationById(final String id) {
        Preconditions.checkNotNull(id, "Id can not be null");
        Preconditions.checkState(!id.isEmpty(), "Id can not be null");

        return conversationsService.getConversationById(id)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(conversationEntityAbstractDataMapper::transform);
    }

    /**
     * Delete Messages By Conversation Id
     * @param id
     * @return
     */
    @Override
    public Observable<String> deleteMessagesByConversationId(final String id, final List<String> messageIds) {
        Preconditions.checkNotNull(id, "Id can not be null");
        Preconditions.checkState(!id.isEmpty(), "Id can not be null");
        Preconditions.checkNotNull(messageIds, "Message ids can not be null");

        return conversationsService.deleteMessagesByConversationId(id, messageIds)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null);
    }

    /**
     * Get Messages By Conversation Id
     * @param id
     * @return
     */
    @Override
    public Observable<List<MessageEntity>> getMessagesByConversationId(String id) {
        Preconditions.checkNotNull(id, "Id can not be null");
        Preconditions.checkState(!id.isEmpty(), "Id can not be null");
        return conversationsService.getMessagesByConversationId(id)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null)
                .map(messageEntityAbstractDataMapper::transform);
    }

    /**
     * Add Message
     * @param kid
     * @param from
     * @param to
     * @param text
     * @return
     */
    @Override
    public Observable<MessageEntity> addMessage(String kid, String from, String to, String text) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(from, "From can not be null");
        Preconditions.checkState(!from.isEmpty(), "From can not be empty");
        Preconditions.checkNotNull(to, "To can not be null");
        Preconditions.checkState(!to.isEmpty(), "To can not be empty");
        Preconditions.checkNotNull(text, "Text can not be null");
        Preconditions.checkState(!text.isEmpty(), "Text can not be empty");

        return conversationsService.addMessage(kid, new AddMessageDTO(text, from, to))
                    .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                    .map(messageEntityAbstractDataMapper::transform);
    }

    /**
     * Add Message By Conversation Id
     * @param conversationId
     * @param from
     * @param to
     * @param text
     * @return
     */
    @Override
    public Observable<MessageEntity> addMessageByConversationId(String conversationId, String from, String to, String text) {

        Preconditions.checkNotNull(conversationId, "Conversation Id can not be null");
        Preconditions.checkState(!conversationId.isEmpty(), "Conversation Id can not be empty");
        Preconditions.checkNotNull(from, "From can not be null");
        Preconditions.checkState(!from.isEmpty(), "From can not be empty");
        Preconditions.checkNotNull(to, "To can not be null");
        Preconditions.checkState(!to.isEmpty(), "To can not be empty");
        Preconditions.checkNotNull(text, "Text can not be null");
        Preconditions.checkState(!text.isEmpty(), "Text can not be empty");

        return conversationsService.addMessageByConversationId(conversationId, new AddMessageDTO(text, from, to))
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(messageEntityAbstractDataMapper::transform);
    }
}
