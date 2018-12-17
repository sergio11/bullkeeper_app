package sanchez.sanchez.sergio.domain.repository;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.CallDetailEntity;

/**
 * Call Repository
 */
public interface ICallsRepository {

    /**
     * Get Call List
     * @param kid
     * @param terminal
     * @return
     */
    Observable<List<CallDetailEntity>> getCallList(final String kid, final String terminal);

    /**
     * Get Call Detail
     * @param kid
     * @param terminal
     * @param call
     * @return
     */
    Observable<CallDetailEntity> getCallDetail(final String kid, final String terminal, final String call);
}
