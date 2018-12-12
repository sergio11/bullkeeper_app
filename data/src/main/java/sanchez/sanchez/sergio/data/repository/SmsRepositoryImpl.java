package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.SmsDTO;
import sanchez.sanchez.sergio.data.net.services.ISmsService;
import sanchez.sanchez.sergio.domain.models.SmsEntity;
import sanchez.sanchez.sergio.domain.repository.ISmsRepository;

/**
 * Sms Repository
 */
public final class SmsRepositoryImpl implements ISmsRepository {

    /**
     * Sms Service
     */
    private final ISmsService smsService;

    /**
     * SMS Entity Data Mapper
     */
    private final AbstractDataMapper<SmsDTO, SmsEntity> smsEntityDataMapper;

    /**
     * @param smsService
     * @param smsEntityDataMapper
     */
    public SmsRepositoryImpl(
            final ISmsService smsService,
            final AbstractDataMapper<SmsDTO, SmsEntity> smsEntityDataMapper) {
        this.smsService = smsService;
        this.smsEntityDataMapper = smsEntityDataMapper;
    }

    /**
     * Get SMS List
     * @param kid
     * @param terminal
     * @return
     */
    @Override
    public Observable<List<SmsEntity>> getSmsList(final String kid, final String terminal) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");

        return smsService.getSmsList(kid, terminal)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null)
                .map(smsEntityDataMapper::transform);
    }

    /**
     * Get SMS Detail
     * @param kid
     * @param terminal
     * @param sms
     * @return
     */
    @Override
    public Observable<SmsEntity> getSmsDetail(final String kid, final String terminal, final String sms) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(sms, "SMS can not be null");
        Preconditions.checkState(!sms.isEmpty(), "SMS can not be empty");

        return smsService.getSmsDetail(kid, terminal, sms)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null)
                .map(smsEntityDataMapper::transform);
    }
}
