package sanchez.sanchez.sergio.bullkeeper.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.impl.AppAllowedByScheduledEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.AppInstalledByTerminalDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.AppInstalledDetailDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.AppModelCategoryDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.AppModelDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.AppStatsEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.CallDetailDataMapper;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.AlertEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.AlertPageEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.AlertsStatisticsEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.AppInstalledDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.AppInstalledRuleDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.AuthenticationResponseEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.ChildrenOfSelfGuardianEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.CommentEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.CommentsBySocialMediaDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.ContactEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.ConversationEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.DayScheduledEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.DimensionEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.FunTimeEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.GeofenceAlertDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.GeofenceEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.ImageEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.KidGuardianEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.KidRequestEntityMapper;
import sanchez.sanchez.sergio.data.mapper.impl.LocationEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.MessageEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.MostActiveFriendsDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.GuardianEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.PersonEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.PhoneNumberBlockedEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SaveDayScheduledEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SaveFunTimeScheduledDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SaveSocialMediaDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.ScheduledBlockEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.ScheduledBlockStatusEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SchoolEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SentimentAnalysisStatisticsEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SmsEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SocialMediaActivityStatisticsEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SocialMediaDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SocialMediaLikesStatisticsDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.KidEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SonImageEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SupervisedChildrenEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.TerminalDetailEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.TerminalEntityDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.AppInstalledRuleDTO;
import sanchez.sanchez.sergio.data.net.models.request.SaveDayScheduledDTO;
import sanchez.sanchez.sergio.data.net.models.request.SaveFunTimeScheduledDTO;
import sanchez.sanchez.sergio.data.net.models.request.SaveScheduledBlockStatusDTO;
import sanchez.sanchez.sergio.data.net.models.request.SaveSocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.AlertDTO;
import sanchez.sanchez.sergio.data.net.models.response.AlertsPageDTO;
import sanchez.sanchez.sergio.data.net.models.response.AlertsStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppAllowedByScheduledDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppInstalledByTerminalDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppInstalledDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppInstalledDetailDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppModelCategoryDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppModelDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppStatsDTO;
import sanchez.sanchez.sergio.data.net.models.response.CallDetailDTO;
import sanchez.sanchez.sergio.data.net.models.response.ChildrenOfSelfGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.CommentDTO;
import sanchez.sanchez.sergio.data.net.models.response.CommentsStatisticsBySocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.ContactDTO;
import sanchez.sanchez.sergio.data.net.models.response.ConversationDTO;
import sanchez.sanchez.sergio.data.net.models.response.DayScheduledDTO;
import sanchez.sanchez.sergio.data.net.models.response.DimensionsStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.FunTimeScheduledDTO;
import sanchez.sanchez.sergio.data.net.models.response.GeofenceAlertDTO;
import sanchez.sanchez.sergio.data.net.models.response.GeofenceDTO;
import sanchez.sanchez.sergio.data.net.models.response.GuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.JwtAuthenticationResponseDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidRequestDTO;
import sanchez.sanchez.sergio.data.net.models.response.LocationDTO;
import sanchez.sanchez.sergio.data.net.models.response.MessageDTO;
import sanchez.sanchez.sergio.data.net.models.response.MostActiveFriendsDTO;
import sanchez.sanchez.sergio.data.net.models.response.PersonDTO;
import sanchez.sanchez.sergio.data.net.models.response.PhoneNumberBlockedDTO;
import sanchez.sanchez.sergio.data.net.models.response.ScheduledBlockDTO;
import sanchez.sanchez.sergio.data.net.models.response.SchoolDTO;
import sanchez.sanchez.sergio.data.net.models.response.SentimentAnalysisStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SmsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaActivityStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaLikesStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidDTO;
import sanchez.sanchez.sergio.data.net.models.response.SupervisedChildrenDTO;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDTO;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDetailDTO;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.models.AlertsPageEntity;
import sanchez.sanchez.sergio.domain.models.AlertsStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.AppAllowedByScheduledEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledByTerminalEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledDetailEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledRuleEntity;
import sanchez.sanchez.sergio.domain.models.AppModelCategoryEntity;
import sanchez.sanchez.sergio.domain.models.AppModelEntity;
import sanchez.sanchez.sergio.domain.models.AppStatsEntity;
import sanchez.sanchez.sergio.domain.models.AuthenticationResponseEntity;
import sanchez.sanchez.sergio.domain.models.CallDetailEntity;
import sanchez.sanchez.sergio.domain.models.ChildrenOfSelfGuardianEntity;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.domain.models.CommentsStatisticsBySocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.ContactEntity;
import sanchez.sanchez.sergio.domain.models.ConversationEntity;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;
import sanchez.sanchez.sergio.domain.models.DimensionEntity;
import sanchez.sanchez.sergio.domain.models.FunTimeScheduledEntity;
import sanchez.sanchez.sergio.domain.models.GeofenceAlertEntity;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;
import sanchez.sanchez.sergio.domain.models.LocationEntity;
import sanchez.sanchez.sergio.domain.models.MessageEntity;
import sanchez.sanchez.sergio.domain.models.MostActiveFriendsEntity;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.models.PersonEntity;
import sanchez.sanchez.sergio.domain.models.PhoneNumberBlockedEntity;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockStatusEntity;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import sanchez.sanchez.sergio.domain.models.SentimentAnalysisStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SmsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaActivityStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaLikesStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;
import sanchez.sanchez.sergio.domain.models.TerminalDetailEntity;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * Data Mapper Module
 */
