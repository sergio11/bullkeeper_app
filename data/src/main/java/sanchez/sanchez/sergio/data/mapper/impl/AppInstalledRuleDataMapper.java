package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.AppInstalledRuleDTO;
import sanchez.sanchez.sergio.domain.models.AppInstalledRuleEntity;
import sanchez.sanchez.sergio.domain.models.AppRuleEnum;

/**
 * App Installed Rule Data Mapper
 */
public final class AppInstalledRuleDataMapper extends AbstractDataMapper<AppInstalledRuleDTO, AppInstalledRuleEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public AppInstalledRuleEntity transform(final AppInstalledRuleDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final AppInstalledRuleEntity appInstalledRuleEntity = new AppInstalledRuleEntity();
        appInstalledRuleEntity.setIdentity(originModel.getIdentity());
        appInstalledRuleEntity.setAppRuleEnum(AppRuleEnum.valueOf(originModel.getAppRule()));
        return appInstalledRuleEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public AppInstalledRuleDTO transformInverse(final AppInstalledRuleEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final AppInstalledRuleDTO appInstalledRuleDTO = new AppInstalledRuleDTO();
        appInstalledRuleDTO.setIdentity(originModel.getIdentity());
        appInstalledRuleDTO.setAppRule(originModel.getAppRuleEnum().name());
        return appInstalledRuleDTO;
    }
}
