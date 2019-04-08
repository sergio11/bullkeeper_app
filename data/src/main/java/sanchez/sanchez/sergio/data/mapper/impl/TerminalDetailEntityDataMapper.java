package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDetailDTO;
import sanchez.sanchez.sergio.data.net.models.response.TerminalHeartbeatDTO;
import sanchez.sanchez.sergio.domain.models.ScreenStatusEnum;
import sanchez.sanchez.sergio.domain.models.TerminalDetailEntity;
import sanchez.sanchez.sergio.domain.models.TerminalHeartbeatEntity;
import sanchez.sanchez.sergio.domain.models.DeviceStatusEnum;
import sanchez.sanchez.sergio.domain.models.TerminalStatusEnum;

/**
 * Terminal Detail Entity Data Mapper
 */
public final class TerminalDetailEntityDataMapper extends AbstractDataMapper<TerminalDetailDTO, TerminalDetailEntity> {

    /**
     * Terminal HeartBeat Data Mapper
     */
    private final AbstractDataMapper<TerminalHeartbeatDTO, TerminalHeartbeatEntity> terminalHeartbeatEntityAbstractDataMapper;

    /**
     *
     * @param terminalHeartbeatEntityAbstractDataMapper
     */
    public TerminalDetailEntityDataMapper(AbstractDataMapper<TerminalHeartbeatDTO, TerminalHeartbeatEntity> terminalHeartbeatEntityAbstractDataMapper) {
        this.terminalHeartbeatEntityAbstractDataMapper = terminalHeartbeatEntityAbstractDataMapper;
    }

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
        terminalEntity.setBedTimeEnabled(originModel.isBedTimeEnabled());
        terminalEntity.setCameraEnabled(originModel.isCameraEnabled());
        terminalEntity.setScreenEnabled(originModel.isScreenEnabled());
        terminalEntity.setSettingsEnabled(originModel.isSettingsEnabled());
        terminalEntity.setLocationPermissionEnabled(originModel.isLocationPermissionEnabled());
        terminalEntity.setStoragePermissionEnabled(originModel.isStoragePermissionEnabled());
        terminalEntity.setCallsHistoryPermissionEnabled(originModel.isCallsHistoryPermissionEnabled());
        terminalEntity.setContactsListPermissionEnabled(originModel.isContactsListPermissionEnabled());
        terminalEntity.setTextMessagePermissionEnabled(originModel.isTextMessagePermissionEnabled());
        terminalEntity.setUsageStatsAllowed(originModel.isUsageStatsAllowed());
        terminalEntity.setAdminAccessAllowed(originModel.isAdminAccessAllowed());
        terminalEntity.setBatteryCharging(originModel.isBatteryCharging());
        terminalEntity.setBatteryLevel(originModel.getBatteryLevel());
        try {
            terminalEntity.setDeviceStatus(DeviceStatusEnum.valueOf(originModel.getDeviceStatus()));
        } catch (final Exception ex) {
            terminalEntity.setDeviceStatus(DeviceStatusEnum.STATE_UNKNOWN);
        }
        try {
            terminalEntity.setScreenStatus(
                    ScreenStatusEnum.valueOf(originModel.getScreenStatus()));
        } catch(final Exception ex) {
            terminalEntity.setScreenStatus(ScreenStatusEnum.STATE_UNKNOWN);
        }
        try {
            terminalEntity.setStatus(
                    TerminalStatusEnum.valueOf(originModel.getStatus()));
        } catch(final Exception ex) {
            terminalEntity.setStatus(TerminalStatusEnum.UNKNOWN);
        }
        terminalEntity.setAppsOverlayEnabled(originModel.isAppsOverlayEnabled());
        terminalEntity.setHighAccuraccyLocationEnabled(originModel.isHighAccuraccyLocationEnabled());
        terminalEntity.setTerminalHeartbeatEntity(
                terminalHeartbeatEntityAbstractDataMapper.transform(originModel.getHeartbeat())
        );
        terminalEntity.setCarrierName(originModel.getCarrierName());
        terminalEntity.setPhoneNumber(originModel.getPhoneNumber());
        terminalEntity.setPhoneCallsEnabled(originModel.isPhoneCallsEnabled());
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
        terminalDTO.setCameraEnabled(originModel.isCameraEnabled());
        terminalDTO.setScreenEnabled(originModel.isScreenEnabled());
        terminalDTO.setBedTimeEnabled(originModel.isBedTimeEnabled());
        terminalDTO.setSettingsEnabled(originModel.isSettingsEnabled());
        terminalDTO.setLocationPermissionEnabled(originModel.isLocationPermissionEnabled());
        terminalDTO.setStoragePermissionEnabled(originModel.isStoragePermissionEnabled());
        terminalDTO.setCallsHistoryPermissionEnabled(originModel.isCallsHistoryPermissionEnabled());
        terminalDTO.setContactsListPermissionEnabled(originModel.isContactsListPermissionEnabled());
        terminalDTO.setTextMessagePermissionEnabled(originModel.isTextMessagePermissionEnabled());
        terminalDTO.setUsageStatsAllowed(originModel.isUsageStatsAllowed());
        terminalDTO.setAdminAccessAllowed(originModel.isAdminAccessAllowed());
        terminalDTO.setBatteryCharging(originModel.isBatteryCharging());
        terminalDTO.setBatteryLevel(originModel.getBatteryLevel());
        terminalDTO.setAppsOverlayEnabled(originModel.isAppsOverlayEnabled());
        terminalDTO.setHighAccuraccyLocationEnabled(originModel.isHighAccuraccyLocationEnabled());
        terminalDTO.setHeartbeat(
                terminalHeartbeatEntityAbstractDataMapper.transformInverse(originModel.getTerminalHeartbeatEntity())
        );

        terminalDTO.setStatus(originModel.getStatus().name());
        terminalDTO.setDeviceStatus(originModel.getDeviceStatus().name());
        terminalDTO.setScreenStatus(originModel.getScreenStatus().name());
        terminalDTO.setCarrierName(originModel.getCarrierName());
        terminalDTO.setPhoneNumber(originModel.getPhoneNumber());
        terminalDTO.setPhoneCallsEnabled(originModel.isPhoneCallsEnabled());
        return terminalDTO;
    }
}
