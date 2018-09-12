package sanchez.sanchez.sergio.domain.interactor.children;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.domain.repository.IChildrenRepository;
import sanchez.sanchez.sergio.domain.repository.ISocialMediaRepository;

/**
 * Get Information About The Child And Their Social Media Interact
 */
public final class GetInformationAboutTheChildAndTheirSocialMediaInteract extends UseCase<GetInformationAboutTheChildAndTheirSocialMediaInteract.Result,
        GetInformationAboutTheChildAndTheirSocialMediaInteract.Params> {

    /**
     * Children Repository
     */
    private final IChildrenRepository childrenRepository;

    /**
     * Social Media Repository
     */
    private final ISocialMediaRepository socialMediaRepository;


    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public GetInformationAboutTheChildAndTheirSocialMediaInteract(final IThreadExecutor threadExecutor,
                                                                  final IPostExecutionThread postExecutionThread,
                                                                  final IChildrenRepository childrenRepository,
                                                                  final ISocialMediaRepository socialMediaRepository) {
        super(threadExecutor, postExecutionThread);
        this.childrenRepository = childrenRepository;
        this.socialMediaRepository = socialMediaRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<Result> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");

        return Observable.zip(
                childrenRepository.getSonById(params.getSonId()),
                socialMediaRepository.getAllSocialMediaBySonId(params.getSonId())
                    .onErrorReturnItem(new ArrayList<SocialMediaEntity>()),
                new BiFunction<SonEntity, List<SocialMediaEntity>, Result>() {
                    @Override
                    public Result apply(SonEntity sonEntity, List<SocialMediaEntity> socialMediaEntities) throws Exception {
                        return new Result(sonEntity, socialMediaEntities);
                    }
                });
    }

    /**
     * Params
     */
    public static class Params {

        private final String sonId;

        public Params(String sonId) {
            this.sonId = sonId;
        }

        public String getSonId() {
            return sonId;
        }

        /**
         * Create
         * @param sonId
         * @return
         */
        public static Params create(final String sonId) {
            return new Params(sonId);
        }
    }

    /**
     * Result
     */
    public static class Result {

        private final SonEntity sonEntity;
        private final List<SocialMediaEntity> socialMediaEntities;

        /**
         * @param sonEntity
         * @param socialMediaEntities
         */
        public Result(SonEntity sonEntity, List<SocialMediaEntity> socialMediaEntities) {
            this.sonEntity = sonEntity;
            this.socialMediaEntities = socialMediaEntities;
        }

        public SonEntity getSonEntity() {
            return sonEntity;
        }

        public List<SocialMediaEntity> getSocialMediaEntities() {
            return socialMediaEntities;
        }
    }
}
