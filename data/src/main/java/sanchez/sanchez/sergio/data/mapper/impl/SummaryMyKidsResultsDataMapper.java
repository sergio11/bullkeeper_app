package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.LocationDTO;
import sanchez.sanchez.sergio.data.net.models.response.SchoolDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.SummaryMyKidResultDTO;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.models.LocationEntity;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.SummaryMyKidResultEntity;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * Summary My Kids Results Data Mapper
 */
public final class SummaryMyKidsResultsDataMapper
        extends AbstractDataMapper<SummaryMyKidResultDTO, SummaryMyKidResultEntity> {

    /**
     * Location Entity Data Mapper
     */
    private final AbstractDataMapper<LocationDTO, LocationEntity> locationEntityAbstractDataMapper;

    /**
     * Social Media Entity Data Mapper
     */
    private final AbstractDataMapper<SocialMediaDTO, SocialMediaEntity> socialMediaEntityAbstractDataMapper;

    /**
     * School Entity Data Mapper
     */
    private final AbstractDataMapper<SchoolDTO, SchoolEntity> schoolEntityAbstractDataMapper;

    /**
     * Api End Points Helper
     */
    private final ApiEndPointsHelper apiEndPointsHelper;

    /**
     * App Utils
     */
    private final IAppUtils appUtils;


    /**
     *
     * @param locationEntityAbstractDataMapper
     * @param socialMediaEntityAbstractDataMapper
     * @param schoolEntityAbstractDataMapper
     * @param apiEndPointsHelper
     * @param appUtils
     */
    public SummaryMyKidsResultsDataMapper(
            final AbstractDataMapper<LocationDTO, LocationEntity> locationEntityAbstractDataMapper,
            final AbstractDataMapper<SocialMediaDTO, SocialMediaEntity> socialMediaEntityAbstractDataMapper,
            final AbstractDataMapper<SchoolDTO, SchoolEntity> schoolEntityAbstractDataMapper,
            final ApiEndPointsHelper apiEndPointsHelper,
            final IAppUtils appUtils) {
        this.locationEntityAbstractDataMapper = locationEntityAbstractDataMapper;
        this.socialMediaEntityAbstractDataMapper = socialMediaEntityAbstractDataMapper;
        this.schoolEntityAbstractDataMapper = schoolEntityAbstractDataMapper;
        this.apiEndPointsHelper = apiEndPointsHelper;
        this.appUtils = appUtils;
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
        summaryMyKidResultEntity.setIdentity(originModel.getIdentity());
        summaryMyKidResultEntity.setFirstName(originModel.getFirstName());
        summaryMyKidResultEntity.setLastName(originModel.getLastName());
        summaryMyKidResultEntity.setAge(originModel.getAge());
        summaryMyKidResultEntity.setBirthdate(originModel.getBirthdate());
        summaryMyKidResultEntity.setProfileImage(appUtils.isValidString(originModel.getProfileImage()) ?
                apiEndPointsHelper.getKidProfileUrl(originModel.getProfileImage()) : null );
        if(originModel.getSchool() != null)
            summaryMyKidResultEntity.setSchool(schoolEntityAbstractDataMapper.transform(originModel.getSchool()));
        if(originModel.getLocation() != null)
            summaryMyKidResultEntity.setLocation(locationEntityAbstractDataMapper.transform(originModel.getLocation()));
        if(originModel.getSocialMedias() != null)
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

        summaryMyKidResultDTO.setIdentity(originModel.getIdentity());
        summaryMyKidResultDTO.setFirstName(originModel.getFirstName());
        summaryMyKidResultDTO.setLastName(originModel.getLastName());
        summaryMyKidResultDTO.setAge(originModel.getAge());
        summaryMyKidResultDTO.setBirthdate(originModel.getBirthdate());
        summaryMyKidResultDTO.setProfileImage(originModel.getProfileImage());
        if(originModel.getSchool() != null)
            summaryMyKidResultDTO.setSchool(schoolEntityAbstractDataMapper.transformInverse(originModel.getSchool()));
        if(originModel.getLocation() != null)
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
        if(originModel.getSocialMediaEntityList() != null)
            summaryMyKidResultDTO.setSocialMedias(socialMediaEntityAbstractDataMapper.transformInverse(originModel.getSocialMediaEntityList()));

        return summaryMyKidResultDTO;

    }
}
