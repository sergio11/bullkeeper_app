package sanchez.sanchez.sergio.domain.interactor.children;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;
import sanchez.sanchez.sergio.domain.repository.IGuardianRepository;
import sanchez.sanchez.sergio.domain.repository.ISocialMediaRepository;

/**
 * Get Information About The Child and their Social Media
 */
public final class GetInformationAboutTheChildAndTheirSocialMediaInteract extends UseCase<GetInformationAboutTheChildAndTheirSocialMediaInteract.Result,
        GetInformationAboutTheChildAndTheirSocialMediaInteract.Params> {

    /**
     * Guardian Repository
     */
    private final IGuardianRepository guardianRepository;

    /**
     * Social Media Repository
     */
    private final ISocialMediaRepository socialMediaRepository;

    /**
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param guardianRepository
     * @param socialMediaRepository
     */
    public GetInformationAboutTheChildAndTheirSocialMediaInteract(final IThreadExecutor threadExecutor,
                                                                  final IPostExecutionThread postExecutionThread,
                                                                  final IGuardianRepository guardianRepository,
                                                                  final ISocialMediaRepository socialMediaRepository) {
        super(threadExecutor, postExecutionThread);
        this.guardianRepository = guardianRepository;
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
                guardianRepository.getSupervisedChildConfirmedById(params.getKid()),
                socialMediaRepository.getAllSocialMediaBySonId(params.getKid())
                        .onErrorReturnItem(new ArrayList<SocialMediaEntity>()),
                new BiFunction<KidGuardianEntity, List<SocialMediaEntity>, Result>() {

                    /**
                     *
                     * @param kidGuardianEntity
                     * @param socialMediaEntities
                     * @return
                     * @throws Exception
                     */
                    @Override
                    public Result apply(
                            final KidGuardianEntity kidGuardianEntity,
                            final List<SocialMediaEntity> socialMediaEntities) throws Exception {
                        return new Result(kidGuardianEntity, socialMediaEntities);
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
         * Kid Guardian Entity
         */
        private final KidGuardianEntity KidGuardianEntity;

        /**
         * Social Media Entities
         */
        private final List<SocialMediaEntity> socialMediaEntities;

        /**
         * @param KidGuardianEntity
         * @param socialMediaEntities
         */
        public Result(final KidGuardianEntity KidGuardianEntity,
                      final List<SocialMediaEntity> socialMediaEntities) {
            this.KidGuardianEntity = KidGuardianEntity;
            this.socialMediaEntities = socialMediaEntities;
        }

        public KidGuardianEntity getKidGuardianEntity() {
            return KidGuardianEntity;
        }

        public List<SocialMediaEntity> getSocialMediaEntities() {
            return socialMediaEntities;
        }

    }
}
