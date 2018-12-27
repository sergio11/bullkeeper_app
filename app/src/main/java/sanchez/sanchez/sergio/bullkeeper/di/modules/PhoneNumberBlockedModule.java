package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.PhoneNumberBlockedDTO;
import sanchez.sanchez.sergio.data.net.services.IPhoneNumbersBlockedService;
import sanchez.sanchez.sergio.data.repository.PhoneNumbersBlockedRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.phonenumbersblocked.AddPhoneNumbersBlockedInteract;
import sanchez.sanchez.sergio.domain.interactor.phonenumbersblocked.DeleteAllPhoneNumbersBlockedInteract;
import sanchez.sanchez.sergio.domain.interactor.phonenumbersblocked.DeletePhoneNumbersBlockedInteract;
import sanchez.sanchez.sergio.domain.interactor.phonenumbersblocked.GetPhoneNumbersBlockedInteract;
import sanchez.sanchez.sergio.domain.models.PhoneNumberBlockedEntity;
import sanchez.sanchez.sergio.domain.repository.IPhoneNumbersBlockedRepository;

/**
 * Phone Number Blocked Module
 */
@Module
public class PhoneNumberBlockedModule {

    /**
     * Provide Phone Number Blocked Service
     * @param retrofit
     * @return
     */
    @Provides @PerActivity
    public IPhoneNumbersBlockedService providePhoneNumbersBlockedService(final Retrofit retrofit) {
        return retrofit.create(IPhoneNumbersBlockedService.class);
    }

    /**
     * Provide Phone Numbers Blocked Repository
     * @param phoneNumbersBlockedService
     * @param phoneNumberBlockedEntityAbstractDataMapper
     * @return
     */
    @Provides @PerActivity
    public IPhoneNumbersBlockedRepository providePhoneNumbersBlockedRepository(
            final IPhoneNumbersBlockedService phoneNumbersBlockedService,
            final AbstractDataMapper<PhoneNumberBlockedDTO, PhoneNumberBlockedEntity>
                phoneNumberBlockedEntityAbstractDataMapper
    ) {
        return new PhoneNumbersBlockedRepositoryImpl(phoneNumbersBlockedService, phoneNumberBlockedEntityAbstractDataMapper);
    }

    /**
     * Provide Get Phone Numbers Blocked Interact
     * @return
     */
    @Provides @PerActivity
    public GetPhoneNumbersBlockedInteract provideGetPhoneNumbersBlockedInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IPhoneNumbersBlockedRepository phoneNumbersBlockedRepository
    ){
        return new GetPhoneNumbersBlockedInteract(threadExecutor, postExecutionThread, phoneNumbersBlockedRepository);
    }

    /**
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param phoneNumbersBlockedRepository
     * @return
     */
    @Provides @PerActivity
    public AddPhoneNumbersBlockedInteract provideAddPhoneNumbersBlockedInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IPhoneNumbersBlockedRepository phoneNumbersBlockedRepository
    ){
        return new AddPhoneNumbersBlockedInteract(threadExecutor, postExecutionThread, phoneNumbersBlockedRepository);
    }

    /**
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param phoneNumbersBlockedRepository
     * @return
     */
    @Provides @PerActivity
    public DeletePhoneNumbersBlockedInteract provideDeletePhoneNumbersBlockedInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IPhoneNumbersBlockedRepository phoneNumbersBlockedRepository
    ) {
        return new DeletePhoneNumbersBlockedInteract(threadExecutor, postExecutionThread, phoneNumbersBlockedRepository);
    }

    /**
     * Provide Delete All Phone Numbers Blocked Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param phoneNumbersBlockedRepository
     * @return
     */
    @Provides @PerActivity
    public DeleteAllPhoneNumbersBlockedInteract provideDeleteAllPhoneNumbersBlockedInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IPhoneNumbersBlockedRepository phoneNumbersBlockedRepository
    ) {
        return new DeleteAllPhoneNumbersBlockedInteract(threadExecutor, postExecutionThread, phoneNumbersBlockedRepository);
    }

}
