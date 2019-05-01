package sanchez.sanchez.sergio.bullkeeper.di.modules;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import sanchez.sanchez.sergio.bullkeeper.BuildConfig;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.PlaceSuggestionEntityDataMapper;
import sanchez.sanchez.sergio.data.net.interceptors.PlacesApiKeyInterceptor;
import sanchez.sanchez.sergio.data.net.models.response.PlaceSuggestionDTO;
import sanchez.sanchez.sergio.data.net.services.IPlacesService;
import sanchez.sanchez.sergio.data.repository.PlacesRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.places.SearchPlacesInteract;
import sanchez.sanchez.sergio.domain.models.PlaceSuggestionEntity;
import sanchez.sanchez.sergio.domain.repository.IPlacesRepository;

/**
 * Places Api Module
 */
@Module
public class PlacesApiModule {

    private final static String PLACES_API  = "https://places.cit.api.here.com/places/v1/";

    /**
     * Provide Places Api Key Interceptor
     * @return
     */
    @Singleton
    @Provides
    public PlacesApiKeyInterceptor providePlacesApiKeyInterceptor() {
        return new PlacesApiKeyInterceptor();
    }

    /**
     * Provide HTTP Client
     * @return
     */
    @Singleton
    @Provides
    @Named("PlacesApiOkHttpClient")
    public OkHttpClient provideHttpClient(final PlacesApiKeyInterceptor placesApiKeyInterceptor){
        return BuildConfig.DEBUG ?
                new OkHttpClient.Builder()
                        .readTimeout(0, TimeUnit.SECONDS)
                        .addInterceptor(placesApiKeyInterceptor)
                        .addNetworkInterceptor(new StethoInterceptor())
                        .build() :
                new OkHttpClient.Builder()
                        .readTimeout(0, TimeUnit.SECONDS)
                        .addInterceptor(placesApiKeyInterceptor)
                        .build();
    }

    /**
     * Provide Retrofit
     * @param client
     * @return
     */
    @Singleton
    @Provides
    @Named("PlacesApiRetrofit")
    public Retrofit provideRetrofit( @Named("PlacesApiOkHttpClient") final OkHttpClient client){
        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(PLACES_API)
                .client(client)
                .build();
    }

    /**
     * Provide Places Service
     * @param retrofit
     * @return
     */
    @Singleton
    @Provides
    public IPlacesService providePlacesService(
            @Named("PlacesApiRetrofit") final Retrofit retrofit) {
        return retrofit.create(IPlacesService.class);
    }

    /**
     * Provide Place Suggestion Entity Data Mapper
     * @return
     */
    @Provides @Singleton
    public AbstractDataMapper<PlaceSuggestionDTO, PlaceSuggestionEntity> providePlaceSuggestionDataMapper(){
        return new PlaceSuggestionEntityDataMapper();
    }

    /**
     * Provide Places Repository
     * @param placesService
     * @param placeSuggestionEntityAbstractDataMapper
     * @return
     */
    @Provides @Singleton
    public IPlacesRepository providePlacesRepository(
            final IPlacesService placesService,
            final AbstractDataMapper<PlaceSuggestionDTO, PlaceSuggestionEntity> placeSuggestionEntityAbstractDataMapper
    ){
        return new PlacesRepositoryImpl(placesService, placeSuggestionEntityAbstractDataMapper);
    }

    /**
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param placesRepository
     * @return
     */
    @Provides @Singleton
    public SearchPlacesInteract provideSearchPlacesInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IPlacesRepository placesRepository) {
        return new SearchPlacesInteract(threadExecutor, postExecutionThread, placesRepository);
    }

}
