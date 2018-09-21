package sanchez.sanchez.sergio.bullkeeper.ui.activity.school.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportDialogFragment;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;

/**
 * School Dialog Fragment
 */
public class SchoolDialogFragment extends SupportDialogFragment
    implements OnMapReadyCallback {

    public static final String TAG = "SCHOOL_DIALOG_DIALOG";

    private static final String SCHOOL_KEY_ARG = "SCHOOL_KEY";

    /**
     * School Name Text VIew
     */
    @BindView(R.id.schoolName)
    protected TextView schoolNameTextView;

    /**
     * School Location Text View
     */
    @BindView(R.id.schoolLocation)
    protected TextView schoolLocationTextView;

    private GoogleMap mMap;
    private SchoolEntity schoolEntity;

    /**
     * Show Dialog
     * @param appCompatActivity
     */
    public static void show(final AppCompatActivity appCompatActivity, final SchoolEntity schoolEntity) {
        final SchoolDialogFragment schoolDialogFragment = new SchoolDialogFragment();
        final Bundle args = new Bundle();
        args.putSerializable(SCHOOL_KEY_ARG, schoolEntity);
        schoolDialogFragment.setArguments(args);
        schoolDialogFragment.setStyle(SchoolDialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        schoolDialogFragment.show(appCompatActivity.getSupportFragmentManager(), TAG);
    }

    /**
     * Get Layout Res
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.school_dialog_fragment_layout;
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        final Bundle args = getArguments();
        if(args != null && args.containsKey(SCHOOL_KEY_ARG)) {
            schoolEntity = (SchoolEntity) args.getSerializable(SCHOOL_KEY_ARG);
            if(schoolEntity != null)
                schoolNameTextView.setText(schoolEntity.getName());

            schoolLocationTextView.setText(String.format(
                    getString(R.string.search_school_location), schoolEntity.getResidence(),
                    schoolEntity.getProvince()));
        }

    }

    /**
     * On Destroy View
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        FragmentManager fm = getFragmentManager();
        if(fm != null) {
            SupportMapFragment mapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
            if (mapFragment != null) {
                fm.beginTransaction().remove(mapFragment).commit();
                mapFragment.onDestroyView();
            }
        }
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {}

    /**
     * On Map Ready
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        if (schoolEntity != null && schoolEntity.getLatitude() != null
                && schoolEntity.getLongitude() != null) {

            LatLng latLng = new LatLng(schoolEntity.getLatitude(), schoolEntity.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 11));

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title(schoolEntity.getName());
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
            mMap.addMarker(markerOptions);
        }

    }
}
