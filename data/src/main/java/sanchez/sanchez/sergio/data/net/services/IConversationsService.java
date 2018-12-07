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
 * Conversation Service
 * DELETE /api/v1/conversations/self/{kid} DELETE_CONVERSATION
 * GET /api/v1/conversations/self/{kid} GET_CONVERSATION_DETAIL
 * DELETE /api/v1/conversations/self/{kid}/messages DELETE_CONVERSATION_MESSAGES
 * GET /api/v1/conversations/self/{kid}/messages GET_CONVERSATION_MESSAGES
 * POST /api/v1/conversations/self/{kid}/messages ADD_MESSAGE
 * DELETE /api/v1/conversations/{id} DELETE_CONVERSATION_BY_ID
 * GET /api/v1/conversations/{id} GET_CONVERSATION_BY_ID
 * DELETE /api/v1/conversations/{id}/messages DELETE_CONVERSATION_MESSAGES
 * GET /api/v1/conversations/{id}/messages GET_CONVERSATION_MESSAGES
 * POST /api/v1/conversations/{id}/messages ADD_MESSAGE
 */
public interface IConversationsService {

    /**
     * Delete Conversation
     * @param kid
     * @return
     */
    @DELETE("conversations/self/{kid}")
    Observable<APIResponse<String>> deleteConversation(@Path("kid") final String kid);


    /**
     * Get Conversation
     * @param kid
     * @return
     */
    @GET("conversations/self/{kid}")
    Observable<APIResponse<ConversationDTO>> getConversation(@Path("kid") final String kid);

    /**
     * Delete Conversation Messages
     * @param kid
     * @return
     */
    @DELETE("conversations/self/{kid}/messages")
    Observable<APIResponse<String>> deleteConversationMessages(@Path("kid") final String kid,
                                                               @Query("ids") final List<String> messageIds);

    /**
     * Get Conversation Messages
     * @param kid
     * @return
     */
    @GET("conversations/self/{kid}/messages")
    Observable<APIResponse<List<MessageDTO>>> getConversationMessages(@Path("kid") final String kid);

    /**
     * Delete Conversation By Id
     * @param id
     * @return
     */
    @DELETE("conversations/{id}")
    Observable<APIResponse<String>> deleteConversationById(@Path("id") final String id);

    /**
     * Get Conversation By Id
     * @param id
     * @return
     */
    @GET("conversations/{id}")
    Observable<APIResponse<ConversationDTO>> getConversationById(@Path("id") final String id);

    /**
     * Delete Messages by conversation id
     * @param id
     * @return
     */
    @DELETE("conversations/{id}/messages")
    Observable<APIResponse<String>> deleteMessagesByConversationId(
            @Path("id") final String id,
            @Query("ids") final List<String> messageIds);

    /**
     * Get Messages by conversation id
     * @param id
     * @return
     */
    @GET("conversations/{id}/messages")
    Observable<APIResponse<List<MessageDTO>>> getMessagesByConversationId(@Path("id") final String id);

    /**
     * Add Message
     * @param kid
     * @param message
     * @return
     */
    @POST("conversations/self/{kid}/messages")
    Observable<APIResponse<MessageDTO>> addMessage(@Path("kid") final String kid,
                                                   @Body final AddMessageDTO message);

    /**
     * Add Message By Conversation Id
     * @param kid
     * @param messageDTO
     * @return
     */
    @POST("conversations/{id}/messages")
    Observable<APIResponse<MessageDTO>> addMessageByConversationId(@Path("id") final String kid,
                                                                   @Body final AddMessageDTO messageDTO);

}
