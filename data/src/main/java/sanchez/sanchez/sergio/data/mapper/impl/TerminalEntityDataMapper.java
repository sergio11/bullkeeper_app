package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDTO;
import sanchez.sanchez.sergio.data.net.models.response.TerminalHeartbeatDTO;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;
import sanchez.sanchez.sergio.domain.models.TerminalHeartbeatEntity;
import sanchez.sanchez.sergio.domain.models.TerminalStatusEnum;

/**
 * Terminal Entity Data Mapper
 */
public final class TerminalEntityDataMapper extends AbstractDataMapper<TerminalDTO, TerminalEntity> {

    /**
     * Terminal Heartbeat Entity Data Mapper
     */
    private final AbstractDataMapper<TerminalHeartbeatDTO, TerminalHeartbeatEntity> terminalHeartbeatEntityDataMapper;

    /**
     *
     * @param terminalHeartbeatEntityDataMapper
     */
    public TerminalEntityDataMapper(final AbstractDataMapper<TerminalHeartbeatDTO, TerminalHeartbeatEntity> terminalHeartbeatEntityDataMapper) {
        this.terminalHeartbeatEntityDataMapper = terminalHeartbeatEntityDataMapper;
    }

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
        terminalEntity.setDetached(originModel.isDetached());
        terminalEntity.setSdkVersion(originModel.getSdkVersion());
        terminalEntity.setBedTimeEnabled(originModel.isBedTimeEnabled());
        terminalEntity.setCameraEnabled(originModel.isCameraEnabled());
        terminalEntity.setScreenEnabled(originModel.isScreenEnabled());
        terminalEntity.setSettingsEnabled(originModel.isSettingsEnabled());
        terminalEntity.setPhoneCallsEnabled(originModel.isPhoneCallsEnabled());
        terminalEntity.setBatteryLevel(originModel.getBatteryLevel());
        terminalEntity.setBatteryCharging(originModel.isBatteryCharging());
        try {
            terminalEntity.setStatus(TerminalStatusEnum.valueOf(originModel.getStatus()));
        } catch (final Exception ex) {
            terminalEntity.setStatus(TerminalStatusEnum.STATE_UNKNOWN);
        }
        terminalEntity.setTerminalHeartbeatEntity(terminalHeartbeatEntityDataMapper
                .transform(originModel.getHeartbeat()));
        terminalEntity.setCarrierName(originModel.getCarrierName());
        terminalEntity.setPhoneNumber(originModel.getPhoneNumber());
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
        terminalDTO.setDetached(originModel.isDetached());
        terminalDTO.setMarketName(originModel.getMarketName());
        terminalDTO.setModel(originModel.getOsVersion());
        terminalDTO.setOsVersion(originModel.getOsVersion());
        terminalDTO.setSdkVersion(originModel.getSdkVersion());
        terminalDTO.setCameraEnabled(originModel.isCameraEnabled());
        terminalDTO.setScreenEnabled(originModel.isScreenEnabled());
        terminalDTO.setBedTimeEnabled(originModel.isBedTimeEnabled());
        terminalDTO.setSettingsEnabled(originModel.isSettingsEnabled());
        terminalDTO.setPhoneCallsEnabled(originModel.isPhoneCallsEnabled());
        terminalDTO.setBatteryLevel(originModel.getBatteryLevel());
        terminalDTO.setBatteryCharging(originModel.isBatteryCharging());
        terminalDTO.setStatus(originModel.getStatus().name());
        terminalDTO.setHeartbeat(
                terminalHeartbeatEntityDataMapper.transformInverse(originModel.getTerminalHeartbeatEntity())
        );
        terminalDTO.setCarrierName(originModel.getCarrierName());
        terminalDTO.setPhoneNumber(originModel.getPhoneNumber());
        return terminalDTO;
    }
}
