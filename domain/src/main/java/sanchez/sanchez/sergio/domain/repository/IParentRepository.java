package sanchez.sanchez.sergio.domain.repository;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.SonEntity;

/**
 * Parent Repository
 */
public interface IParentRepository {

    /**
     * Get Self Children
     * @return
     */
    Observable<List<SonEntity>> getSelfChildren();

}
