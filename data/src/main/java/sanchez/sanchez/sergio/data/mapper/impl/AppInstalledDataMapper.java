package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AppInstalledDTO;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
import sanchez.sanchez.sergio.domain.models.AppRuleEnum;

/**
 * App Installed Data Mapper
 */
public final class AppInstalledDataMapper extends AbstractDataMapper<AppInstalledDTO, AppInstalledEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public AppInstalledEntity transform(final AppInstalledDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final AppInstalledEntity appInstalledEntity = new AppInstalledEntity();
        appInstalledEntity.setIdentity(originModel.getIdentity());
        appInstalledEntity.setAppName(originModel.getAppName());
        appInstalledEntity.setFirstInstallTime(originModel.getFirstInstallTime());
        appInstalledEntity.setPackageName(originModel.getPackageName());
        appInstalledEntity.setLastUpdateTime(originModel.getLastUpdateTime());
        appInstalledEntity.setVersionCode(originModel.getVersionCode());
        appInstalledEntity.setVersionName(originModel.getVersionName());
        appInstalledEntity.setAppRuleEnum(AppRuleEnum.valueOf(originModel.getAppRule()));
        return appInstalledEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public AppInstalledDTO transformInverse(AppInstalledEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final AppInstalledDTO appInstalledDTO = new AppInstalledDTO();
        appInstalledDTO.setIdentity(originModel.getIdentity());
        appInstalledDTO.setAppName(originModel.getAppName());
        appInstalledDTO.setFirstInstallTime(originModel.getFirstInstallTime());
        appInstalledDTO.setLastUpdateTime(originModel.getLastUpdateTime());
        appInstalledDTO.setPackageName(originModel.getPackageName());
        appInstalledDTO.setVersionCode(originModel.getVersionCode());
        appInstalledDTO.setVersionName(originModel.getVersionName());
        appInstalledDTO.setAppRule(originModel.getAppRuleEnum().name());
        return appInstalledDTO;
    }
}
