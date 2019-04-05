package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.UserPreferenceDTO;
import sanchez.sanchez.sergio.domain.models.RemoveAlertsEveryEnum;
import sanchez.sanchez.sergio.domain.models.UserPreferenceEntity;


public final class UserPreferenceEntityDataMapper
    extends AbstractDataMapper<UserPreferenceDTO, UserPreferenceEntity> {

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public UserPreferenceEntity transform(final UserPreferenceDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final UserPreferenceEntity userPreferenceEntity = new UserPreferenceEntity();
        userPreferenceEntity.setPushNotificationsEnabled(originModel.getPushNotificationsEnabled());
        try {
            userPreferenceEntity.setRemoveAlertsEvery(RemoveAlertsEveryEnum.valueOf(originModel.getRemoveAlertsEvery()));
        } catch (final Exception ex) {
            userPreferenceEntity.setRemoveAlertsEvery(RemoveAlertsEveryEnum.NEVER);
        }
        return userPreferenceEntity;
    }

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public UserPreferenceDTO transformInverse(UserPreferenceEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final UserPreferenceDTO userPreferenceDTO = new UserPreferenceDTO();
        userPreferenceDTO.setPushNotificationsEnabled(originModel.getPushNotificationsEnabled());
        userPreferenceDTO.setRemoveAlertsEvery(originModel.getRemoveAlertsEvery().name());
        return userPreferenceDTO;
    }
}
