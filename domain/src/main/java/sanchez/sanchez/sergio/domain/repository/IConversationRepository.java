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
     * Get Conversation By Id
     * @param id
     * @return
     */
    Observable<ConversationEntity> getConversationById(final String id);


    /**
     * Delete Conversation By Id
     * @param id
     * @return
     */
    Observable<String> deleteConversationById(final String id);


    /**
     * Get Conversation Messages
     * @param id
     * @return
     */
    Observable<List<MessageEntity>> getConversationMessages(final String id);


    /**
     * Delete Conversation Messages
     * @param id
     * @return
     */
    Observable<String> deleteConversationMessages(final String id);

    /**
     * Delete Conversation Messages
     * @param id
     * @param messageIds
     * @return
     */
    Observable<String> deleteConversationMessages(final String id, final List<String> messageIds);

    /**
     * Add Message
     * @param conversation
     * @param from
     * @param to
     * @param text
     * @return
     */
    Observable<MessageEntity> addMessage(
            final String conversation, final String from,
            final String to, final String text);


    /**
     * Get Conversations For Self User
     * @return
     */
    Observable<List<ConversationEntity>> getConversationsForSelfUser();

    /**
     * Get Conversation For Members
     * @param memberOne
     * @param memberTwo
     * @return
     */
    Observable<ConversationEntity> getConversationForMembers(
            final String memberOne,
            final String memberTwo
    );

    /**
     * Create Conversation
     * @param memberOne
     * @param memberTwo
     * @return
     */
    Observable<ConversationEntity> createConversation(
            final String memberOne,
            final String memberTwo
    );

    /**
     * Delete Conversation For Members
     * @param memberOne
     * @param memberTwo
     * @return
     */
    Observable<String> deleteConversationForMembers(
            final String memberOne,
            final String memberTwo
    );

    /**
     * Get Conversation Messages For Members
     * @return
     */
    Observable<List<MessageEntity>> getConversationMessagesForMembers(
            final String memberOne,
            final String memberTwo
    );

    /**
     * Delete Conversation Messages For Members
     * @param memberOne
     * @param memberTwo
     * @return
     */
    Observable<String> deleteConversationMessagesForMembers(
            final String memberOne,
            final String memberTwo
    );

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
    Observable<MessageEntity> addMessage(
            final String memberOne, final String memberTwo,
            final String conversation, final String from,
            final String to, final String text
    );

}
