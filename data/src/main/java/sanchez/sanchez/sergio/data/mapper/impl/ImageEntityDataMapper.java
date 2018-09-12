package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
/**
 * Image Entity Data Mapper
 */
public final class ImageEntityDataMapper extends AbstractDataMapper<ImageDTO, ImageEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public ImageEntity transform(ImageDTO originModel) {
        final ImageEntity imageEntity = new ImageEntity();
        imageEntity.setIdentity(originModel.getIdentity());
        imageEntity.setContentType(originModel.getContentType());
        imageEntity.setSize(originModel.getSize());
        return imageEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public ImageDTO transformInverse(ImageEntity originModel) {
        final ImageDTO imageDTO = new ImageDTO();
        imageDTO.setIdentity(originModel.getIdentity());
        imageDTO.setContentType(originModel.getContentType());
        imageDTO.setSize(originModel.getSize());
        return imageDTO;
    }
}
