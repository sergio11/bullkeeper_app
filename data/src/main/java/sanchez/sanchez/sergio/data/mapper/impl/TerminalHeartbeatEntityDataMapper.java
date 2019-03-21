package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.TerminalHeartbeatDTO;
import sanchez.sanchez.sergio.domain.models.TerminalHeartbeatEntity;

/**
 * Terminal Heartbeat Entity Data Mapper
 */
public final class TerminalHeartbeatEntityDataMapper
    extends AbstractDataMapper<TerminalHeartbeatDTO, TerminalHeartbeatEntity> {

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public TerminalHeartbeatEntity transform(final TerminalHeartbeatDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final TerminalHeartbeatEntity terminalHeartbeatEntity = new TerminalHeartbeatEntity();
        terminalHeartbeatEntity.setAlertThresholdInMinutes(originModel.getAlertThresholdInMinutes());
        terminalHeartbeatEntity.setAlertModeEnabled(originModel.isAlertModeEnabled());
        terminalHeartbeatEntity.setLastTimeNotifiedSince(originModel.getLastTimeNotifiedSince());
        terminalHeartbeatEntity.setLastTimeNotified(originModel.getLastTimeNotified());
        return terminalHeartbeatEntity;
    }

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public TerminalHeartbeatDTO transformInverse(final TerminalHeartbeatEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final TerminalHeartbeatDTO terminalHeartbeatDTO = new TerminalHeartbeatDTO();
        terminalHeartbeatDTO.setAlertModeEnabled(originModel.isAlertModeEnabled());
        terminalHeartbeatDTO.setAlertThresholdInMinutes(originModel.getAlertThresholdInMinutes());
        terminalHeartbeatDTO.setLastTimeNotified(originModel.getLastTimeNotified());
        terminalHeartbeatDTO.setLastTimeNotifiedSince(originModel.getLastTimeNotifiedSince());
        return terminalHeartbeatDTO;
    }
}
