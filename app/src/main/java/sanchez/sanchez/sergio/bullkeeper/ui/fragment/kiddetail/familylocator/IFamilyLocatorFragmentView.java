package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.familylocator;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.LocationEntity;

/**
 * Family Locator Fragment View
 */
interface IFamilyLocatorFragmentView extends ISupportView {

    /**
     * On Current Location Loaded
     * @param locationEntity
     */
    void onCurrentLocationLoaded(final LocationEntity locationEntity);

    /**
     * No Current Location Found
     */
    void noCurrentLocationFound();

}
