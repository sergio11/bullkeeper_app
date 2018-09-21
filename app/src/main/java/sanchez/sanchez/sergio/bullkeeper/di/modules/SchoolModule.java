package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.SchoolDTO;
import sanchez.sanchez.sergio.data.net.services.ISchoolService;
import sanchez.sanchez.sergio.data.repository.SchoolRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.school.SearchSchoolsInteract;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import sanchez.sanchez.sergio.domain.repository.ISchoolRepository;

/**
 * School Module
 */
@Module
public class SchoolModule {

    /**
     * Provide School Service
     * @return
     */
    @Provides @PerActivity
    ISchoolService provideSchoolService(final Retrofit retrofit){
        return retrofit.create(ISchoolService.class);
    }

    /**
     * Provide School Repository
     * @param schoolService
     * @param schoolEntityAbstractDataMapper
     * @return
     */
    @Provides @PerActivity
    ISchoolRepository provideSchoolRepository(final ISchoolService schoolService,
                                              final AbstractDataMapper<SchoolDTO, SchoolEntity> schoolEntityAbstractDataMapper) {
        return new SchoolRepositoryImpl(schoolService, schoolEntityAbstractDataMapper);
    }

    /**
     * Provide Search School Interact
     * @param schoolRepository
     * @return
     */
    @Provides @PerActivity
    SearchSchoolsInteract provideSeachSchoolsInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                      final ISchoolRepository schoolRepository) {
        return new SearchSchoolsInteract(threadExecutor, postExecutionThread, schoolRepository);
    }

}
