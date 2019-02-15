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
     * Get Conversation By Id
     * @param id
     * @return
     */
    @Override
    public Observable<ConversationEntity> getConversationById(final String id) {
        Preconditions.checkNotNull(id, "Id can not be null");

        return conversationsService.getConversationById(id)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(conversationEntityAbstractDataMapper::transform);
    }

    /**
     * Delete Conversation By Id
     * @param id
     * @return
     */
    @Override
    public Observable<String> deleteConversationById(final String id) {
        Preconditions.checkNotNull(id, "Id can not be null");

        return conversationsService.deleteConversationById(id)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null);
    }

    /**
     * Get Conversation Messages
     * @param id
     * @return
     */
    @Override
    public Observable<List<MessageEntity>> getConversationMessages(final String id) {
        Preconditions.checkNotNull(id, "Id can not be null");

        return conversationsService.getConversationMessages(id)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(messageEntityAbstractDataMapper::transform);
    }

    /**
     * Delete Conversation
     * @param id
     * @return
     */
    @Override
    public Observable<String> deleteConversationMessages(final String id) {
        Preconditions.checkNotNull(id, "Id can not be null");

        return conversationsService.deleteConversation(id)
                .map(response -> response != null
                        && response.getData() != null ?
                        response.getData(): null);
    }

    /**
     * Delete Conversation Messages
     * @param id
     * @param messageIds
     * @return
     */
    @Override
    public Observable<String> deleteConversationMessages(final String id, final List<String> messageIds) {
        Preconditions.checkNotNull(id, "Id can not be null");
        Preconditions.checkNotNull(messageIds, "Message Ids can not be null");

        return conversationsService.deleteConversationMessage(id, messageIds)
                .map(response -> response != null
                        && response.getData() != null ?
                        response.getData(): null);
    }

    /**
     * Add Message
     * @param conversation
     * @param from
     * @param to
     * @param text
     * @return
     */
    @Override
    public Observable<MessageEntity> addMessage(final String conversation, final String from,
                                                final String to, final String text) {
        Preconditions.checkNotNull(conversation, "Conversaton can not be null");
        Preconditions.checkNotNull(from, "From can not be null");
        Preconditions.checkNotNull(to, "To can not be null");
        Preconditions.checkNotNull(text, "Text can not be null");

        return conversationsService.addMessage(conversation,
                new AddMessageDTO(conversation, text, from, to))
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(messageEntityAbstractDataMapper::transform);

    }

    /**
     * Get Conversations For Self User
     * @return
     */
    @Override
    public Observable<List<ConversationEntity>> getConversationsForSelfUser() {

        return conversationsService.getConversationsForSelfUser()
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(conversationEntityAbstractDataMapper::transform);
    }

    /**
     * Delete All Conversations For Self User
     * @return
     */
    @Override
    public Observable<String> deleteAllConversationsForSelfUser() {
        return conversationsService.deleteConversationsForSelfUser()
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null);
    }

    /**
     * Get Conversation For Members
     * @param memberOne
     * @param memberTwo
     * @return
     */
    @Override
    public Observable<ConversationEntity> getConversationForMembers(
            final String memberOne, final String memberTwo) {
        Preconditions.checkNotNull(memberOne, "Member One can not be null");
        Preconditions.checkNotNull(memberTwo, "Member Two can not be null");

        return conversationsService.getConversationForMembers(memberOne, memberTwo)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(conversationEntityAbstractDataMapper::transform);
    }

    /**
     * Create Conversation
     * @param memberOne
     * @param memberTwo
     * @return
     */
    @Override
    public Observable<ConversationEntity> createConversation(
            final String memberOne, final String memberTwo) {
        Preconditions.checkNotNull(memberOne, "Member one can not be null");
        Preconditions.checkNotNull(memberTwo, "Member Two can not be null");

        return conversationsService.createConversation(memberOne, memberTwo)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(conversationEntityAbstractDataMapper::transform);
    }

    /**
     * Delete Conversation Form Members
     * @param memberOne
     * @param memberTwo
     * @return
     */
    @Override
    public Observable<String> deleteConversationForMembers(final String memberOne, final String memberTwo) {
        Preconditions.checkNotNull(memberOne, "Member One can not be null");
        Preconditions.checkNotNull(memberTwo, "Member Two can not be null");

        return conversationsService.deleteConversationForMembers(memberOne, memberTwo)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null);


    }

    /**
     * Get Conversation Message For Members
     * @param memberOne
     * @param memberTwo
     * @return
     */
    @Override
    public Observable<List<MessageEntity>> getConversationMessagesForMembers(
            final String memberOne, final String memberTwo) {
        Preconditions.checkNotNull(memberOne, "Member One can not be null");
        Preconditions.checkNotNull(memberTwo, "Member Two can not be null");

        return conversationsService.getConversationMessagesForMembers(memberOne, memberTwo)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(messageEntityAbstractDataMapper::transform);

    }

    /**
     * Delete Conversation Message For Members
     * @param memberOne
     * @param memberTwo
     * @return
     */
    @Override
    public Observable<String> deleteConversationMessagesForMembers(
            final String memberOne, final String memberTwo) {
        Preconditions.checkNotNull(memberOne, "Member one can not be null");
        Preconditions.checkNotNull(memberTwo, "Member Two can not be null");

        return conversationsService.deleteConversationForMembers(
                memberOne, memberTwo)
                .map(response -> response != null && response.getData() != null
                    ? response.getData(): null);
    }

    /**
     * Add Message
     * @param memberOne
     * @param memberTwo
     * @param conversation
     * @param from
     * @param to
     * @param text
     * @return
     */
    @Override
    public Observable<MessageEntity> addMessage(
            final String memberOne,
            final String memberTwo,
            final String conversation,
            final String from,
            final String to,
            final String text) {
        Preconditions.checkNotNull(memberOne, "Member One can not be null");
        Preconditions.checkNotNull(memberTwo, "Member Two can not be null");
        Preconditions.checkNotNull(conversation, "Conversation can not be null");
        Preconditions.checkNotNull(from, "From can not be null");
        Preconditions.checkNotNull(to, "To can not be null");
        Preconditions.checkNotNull(text, "Text can not be null");

        return conversationsService.addMessage(memberOne, memberTwo,
                new AddMessageDTO(conversation, text, from, to))
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(messageEntityAbstractDataMapper::transform);


    }
}
