package sanchez.sanchez.sergio.bullkeeper.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.DimensionsStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.SonDTO;
import sanchez.sanchez.sergio.data.net.services.IChildrenService;
import sanchez.sanchez.sergio.data.repository.ChildrenRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.children.GetFourDimensionsStatisticsByChildInteract;
import sanchez.sanchez.sergio.domain.interactor.children.GetSonByIdInteract;
import sanchez.sanchez.sergio.domain.models.DimensionEntity;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.domain.repository.IChildrenRepository;

/**
 * Children Module
 */
@Module
public class ChildrenModule {

    /**
     * Provide Children Service
     * @return
     */
    @Provides @PerActivity
    IChildrenService provideChildrenService(final Retrofit retrofit) {
        return retrofit.create(IChildrenService.class);
    }

    /**
     * Provide Children Repository
     * @param childrenService
     * @return
     */
    @Provides @PerActivity
    IChildrenRepository provideChildrenRepository(final IChildrenService childrenService,
                                                  final AbstractDataMapper<SonDTO, SonEntity> sonDataMapper,
                                                  @Named("SonImageEntity") final AbstractDataMapper<ImageDTO, ImageEntity> imageDataMapper,
                                                  final AbstractDataMapper<DimensionsStatisticsDTO.DimensionDTO, DimensionEntity> dimensionDataMapper) {
        return new ChildrenRepositoryImpl(childrenService, sonDataMapper, imageDataMapper, dimensionDataMapper);
    }

    /**
     * Provide Get Son By Id Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param childrenRepository
     * @return
     */
    @Provides @PerActivity
    GetSonByIdInteract provideGetSonByIdInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                 final IChildrenRepository childrenRepository){
        return new GetSonByIdInteract(threadExecutor, postExecutionThread, childrenRepository);
    }

    /**
     * Provide Get Four Dimensions Statistics By Child Interact
     * @return
     */
    @Provides @PerActivity
    GetFourDimensionsStatisticsByChildInteract provideGetFourDimensionsStatisticsByChildInteract(final IThreadExecutor threadExecutor,
                                                                                                 final IPostExecutionThread postExecutionThread,
                                                                                                 final IChildrenRepository childrenRepository){
        return new GetFourDimensionsStatisticsByChildInteract(threadExecutor, postExecutionThread, childrenRepository);
    }

}
