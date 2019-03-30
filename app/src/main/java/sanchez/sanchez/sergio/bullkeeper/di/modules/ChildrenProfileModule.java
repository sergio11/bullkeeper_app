package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.children.GetInformationAboutTheChildAndTheirSocialMediaInteract;
import sanchez.sanchez.sergio.domain.interactor.children.SaveChildrenInteract;
import sanchez.sanchez.sergio.domain.repository.IChildrenRepository;
import sanchez.sanchez.sergio.domain.repository.IGuardianRepository;
import sanchez.sanchez.sergio.domain.repository.ISocialMediaRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * 3
 */
@Module(includes = { ChildrenModule.class, SocialMediaModule.class })
public class ChildrenProfileModule {

    /**
     * Get Information About The Child and their social media interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param guardianRepository
     * @param socialMediaRepository
     * @return
     */
    @Provides @PerActivity
    GetInformationAboutTheChildAndTheirSocialMediaInteract provideGetInformationAboutTheChildAndTheirSocialMediaInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                                                         final IGuardianRepository guardianRepository,
                                                                                                                         final ISocialMediaRepository socialMediaRepository){
        return new GetInformationAboutTheChildAndTheirSocialMediaInteract(threadExecutor, postExecutionThread, guardianRepository, socialMediaRepository);
    }

    /**
     * Provide Save Children Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param childrenRepository
     * @return
     */
    @Provides @PerActivity
    SaveChildrenInteract provideSaveChildrenInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                     final IChildrenRepository childrenRepository, final IAppUtils appUtils, final ISocialMediaRepository socialMediaRepository){
        return new SaveChildrenInteract(threadExecutor, postExecutionThread, childrenRepository, appUtils, socialMediaRepository);
    }
}
