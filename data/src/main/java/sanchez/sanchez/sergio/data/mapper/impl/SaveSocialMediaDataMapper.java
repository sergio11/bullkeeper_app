package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.SaveSocialMediaDTO;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaTypeEnum;

/**
 * Save Social Media Data Mapper
 */
public final class SaveSocialMediaDataMapper extends AbstractDataMapper<SaveSocialMediaDTO, SocialMediaEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public SocialMediaEntity transform(SaveSocialMediaDTO originModel) {
        final SocialMediaEntity socialMediaEntity = new SocialMediaEntity();
        socialMediaEntity.setIdentity(originModel.getIdentity());
        socialMediaEntity.setAccessToken(originModel.getAccessToken());
        socialMediaEntity.setRefreshToken(originModel.getRefreshToken());
        socialMediaEntity.setSonFullName(originModel.getSon());
        socialMediaEntity.setType(SocialMediaTypeEnum.valueOf(originModel.getType()));
        return socialMediaEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public SaveSocialMediaDTO transformInverse(SocialMediaEntity originModel) {
        final SaveSocialMediaDTO saveSocialMediaDTO = new SaveSocialMediaDTO();
        saveSocialMediaDTO.setIdentity(originModel.getIdentity());
        saveSocialMediaDTO.setAccessToken(originModel.getAccessToken());
        saveSocialMediaDTO.setRefreshToken(originModel.getRefreshToken());
        saveSocialMediaDTO.setSon(originModel.getSonFullName());
        saveSocialMediaDTO.setType(originModel.getType().name());
        return saveSocialMediaDTO;
    }
}
