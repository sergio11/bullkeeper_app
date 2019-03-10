package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.KidDTO;
import sanchez.sanchez.sergio.data.net.models.response.LocationDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.SummaryMyKidResultDTO;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.models.LocationEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.SummaryMyKidResultEntity;

/**
 * Summary My Kids Results Data Mapper
 */
public final class SummaryMyKidsResultsDataMapper
        extends AbstractDataMapper<SummaryMyKidResultDTO, SummaryMyKidResultEntity> {

    /**
     * Kid Entity Data Mapper
     */
    private final AbstractDataMapper<KidDTO, KidEntity> kidEntityAbstractDataMapper;

    /**
     * Location Entity Data Mapper
     */
    private final AbstractDataMapper<LocationDTO, LocationEntity> locationEntityAbstractDataMapper;

    /**
     * Social Media Entity Data Mapper
     */
    private final AbstractDataMapper<SocialMediaDTO, SocialMediaEntity> socialMediaEntityAbstractDataMapper;

    /**
     *
     * @param kidEntityAbstractDataMapper
     * @param locationEntityAbstractDataMapper
     * @param socialMediaEntityAbstractDataMapper
     */
    public SummaryMyKidsResultsDataMapper(
            final AbstractDataMapper<KidDTO, KidEntity> kidEntityAbstractDataMapper,
            final AbstractDataMapper<LocationDTO, LocationEntity> locationEntityAbstractDataMapper,
            final AbstractDataMapper<SocialMediaDTO, SocialMediaEntity> socialMediaEntityAbstractDataMapper) {
        this.kidEntityAbstractDataMapper = kidEntityAbstractDataMapper;
        this.locationEntityAbstractDataMapper = locationEntityAbstractDataMapper;
        this.socialMediaEntityAbstractDataMapper = socialMediaEntityAbstractDataMapper;
    }

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public SummaryMyKidResultEntity transform(final SummaryMyKidResultDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final SummaryMyKidResultEntity summaryMyKidResultEntity = new SummaryMyKidResultEntity();
        summaryMyKidResultEntity.setKidEntity(kidEntityAbstractDataMapper.transform(originModel.getKid()));
        summaryMyKidResultEntity.setLocation(locationEntityAbstractDataMapper.transform(originModel.getLocation()));
        summaryMyKidResultEntity.setSocialMediaEntityList(socialMediaEntityAbstractDataMapper.transform(originModel.getSocialMedias()));
        summaryMyKidResultEntity.setTotalCommentsAdultContent(originModel.getTotalCommentsAdultContent());
        summaryMyKidResultEntity.setTotalCommentsAnalyzed(originModel.getTotalCommentsAnalyzed());
        summaryMyKidResultEntity.setTotalCommentsBullying(originModel.getTotalCommentsBullying());
        summaryMyKidResultEntity.setTotalCommentsDrugs(originModel.getTotalCommentsDrugs());
        summaryMyKidResultEntity.setTotalCommentsNegativeSentiment(originModel.getTotalCommentsNegativeSentiment());
        summaryMyKidResultEntity.setTotalCommentsNeutralSentiment(originModel.getTotalCommentsNeutralSentiment());
        summaryMyKidResultEntity.setTotalCommentsPositiveSentiment(originModel.getTotalCommentsPositiveSentiment());
        summaryMyKidResultEntity.setTotalDevices(originModel.getTotalDevices());
        summaryMyKidResultEntity.setTotalViolentComments(originModel.getTotalViolentComments());

        return summaryMyKidResultEntity;
    }


    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public SummaryMyKidResultDTO transformInverse(final SummaryMyKidResultEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final SummaryMyKidResultDTO summaryMyKidResultDTO = new SummaryMyKidResultDTO();
        summaryMyKidResultDTO.setKid(kidEntityAbstractDataMapper.transformInverse(originModel.getKidEntity()));
        summaryMyKidResultDTO.setLocation(locationEntityAbstractDataMapper.transformInverse(originModel.getLocation()));
        summaryMyKidResultDTO.setTotalCommentsAdultContent(originModel.getTotalCommentsAdultContent());
        summaryMyKidResultDTO.setTotalCommentsAnalyzed(originModel.getTotalCommentsAnalyzed());
        summaryMyKidResultDTO.setTotalCommentsBullying(originModel.getTotalCommentsBullying());
        summaryMyKidResultDTO.setTotalCommentsDrugs(originModel.getTotalCommentsDrugs());
        summaryMyKidResultDTO.setTotalCommentsNegativeSentiment(originModel.getTotalCommentsNegativeSentiment());
        summaryMyKidResultDTO.setTotalCommentsNeutralSentiment(originModel.getTotalCommentsNeutralSentiment());
        summaryMyKidResultDTO.setTotalCommentsPositiveSentiment(originModel.getTotalCommentsPositiveSentiment());
        summaryMyKidResultDTO.setTotalDevices(originModel.getTotalDevices());
        summaryMyKidResultDTO.setTotalViolentComments(originModel.getTotalViolentComments());
        summaryMyKidResultDTO.setSocialMedias(socialMediaEntityAbstractDataMapper.transformInverse(originModel.getSocialMediaEntityList()));

        return summaryMyKidResultDTO;

    }
}
