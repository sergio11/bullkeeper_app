package sanchez.sanchez.sergio.bullkeeper.ui.services;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.fernandocejas.arrow.checks.Preconditions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import sanchez.sanchez.sergio.bullkeeper.R;

/**
 * Fetch Address Intent Service
 */
public class FetchAddressIntentService extends IntentService {

    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;

    public static final String RECEIVER = "RECEIVER";
    public static final String RESULT_DATA_KEY = "RESULT_DATA_KEY";
    public static final String LOCATION_DATA_EXTRA = "LOCATION_DATA_EXTRA";

    /**
     * Result Receiver
     */
    protected ResultReceiver resultReceiver;

    /**
     *
     */
    public FetchAddressIntentService(){
        super("");
    }

    /**
     *
     * @param name
     */
    public FetchAddressIntentService(String name) {
        super(name);
    }

    /**
     * Deliver Result To Receiver
     * @param resultCode
     * @param message
     */
    private void deliverResultToReceiver(int resultCode, final String message) {
        Preconditions.checkNotNull(resultReceiver, "Result Receiver can not be null");
        Bundle bundle = new Bundle();
        bundle.putString(RESULT_DATA_KEY, message);
        resultReceiver.send(resultCode, bundle);
    }

    /**
     *
     * @param intent
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent == null) {
            return;
        }

        // Create Geocoder
        final Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        resultReceiver = intent.getParcelableExtra(FetchAddressIntentService.RECEIVER);

        String errorMessage = "";

        // Get the location passed to this service through an extra.
        Location location = intent.getParcelableExtra(
                LOCATION_DATA_EXTRA);

        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    // In this sample, get just a single address.
                    1);
        } catch (IOException ioException) {
            // Catch network or other I/O problems.
            errorMessage = getString(R.string.service_not_available);
        } catch (IllegalArgumentException illegalArgumentException) {
            // Catch invalid latitude or longitude values.
            errorMessage = getString(R.string.invalid_lat_long_used);
        }

        // Handle case where no address was found.
        if (addresses == null || addresses.size()  == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = getString(R.string.no_address_found);
            }
            deliverResultToReceiver(FAILURE_RESULT, errorMessage);
        } else {

            Address address = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<>();
            // Fetch the address lines using getAddressLine,
            // join them, and send them to the thread.
            for(int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }

            deliverResultToReceiver(SUCCESS_RESULT,
                    TextUtils.join(System.getProperty("line.separator"),
                            addressFragments));
        }
    }
}
