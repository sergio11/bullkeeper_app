package sanchez.sanchez.sergio.domain.interactor.children;

import com.fernandocejas.arrow.checks.Preconditions;

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
    private Observable<KidEntity> addKidToSelfParent(final Params params){
        return childrenRepository.addSonToSelfParentInteract(params.getFirstname(), params.getLastName(),
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
                addKidToSelfParent(params)).flatMap(new Function<KidEntity, ObservableSource<KidEntity>>() {
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
                .flatMap(new Function<KidEntity, ObservableSource<Result>>() {
                    @Override
                    public ObservableSource<Result> apply(final KidEntity kidEntity) throws Exception {
                        return !params.socialMediaEntities.isEmpty() ?
                                socialMediaRepository.saveAllSocialMedia(kidEntity.getIdentity(), params.socialMediaEntities)
                                .map(new Function<List<SocialMediaEntity>, Result>() {
                                    @Override
                                    public Result apply(List<SocialMediaEntity> socialMediaEntities) throws Exception {
                                        return new Result(kidEntity, socialMediaEntities);
                                    }
                                }) : Observable.just(new Result(kidEntity, params.socialMediaEntities));
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

        private final KidEntity kidEntity;
        private final List<SocialMediaEntity> socialMediaEntities;

        public Result(KidEntity kidEntity, List<SocialMediaEntity> socialMediaEntities) {
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
        private Params(String kid, String firstname, String lastName, String birthday, String school, String profileImage,
                       final List<SocialMediaEntity> socialMediaEntities) {
            this.kid = kid;
            this.firstname = firstname;
            this.lastName = lastName;
            this.birthday = birthday;
            this.school = school;
            this.profileImage = profileImage;
            this.socialMediaEntities = socialMediaEntities;
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

        /**
         * Create
         * @param kid
         * @param firstname
         * @param lastName
         * @param birthday
         * @param school
         * @param profileImage
         * @return
         */
        public static Params create(final String kid, final String firstname, final String lastName,
                                    final String birthday, final String school,
                                    final String profileImage, final List<SocialMediaEntity> socialMediaEntities) {
            return new Params(kid, firstname, lastName, birthday, school, profileImage, socialMediaEntities);
        }
    }


}
