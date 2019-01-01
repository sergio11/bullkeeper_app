package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDTO;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;

/**
 * Terminal Entity Data Mapper
 */
public final class TerminalEntityDataMapper extends AbstractDataMapper<TerminalDTO, TerminalEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public TerminalEntity transform(final TerminalDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin model can not be null");

        final TerminalEntity terminalEntity = new TerminalEntity();
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
        terminalEntity.setBedTimeEnabled(originModel.isBedTimeEnabled());
        terminalEntity.setLockCameraEnabled(originModel.isLockCameraEnabled());
        terminalEntity.setLockScreenEnabled(originModel.isLockScreenEnabled());
        return terminalEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public TerminalDTO transformInverse(final TerminalEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final TerminalDTO terminalDTO = new TerminalDTO();
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
        terminalDTO.setLockCameraEnabled(originModel.isLockCameraEnabled());
        terminalDTO.setLockScreenEnabled(originModel.isLockScreenEnabled());
        terminalDTO.setBedTimeEnabled(originModel.isBedTimeEnabled());
        return terminalDTO;
    }
}
