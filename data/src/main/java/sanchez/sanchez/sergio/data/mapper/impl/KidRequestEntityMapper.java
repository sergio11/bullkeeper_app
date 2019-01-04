package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.KidDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidRequestDTO;
import sanchez.sanchez.sergio.data.net.models.response.LocationDTO;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDTO;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;
import sanchez.sanchez.sergio.domain.models.LocationEntity;
import sanchez.sanchez.sergio.domain.models.RequestTypeEnum;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;

/**
 * Kid Request Entity Mapper
 */
public final class KidRequestEntityMapper extends AbstractDataMapper<KidRequestDTO, KidRequestEntity> {

    /**
     * Kid Entity Mapper
     */
    private final AbstractDataMapper<KidDTO, KidEntity> kidEntityAbstractDataMapper;

    /**
     * Terminal Entity Mapper
     */
    private final AbstractDataMapper<TerminalDTO, TerminalEntity> terminalEntityAbstractDataMapper;

    /**
     * Location Entity Mapper
     */
    private final AbstractDataMapper<LocationDTO, LocationEntity> locationEntityAbstractDataMapper;


    /**
     *
     * @param kidEntityAbstractDataMapper
     * @param terminalEntityAbstractDataMapper
     */
    public KidRequestEntityMapper(
            final AbstractDataMapper<KidDTO, KidEntity> kidEntityAbstractDataMapper,
            final AbstractDataMapper<TerminalDTO, TerminalEntity> terminalEntityAbstractDataMapper,
            final AbstractDataMapper<LocationDTO, LocationEntity> locationEntityAbstractDataMapper) {
        this.kidEntityAbstractDataMapper = kidEntityAbstractDataMapper;
        this.terminalEntityAbstractDataMapper = terminalEntityAbstractDataMapper;
        this.locationEntityAbstractDataMapper = locationEntityAbstractDataMapper;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public KidRequestEntity transform(final KidRequestDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final KidRequestEntity kidRequestEntity = new KidRequestEntity();
        kidRequestEntity.setIdentity(originModel.getIdentity());
        kidRequestEntity.setExpiredAt(originModel.getExpiredAt());
        kidRequestEntity.setRequestAt(originModel.getRequestAt());
        try {
            kidRequestEntity.setType(RequestTypeEnum.valueOf(originModel.getType()));
        } catch (final Exception ex) {
            kidRequestEntity.setType(null);
        }
        kidRequestEntity.setKid(kidEntityAbstractDataMapper
                .transform(originModel.getKid()));
        kidRequestEntity.setTerminal(terminalEntityAbstractDataMapper
                .transform(originModel.getTerminal()));
        kidRequestEntity.setLocation(locationEntityAbstractDataMapper
                .transform(originModel.getLocation()));
        return kidRequestEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public KidRequestDTO transformInverse(KidRequestEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final KidRequestDTO kidRequestDTO = new KidRequestDTO();
        kidRequestDTO.setIdentity(originModel.getIdentity());
        kidRequestDTO.setExpiredAt(originModel.getExpiredAt());
        kidRequestDTO.setRequestAt(originModel.getRequestAt());
        kidRequestDTO.setType(originModel.getType().name());
        kidRequestDTO.setKid(kidEntityAbstractDataMapper
                .transformInverse(originModel.getKid()));
        kidRequestDTO.setTerminal(terminalEntityAbstractDataMapper
                .transformInverse(originModel.getTerminal()));
        kidRequestDTO.setLocation(locationEntityAbstractDataMapper
                .transformInverse(originModel.getLocation()));
        return kidRequestDTO;

    }
}
