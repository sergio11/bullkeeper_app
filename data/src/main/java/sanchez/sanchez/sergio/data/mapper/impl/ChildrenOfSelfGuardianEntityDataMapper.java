package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ChildrenOfSelfGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.SupervisedChildrenDTO;
import sanchez.sanchez.sergio.domain.models.ChildrenOfSelfGuardianEntity;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;

/**
 * Children Of Self Guardian Entity Data Mapper
 */
public class ChildrenOfSelfGuardianEntityDataMapper
        extends AbstractDataMapper<ChildrenOfSelfGuardianDTO, ChildrenOfSelfGuardianEntity> {

    /**
     * Kid Data Mapper
     */
    private final AbstractDataMapper<SupervisedChildrenDTO, SupervisedChildrenEntity> supervisedChildrenEntityDataMapper;

    /**
     *
     * @param supervisedChildrenEntityDataMapper
     */
    public ChildrenOfSelfGuardianEntityDataMapper(final AbstractDataMapper<SupervisedChildrenDTO, SupervisedChildrenEntity> supervisedChildrenEntityDataMapper) {
        this.supervisedChildrenEntityDataMapper = supervisedChildrenEntityDataMapper;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public ChildrenOfSelfGuardianEntity transform(final ChildrenOfSelfGuardianDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can no be null");

        final ChildrenOfSelfGuardianEntity childrenOfSelfGuardianEntity = new ChildrenOfSelfGuardianEntity();
        childrenOfSelfGuardianEntity.setConfirmed(originModel.getConfirmed());
        childrenOfSelfGuardianEntity.setNoConfirmed(originModel.getNoConfirmed());
        childrenOfSelfGuardianEntity.setTotal(originModel.getTotal());
        childrenOfSelfGuardianEntity
                .setSupervisedChildrenEntities(supervisedChildrenEntityDataMapper.transform(originModel.getSupervisedChildrenList()));

        return childrenOfSelfGuardianEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public ChildrenOfSelfGuardianDTO transformInverse(final ChildrenOfSelfGuardianEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final ChildrenOfSelfGuardianDTO childrenOfSelfGuardianDTO = new ChildrenOfSelfGuardianDTO();
        childrenOfSelfGuardianDTO.setConfirmed(originModel.getConfirmed());
        childrenOfSelfGuardianDTO.setNoConfirmed(originModel.getNoConfirmed());
        childrenOfSelfGuardianDTO.setTotal(originModel.getTotal());
        childrenOfSelfGuardianDTO
                .setSupervisedChildrenList(supervisedChildrenEntityDataMapper.transformInverse(originModel.getSupervisedChildrenEntities()));

        return childrenOfSelfGuardianDTO;
    }
}
