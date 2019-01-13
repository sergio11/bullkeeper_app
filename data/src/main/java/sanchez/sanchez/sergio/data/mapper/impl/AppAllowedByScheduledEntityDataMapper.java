package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AppAllowedByScheduledDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppInstalledDTO;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDTO;
import sanchez.sanchez.sergio.domain.models.AppAllowedByScheduledEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;

/**
 * App Allowed By Scheduled Entity Data Mapper
 */
public final class AppAllowedByScheduledEntityDataMapper extends AbstractDataMapper<AppAllowedByScheduledDTO, AppAllowedByScheduledEntity> {

    /**
     * App Installed Data Mapper
     */
    private final AbstractDataMapper<AppInstalledDTO, AppInstalledEntity> appInstalledEntityAbstractDataMapper;

    /**
     * Terminal Entity Data Mapper
     */
    private final AbstractDataMapper<TerminalDTO, TerminalEntity> terminalEntityAbstractDataMapper;

    /**
     *
     * @param appInstalledEntityAbstractDataMapper
     * @param terminalEntityAbstractDataMapper
     */
    public AppAllowedByScheduledEntityDataMapper(
            final AbstractDataMapper<AppInstalledDTO, AppInstalledEntity> appInstalledEntityAbstractDataMapper,
            final AbstractDataMapper<TerminalDTO, TerminalEntity> terminalEntityAbstractDataMapper) {
        this.appInstalledEntityAbstractDataMapper = appInstalledEntityAbstractDataMapper;
        this.terminalEntityAbstractDataMapper = terminalEntityAbstractDataMapper;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public AppAllowedByScheduledEntity transform(final AppAllowedByScheduledDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final AppAllowedByScheduledEntity appAllowedByScheduledEntity = new AppAllowedByScheduledEntity();
        appAllowedByScheduledEntity.setApp(appInstalledEntityAbstractDataMapper.transform(originModel.getApp()));
        appAllowedByScheduledEntity.setTerminal(terminalEntityAbstractDataMapper.transform(originModel.getTerminal()));
        return appAllowedByScheduledEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public AppAllowedByScheduledDTO transformInverse(final AppAllowedByScheduledEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final AppAllowedByScheduledDTO appAllowedByScheduledDTO = new AppAllowedByScheduledDTO();
        appAllowedByScheduledDTO.setApp(appInstalledEntityAbstractDataMapper.transformInverse(originModel.getApp()));
        appAllowedByScheduledDTO.setTerminal(terminalEntityAbstractDataMapper.transformInverse(originModel.getTerminal()));
        return appAllowedByScheduledDTO;
    }
}
