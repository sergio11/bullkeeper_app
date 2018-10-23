package sanchez.sanchez.sergio.data.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaActivityStatisticsDTO;
import sanchez.sanchez.sergio.domain.models.SocialMediaActivityStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;
import timber.log.Timber;

/**
 * Social Media Activity Statistics Entity
 */
public final class SocialMediaActivityStatisticsEntityDataMapper
    extends AbstractDataMapper<SocialMediaActivityStatisticsDTO, SocialMediaActivityStatisticsEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public SocialMediaActivityStatisticsEntity transform(SocialMediaActivityStatisticsDTO originModel) {
        final SocialMediaActivityStatisticsEntity socialMediaActivityStatisticsEntity = new SocialMediaActivityStatisticsEntity();
        socialMediaActivityStatisticsEntity.setTitle(originModel.getTitle());
        socialMediaActivityStatisticsEntity.setSubtitle(originModel.getSubtitle());
        final List<SocialMediaActivityStatisticsEntity.ActivityEntity> activityEntities = new ArrayList<>();
        for(final SocialMediaActivityStatisticsDTO.ActivityDTO activityDTO: originModel.getActivities()) {
            final SocialMediaActivityStatisticsEntity.ActivityEntity activityEntity = new SocialMediaActivityStatisticsEntity.ActivityEntity();
            activityEntity.setLabel(activityDTO.getLabel());
            activityEntity.setValue(activityDTO.getValue());
            try {
                activityEntity.setSocialMediaEnum(SocialMediaEnum.valueOf(activityDTO.getType()));
            } catch (IllegalArgumentException ex) {
                Timber.e("Social Media Unknow");
                activityEntity.setSocialMediaEnum(null);
            }
            activityEntities.add(activityEntity);
        }
        socialMediaActivityStatisticsEntity.setActivities(activityEntities);
        return socialMediaActivityStatisticsEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public SocialMediaActivityStatisticsDTO transformInverse(SocialMediaActivityStatisticsEntity originModel) {
        return null;
    }
}
