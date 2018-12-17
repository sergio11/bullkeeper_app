package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.CallDetailDTO;
import sanchez.sanchez.sergio.domain.models.CallDetailEntity;
import sanchez.sanchez.sergio.domain.models.CallTypeEnum;

/**
 * Call Detail Adapter
 */
public final class CallDetailDataMapper
        extends AbstractDataMapper<CallDetailDTO, CallDetailEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public CallDetailEntity transform(final CallDetailDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final CallDetailEntity callDetailEntity = new CallDetailEntity();
        callDetailEntity.setIdentity(originModel.getIdentity());
        callDetailEntity.setCallDayTime(originModel.getCallDayTime());
        callDetailEntity.setCallDuration(originModel.getCallDuration());
        try {
            callDetailEntity.setCallType(CallTypeEnum.valueOf(originModel.getCallType()));
        } catch (Exception ex) {
            callDetailEntity.setCallType(CallTypeEnum.UNKNOWN);
        }
        callDetailEntity.setKid(originModel.getKid());
        callDetailEntity.setTerminal(originModel.getTerminal());
        callDetailEntity.setPhoneNumber(originModel.getPhoneNumber());
        return callDetailEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public CallDetailDTO transformInverse(final CallDetailEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model");
        final CallDetailDTO callDetailDTO = new CallDetailDTO();
        callDetailDTO.setIdentity(originModel.getIdentity());
        callDetailDTO.setCallDayTime(originModel.getCallDayTime());
        callDetailDTO.setCallDuration(originModel.getCallDuration());
        callDetailDTO.setCallType(originModel.getCallType().name());
        callDetailDTO.setKid(originModel.getKid());
        callDetailDTO.setTerminal(originModel.getTerminal());
        callDetailDTO.setPhoneNumber(originModel.getPhoneNumber());
        return callDetailDTO;
    }
}