@Module
public class DataMapperModule {

    /**
     * Provide Parent Entity Data Mapper
     * @param apiEndPointsHelper
     * @param appUtils
     * @return
     */
    @Provides
    @PerActivity
    public AbstractDataMapper<GuardianDTO, GuardianEntity> provideParentEntityDataMapper(final ApiEndPointsHelper apiEndPointsHelper,
                                                                                         final IAppUtils appUtils) {
        return new GuardianEntityDataMapper(apiEndPointsHelper, appUtils);
    }

    /**
     * Provide School Entity DataMapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<SchoolDTO, SchoolEntity> provideSchoolEntityDataMapper(){
        return new SchoolEntityDataMapper();
    }

    /**
     * Provide Image Entity DataMapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<ImageDTO, ImageEntity> provideImageEntityDataMapper(){
        return new ImageEntityDataMapper();
    }

    /**
     * Provide Image Entity DataMapper
     * @return
     */
    @Provides @PerActivity @Named("SonImageEntity")
    public AbstractDataMapper<ImageDTO, ImageEntity> provideSonImageEntityDataMapper(final ApiEndPointsHelper apiEndPointsHelper){
        return new SonImageEntityDataMapper(apiEndPointsHelper);
    }



    /**
     * Provide Son Entity Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<KidDTO, KidEntity> provideSonEntityDataMapper(final AbstractDataMapper<SchoolDTO, SchoolEntity> schoolEntityDataMapper,
                                                                            final ApiEndPointsHelper apiEndPointsHelper,
                                                                            final IAppUtils appUtils,
                                                                            final AbstractDataMapper<TerminalDTO, TerminalEntity> terminalEntityAbstractDataMapper){
        return new KidEntityDataMapper(schoolEntityDataMapper, terminalEntityAbstractDataMapper, apiEndPointsHelper, appUtils);
    }

    /**
     * Provide Alerts Entity Data Mapper
     * @param sonDataMapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<AlertDTO, AlertEntity> provideAlertsEntityDataMapper(final AbstractDataMapper<KidDTO, KidEntity> sonDataMapper) {
        return new AlertEntityDataMapper(sonDataMapper);
    }

    /**
     * Provide Alerts Page Entity Data Mapper
     * @param alertsDataMapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<AlertsPageDTO, AlertsPageEntity> provideAlertsPageEntityDataMapper(final AbstractDataMapper<AlertDTO, AlertEntity> alertsDataMapper){
        return new AlertPageEntityDataMapper(alertsDataMapper);
    }

    /**
     * Provide Social Media Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<SocialMediaDTO, SocialMediaEntity> provideSocialMediaDataMapper(){
        return new SocialMediaDataMapper();
    }

    /**
     * Provide Save Social Media Media Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<SaveSocialMediaDTO, SocialMediaEntity> provideSaveSocialMediaDataMapper(){
        return new SaveSocialMediaDataMapper();
    }

    /**
     * Provide Dimension Entity Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<DimensionsStatisticsDTO.DimensionDTO, DimensionEntity> provideDimensionEntityDataMapper(){
        return new DimensionEntityDataMapper();
    }

    /**
     * Provide Comments Statistics By Social Media
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<CommentsStatisticsBySocialMediaDTO, CommentsStatisticsBySocialMediaEntity> provideCommentsStatisticsBySocialMediaDataMapper() {
        return new CommentsBySocialMediaDataMapper();
    }

    /**
     * Provide Social Media Activity Statistics Entity DataMapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<SocialMediaActivityStatisticsDTO, SocialMediaActivityStatisticsEntity> provideSocialMediaActivityStatisticsEntityDataMapper(){
        return new SocialMediaActivityStatisticsEntityDataMapper();
    }

    /**
     * Provide Sentiment Analysis Statistics Entity Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<SentimentAnalysisStatisticsDTO,
            SentimentAnalysisStatisticsEntity> provideSentimentAnalysisStatisticsEntityDataMapper(){
        return new SentimentAnalysisStatisticsEntityDataMapper();
    }

    /**
     * Provide Alerts Statistics Enitiy Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<AlertsStatisticsDTO, AlertsStatisticsEntity> provideAlertsStatisticsEntityDataMapper(){
        return new AlertsStatisticsEntityDataMapper();
    }

    /**
     * Social Media Likes Statistics Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<SocialMediaLikesStatisticsDTO,
            SocialMediaLikesStatisticsEntity> provideSocialMediaLikesStatisticsDataMapper(){
        return new SocialMediaLikesStatisticsDataMapper();
    }

    /**
     * Provide Comments Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<CommentDTO, CommentEntity> provideCommentsDataMapper(){
        return new CommentEntityDataMapper();
    }

    /**
     * Provide Most Active Friends
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<MostActiveFriendsDTO, MostActiveFriendsEntity> provideMostActiveFriends(){
        return new MostActiveFriendsDataMapper();
    }

    /**
     * Provide Scheduled Block Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<ScheduledBlockDTO, ScheduledBlockEntity> provideScheduledBlockEntityDataMapper(
            final ApiEndPointsHelper apiEndPointsHelper,
            final IAppUtils appUtils,
            final AbstractDataMapper<AppAllowedByScheduledDTO, AppAllowedByScheduledEntity> appAllowedByScheduledMapper,
            final AbstractDataMapper<GeofenceDTO, GeofenceEntity> geofenceEntityAbstractDataMapper
    ){
        return new ScheduledBlockEntityDataMapper(apiEndPointsHelper, appUtils, appAllowedByScheduledMapper, geofenceEntityAbstractDataMapper);
    }

    /**
     * Provide Scheduled Block Status Entity Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<SaveScheduledBlockStatusDTO, ScheduledBlockStatusEntity> provideScheduledBlockStatusEntityDataMapper(){
        return new ScheduledBlockStatusEntityDataMapper();
    }

    /**
     * Provide App Installed Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<AppInstalledDTO, AppInstalledEntity> provideAppInstalledDataMapper(){
        return new AppInstalledDataMapper();
    }

    /**
     * Provide App Installed Rule Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<AppInstalledRuleDTO, AppInstalledRuleEntity> provideAppInstalledRuleDataMapper(){
        return new AppInstalledRuleDataMapper();
    }

    /**
     * Provide Terminal Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<TerminalDTO, TerminalEntity> provideTerminalDataMapper(){
        return new TerminalEntityDataMapper();
    }

    /**
     * Provide Terminal Detail DataMapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<TerminalDetailDTO, TerminalDetailEntity> provideTerminalDetailDataMapper(){
        return new TerminalDetailEntityDataMapper();
    }

    /**
     * Provide Supervised Children Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<SupervisedChildrenDTO, SupervisedChildrenEntity> provideSupervisedChildrenEntityDataMapper(
            final AbstractDataMapper<KidDTO, KidEntity> kidEntityAbstractDataMapper
    ){
        return new SupervisedChildrenEntityDataMapper(kidEntityAbstractDataMapper);
    }

    /**
     * Provide Children Of Self Guardian
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<ChildrenOfSelfGuardianDTO, ChildrenOfSelfGuardianEntity> provideChildrenOfSelfGuardianDataMapper(
            final AbstractDataMapper<SupervisedChildrenDTO, SupervisedChildrenEntity> supervisedChildrenEntityAbstractDataMapper
    ){
        return new ChildrenOfSelfGuardianEntityDataMapper(supervisedChildrenEntityAbstractDataMapper);
    }

    /**
     * Provide Kid Guardian Data Mapper
     * @param kidEntityAbstractDataMapper
     * @param guardianEntityAbstractDataMapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<KidGuardianDTO, KidGuardianEntity> provideKidGuardianDataMapper(
            final AbstractDataMapper<KidDTO, KidEntity> kidEntityAbstractDataMapper,
            final AbstractDataMapper<GuardianDTO, GuardianEntity> guardianEntityAbstractDataMapper
    ){
        return new KidGuardianEntityDataMapper(kidEntityAbstractDataMapper, guardianEntityAbstractDataMapper);
    }

    /**
     * Provide Conversation Data Mapper
     * @param kidGuardianEntityAbstractDataMapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<ConversationDTO, ConversationEntity> provideConversationDataMapper(
            final AbstractDataMapper<KidGuardianDTO, KidGuardianEntity> kidGuardianEntityAbstractDataMapper
    ) {
        return new ConversationEntityDataMapper(kidGuardianEntityAbstractDataMapper);
    }


    /**
     * Provide Person Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<PersonDTO, PersonEntity> providePersonDataMapper(
            final ApiEndPointsHelper apiEndPointsHelper,
            final IAppUtils appUtils
    ){
        return new PersonEntityDataMapper(apiEndPointsHelper, appUtils);
    }

    /**
     * Provide Message Data Mapper
     * @param personEntityAbstractDataMapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<MessageDTO, MessageEntity> provideMessageDataMapper(
        final AbstractDataMapper<PersonDTO, PersonEntity> personEntityAbstractDataMapper
    ){
        return new MessageEntityDataMapper(personEntityAbstractDataMapper);
    }

    /**
     * Provide Authentication Response Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<JwtAuthenticationResponseDTO, AuthenticationResponseEntity> provideAuthenticationResponse(){
        return new AuthenticationResponseEntityDataMapper();
    }

    /**
     * Provide Sms Entity Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<SmsDTO, SmsEntity> provideSmsEntityDataMapper(){
        return new SmsEntityDataMapper();
    }

    /**
     * Provide Call Detail Entity Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<CallDetailDTO, CallDetailEntity> provideCallDetailEntityDataMapper(){
        return new CallDetailDataMapper();
    }

    /**
     * Provide Contact Entity Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<ContactDTO, ContactEntity> provideContactEntityDataMapper(){
        return new ContactEntityDataMapper();
    }

    /**
     * Provide Phone Number Blocked Entity Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<PhoneNumberBlockedDTO, PhoneNumberBlockedEntity> providePhoneNumberBlockedEntityDataMapper(){
        return new PhoneNumberBlockedEntityDataMapper();
    }

    /**
     * Provide Location Entity Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<LocationDTO, LocationEntity> provideLocationEntityDataMapper(){
        return new LocationEntityDataMapper();
    }

    /**
     * Provide Kid Request Entity Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<KidRequestDTO, KidRequestEntity> provideKidRequestEntityMapper(
            final AbstractDataMapper<KidDTO, KidEntity> kidEntityAbstractDataMapper,
            final AbstractDataMapper<TerminalDTO, TerminalEntity> terminalEntityAbstractDataMapper,
            final AbstractDataMapper<LocationDTO, LocationEntity> locationEntityAbstractDataMapper
    ){
        return new KidRequestEntityMapper(kidEntityAbstractDataMapper,
                terminalEntityAbstractDataMapper, locationEntityAbstractDataMapper);
    }

    /**
     * Provide App Stats Entity Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<AppStatsDTO, AppStatsEntity> provideAppStatsEntityMapper() {
        return new AppStatsEntityDataMapper();
    }

    /**
     * Provide Geofence Entity Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<GeofenceDTO, GeofenceEntity> provideGeofenceEntityDataMapper(){
        return new GeofenceEntityDataMapper();
    }

    /**
     * Provide App Allowed By Scheduled Entity
     * @param appInstalledEntityAbstractDataMapper
     * @param terminalEntityAbstractDataMapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<AppAllowedByScheduledDTO, AppAllowedByScheduledEntity> provideAppAllowedByScheduledEntityDataMapper(
            final AbstractDataMapper<AppInstalledDTO, AppInstalledEntity> appInstalledEntityAbstractDataMapper,
            final AbstractDataMapper<TerminalDTO, TerminalEntity> terminalEntityAbstractDataMapper
    ){
        return new AppAllowedByScheduledEntityDataMapper(appInstalledEntityAbstractDataMapper, terminalEntityAbstractDataMapper);
    }

    /**
     *
     * @param terminalEntityAbstractDataMapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<AppInstalledByTerminalDTO, AppInstalledByTerminalEntity> provideAppInstalledByTerminalDataMapper(
            final AbstractDataMapper<TerminalDTO, TerminalEntity> terminalEntityAbstractDataMapper
    ){
        return new AppInstalledByTerminalDataMapper(terminalEntityAbstractDataMapper);
    }

    /**
     * Provide Day Scheduled Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<DayScheduledDTO, DayScheduledEntity> provideDayScheduledDataMapper(){
        return new DayScheduledEntityDataMapper();
    }

    /**
     * Provide Fun Time Entity Data Mapper
     * @param dtoDayScheduledEntityAbstractDataMapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<FunTimeScheduledDTO, FunTimeScheduledEntity> provideFunTimeEntityDataMapper(
            final AbstractDataMapper<DayScheduledDTO, DayScheduledEntity> dtoDayScheduledEntityAbstractDataMapper
    ){
        return new FunTimeEntityDataMapper(dtoDayScheduledEntityAbstractDataMapper);
    }


    /**
     * Provide Day Scheduled Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<DayScheduledEntity, SaveDayScheduledDTO> provideSaveDayScheduledDataMapper(){
        return new SaveDayScheduledEntityDataMapper();
    }

    /**
     * Provide Fun Time Scheduled Data Mapper
     * @param dayScheduledDTOAbstractDataMapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<FunTimeScheduledEntity, SaveFunTimeScheduledDTO> provideFunTimeScheduledDataMapper(
            final AbstractDataMapper<DayScheduledEntity, SaveDayScheduledDTO> dayScheduledDTOAbstractDataMapper
    ){
        return new SaveFunTimeScheduledDataMapper(dayScheduledDTOAbstractDataMapper);
    }

    /**
     * Provide Geofence Alert Entity Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<GeofenceAlertDTO, GeofenceAlertEntity> provideGeofenceAlertEntityDataMapper(){
        return new GeofenceAlertDataMapper();
    }

    /**
     * Provide App Model Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<AppModelDTO, AppModelEntity> provideAppModelDataMapper(
            final AbstractDataMapper<AppModelCategoryDTO, AppModelCategoryEntity> appModelCategoryEntityAbstractDataMapper
    ){
        return new AppModelDataMapper(appModelCategoryEntityAbstractDataMapper);
    }

    /**
     * Provide App Installed Detail DTO
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<AppInstalledDetailDTO, AppInstalledDetailEntity> provideAppInstalledDetailDataMapper(
            final AbstractDataMapper<AppModelDTO, AppModelEntity> appModelEntityAbstractDataMapper
    ){
        return new AppInstalledDetailDataMapper(appModelEntityAbstractDataMapper);
    }

    /**
     * Provide App Model Category Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<AppModelCategoryDTO, AppModelCategoryEntity> provideAppModelCategoryDataMapper(){
        return new AppModelCategoryDataMapper();
    }

}
