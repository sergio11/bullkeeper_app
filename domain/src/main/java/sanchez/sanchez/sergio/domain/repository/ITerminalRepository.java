package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;

/**
 * Terminal Repository
 */
public interface ITerminalRepository {

    /**
     * Get Monitored Terminals
     * @param childId
     * @return
     */
    Observable<List<TerminalEntity>> getMonitoredTerminals(final String childId);


}
