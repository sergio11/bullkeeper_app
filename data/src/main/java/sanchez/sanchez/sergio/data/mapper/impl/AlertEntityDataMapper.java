package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AlertDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidDTO;
import sanchez.sanchez.sergio.domain.models.AlertCategoryEnum;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import sanchez.sanchez.sergio.domain.models.KidEntity;

/**
 * Alert Entity Data Mapper
 */
public final class AlertEntityDataMapper extends AbstractDataMapper<AlertDTO, AlertEntity> {

    /**
     * Son Entity Data Mapper
     */
    private final AbstractDataMapper<KidDTO, KidEntity> sonEntityDataMapper;


    public AlertEntityDataMapper(final AbstractDataMapper<KidDTO, KidEntity> sonEntityDataMapper) {
        this.sonEntityDataMapper = sonEntityDataMapper;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public AlertEntity transform(final AlertDTO originModel) {
        final AlertEntity alertEntity = new AlertEntity();
        alertEntity.setIdentity(originModel.getIdentity());
        alertEntity.setCategory(AlertCategoryEnum.valueOf(originModel.getCategory()));
        alertEntity.setCreateAt(originModel.getCreateAt());
        alertEntity.setLevel(AlertLevelEnum.valueOf(originModel.getLevel()));
        alertEntity.setPayload(originModel.getPayload());
        alertEntity.setSince(originModel.getSince());
        alertEntity.setTitle(originModel.getTitle());
        alertEntity.setSon(sonEntityDataMapper.transform(originModel.getKid()));
        return alertEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public AlertDTO transformInverse(AlertEntity originModel) {
        final AlertDTO alertDTO = new AlertDTO();
        alertDTO.setIdentity(originModel.getIdentity());
        alertDTO.setCategory(originModel.getCategory().name());
        alertDTO.setCreateAt(originModel.getCreateAt());
        alertDTO.setLevel(originModel.getLevel().name());
        alertDTO.setPayload(originModel.getPayload());
        alertDTO.setTitle(originModel.getTitle());
        alertDTO.setSince(originModel.getSince());
        alertDTO.setKid(sonEntityDataMapper.transformInverse(originModel.getSon()));
        return alertDTO;
    }
}
