package sanchez.sanchez.sergio.masom_app.ui.fragment.mykids;

import java.util.List;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.masom_app.ui.support.ISupportView;

public interface IMyKidsView extends ISupportView {

    /**
     * On My Kids Loaded
     * @param myKids
     */
    void onMyKidsLoaded(final List<SonEntity> myKids);

}
