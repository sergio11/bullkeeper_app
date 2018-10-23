package sanchez.sanchez.sergio.data.mapper.impl;

import java.util.ArrayList;
import java.util.List;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.MostActiveFriendsDTO;
import sanchez.sanchez.sergio.domain.models.MostActiveFriendsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaFriendEntity;
import timber.log.Timber;

/**
 * Social Media Friends Data Mapper
 */
public final class MostActiveFriendsDataMapper extends AbstractDataMapper<MostActiveFriendsDTO,
        MostActiveFriendsEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public MostActiveFriendsEntity transform(final MostActiveFriendsDTO originModel) {
        final MostActiveFriendsEntity mostActiveFriendsEntity = new MostActiveFriendsEntity();
        mostActiveFriendsEntity.setTitle(originModel.getTitle());
        final List<SocialMediaFriendEntity> socialMediaFriendEntities = new ArrayList<>();
        for(final MostActiveFriendsDTO.UserDTO userDTO: originModel.getUsers()) {
            final SocialMediaFriendEntity socialMediaFriendEntity = new SocialMediaFriendEntity();
            socialMediaFriendEntity.setId(userDTO.getId());
            socialMediaFriendEntity.setName(userDTO.getName());
            socialMediaFriendEntity.setProfileImage(userDTO.getProfileImage());
            try {
                socialMediaFriendEntity.setSocialMediaEnum(
                        SocialMediaEnum.valueOf(userDTO.getSocialMediaType()));
            } catch(final IllegalArgumentException ex) {
                Timber.e("Social Media unknown");
                socialMediaFriendEntity.setSocialMediaEnum(null);
            }
            socialMediaFriendEntity.setValue(userDTO.getValue());
            socialMediaFriendEntity.setLabel(userDTO.getValueLabel());
            socialMediaFriendEntities.add(socialMediaFriendEntity);
        }
        mostActiveFriendsEntity.setFriends(socialMediaFriendEntities);
        return mostActiveFriendsEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public MostActiveFriendsDTO transformInverse(final MostActiveFriendsEntity originModel) {
        throw new UnsupportedOperationException();
    }
}
