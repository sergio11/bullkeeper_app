package sanchez.sanchez.sergio.domain.interactor.children;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;
import sanchez.sanchez.sergio.domain.repository.IChildrenRepository;
import sanchez.sanchez.sergio.domain.repository.ISocialMediaRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Save Children Interact
 */
public final class SaveChildrenInteract extends UseCase<SaveChildrenInteract.Result, SaveChildrenInteract.Params> {

    private final IChildrenRepository childrenRepository;
    private final ISocialMediaRepository socialMediaRepository;
    private final IAppUtils appUtils;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param socialMediaRepository
     */
    public SaveChildrenInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                final IChildrenRepository childrenRepository, final IAppUtils appUtils,
                                final ISocialMediaRepository socialMediaRepository) {
        super(threadExecutor, postExecutionThread);
        this.childrenRepository = childrenRepository;
        this.appUtils = appUtils;
        this.socialMediaRepository = socialMediaRepository;
    }

    /**
     * Save Kid Information
     * @param params
     * @return
     */
    private Observable<KidEntity> saveKidInformation(final Params params) {
        return childrenRepository.saveSonInformation(params.getKid(), params.getFirstname(), params.getLastName(),
                params.getBirthday(), params.getSchool());
    }

    /**
     * Add Kid To Self Parent
     * @param params
     * @return
     */
    private Observable<KidEntity> addKidToSelfGuardian(final Params params){
        return childrenRepository.addSonToSelfGuardian(params.getFirstname(), params.getLastName(),
                params.getBirthday(), params.getSchool());
    }


    /**
     * Save Son Profile
     * @param params
     * @return
     */
    private Observable<KidEntity> saveKidProfile(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");

        return (appUtils.isValidString(params.getKid()) ? saveKidInformation(params):
                addKidToSelfGuardian(params)).flatMap(new Function<KidEntity, ObservableSource<KidEntity>>() {
            @Override
            public ObservableSource<KidEntity> apply(final KidEntity kidEntity) throws Exception {

                if(appUtils.isValidString(params.getProfileImage())) {

                    if(!appUtils.isValidString(kidEntity.getProfileImage()) ||
                            !kidEntity.getProfileImage().equalsIgnoreCase(params.getProfileImage())) {

                        return childrenRepository.uploadProfileImage(kidEntity.getIdentity(),
                                params.getProfileImage()).map(new Function<ImageEntity, KidEntity>() {
                            @Override
                            public KidEntity apply(ImageEntity imageEntity) throws Exception {
                                kidEntity.setProfileImage(imageEntity.getUrl());
                                return kidEntity;
                            }
                        });

                    } else {

                        return Observable.just(kidEntity);
                    }


                } else {

                    return Observable.just(kidEntity);
                }
            }
        });
    }


    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<Result> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.socialMediaEntities, "Social Media Entities can not be null");

        return saveKidProfile(params)
                .map(new Function<KidEntity, Result>() {

                    /**
                     *
                     * @param kidEntity
                     * @return
                     * @throws Exception
                     */
                    @Override
                    public Result apply(KidEntity kidEntity) throws Exception {
                        final Result result = new Result();
                        result.setKidEntity(kidEntity);
                        return result;
                    }

                })
                .flatMap(new Function<Result, ObservableSource<Result>>() {

                    /**
                     *
                     * @param result
                     * @return
                     * @throws Exception
                     */
                    @Override
                    public ObservableSource<Result> apply(final Result result) throws Exception {
                        return !params.socialMediaEntities.isEmpty() ?
                                socialMediaRepository.saveAllSocialMedia(result
                                            .getKidEntity().getIdentity(), params.socialMediaEntities)
                                        .map(new Function<List<SocialMediaEntity>, Result>() {
                                            @Override
                                            public Result apply(List<SocialMediaEntity> socialMediaEntities) throws Exception {
                                                result.setSocialMediaEntities(socialMediaEntities);
                                                return result;
                                            }
                                        }) : Observable.just(result);
                    }

                }).flatMap(new Function<Result, ObservableSource<Result>>() {

                    /**
                     *
                     * @param result
                     * @return
                     * @throws Exception
                     */
                    @Override
                    public ObservableSource<Result> apply(final Result result) throws Exception {
                        Preconditions.checkNotNull(result, "Result can not be null");
                        return childrenRepository.saveGuardians(params.getKid(),
                                params.getKidGuardianEntities()).map(new Function<List<KidGuardianEntity>, Result>() {
                            @Override
                            public Result apply(List<KidGuardianEntity> kidGuardianEntities) throws Exception {
                                result.setKidGuardianEntities(kidGuardianEntities);
                                return result;
                            }
                        });
                    }

                });
    }

    /**
     * Save Children Api Errors
     */
    public enum SaveChildrenApiErrors implements ISupportVisitable<SaveChildrenApiErrors.ISaveChildrenApiErrorVisitor> {

        /**
         * Validation Errors
         */
        VALIDATION_ERROR(){
            @Override
            public <E> void accept(ISaveChildrenApiErrorVisitor visitor, E data) {
                visitor.visitValidationError(this, (LinkedHashMap<String, List<LinkedHashMap<String, String>>>) data);
            }
        };

        /**
         * Save Children Information API Error Visitor
         */
        public interface ISaveChildrenApiErrorVisitor extends ISupportVisitor {

            /**
             * Visit Validation Error
             * @param apiErrors
             * @param errors
             */
            void visitValidationError(final SaveChildrenApiErrors apiErrors, final LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors);

        }
    }

    /**
     * Result
     */
    public static class Result {

        /**
         * Kid Entity
         */
        private KidEntity kidEntity;

        /**
         * Social Media Entities
         */
        private List<SocialMediaEntity> socialMediaEntities = new ArrayList<>();

        /**
         * Kid Guardian Entities
         */
        private List<KidGuardianEntity> kidGuardianEntities = new ArrayList<>();

        public Result(){}

        /**
         *
         * @param kidEntity
         * @param socialMediaEntities
         * @param kidGuardianEntities
         */
        public Result(final KidEntity kidEntity, final List<SocialMediaEntity> socialMediaEntities,
                      final  List<KidGuardianEntity> kidGuardianEntities) {
            this.kidEntity = kidEntity;
            this.socialMediaEntities = socialMediaEntities;
            this.kidGuardianEntities = kidGuardianEntities;
        }

        public KidEntity getKidEntity() {
            return kidEntity;
        }

        public void setKidEntity(KidEntity kidEntity) {
            this.kidEntity = kidEntity;
        }

        public List<SocialMediaEntity> getSocialMediaEntities() {
            return socialMediaEntities;
        }

        public void setSocialMediaEntities(List<SocialMediaEntity> socialMediaEntities) {
            this.socialMediaEntities = socialMediaEntities;
        }

        public List<KidGuardianEntity> getKidGuardianEntities() {
            return kidGuardianEntities;
        }

        public void setKidGuardianEntities(List<KidGuardianEntity> kidGuardianEntities) {
            this.kidGuardianEntities = kidGuardianEntities;
        }
    }


    /**
     * Params
     */
    public static class Params {

        private final String kid;
        private final String firstname;
        private final String lastName;
        private final String birthday;
        private final String school;
        private final String profileImage;
        private final List<SocialMediaEntity> socialMediaEntities;
        private final List<KidGuardianEntity> kidGuardianEntities;

        /***
         *
         * @param kid
         * @param firstname
         * @param lastName
         * @param birthday
         * @param school
         * @param profileImage
         * @param socialMediaEntities
         */
        private Params(final String kid, final String firstname, final String lastName,
                       final String birthday, final String school, final String profileImage,
                       final List<SocialMediaEntity> socialMediaEntities,
                       final List<KidGuardianEntity> kidGuardianEntities) {
            this.kid = kid;
            this.firstname = firstname;
            this.lastName = lastName;
            this.birthday = birthday;
            this.school = school;
            this.profileImage = profileImage;
            this.socialMediaEntities = socialMediaEntities;
            this.kidGuardianEntities = kidGuardianEntities;
        }

        public String getKid() {
            return kid;
        }

        public String getFirstname() {
            return firstname;
        }

        public String getLastName() {
            return lastName;
        }

        public String getBirthday() {
            return birthday;
        }

        public String getSchool() {
            return school;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public List<SocialMediaEntity> getSocialMediaEntities() {
            return socialMediaEntities;
        }

        public List<KidGuardianEntity> getKidGuardianEntities() {
            return kidGuardianEntities;
        }

        /**
         * Create
         * @param kid
         * @param firstname
         * @param lastName
         * @param birthday
         * @param school
         * @param profileImage
         * @param kidGuardianEntities
         * @return
         */
        public static Params create(final String kid, final String firstname, final String lastName,
                                    final String birthday, final String school, final String profileImage,
                                    final List<SocialMediaEntity> socialMediaEntities,
                                    final List<KidGuardianEntity> kidGuardianEntities) {
            return new Params(kid, firstname, lastName,
                    birthday, school, profileImage, socialMediaEntities, kidGuardianEntities);
        }
    }


}
