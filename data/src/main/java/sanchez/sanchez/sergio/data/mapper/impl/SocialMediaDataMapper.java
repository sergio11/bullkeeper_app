package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaDTO;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaTypeEnum;

/**
 * Social Media Data Mapper
 */
public final class SocialMediaDataMapper extends AbstractDataMapper<SocialMediaDTO, SocialMediaEntity> {
    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public SocialMediaEntity transform(final SocialMediaDTO originModel) {
        final SocialMediaEntity socialMediaEntity = new SocialMediaEntity();
        socialMediaEntity.setIdentity(originModel.getIdentity());
        socialMediaEntity.setAccessToken(originModel.getAccessToken());
        socialMediaEntity.setInvalidToken(originModel.getInvalidToken());
        socialMediaEntity.setRefreshToken(originModel.getRefreshToken());
        socialMediaEntity.setSonFullName(originModel.getSon());
        socialMediaEntity.setUserPicture(originModel.getUserPicture());
        socialMediaEntity.setUserSocialName(originModel.getUserSocialName());
        socialMediaEntity.setType(SocialMediaTypeEnum.valueOf(originModel.getType()));
        return socialMediaEntity;
    }

    /**
     * Transform inverse
     * @param originModel
     * @return
     */
    @Override
    public SocialMediaDTO transformInverse(SocialMediaEntity originModel) {
        final SocialMediaDTO socialMediaDTO = new SocialMediaDTO();
        socialMediaDTO.setIdentity(originModel.getIdentity());
        socialMediaDTO.setAccessToken(originModel.getAccessToken());
        socialMediaDTO.setInvalidToken(originModel.hasInvalidToken());
        socialMediaDTO.setRefreshToken(originModel.getRefreshToken());
        socialMediaDTO.setSon(originModel.getSonFullName());
        socialMediaDTO.setType(originModel.getType().name());
        socialMediaDTO.setUserPicture(originModel.getUserPicture());
        socialMediaDTO.setUserSocialName(originModel.getUserSocialName());
        return socialMediaDTO;
    }
}
