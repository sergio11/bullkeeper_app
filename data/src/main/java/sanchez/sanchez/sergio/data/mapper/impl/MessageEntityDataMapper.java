package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.MessageDTO;
import sanchez.sanchez.sergio.domain.models.MessageEntity;

/**
 * Message Entity Data Mapper
 */
public final class MessageEntityDataMapper extends AbstractDataMapper<MessageDTO, MessageEntity> {

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
        return messageDTO;
    }
}
