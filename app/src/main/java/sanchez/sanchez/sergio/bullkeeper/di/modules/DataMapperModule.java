package sanchez.sanchez.sergio.bullkeeper.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.AlertEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.AlertPageEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.AlertsStatisticsEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.CommentEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.CommentsBySocialMediaDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.DimensionEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.ImageEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.ParentEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SaveSocialMediaDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SchoolEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SentimentAnalysisStatisticsEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SocialMediaActivityStatisticsEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SocialMediaDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SocialMediaLikesStatisticsDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SonEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SonImageEntityDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.SaveSocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.AlertDTO;
import sanchez.sanchez.sergio.data.net.models.response.AlertsPageDTO;
import sanchez.sanchez.sergio.data.net.models.response.AlertsStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.CommentDTO;
import sanchez.sanchez.sergio.data.net.models.response.CommentsStatisticsBySocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.DimensionsStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.ParentDTO;
import sanchez.sanchez.sergio.data.net.models.response.SchoolDTO;
import sanchez.sanchez.sergio.data.net.models.response.SentimentAnalysisStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaActivityStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaLikesStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SonDTO;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.models.AlertsPageEntity;
import sanchez.sanchez.sergio.domain.models.AlertsStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.domain.models.CommentsStatisticsBySocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.DimensionEntity;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import sanchez.sanchez.sergio.domain.models.SentimentAnalysisStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaActivityStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaLikesStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;
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
    public AbstractDataMapper<ParentDTO, ParentEntity> provideParentEntityDataMapper(final ApiEndPointsHelper apiEndPointsHelper,
                                                                                     final IAppUtils appUtils) {
        return new ParentEntityDataMapper(apiEndPointsHelper, appUtils);
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
    public AbstractDataMapper<SonDTO, SonEntity> provideSonEntityDataMapper(final AbstractDataMapper<SchoolDTO, SchoolEntity> schoolEntityDataMapper,
                                                                            final ApiEndPointsHelper apiEndPointsHelper,
                                                                            final IAppUtils appUtils){
        return new SonEntityDataMapper(schoolEntityDataMapper, apiEndPointsHelper, appUtils);
    }

    /**
     * Provide Alerts Entity Data Mapper
     * @param sonDataMapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<AlertDTO, AlertEntity> provideAlertsEntityDataMapper(final AbstractDataMapper<SonDTO, SonEntity> sonDataMapper) {
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
}
