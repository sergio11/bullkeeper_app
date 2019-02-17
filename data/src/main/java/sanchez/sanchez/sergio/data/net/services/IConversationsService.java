package sanchez.sanchez.sergio.data.net.services;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sanchez.sanchez.sergio.data.net.models.request.AddMessageDTO;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.ConversationDTO;
import sanchez.sanchez.sergio.data.net.models.response.MessageDTO;

/**
 * GET -> /api/v1/conversations/{id}
 * DELETE -> /api/v1/conversations/{id}
 * GET -> /api/v1/conversations/{id}/messages
 * DELETE -> /api/v1/conversations/{id}/messages
 * POST -> /api/v1/conversations/{id}/messages
 * GET -> /api/v1/conversations/members/self
 * GET -> /api/v1/conversations/members/{member}
 * GET -> /api/v1/conversations/members/{memberOne}/{memberTwo}
 * POST -> /api/v1/conversations/members/{memberOne}/{memberTwo}
 * DELETE -> /api/v1/conversations/members/{memberOne}/{memberTwo}
 * GET -> /api/v1/conversations/members/{memberOne}/{memberTwo}/messages
 * DELETE -> /api/v1/conversations/members/{memberOne}/{memberTwo}/messages
 * POST -> /api/v1/conversations/members/{memberOne}/{memberTwo}/messages
 * POST -> /api/v1/conversations/members/{memberOne}/{memberTwo}/messages/viewed
 * POST -> /api/v1/conversations/{id}/messages/viewed
 */
public interface IConversationsService {

    /**
     * Get Conversation By Id
     * @param id
     * @return
     */
    @GET("conversations/{id}")
    Observable<APIResponse<ConversationDTO>> getConversationById(
            @Path("id") final String id);


    /**
     * Delete Conversation By Id
     * @param id
     * @return
     */
    @DELETE("conversations/{id}")
    Observable<APIResponse<String>> deleteConversationById(
            @Path("id") final String id);

    /**
     * Get Conversation Messages
     * @param id
     * @return
     */
    @GET("conversations/{id}/messages")
    Observable<APIResponse<List<MessageDTO>>> getConversationMessages(
            @Path("id") final String id);


    /**
     * Delete Conversation
     * @param id
     * @return
     */
    @DELETE("conversations/{id}/messages")
    Observable<APIResponse<String>> deleteConversation(
            @Path("id") final String id);

    /**
     * Delete Conversation Messages
     * @param id
     * @param messages
     * @return
     */
    @DELETE("conversations/{id}/messages")
    Observable<APIResponse<String>> deleteConversationMessage(
            @Path("id") final String id,
            @Query("messages") final List<String> messages
    );


    /**
     * Add Message
     * @param id
     * @param message
     * @return
     */
    @POST("conversations/{id}/messages")
    Observable<APIResponse<MessageDTO>> addMessage(
            @Path("id") final String id,
            @Body final AddMessageDTO message);


    /**
     * Get Conversations For Self User
     * @return
     */
    @GET("conversations/members/self")
    Observable<APIResponse<List<ConversationDTO>>> getConversationsForSelfUser();

    /**
     * Delete Conversations For Self User
     * @return
     */
    @DELETE("conversations/members/self")
    Observable<APIResponse<String>> deleteConversationsForSelfUser();


    /**
     * Get Conversation For Members
     * @param memberOne
     * @param memberTwo
     * @return
     */
    @GET("conversations/members/{memberOne}/{memberTwo}")
    Observable<APIResponse<ConversationDTO>> getConversationForMembers(
            @Path("memberOne") final String memberOne,
            @Path("memberTwo") final String memberTwo
    );

    /**
     * Create Conversation
     * @param memberOne
     * @param memberTwo
     * @return
     */
    @POST("conversations/members/{memberOne}/{memberTwo}")
    Observable<APIResponse<ConversationDTO>> createConversation(
            @Path("memberOne") final String memberOne,
            @Path("memberTwo") final String memberTwo
    );

    /**
     * Delete Conversatioin For Members
     * @param memberOne
     * @param memberTwo
     * @return
     */
    @DELETE("conversations/members/{memberOne}/{memberTwo}")
    Observable<APIResponse<String>> deleteConversationForMembers(
            @Path("memberOne") final String memberOne,
            @Path("memberTwo") final String memberTwo
    );

    /**
     * Get Conversation Messages For Members
     * @param memberOne
     * @param memberTwo
     * @return
     */
    @GET("conversations/members/{memberOne}/{memberTwo}/messages")
    Observable<APIResponse<List<MessageDTO>>> getConversationMessagesForMembers(
            @Path("memberOne") final String memberOne,
            @Path("memberTwo") final String memberTwo
    );

    /**
     * Delete Conversation Messages For Members
     * @param memberOne
     * @param memberTwo
     * @return
     */
    @DELETE("conversations/members/{memberOne}/{memberTwo}/messages")
    Observable<APIResponse<String>> deleteConversationMessagesForMembers(
            @Path("memberOne") final String memberOne,
            @Path("memberTwo") final String memberTwo
    );

    /**
     * Add Message DTO
     * @param memberOne
     * @param memberTwo
     * @param addMessageDTO
     * @return
     */
    @POST("conversations/members/{memberOne}/{memberTwo}/messages")
    Observable<APIResponse<MessageDTO>> addMessage(
            @Path("memberOne") final String memberOne,
            @Path("memberTwo") final String memberTwo,
            final AddMessageDTO addMessageDTO
    );

    /**
     * Set Messages As Viewed
     * @param memberOne
     * @param memberTwo
     * @param messageIds
     * @return
     */
    @POST("conversations/members/{memberOne}/{memberTwo}/messages/viewed")
    Observable<APIResponse<String>> setMessagesAsViewed(
            @Path("memberOne") final String memberOne,
            @Path("memberTwo") final String memberTwo,
            final List<String> messageIds
    );

    /**
     * Set Messages As Viewed
     * @param id
     * @param messageIds
     * @return
     */
    @POST("conversations/{id}/messages/viewed")
    Observable<APIResponse<String>> setMessagesAsViewed(
            @Path("id") final String id,
            final List<String> messageIds
    );
}
