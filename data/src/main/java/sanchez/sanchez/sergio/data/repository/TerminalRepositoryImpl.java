package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDTO;
import sanchez.sanchez.sergio.data.net.services.ITerminalService;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;
import sanchez.sanchez.sergio.domain.repository.ITerminalRepository;

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
     *
     * @param terminalService
     * @param terminalDataMapper
     */
    public TerminalRepositoryImpl(final ITerminalService terminalService,
                                  final AbstractDataMapper<TerminalDTO, TerminalEntity> terminalDataMapper) {
        this.terminalService = terminalService;
        this.terminalDataMapper = terminalDataMapper;
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
}
