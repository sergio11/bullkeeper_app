package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.KidDTO;
import sanchez.sanchez.sergio.data.net.models.response.SupervisedChildrenDTO;
import sanchez.sanchez.sergio.domain.models.GuardianRolesEnum;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;

/**
 * Supervised Children Entity Data Mapper
 */
public class SupervisedChildrenEntityDataMapper
        extends AbstractDataMapper<SupervisedChildrenDTO, SupervisedChildrenEntity> {

    /**
     * Kid Entity Data Mappers
     */
    private final AbstractDataMapper<KidDTO, KidEntity> kidEntityDataMapper;

    /**
     *
     * @param kidEntityDataMapper
     */
    public SupervisedChildrenEntityDataMapper(AbstractDataMapper<KidDTO, KidEntity> kidEntityDataMapper) {
        this.kidEntityDataMapper = kidEntityDataMapper;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public SupervisedChildrenEntity transform(SupervisedChildrenDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model");

        final SupervisedChildrenEntity supervisedChildrenEntity = new SupervisedChildrenEntity();
        supervisedChildrenEntity.setIdentity(originModel.getIdentity());
        supervisedChildrenEntity.setGuardianRolesEnum(GuardianRolesEnum.valueOf(originModel.getRole()));
        supervisedChildrenEntity.setKid(kidEntityDataMapper.transform(originModel.getKid()));
        supervisedChildrenEntity.setPendingMessageCount(originModel.getPendingMessageCount());
        return supervisedChildrenEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public SupervisedChildrenDTO transformInverse(SupervisedChildrenEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model");

        final SupervisedChildrenDTO supervisedChildrenDTO = new SupervisedChildrenDTO();
        supervisedChildrenDTO.setIdentity(originModel.getIdentity());
        supervisedChildrenDTO.setRole(originModel.getGuardianRolesEnum().name());
        supervisedChildrenDTO.setKid(kidEntityDataMapper.transformInverse(originModel.getKid()));
        supervisedChildrenDTO.setPendingMessageCount(originModel.getPendingMessageCount());
        return supervisedChildrenDTO;
    }
}
