package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AppStatsDTO;
import sanchez.sanchez.sergio.domain.models.AppStatsEntity;

/**
 * App Stats Entity Data Mapper
 */
public final class AppStatsEntityDataMapper
    extends AbstractDataMapper<AppStatsDTO, AppStatsEntity> {

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public AppStatsEntity transform(AppStatsDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final AppStatsEntity appStatsEntity = new AppStatsEntity();
        appStatsEntity.setIdentity(originModel.getIdentity());
        appStatsEntity.setFirstTime(originModel.getFirstTime());
        appStatsEntity.setKid(originModel.getKid());
        appStatsEntity.setLastTime(originModel.getLastTime());
        appStatsEntity.setLastTimeUsed(originModel.getLastTimeUsed());
        appStatsEntity.setPackageName(originModel.getPackageName());
        appStatsEntity.setTerminal(originModel.getTerminal());
        appStatsEntity.setTotalTimeInForeground(originModel.getTotalTimeInForeground());
        appStatsEntity.setAppName(originModel.getAppName());
        appStatsEntity.setIconEncodedString(originModel.getIconEncodedString());
        return appStatsEntity;
    }

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public AppStatsDTO transformInverse(AppStatsEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final AppStatsDTO appStatsDTO = new AppStatsDTO();
        appStatsDTO.setIdentity(originModel.getIdentity());
        appStatsDTO.setFirstTime(originModel.getFirstTime());
        appStatsDTO.setKid(originModel.getKid());
        appStatsDTO.setLastTime(originModel.getLastTime());
        appStatsDTO.setLastTimeUsed(originModel.getLastTimeUsed());
        appStatsDTO.setPackageName(originModel.getPackageName());
        appStatsDTO.setTerminal(originModel.getTerminal());
        appStatsDTO.setTotalTimeInForeground(originModel.getTotalTimeInForeground());
        appStatsDTO.setAppName(originModel.getAppName());
        appStatsDTO.setIconEncodedString(originModel.getIconEncodedString());
        return appStatsDTO;
    }
}
