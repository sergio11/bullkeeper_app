package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ContactDTO;
import sanchez.sanchez.sergio.data.net.services.IContactsService;
import sanchez.sanchez.sergio.data.repository.ContactsRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.contacts.DisableContactInteract;
import sanchez.sanchez.sergio.domain.interactor.contacts.GetContactDetailInteract;
import sanchez.sanchez.sergio.domain.interactor.contacts.GetContactListInteract;
import sanchez.sanchez.sergio.domain.models.ContactEntity;
import sanchez.sanchez.sergio.domain.repository.IContactsRepository;

/**
 * Contacts Module
 */
@Module
public class ContactsModule {

    /**
     * Provide Contacts Service
     * @param retrofit
     * @return
     */
    @Provides @PerActivity
    public IContactsService provideContactsService(final Retrofit retrofit) {
        return retrofit.create(IContactsService.class);
    }

    /**
     * Provide Contacts Repository
     * @param contactsService
     * @param contactEntityAbstractDataMapper
     * @return
     */
    @Provides @PerActivity
    public IContactsRepository provideContactsRepository(
            final IContactsService contactsService,
            final AbstractDataMapper<ContactDTO, ContactEntity>
                contactEntityAbstractDataMapper) {
        return new ContactsRepositoryImpl(contactsService, contactEntityAbstractDataMapper);
    }

    /**
     * Provide Get Contact List Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param contactsRepository
     * @return
     */
    @Provides @PerActivity
    public GetContactListInteract provideGetContactListInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IContactsRepository contactsRepository
    ){
        return new GetContactListInteract(threadExecutor, postExecutionThread, contactsRepository);
    }

    /**
     * Provide Get Contact Detail Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param contactsRepository
     * @return
     */
    @Provides @PerActivity
    public GetContactDetailInteract provideGetContactDetailInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IContactsRepository contactsRepository
    ){
        return new GetContactDetailInteract(threadExecutor, postExecutionThread, contactsRepository);
    }

    /**
     * Provide Disable Contact Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param contactsRepository
     * @return
     */
    @Provides @PerActivity
    public DisableContactInteract provideDisableContactInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IContactsRepository contactsRepository
    ){
        return new DisableContactInteract(threadExecutor, postExecutionThread, contactsRepository);
    }

}
