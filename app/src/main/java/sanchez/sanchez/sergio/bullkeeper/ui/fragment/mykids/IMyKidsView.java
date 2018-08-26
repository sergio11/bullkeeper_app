package sanchez.sanchez.sergio.bullkeeper.ui.fragment.mykids;

import java.util.List;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.bullkeeper.ui.support.ISupportView;

public interface IMyKidsView extends ISupportView {

    /**
     * On My Kids Loaded
     * @param myKids
     */
    void onMyKidsLoaded(final List<SonEntity> myKids);

    /**
     * On No Children Found
     */
    void onNoChildrenFound();

}
