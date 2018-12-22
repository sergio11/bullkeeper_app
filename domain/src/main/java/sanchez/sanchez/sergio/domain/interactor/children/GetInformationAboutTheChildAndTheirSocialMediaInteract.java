package sanchez.sanchez.sergio.domain.interactor.children;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;
import sanchez.sanchez.sergio.domain.repository.IChildrenRepository;
import sanchez.sanchez.sergio.domain.repository.ISocialMediaRepository;

/**
 * Get Information About The Child and their Social Media
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
     * @param params
     * @return
     */
    @Override
    protected Observable<Result> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");

        return Observable.zip(
                childrenRepository.getKidById(params.getKid()),
                socialMediaRepository.getAllSocialMediaBySonId(params.getKid())
                        .onErrorReturnItem(new ArrayList<SocialMediaEntity>()),
                new BiFunction<KidEntity, List<SocialMediaEntity>, Result>() {
                    /**
                     * Apply
                     * @param kidEntity
                     * @param socialMediaEntities
                     * @return
                     * @throws Exception
                     */
                    @Override
                    public Result apply(final KidEntity kidEntity,
                                        final List<SocialMediaEntity> socialMediaEntities) throws Exception {
                        return new Result(kidEntity, socialMediaEntities);
                    }
                });

    }

    /**
     * Params
     */
    public static class Params {

        private final String kid;

        public Params(String kid) {
            this.kid = kid;
        }

        public String getKid() {
            return kid;
        }

        /**
         * Create
         * @param kid
         * @return
         */
        public static Params create(final String kid) {
            return new Params(kid);
        }
    }

    /**
     * Result
     */
    public static class Result {

        /**
         * Kid
         */
        private final KidEntity kidEntity;

        /**
         * Social Media Entities
         */
        private final List<SocialMediaEntity> socialMediaEntities;

        /**
         * @param kidEntity
         * @param socialMediaEntities
         */
        public Result(final KidEntity kidEntity,
                      final List<SocialMediaEntity> socialMediaEntities) {
            this.kidEntity = kidEntity;
            this.socialMediaEntities = socialMediaEntities;
        }

        public KidEntity getKidEntity() {
            return kidEntity;
        }

        public List<SocialMediaEntity> getSocialMediaEntities() {
            return socialMediaEntities;
        }

    }
}
