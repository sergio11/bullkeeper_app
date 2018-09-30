package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.models.ImageEntity;

/**
 * Son Image Entity Data Mapper
 */
public class SonImageEntityDataMapper extends ImageEntityDataMapper {

    private final ApiEndPointsHelper apiEndPointsHelper;

    public SonImageEntityDataMapper(ApiEndPointsHelper apiEndPointsHelper) {
        this.apiEndPointsHelper = apiEndPointsHelper;
    }

    @Override
    public ImageEntity transform(ImageDTO originModel) {
        final ImageEntity imageEntity = super.transform(originModel);
        imageEntity.setUrl(apiEndPointsHelper.getSonProfileUrl(originModel.getIdentity()));
        return imageEntity;
    }
}
