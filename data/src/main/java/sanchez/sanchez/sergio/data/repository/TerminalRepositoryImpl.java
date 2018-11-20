package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDTO;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDetailDTO;
import sanchez.sanchez.sergio.data.net.services.ITerminalService;
import sanchez.sanchez.sergio.domain.models.TerminalDetailEntity;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;
import sanchez.sanchez.sergio.domain.repository.ITerminalRepository;
import timber.log.Timber;

/**
 * Terminal Repository Impl
 */
public final class TerminalRepositoryImpl implements ITerminalRepository {

    /**
     * Terminal Service
     */
    private final ITerminalService terminalService;

    /**
     * Terminal Data Mapper
     */
    private final AbstractDataMapper<TerminalDTO, TerminalEntity> terminalDataMapper;

    /**
     * Terminal Detail Data Mapper
     */
    private final AbstractDataMapper<TerminalDetailDTO, TerminalDetailEntity> terminalDetailDataMapper;

    /**
     *
     * @param terminalService
     * @param terminalDataMapper
     * @param terminalDetailDataMapper
     */
    public TerminalRepositoryImpl(final ITerminalService terminalService,
                                  final AbstractDataMapper<TerminalDTO, TerminalEntity> terminalDataMapper,
                                  final AbstractDataMapper<TerminalDetailDTO, TerminalDetailEntity> terminalDetailDataMapper) {
        this.terminalService = terminalService;
        this.terminalDataMapper = terminalDataMapper;
        this.terminalDetailDataMapper = terminalDetailDataMapper;
    }

    /**
     * Get Monitored Terminals
     * @param childId
     * @return
     */
    @Override
    public Observable<List<TerminalEntity>> getMonitoredTerminals(String childId) {
        Preconditions.checkNotNull(childId, "Child id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child id can not be empty");

        return terminalService.getMonitoredTerminals(childId)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null)
                .map(terminalDataMapper::transform);

    }

    /**
     * Get Terminal Detail
     * @param childId
     * @param terminalId
     * @return
     */
    @Override
    public Observable<TerminalDetailEntity> getTerminalDetail(String childId, String terminalId) {
        Preconditions.checkNotNull(childId, "Child id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(terminalId, "Terminal Id can not be null");
        Preconditions.checkState(!terminalId.isEmpty(), "Terminal id can not be empty");

        return terminalService.getTerminalDetail(childId, terminalId)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(terminalDetailDataMapper::transform);
    }

    /**
     * Delete Terminal
     * @param childId
     * @param terminalId
     * @return
     */
    @Override
    public Observable<String> deleteTerminal(final String childId, final String terminalId) {
        Preconditions.checkNotNull(childId, "Child id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(terminalId, "Terminal Id can not be null");
        Preconditions.checkState(!terminalId.isEmpty(), "Terminal id can not be empty");

        return terminalService.deleteTerminal(childId, terminalId)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null);
    }
}
