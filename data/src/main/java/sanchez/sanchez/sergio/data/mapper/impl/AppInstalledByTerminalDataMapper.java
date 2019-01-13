package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AppInstalledByTerminalDTO;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDTO;
import sanchez.sanchez.sergio.domain.models.AppInstalledByTerminalEntity;
import sanchez.sanchez.sergio.domain.models.AppRuleEnum;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;

/**
 * App Installed By Terminal Data Mapper
 */
public final class AppInstalledByTerminalDataMapper
    extends AbstractDataMapper<AppInstalledByTerminalDTO, AppInstalledByTerminalEntity> {

    /**
     * Terminal Entity Data Mapper
     */
    private final AbstractDataMapper<TerminalDTO, TerminalEntity> terminalEntityAbstractDataMapper;

    /**
     *
     * @param terminalEntityAbstractDataMapper
     */
    public AppInstalledByTerminalDataMapper(final AbstractDataMapper<TerminalDTO, TerminalEntity> terminalEntityAbstractDataMapper) {
        this.terminalEntityAbstractDataMapper = terminalEntityAbstractDataMapper;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public AppInstalledByTerminalEntity transform(AppInstalledByTerminalDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final AppInstalledByTerminalEntity appInstalledByTerminalEntity = new AppInstalledByTerminalEntity();
        appInstalledByTerminalEntity.setAppName(originModel.getAppName());
        appInstalledByTerminalEntity.setDisabled(originModel.getDisabled());
        appInstalledByTerminalEntity.setFirstInstallTime(originModel.getFirstInstallTime());
        appInstalledByTerminalEntity.setIconEncodedString(originModel.getIconEncodedString());
        appInstalledByTerminalEntity.setIdentity(originModel.getIdentity());
        appInstalledByTerminalEntity.setLastUpdateTime(originModel.getLastUpdateTime());
        appInstalledByTerminalEntity.setPackageName(originModel.getPackageName());
        appInstalledByTerminalEntity.setVersionCode(originModel.getVersionCode());
        appInstalledByTerminalEntity.setVersionName(originModel.getVersionName());
        appInstalledByTerminalEntity.setAppRuleEnum(AppRuleEnum.valueOf(originModel.getAppRule()));
        appInstalledByTerminalEntity.setTerminal(
                terminalEntityAbstractDataMapper.transform(originModel.getTerminal()));
        return appInstalledByTerminalEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public AppInstalledByTerminalDTO transformInverse(AppInstalledByTerminalEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final AppInstalledByTerminalDTO appInstalledByTerminalDTO = new AppInstalledByTerminalDTO();
        appInstalledByTerminalDTO.setIdentity(originModel.getIdentity());
        appInstalledByTerminalDTO.setAppName(originModel.getAppName());
        appInstalledByTerminalDTO.setAppRule(originModel.getAppRuleEnum().name());
        appInstalledByTerminalDTO.setDisabled(originModel.getDisabled());
        appInstalledByTerminalDTO.setFirstInstallTime(originModel.getFirstInstallTime());
        appInstalledByTerminalDTO.setIconEncodedString(originModel.getIconEncodedString());
        appInstalledByTerminalDTO.setLastUpdateTime(originModel.getLastUpdateTime());
        appInstalledByTerminalDTO.setPackageName(originModel.getPackageName());
        appInstalledByTerminalDTO.setVersionCode(originModel.getVersionCode());
        appInstalledByTerminalDTO.setVersionName(originModel.getVersionName());
        appInstalledByTerminalDTO.setTerminal(
                terminalEntityAbstractDataMapper.transformInverse(originModel.getTerminal()));
        return appInstalledByTerminalDTO;
    }
}
