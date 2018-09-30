package sanchez.sanchez.sergio.bullkeeper.di.modules;

import com.fernandocejas.arrow.checks.Preconditions;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.SaveSocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaDTO;
import sanchez.sanchez.sergio.data.net.services.ISocialMediaService;
import sanchez.sanchez.sergio.data.repository.SocialMediaRepositoryImpl;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;
import sanchez.sanchez.sergio.domain.repository.ISocialMediaRepository;

/**
 * Social Media Module
 */
@Module
public class SocialMediaModule {

    /**
     * Provide Social Media Service
     * @return
     */
    @Provides @PerActivity
    ISocialMediaService provideSocialMediaService(final Retrofit retrofit) {
        return retrofit.create(ISocialMediaService.class);
    }

    /**
     * Provide Social Media Repository
     * @param socialMediaService
     * @param socialMediaDataMapper
     * @return
     */
    @Provides @PerActivity
    ISocialMediaRepository provideSocialMediaRepository(final ISocialMediaService socialMediaService,
                                                        final AbstractDataMapper<SocialMediaDTO, SocialMediaEntity> socialMediaDataMapper,
                                                        final AbstractDataMapper<SaveSocialMediaDTO, SocialMediaEntity> socialMediaEntityAbstractDataMapper){
        Preconditions.checkNotNull(socialMediaService, "Social Media Service can not be null");
        Preconditions.checkNotNull(socialMediaDataMapper, "Social Media Data Mapper");
        Preconditions.checkNotNull(socialMediaEntityAbstractDataMapper , "Social Media Entity Abstract Data Mapper");
        return new SocialMediaRepositoryImpl(socialMediaService, socialMediaDataMapper, socialMediaEntityAbstractDataMapper);
    }

}
