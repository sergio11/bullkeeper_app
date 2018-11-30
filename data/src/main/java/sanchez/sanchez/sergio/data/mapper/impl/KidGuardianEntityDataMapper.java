package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.GuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidGuardianDTO;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.models.GuardianRolesEnum;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;

/**
 * Kid Guardian Entity
 */
public final class KidGuardianEntityDataMapper extends AbstractDataMapper<KidGuardianDTO, KidGuardianEntity> {

    /**
     * Kid Entity Data Mapper
     */
    private final AbstractDataMapper<KidDTO, KidEntity> kidEntityAbstractDataMapper;

    /**
     * Guardian Data Mapper
     */
    private final AbstractDataMapper<GuardianDTO, GuardianEntity> guardianEntityAbstractDataMapper;

    /**
     *
     * @param kidEntityAbstractDataMapper
     * @param guardianEntityAbstractDataMapper
     */
    public KidGuardianEntityDataMapper(
            final AbstractDataMapper<KidDTO, KidEntity> kidEntityAbstractDataMapper,
            final AbstractDataMapper<GuardianDTO, GuardianEntity> guardianEntityAbstractDataMapper) {
        this.kidEntityAbstractDataMapper = kidEntityAbstractDataMapper;
        this.guardianEntityAbstractDataMapper = guardianEntityAbstractDataMapper;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public KidGuardianEntity transform(final KidGuardianDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final KidGuardianEntity kidGuardianEntity = new KidGuardianEntity();
        kidGuardianEntity.setIdentity(originModel.getIdentity());
        kidGuardianEntity.setConfirmed(originModel.isConfirmed());
        kidGuardianEntity.setGuardian(guardianEntityAbstractDataMapper
                .transform(originModel.getGuardian()));
        kidGuardianEntity.setKid(kidEntityAbstractDataMapper
                .transform(originModel.getKid()));
        kidGuardianEntity.setRequestAt(null);
        try {
            kidGuardianEntity.setRole(GuardianRolesEnum.valueOf(originModel.getRole()));
        } catch(final Exception ex) {
            kidGuardianEntity.setRole(null);
        }
        return kidGuardianEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public KidGuardianDTO transformInverse(KidGuardianEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final KidGuardianDTO kidGuardianDTO = new KidGuardianDTO();
        kidGuardianDTO.setIdentity(originModel.getIdentity());
        kidGuardianDTO.setConfirmed(originModel.isConfirmed());
        kidGuardianDTO.setRole(originModel.getRole().name());
        kidGuardianDTO.setGuardian(guardianEntityAbstractDataMapper
                .transformInverse(originModel.getGuardian()));
        kidGuardianDTO.setKid(kidEntityAbstractDataMapper
                .transformInverse(originModel.getKid()));
        kidGuardianDTO.setRequestAt(null);
        return kidGuardianDTO;
    }
}
