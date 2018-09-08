package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.AlertEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.AlertPageEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.ImageEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.ParentEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SchoolEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SocialMediaDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SonEntityDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AlertDTO;
import sanchez.sanchez.sergio.data.net.models.response.AlertsPageDTO;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.ParentDTO;
import sanchez.sanchez.sergio.data.net.models.response.SchoolDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.SonDTO;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.models.AlertsPageEntity;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;

/**
 * Data Mapper Module
 */
@Module
public class DataMapperModule {

    /**
     * Provide Parent Entity Data Mapper
     * @return
     */
    @Provides
    @PerActivity
    public AbstractDataMapper<ParentDTO, ParentEntity> provideParentEntityDataMapper(final ApiEndPointsHelper apiEndPointsHelper) {
        return new ParentEntityDataMapper(apiEndPointsHelper);
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
     * Provide Son Entity Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<SonDTO, SonEntity> provideSonEntityDataMapper(final AbstractDataMapper<SchoolDTO, SchoolEntity> schoolEntityDataMapper,
                                                                            final ApiEndPointsHelper apiEndPointsHelper){
        return new SonEntityDataMapper(schoolEntityDataMapper, apiEndPointsHelper);
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

}
