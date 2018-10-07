package sanchez.sanchez.sergio.data.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.MostActiveFriendsBySocialMediaStatisticsDTO;
import sanchez.sanchez.sergio.domain.models.MostActiveFriendsBySocialMediaStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;
import timber.log.Timber;

/**
 * Most Active Friends By Social Media Statistics Entity Data Mapper
 */
public final class MostActiveFriendsBySocialMediaStatisticsEntityDataMapper
    extends AbstractDataMapper<MostActiveFriendsBySocialMediaStatisticsDTO, MostActiveFriendsBySocialMediaStatisticsEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public MostActiveFriendsBySocialMediaStatisticsEntity transform(MostActiveFriendsBySocialMediaStatisticsDTO originModel) {
        final MostActiveFriendsBySocialMediaStatisticsEntity mostActiveFriendsBySocialMediaStatisticsEntity =
                new MostActiveFriendsBySocialMediaStatisticsEntity();
        mostActiveFriendsBySocialMediaStatisticsEntity.setTitle(originModel.getTitle());
        final List<MostActiveFriendsBySocialMediaStatisticsEntity.FriendEntity> friendList = new ArrayList<>();
        for(final MostActiveFriendsBySocialMediaStatisticsDTO.FriendDTO friendDTO: originModel.getData()) {
            final MostActiveFriendsBySocialMediaStatisticsEntity.FriendEntity friendEntity = new MostActiveFriendsBySocialMediaStatisticsEntity.FriendEntity();
            friendEntity.setId(friendDTO.getId());
            friendEntity.setName(friendDTO.getName());
            friendEntity.setProfileImage(friendDTO.getProfileImage());
            friendEntity.setValue(friendDTO.getValue());
            friendEntity.setValueLabel(friendDTO.getValueLabel());
            try {
               friendEntity.setSocialMediaType(SocialMediaEnum.valueOf(friendDTO.getSocialMediaType()));
            } catch (final IllegalArgumentException ex) {
                Timber.e("Social Media Type Unknonw");
                friendEntity.setSocialMediaType(null);
            }
            friendList.add(friendEntity);
        }
        mostActiveFriendsBySocialMediaStatisticsEntity.setFriends(friendList);
        return mostActiveFriendsBySocialMediaStatisticsEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public MostActiveFriendsBySocialMediaStatisticsDTO transformInverse(MostActiveFriendsBySocialMediaStatisticsEntity originModel) {
        throw new UnsupportedOperationException();
    }
}
