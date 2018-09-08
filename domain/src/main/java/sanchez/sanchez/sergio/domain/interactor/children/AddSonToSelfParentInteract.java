package sanchez.sanchez.sergio.domain.interactor.children;

import java.util.Date;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.SonEntity;

/**
 * Add Son To Self Parent Interact
 */
public final class AddSonToSelfParentInteract extends UseCase<SonEntity, AddSonToSelfParentInteract.Params> {

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public AddSonToSelfParentInteract(IThreadExecutor threadExecutor, IPostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
    }

    @Override
    protected Observable<SonEntity> buildUseCaseObservable(Params params) {
        return null;
    }

    public static class Params {

        private final String firstName;
        private final String lastName;
        private final Date birthdate;
        private final String school;

        public Params(String firstName, String lastName, Date birthdate, String school) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthdate = birthdate;
            this.school = school;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public Date getBirthdate() {
            return birthdate;
        }

        public String getSchool() {
            return school;
        }

        public static Params create(final String firstName, final String lastName,
                                    final Date birthdate, final String school) {
            return new Params(firstName, lastName, birthdate, school);
        }
    }
}
