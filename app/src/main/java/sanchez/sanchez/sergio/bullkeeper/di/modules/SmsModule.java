package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.SmsDTO;
import sanchez.sanchez.sergio.data.net.services.ISmsService;
import sanchez.sanchez.sergio.data.repository.SmsRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.sms.GetSmsDetailInteract;
import sanchez.sanchez.sergio.domain.interactor.sms.GetSmsListInteract;
import sanchez.sanchez.sergio.domain.models.SmsEntity;
import sanchez.sanchez.sergio.domain.repository.ISmsRepository;

/**
 * Sms Module
 */
@Module
public class SmsModule {

    /**
     * Provide Sms Service
     * @param retrofit
     * @return
     */
    @Provides @PerActivity
    public ISmsService provideSmsService(final Retrofit retrofit){
        return retrofit.create(ISmsService.class);
    }

    /**
     *
     * @param smsService
     * @param smsEntityAbstractDataMapper
     * @return
     */
    @Provides @PerActivity
    public ISmsRepository provideSmsRepository(
            final ISmsService smsService,
            final AbstractDataMapper<SmsDTO, SmsEntity> smsEntityAbstractDataMapper){
        return new SmsRepositoryImpl(smsService, smsEntityAbstractDataMapper);
    }

    /**
     * Provide Get SMS List Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param smsRepository
     * @return
     */
    @Provides @PerActivity
    public GetSmsListInteract provideGetSmsListInteract(final IThreadExecutor threadExecutor,
                                                        final IPostExecutionThread postExecutionThread,
                                                        final ISmsRepository smsRepository){
        return new GetSmsListInteract(threadExecutor, postExecutionThread, smsRepository);
    }

    /**
     * Provide Get SMS Detail Interacts
     * @param threadExecutor
     * @param postExecutionThread
     * @param smsRepository
     * @return
     */
    @Provides @PerActivity
    public GetSmsDetailInteract provideGetSmsDetailInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final ISmsRepository smsRepository
    ){
        return new GetSmsDetailInteract(threadExecutor, postExecutionThread, smsRepository);
    }

}
