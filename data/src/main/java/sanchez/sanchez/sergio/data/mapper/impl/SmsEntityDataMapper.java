package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.SmsDTO;
import sanchez.sanchez.sergio.domain.models.SmsEntity;
import sanchez.sanchez.sergio.domain.models.SmsFolderNameEnum;
import sanchez.sanchez.sergio.domain.models.SmsReadStateEnum;

/**
 * Sms Entity Data Mapper
 */
public final class SmsEntityDataMapper extends AbstractDataMapper<SmsDTO, SmsEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public SmsEntity transform(final SmsDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final SmsEntity smsEntity = new SmsEntity();
        smsEntity.setIdentity(originModel.getIdentity());
        smsEntity.setAddress(originModel.getAddress());
        smsEntity.setDate(originModel.getDate());
        try {
            smsEntity.setFolderName(SmsFolderNameEnum.valueOf(originModel.getFolderName()));
        } catch (final Exception ex){
            smsEntity.setFolderName(null);
        }

        try {
            smsEntity.setReadState(SmsReadStateEnum.valueOf(originModel.getReadState()));
        } catch (final Exception ex) {
            smsEntity.setReadState(null);
        }
        smsEntity.setMessage(originModel.getMessage());
        smsEntity.setTerminal(originModel.getTerminal());
        smsEntity.setKid(originModel.getKid());
        return smsEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public SmsDTO transformInverse(final SmsEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model");
        final SmsDTO smsDTO = new SmsDTO();
        smsDTO.setIdentity(originModel.getIdentity());
        smsDTO.setAddress(originModel.getAddress());
        smsDTO.setDate(originModel.getDate());
        smsDTO.setFolderName(originModel.getFolderName().name());
        smsDTO.setKid(originModel.getKid());
        smsDTO.setMessage(originModel.getMessage());
        smsDTO.setTerminal(originModel.getTerminal());
        return smsDTO;
    }
}
