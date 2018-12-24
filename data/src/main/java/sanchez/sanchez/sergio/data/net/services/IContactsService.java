package sanchez.sanchez.sergio.data.net.services;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.ContactDTO;

/**
 * Contacts Service
 */
public interface IContactsService {

    /**
     * Get All Contacts From Terminal
     * @param kid
     * @return
     */
    @GET("children/{kid}/terminal/{terminal}/contacts")
    Observable<APIResponse<List<ContactDTO>>> getAllContacts(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal,
            final @Query("text") String query);


    /**
     * Get All Contacts From Terminal
     * @param kid
     * @return
     */
    @GET("children/{kid}/terminal/{terminal}/contacts")
    Observable<APIResponse<List<ContactDTO>>> getAllContacts(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal);


    /**
     * Get Contact Detail
     * @param kid
     * @param terminal
     * @param contact
     * @return
     */
    @GET("children/{kid}/terminal/{terminal}/contacts/{contact}")
    Observable<APIResponse<ContactDTO>> getContactDetail(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal,
            final @Path("contact") String contact
    );

    /**
     * Delete All Contacts From Terminal
     * @param kid
     * @param terminal
     * @return
     */
    @DELETE("children/{kid}/terminal/{terminal}/contacts")
    Observable<APIResponse<ContactDTO>> deleteAllContactsFromTerminal(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal
    );

    /**
     * Delete Single Contacts From Terminal
     * @param kid
     * @param terminal
     * @param contact
     * @return
     */
    @DELETE("children/{kid}/terminal/{terminal}/contacts/{contact}")
    Observable<APIResponse<ContactDTO>> deleteSingleContactsFromTerminal(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal,
            final @Path("contact") String contact
    );

}
