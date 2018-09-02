package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AlertDTO;
import sanchez.sanchez.sergio.data.net.models.response.SonDTO;
import sanchez.sanchez.sergio.domain.models.AlertCategoryEnum;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import sanchez.sanchez.sergio.domain.models.SonEntity;

/**
 * Alert Entity Data Mapper
 */
public final class AlertEntityDataMapper extends AbstractDataMapper<AlertDTO, AlertEntity> {

    /**
     * Son Entity Data Mapper
     */
    private final AbstractDataMapper<SonDTO, SonEntity> sonEntityDataMapper;


    public AlertEntityDataMapper(final AbstractDataMapper<SonDTO, SonEntity> sonEntityDataMapper) {
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
        alertEntity.setSon(sonEntityDataMapper.transform(originModel.getSon()));
        return alertEntity;
    }
}
