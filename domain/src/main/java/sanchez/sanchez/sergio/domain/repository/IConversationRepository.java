package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.ConversationEntity;
import sanchez.sanchez.sergio.domain.models.MessageEntity;

/**
 * Conversation Repository
 */
public interface IConversationRepository {

    /**
     * Delete Conversation
     * @param kid
     * @return
     */
    Observable<String> deleteConversation(final String kid);

    /**
     * Get Conversation
     * @param kid
     * @return
     */
    Observable<ConversationEntity> getConversation(final String kid);

    /**
     * Delete Conversation Messages
     * @param kid
     * @return
     */
    Observable<String> deleteConversationMessages(final String kid);

    /**
     * Get Conversation Messages
     * @param kid
     * @return
     */
    Observable<List<MessageEntity>> getConversationMessages(final String kid);

    /**
     * Delete Conversation By Id
     * @param id
     * @return
     */
    Observable<String> deleteConversationById(final String id);

    /**
     * Get Conversation By Id
     * @param id
     * @return
     */
    Observable<ConversationEntity> getConversationById(final String id);

    /**
     * Delete Messages by conversation id
     * @param id
     * @return
     */
    Observable<String> deleteMessagesByConversationId(final String id);

    /**
     * Get Messages by conversation id
     * @param id
     * @return
     */
    Observable<List<MessageEntity>> getMessagesByConversationId(final String id);

    /**
     * Add Message
     * @param kid
     * @param from
     * @param to
     * @param text
     * @return
     */
    Observable<MessageEntity> addMessage(final String kid, final String from, final String to, final String text);

    /**
     * Add Message By Conversation Id
     * @param kid
     * @param from
     * @param to
     * @param text
     * @return
     */
    Observable<MessageEntity> addMessageByConversationId(final String kid, final String from, final String to, final String text);

}
