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
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.domain.repository.IChildrenRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Save Children Interact
 */
public final class SaveChildrenInteract extends UseCase<SonEntity, SaveChildrenInteract.Params> {

    private final IChildrenRepository childrenRepository;
    private final IAppUtils appUtils;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public SaveChildrenInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                final IChildrenRepository childrenRepository, final IAppUtils appUtils) {
        super(threadExecutor, postExecutionThread);
        this.childrenRepository = childrenRepository;
        this.appUtils = appUtils;
    }

    /**
     * Save Son Information
     * @param params
     * @return
     */
    private Observable<SonEntity> saveSonInformation(final Params params) {
        return childrenRepository.saveSonInformation(params.getSonId(), params.getFirstname(), params.getLastName(),
                params.getBirthday(), params.getSchool());
    }

    /**
     * Add To Self Parent
     * @param params
     * @return
     */
    private Observable<SonEntity> addSonToSelfParent(final Params params){
        return childrenRepository.addSonToSelfParentInteract(params.getFirstname(), params.getLastName(),
                params.getBirthday(), params.getSchool());
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<SonEntity> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");

        return (appUtils.isValidString(params.getSonId()) ? saveSonInformation(params):
                addSonToSelfParent(params)).flatMap(new Function<SonEntity, ObservableSource<SonEntity>>() {
            @Override
            public ObservableSource<SonEntity> apply(final SonEntity sonEntity) throws Exception {

                if(appUtils.isValidString(params.getProfileImage())) {

                    if(!appUtils.isValidString(sonEntity.getProfileImage()) ||
                            !sonEntity.getProfileImage().equalsIgnoreCase(params.getProfileImage())) {

                        return childrenRepository.uploadProfileImage(sonEntity.getIdentity(),
                                params.getProfileImage()).map(new Function<ImageEntity, SonEntity>() {
                            @Override
                            public SonEntity apply(ImageEntity imageEntity) throws Exception {
                                sonEntity.setProfileImage(imageEntity.getUrl());
                                return sonEntity;
                            }
                        });

                    } else {

                        return Observable.just(sonEntity);
                    }


                } else {

                    return Observable.just(sonEntity);
                }
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
     * Params
     */
    public static class Params {

        private final String sonId;
        private final String firstname;
        private final String lastName;
        private final String birthday;
        private final String school;
        private final String profileImage;

        private Params(String sonId, String firstname, String lastName, String birthday, String school, String profileImage) {
            this.sonId = sonId;
            this.firstname = firstname;
            this.lastName = lastName;
            this.birthday = birthday;
            this.school = school;
            this.profileImage = profileImage;
        }

        public String getSonId() {
            return sonId;
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

        /**
         * Create
         * @param sonId
         * @param firstname
         * @param lastName
         * @param birthday
         * @param school
         * @param profileImage
         * @return
         */
        public static Params create(final String sonId, final String firstname, final String lastName,
                                    final String birthday, final String school,
                                    final String profileImage) {
            return new Params(sonId, firstname, lastName, birthday, school, profileImage);
        }
    }


}
