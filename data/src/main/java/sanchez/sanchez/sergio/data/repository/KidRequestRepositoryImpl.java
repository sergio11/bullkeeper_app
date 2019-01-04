package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.KidRequestDTO;
import sanchez.sanchez.sergio.data.net.services.IKidRequestService;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;
import sanchez.sanchez.sergio.domain.repository.IKidRequestRepository;

/**
 * Kid Request Repository Impl
 */
public final class KidRequestRepositoryImpl implements IKidRequestRepository {

    /**
     * Kid Request service
     */
    private final IKidRequestService kidRequestService;

    /**
     * Kid Request Entity Mapper
     */
    private final AbstractDataMapper<KidRequestDTO, KidRequestEntity> kidRequestEntityAbstractDataMapper;

    /**
     *
     * @param kidRequestService
     * @param kidRequestEntityAbstractDataMapper
     */
    public KidRequestRepositoryImpl(
            final IKidRequestService kidRequestService,
            final AbstractDataMapper<KidRequestDTO, KidRequestEntity> kidRequestEntityAbstractDataMapper) {
        this.kidRequestService = kidRequestService;
        this.kidRequestEntityAbstractDataMapper = kidRequestEntityAbstractDataMapper;
    }

    /**
     * Get All Request For Kid
     * @param kid
     * @return
     */
    @Override
    public Observable<List<KidRequestEntity>> getAllRequestForKid(final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        return kidRequestService.getAllRequestForKid(kid)
                .map(listAPIResponse -> listAPIResponse != null
                        && listAPIResponse.getData() != null ? listAPIResponse.getData(): null)
                .map(kidRequestEntityAbstractDataMapper::transform);

    }

    /**
     * Get Kid Request Detail
     * @param kid
     * @param id
     * @return
     */
    @Override
    public Observable<KidRequestEntity> getKidRequestDetail(final String kid, final String id) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(id, "id can not be null");
        Preconditions.checkState(!id.isEmpty(), "id can not be empty");

        return kidRequestService.getKidRequestDetail(kid, id)
                .map(listAPIResponse -> listAPIResponse != null
                        && listAPIResponse.getData() != null ? listAPIResponse.getData(): null)
                .map(kidRequestEntityAbstractDataMapper::transform);
    }

    /**
     * Delete Kid Request
     * @param kid
     * @param id
     * @return
     */
    @Override
    public Observable<String> deleteKidRequest(final String kid, final String id) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(id, "id can not be null");
        Preconditions.checkState(!id.isEmpty(), "id can not be empty");


        return kidRequestService.deleteKidRequest(kid, id)
                .map(stringAPIResponse -> stringAPIResponse != null
                        && stringAPIResponse.getData() != null ? stringAPIResponse.getData(): null);
    }

    /**
     * Delete All Request For Kid
     * @param kid
     * @return
     */
    @Override
    public Observable<String> deleteAllRequestForKid(final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        return kidRequestService.deleteAllRequestForKid(kid)
                .map(stringAPIResponse -> stringAPIResponse != null
                        && stringAPIResponse.getData() != null ? stringAPIResponse.getData(): null);

    }

    /**
     * Delete Request For Kid
     * @param kid
     * @param ids
     * @return
     */
    @Override
    public Observable<String> deleteRequestForKid(final  String kid, final List<String> ids) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(ids, "Ids can not be null");

        return kidRequestService.deleteRequestForKid(kid, ids)
                .map(stringAPIResponse -> stringAPIResponse != null
                        && stringAPIResponse.getData() != null ? stringAPIResponse.getData(): null);
    }
}
