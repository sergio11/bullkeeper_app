package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.MessageDTO;
import sanchez.sanchez.sergio.data.net.models.response.PersonDTO;
import sanchez.sanchez.sergio.domain.models.MessageEntity;
import sanchez.sanchez.sergio.domain.models.PersonEntity;

/**
 * Message Entity Data Mapper
 */
public final class MessageEntityDataMapper extends AbstractDataMapper<MessageDTO, MessageEntity> {

    /**
     * Person Entity Mapper
     */
    private final AbstractDataMapper<PersonDTO, PersonEntity> personEntityAbstractDataMapper;

    /**
     *
     * @param personEntityAbstractDataMapper
     */
    public MessageEntityDataMapper(final AbstractDataMapper<PersonDTO, PersonEntity> personEntityAbstractDataMapper) {
        this.personEntityAbstractDataMapper = personEntityAbstractDataMapper;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public MessageEntity transform(MessageDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");
        final MessageEntity messageEntity = new MessageEntity();
        messageEntity.setIdentity(originModel.getIdentity());
        messageEntity.setConversation(originModel.getConversation());
        messageEntity.setCreateAt(originModel.getCreateAt());
        messageEntity.setText(originModel.getText());
        messageEntity.setFrom(personEntityAbstractDataMapper.transform(originModel.getFrom()));
        messageEntity.setTo(personEntityAbstractDataMapper.transform(originModel.getTo()));
        messageEntity.setViewed(originModel.isViewed());
        return messageEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public MessageDTO transformInverse(MessageEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model");
        final MessageDTO messageDTO = new MessageDTO();
        messageDTO.setIdentity(originModel.getIdentity());
        messageDTO.setConversation(originModel.getConversation());
        messageDTO.setCreateAt(originModel.getCreateAt());
        messageDTO.setText(originModel.getText());
        messageDTO.setFrom(personEntityAbstractDataMapper.transformInverse(originModel.getFrom()));
        messageDTO.setTo(personEntityAbstractDataMapper.transformInverse(originModel.getTo()));
        messageDTO.setViewed(originModel.isViewed());
        return messageDTO;
    }
}
