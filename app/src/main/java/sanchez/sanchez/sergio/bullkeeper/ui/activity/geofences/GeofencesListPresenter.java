package sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;

import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportLCEPresenter;

/**
 * Geofences List Presenter
 */
public final class GeofencesListPresenter
        extends SupportLCEPresenter<IGeofencesListView> {

    /**
     * Args
     */
    public final static String KID_ID_ARG = "KID_ID_ARG";

    @Inject
    public GeofencesListPresenter() {
    }

    /**
     * Load Data
     */
    @Override
    public void loadData() {
        Preconditions.checkState(!args.getString(KID_ID_ARG, "").isEmpty(),
                "Kid id can not be empty");
    }

    /**
     * Load Data
     */
    @Override
    public void loadData(final Bundle args) { loadData(); }

}
