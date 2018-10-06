package sanchez.sanchez.sergio.data.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaLikesStatisticsDTO;
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaLikesStatisticsEntity;
import timber.log.Timber;

/**
 * Social Media Likes Statistics Data Mapper
 */
public final class SocialMediaLikesStatisticsDataMapper
    extends AbstractDataMapper<SocialMediaLikesStatisticsDTO, SocialMediaLikesStatisticsEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public SocialMediaLikesStatisticsEntity transform(SocialMediaLikesStatisticsDTO originModel) {
        final SocialMediaLikesStatisticsEntity socialMediaLikesStatisticsEntity = new SocialMediaLikesStatisticsEntity();
        socialMediaLikesStatisticsEntity.setTitle(originModel.getTitle());
        socialMediaLikesStatisticsEntity.setSubtitle(originModel.getSubtitle());
        final List<SocialMediaLikesStatisticsEntity.SocialMediaLikesEntity> socialMediaLikesEntities = new ArrayList<>();
        for(final SocialMediaLikesStatisticsDTO.SocialMediaLikesDTO socialMediaLikesDTO: originModel.getData()) {
            final SocialMediaLikesStatisticsEntity.SocialMediaLikesEntity socialMediaLikesEntity = new SocialMediaLikesStatisticsEntity.SocialMediaLikesEntity();
            socialMediaLikesEntity.setLabel(socialMediaLikesDTO.getLabel());
            socialMediaLikesEntity.setLikes(socialMediaLikesDTO.getLikes());
            try {
                socialMediaLikesEntity.setType(SocialMediaEnum.valueOf(socialMediaLikesDTO.getType()));
            } catch(final IllegalArgumentException ex) {
                Timber.e("Invalid Social Media Enum");
                socialMediaLikesEntity.setType(null);
            }
            socialMediaLikesEntities.add(socialMediaLikesEntity);
        }
        socialMediaLikesStatisticsEntity.setSocialMediaLikesEntities(socialMediaLikesEntities);
        return socialMediaLikesStatisticsEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public SocialMediaLikesStatisticsDTO transformInverse(SocialMediaLikesStatisticsEntity originModel) {
        throw new UnsupportedOperationException();
    }
}
