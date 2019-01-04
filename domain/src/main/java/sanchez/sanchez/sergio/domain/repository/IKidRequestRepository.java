package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;

/**
 * Kid Request Repository
 */
public interface IKidRequestRepository {


    /**
     * Get All Request For Kid
     * @param kid
     * @return
     */
    Observable<List<KidRequestEntity>> getAllRequestForKid(final String kid);

    /**
     * Get Kid Request Detail
     * @param kid
     * @param id
     * @return
     */
    Observable<KidRequestEntity> getKidRequestDetail(
            final String kid, final String id
    );

    /**
     * Delete Kid Request
     * @param kid
     * @param id
     * @return
     */
    Observable<String> deleteKidRequest(final String kid, final String id);

    /**
     * Delete All Request For Kid
     * @param kid
     * @return
     */
    Observable<String> deleteAllRequestForKid(final String kid);

    /**
     * Delete Request For Kid
     * @param kid
     * @param ids
     * @return
     */
    Observable<String> deleteRequestForKid(final String kid, final List<String> ids);
}
