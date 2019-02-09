package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AppModelDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppInstalledDetailDTO;
import sanchez.sanchez.sergio.domain.models.AppInstalledDetailEntity;
import sanchez.sanchez.sergio.domain.models.AppModelCategoryEnum;
import sanchez.sanchez.sergio.domain.models.AppModelEntity;
import sanchez.sanchez.sergio.domain.models.AppRuleEnum;

/**
 * App Installed Detail Data Mapper
 */
public final class AppInstalledDetailDataMapper extends AbstractDataMapper<AppInstalledDetailDTO, AppInstalledDetailEntity> {

    /**
     * App Model Data Mapper
     */
    private final AbstractDataMapper<AppModelDTO, AppModelEntity> appModelDataMapper;


    /**
     *
     * @param appModelDataMapper
     */
    public AppInstalledDetailDataMapper(
            final AbstractDataMapper<AppModelDTO, AppModelEntity> appModelDataMapper) {
        this.appModelDataMapper = appModelDataMapper;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public AppInstalledDetailEntity transform(final AppInstalledDetailDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final AppInstalledDetailEntity appInstalledDetailEntity = new AppInstalledDetailEntity();
        appInstalledDetailEntity.setIdentity(originModel.getIdentity());
        appInstalledDetailEntity.setAppName(originModel.getAppName());
        appInstalledDetailEntity.setFirstInstallTime(originModel.getFirstInstallTime());
        appInstalledDetailEntity.setPackageName(originModel.getPackageName());
        appInstalledDetailEntity.setLastUpdateTime(originModel.getLastUpdateTime());
        appInstalledDetailEntity.setVersionCode(originModel.getVersionCode());
        appInstalledDetailEntity.setVersionName(originModel.getVersionName());
        appInstalledDetailEntity.setAppRuleEnum(AppRuleEnum.valueOf(originModel.getAppRule()));
        appInstalledDetailEntity.setIconEncodedString(originModel.getIconEncodedString());
        appInstalledDetailEntity.setDisabled(originModel.getDisabled());
        appInstalledDetailEntity.setCategory(originModel.getCategory());

        try {
            appInstalledDetailEntity.setCategoryKey(AppModelCategoryEnum
                    .valueOf(originModel.getCategoryKey()));

        } catch(final Exception ex) {
            appInstalledDetailEntity.setCategoryKey(
                    AppModelCategoryEnum.UNKNOWN
            );
        }

        // Set App Model
        appInstalledDetailEntity.setModel(appModelDataMapper
                .transform(originModel.getModel()));

        return appInstalledDetailEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public AppInstalledDetailDTO transformInverse(AppInstalledDetailEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final AppInstalledDetailDTO appInstalledDetailDTO = new AppInstalledDetailDTO();
        appInstalledDetailDTO.setIdentity(originModel.getIdentity());
        appInstalledDetailDTO.setAppName(originModel.getAppName());
        appInstalledDetailDTO.setFirstInstallTime(originModel.getFirstInstallTime());
        appInstalledDetailDTO.setLastUpdateTime(originModel.getLastUpdateTime());
        appInstalledDetailDTO.setPackageName(originModel.getPackageName());
        appInstalledDetailDTO.setVersionCode(originModel.getVersionCode());
        appInstalledDetailDTO.setVersionName(originModel.getVersionName());
        appInstalledDetailDTO.setAppRule(originModel.getAppRuleEnum().name());
        appInstalledDetailDTO.setIconEncodedString(originModel.getIconEncodedString());
        appInstalledDetailDTO.setDisabled(originModel.getDisabled());
        appInstalledDetailDTO.setCategory(originModel.getCategory());
        appInstalledDetailDTO.setCategoryKey(originModel.getCategoryKey().name());
        appInstalledDetailDTO.setModel(appModelDataMapper
                .transformInverse(originModel.getModel()));
        return appInstalledDetailDTO;
    }
}
