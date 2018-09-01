package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
/**
 * Image Entity Data Mapper
 */
public final class ImageEntityDataMapper extends AbstractDataMapper<ImageDTO, ImageEntity> {

    @Override
    public ImageEntity transform(ImageDTO originModel) {
        final ImageEntity imageEntity = new ImageEntity();
        imageEntity.setIdentity(originModel.getIdentity());
        imageEntity.setContentType(originModel.getContentType());
        imageEntity.setSize(originModel.getSize());
        return imageEntity;
    }
}
