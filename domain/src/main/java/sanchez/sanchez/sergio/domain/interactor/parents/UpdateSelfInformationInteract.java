package sanchez.sanchez.sergio.domain.interactor.parents;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.domain.repository.IParentRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Update Self Children Interact
 */
public final class UpdateSelfInformationInteract extends UseCase<ParentEntity, UpdateSelfInformationInteract.Params> {

    private final IParentRepository parentRepository;

    /**
     * Get Self Children Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param parentRepository
     */
    @Inject
    public UpdateSelfInformationInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                         final IParentRepository parentRepository) {
        super(threadExecutor, postExecutionThread);
        this.parentRepository = parentRepository;
    }

    /**
     * Build Use Case Observable
     * @param params
     * @return
     */
    @Override
    protected Observable<ParentEntity> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");

        return params.getProfileImage() != null ?
                parentRepository.uploadProfileImage(params.getProfileImage()).flatMap(new Function<ImageEntity, Observable<ParentEntity>>() {
                    @Override
                    public Observable<ParentEntity> apply(final ImageEntity imageEntity) {
                        return parentRepository.updateSelfInformation(params.getFirstName(), params.getLastName(),
                                params.getBirthdate(), params.getEmail(), params.getTelephone()).subscribeOn(Schedulers.io())
                                .observeOn(Schedulers.io());
                    }
                }) : parentRepository.updateSelfInformation(params.getFirstName(), params.getLastName(),
                params.getBirthdate(), params.getEmail(), params.getTelephone());
    }

    /**
     * Params
     */
    public static final class Params {

        private final String firstName;
        private final String lastName;
        private final String birthdate;
        private final String email;
        private final String telephone;
        private final String profileImage;

        /**
         *
         * @param firstName
         * @param lastName
         * @param birthdate
         * @param email
         * @param telephone
         */
        public Params(String firstName, String lastName, String birthdate, String email, String telephone) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthdate = birthdate;
            this.email = email;
            this.telephone = telephone;
            this.profileImage = null;
        }

        /**
         *
         * @param firstName
         * @param lastName
         * @param birthdate
         * @param email
         * @param telephone
         * @param profileImage
         */
        public Params(String firstName, String lastName, String birthdate, String email, String telephone, final String profileImage) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthdate = birthdate;
            this.email = email;
            this.telephone = telephone;
            this.profileImage = profileImage;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getBirthdate() {
            return birthdate;
        }

        public String getEmail() {
            return email;
        }

        public String getTelephone() {
            return telephone;
        }

        public String getProfileImage() {
            return profileImage;
        }

        /**
         * Create
         * @param firstName
         * @param lastName
         * @param birthdate
         * @param email
         * @param telephone
         * @return
         */
        public static Params create(final String firstName, final String lastName, final String birthdate, final String email,
                                    final String telephone) {
            return new Params(firstName, lastName, birthdate, email, telephone);
        }

        /**
         *
         * @param firstName
         * @param lastName
         * @param birthdate
         * @param email
         * @param telephone
         * @param profileImage
         * @return
         */
        public static Params create(final String firstName, final String lastName, final String birthdate, final String email,
                                    final String telephone, final String profileImage) {
            return new Params(firstName, lastName, birthdate, email, telephone, profileImage);
        }
    }


    /**
     * Update Api Errors
     */
    public enum UpdateSelfInformationApiErrors implements ISupportVisitable<UpdateSelfInformationApiErrors.IUpdateSelfInformationApiErrorVisitor> {

        /**
         * Validation Errors
         */
        VALIDATION_ERROR(){
            @Override
            public <E> void accept(IUpdateSelfInformationApiErrorVisitor visitor, E data) {
                visitor.visitValidationError(this, (LinkedHashMap<String, List<LinkedHashMap<String, String>>>) data);
            }
        },
        /**
         * Failed To Upload Image
         */
        FAILED_TO_UPLOAD_IMAGE() {
            @Override
            public <E> void accept(IUpdateSelfInformationApiErrorVisitor visitor, E data) {
                visitor.visitFailedToUploadImage(this);
            }
        },

        /**
         * Upload File Is Too Large
         */
        UPLOAD_FILE_IS_TOO_LARGE(){
            @Override
            public <E> void accept(IUpdateSelfInformationApiErrorVisitor visitor, E data) {
                visitor.visitUploadFileIsTooLarge(this);
            }
        };

        /**
         * Update Self Information API Error Visitor
         */
        public interface IUpdateSelfInformationApiErrorVisitor extends ISupportVisitor {

            /**
             * Visit Validation Error
             * @param apiErrors
             * @param errors
             */
            void visitValidationError(final UpdateSelfInformationApiErrors apiErrors, final LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors);

            /**
             * Visit Failed To Upload Image
             * @param apiErrors
             */
            void visitFailedToUploadImage(final UpdateSelfInformationApiErrors apiErrors);

            /**
             * Visit Upload File Is Too Large
             * @param apiErrors
             */
            void visitUploadFileIsTooLarge(final UpdateSelfInformationApiErrors apiErrors);

        }
    }

}
