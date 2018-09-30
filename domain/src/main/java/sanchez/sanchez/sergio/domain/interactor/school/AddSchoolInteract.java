package sanchez.sanchez.sergio.domain.interactor.school;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import sanchez.sanchez.sergio.domain.repository.ISchoolRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Add School Interact
 */
public final class AddSchoolInteract extends UseCase<SchoolEntity, AddSchoolInteract.Params> {

    private final ISchoolRepository schoolRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public AddSchoolInteract(final IThreadExecutor threadExecutor,
                             final IPostExecutionThread postExecutionThread, final ISchoolRepository schoolRepository) {
        super(threadExecutor, postExecutionThread);
        this.schoolRepository = schoolRepository;
    }

    @Override
    protected Observable<SchoolEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        return schoolRepository.createSchool(params.getName(), params.getResidence(), params.getProvince(),
                params.getLatitude(), params.getLongitude(), params.getTfno(), params.getEmail());
    }

    /**
     * Params
     */
    public static class Params {

        private final String name;
        private final String residence;
        private final String province;
        private final Double latitude;
        private final Double longitude;
        private final String tfno;
        private final String email;

        private Params(String name, String residence, String province,
                      Double latitude, Double longitude, String tfno, String email) {
            this.name = name;
            this.residence = residence;
            this.province = province;
            this.latitude = latitude;
            this.longitude = longitude;
            this.tfno = tfno;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getResidence() {
            return residence;
        }

        public String getProvince() {
            return province;
        }

        public Double getLatitude() {
            return latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public String getTfno() {
            return tfno;
        }

        public String getEmail() {
            return email;
        }

        /**
         * Create
         * @param name
         * @param residence
         * @param province
         * @param latitude
         * @param longitude
         * @param tfno
         * @param email
         * @return
         */
        public static Params create(final String name, final String residence, final String province, final Double latitude,
                                    final Double longitude, final String tfno, final String email){
            return new Params(name, residence, province, latitude, longitude, tfno, email);
        }
    }


    /**
     * Update Api Errors
     */
    public enum AddSchoolApiErrors implements ISupportVisitable<AddSchoolApiErrors.IAddSchoolApiErrorVisitor> {

        /**
         * Validation Errors
         */
        VALIDATION_ERROR(){
            @Override
            public <E> void accept(IAddSchoolApiErrorVisitor visitor, E data) {
                visitor.visitValidationError(this, (LinkedHashMap<String, List<LinkedHashMap<String, String>>>) data);
            }
        };

        /**
         * Add School API Error Visitor
         */
        public interface IAddSchoolApiErrorVisitor extends ISupportVisitor {

            /**
             * Visit Validation Error
             * @param apiErrors
             * @param errors
             */
            void visitValidationError(final AddSchoolApiErrors apiErrors, final LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors);

        }
    }
}
