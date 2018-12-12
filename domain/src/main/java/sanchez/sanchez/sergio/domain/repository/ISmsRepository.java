package sanchez.sanchez.sergio.domain.repository;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.SmsEntity;

/**
 * SMS Repository
 */
public interface ISmsRepository {

    /**
     * Get SMS List
     * @param kid
     * @param terminal
     * @return
     */
    Observable<List<SmsEntity>> getSmsList(final String kid, final String terminal);

    /**
     * Get Sms Detail
     * @param kid
     * @param terminal
     * @param sms
     * @return
     */
    Observable<SmsEntity> getSmsDetail(final String kid, final String terminal, final String sms);
}
