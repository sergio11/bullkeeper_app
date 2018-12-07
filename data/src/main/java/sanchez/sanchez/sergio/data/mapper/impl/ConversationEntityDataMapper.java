package sanchez.sanchez.sergio.data.mapper.impl;
import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ConversationDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidGuardianDTO;
import sanchez.sanchez.sergio.domain.models.ConversationEntity;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;

/**
 * Conversation Entity Data Mapper
 */
public final class ConversationEntityDataMapper extends AbstractDataMapper<ConversationDTO, ConversationEntity> {

    /**
     * Kid Guardian Data Mapper
     */
    private final AbstractDataMapper<KidGuardianDTO, KidGuardianEntity> kidGuardianEntityAbstractDataMapper;

    /**
     *
     * @param kidGuardianEntityAbstractDataMapper
     */
    public ConversationEntityDataMapper(final AbstractDataMapper<KidGuardianDTO, KidGuardianEntity> kidGuardianEntityAbstractDataMapper) {
        this.kidGuardianEntityAbstractDataMapper = kidGuardianEntityAbstractDataMapper;
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
        conversationEntity.setKidGuardian(kidGuardianEntityAbstractDataMapper
                .transform(originModel.getKidGuardian()));
        conversationEntity.setIdentity(originModel.getIdentity());
        conversationEntity.setCreateAt(originModel.getCreateAt());
        conversationEntity.setMessagesCount(originModel.getMessagesCount());
        conversationEntity.setUpdateAt(originModel.getUpdateAt());
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
        conversationDTO.setKidGuardian(kidGuardianEntityAbstractDataMapper
                .transformInverse(originModel.getKidGuardian()));
        conversationDTO.setIdentity(originModel.getIdentity());
        conversationDTO.setCreateAt(originModel.getCreateAt());
        conversationDTO.setMessagesCount(originModel.getMessagesCount());
        conversationDTO.setUpdateAt(originModel.getUpdateAt());
        return conversationDTO;
    }
}
