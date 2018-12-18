package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDetailDTO;
import sanchez.sanchez.sergio.domain.models.ScreenStatusEnum;
import sanchez.sanchez.sergio.domain.models.TerminalDetailEntity;

/**
 * Terminal Detail Entity Data Mapper
 */
public final class TerminalDetailEntityDataMapper extends AbstractDataMapper<TerminalDetailDTO, TerminalDetailEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public TerminalDetailEntity transform(final TerminalDetailDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin model can not be null");

        final TerminalDetailEntity terminalEntity = new TerminalDetailEntity();
        terminalEntity.setIdentity(originModel.getIdentity());
        terminalEntity.setAppVersionCode(originModel.getAppVersionCode());
        terminalEntity.setAppVersionName(originModel.getAppVersionName());
        terminalEntity.setCodeName(originModel.getCodeName());
        terminalEntity.setDeviceName(originModel.getDeviceName());
        terminalEntity.setManufacturer(originModel.getManufacturer());
        terminalEntity.setMarketName(originModel.getMarketName());
        terminalEntity.setModel(originModel.getModel());
        terminalEntity.setOsVersion(originModel.getOsVersion());
        terminalEntity.setSdkVersion(originModel.getSdkVersion());
        terminalEntity.setTotalApps(originModel.getTotalApps());
        terminalEntity.setTotalCalls(originModel.getTotalCalls());
        terminalEntity.setTotalSms(originModel.getTotalSms());
        terminalEntity.setTotalContacts(originModel.getTotalContacts());
        try {
            terminalEntity.setScreenStatusEnum(
                    ScreenStatusEnum.valueOf(originModel.getScreenStatus()));
        } catch(final Exception ex) {
            terminalEntity.setScreenStatusEnum(ScreenStatusEnum.STATE_UNKNOWN);
        }
        terminalEntity.setLastTimeUsed(originModel.getLastTimeUsed());
        return terminalEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public TerminalDetailDTO transformInverse(final TerminalDetailEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final TerminalDetailDTO terminalDTO = new TerminalDetailDTO();
        terminalDTO.setIdentity(originModel.getIdentity());
        terminalDTO.setAppVersionCode(originModel.getAppVersionCode());
        terminalDTO.setAppVersionName(originModel.getAppVersionName());
        terminalDTO.setCodeName(originModel.getCodeName());
        terminalDTO.setDeviceName(originModel.getDeviceName());
        terminalDTO.setManufacturer(originModel.getManufacturer());
        terminalDTO.setMarketName(originModel.getMarketName());
        terminalDTO.setModel(originModel.getOsVersion());
        terminalDTO.setOsVersion(originModel.getOsVersion());
        terminalDTO.setSdkVersion(originModel.getSdkVersion());
        terminalDTO.setTotalApps(originModel.getTotalApps());
        terminalDTO.setTotalCalls(originModel.getTotalCalls());
        terminalDTO.setTotalContacts(originModel.getTotalContacts());
        terminalDTO.setScreenStatus(originModel.getScreenStatusEnum().name());
        terminalDTO.setLastTimeUsed(originModel.getLastTimeUsed());
        return terminalDTO;
    }
}
