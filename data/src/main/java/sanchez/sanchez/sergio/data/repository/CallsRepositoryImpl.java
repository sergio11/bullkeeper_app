package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.CallDetailDTO;
import sanchez.sanchez.sergio.data.net.services.ICallsService;
import sanchez.sanchez.sergio.domain.models.CallDetailEntity;
import sanchez.sanchez.sergio.domain.repository.ICallsRepository;

/**
 * Calls Repository Impl
 */
public final class CallsRepositoryImpl implements ICallsRepository {

    /**
     * Call Service
     */
    private final ICallsService callsService;

    /**
     * Call Detail Entity Mapper
     */
    private final AbstractDataMapper<CallDetailDTO, CallDetailEntity>
            callDetailEntityAbstractDataMapper;


    /**
     *
     * @param callsService
     * @param callDetailEntityAbstractDataMapper
     */
    public CallsRepositoryImpl(
            final ICallsService callsService,
            final AbstractDataMapper<CallDetailDTO, CallDetailEntity>
                    callDetailEntityAbstractDataMapper) {
        this.callsService = callsService;
        this.callDetailEntityAbstractDataMapper = callDetailEntityAbstractDataMapper;
    }

    /**
     * Get Call List
     * @param kid
     * @param terminal
     * @return
     */
    @Override
    public Observable<List<CallDetailEntity>> getCallList(final String kid, final String terminal) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");

        return callsService.getCallsList(kid, terminal)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null)
                .map(callDetailEntityAbstractDataMapper::transform);
    }


    /**
     * Get Call Detail
     * @param kid
     * @param terminal
     * @param call
     * @return
     */
    @Override
    public Observable<CallDetailEntity> getCallDetail(final String kid, final String terminal, final String call) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(call, "Call can not be null");
        Preconditions.checkState(!call.isEmpty(), "Call can not be empty");

        return callsService.getCallDetail(kid, terminal, call)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null)
                .map(callDetailEntityAbstractDataMapper::transform);
    }
}
