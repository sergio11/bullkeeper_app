package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ConversationDTO;
import sanchez.sanchez.sergio.data.net.models.response.PersonDTO;
import sanchez.sanchez.sergio.domain.models.ConversationEntity;
import sanchez.sanchez.sergio.domain.models.PersonEntity;

/**
 * Conversation Entity Data Mapper
 */
public final class ConversationEntityDataMapper extends AbstractDataMapper<ConversationDTO, ConversationEntity> {

    /**
     * Kid Guardian Data Mapper
     */
    private final AbstractDataMapper<PersonDTO, PersonEntity> personEntityAbstractDataMapper;

    /**
     *
     * @param personEntityAbstractDataMapper
     */
    public ConversationEntityDataMapper(final AbstractDataMapper<PersonDTO, PersonEntity> personEntityAbstractDataMapper) {
        this.personEntityAbstractDataMapper = personEntityAbstractDataMapper;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public ConversationEntity transform(ConversationDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model");
        final ConversationEntity conversationEntity = new ConversationEntity();
        conversationEntity.setIdentity(originModel.getIdentity());
        conversationEntity.setCreateAt(originModel.getCreateAt());
        conversationEntity.setMessagesCount(originModel.getMessagesCount());
        conversationEntity.setUpdateAt(originModel.getUpdateAt());
        conversationEntity.setMemberOne(personEntityAbstractDataMapper
                .transform(originModel.getMemberOne()));
        conversationEntity.setMemberTwo(personEntityAbstractDataMapper
                .transform(originModel.getMemberTwo()));
        return conversationEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public ConversationDTO transformInverse(ConversationEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model");
        final ConversationDTO conversationDTO = new ConversationDTO();
        conversationDTO.setIdentity(originModel.getIdentity());
        conversationDTO.setCreateAt(originModel.getCreateAt());
        conversationDTO.setMessagesCount(originModel.getMessagesCount());
        conversationDTO.setUpdateAt(originModel.getUpdateAt());
        conversationDTO.setMemberOne(personEntityAbstractDataMapper
                .transformInverse(originModel.getMemberOne()));
        conversationDTO.setMemberTwo(personEntityAbstractDataMapper
                .transformInverse(originModel.getMemberTwo()));
        return conversationDTO;
    }
}
