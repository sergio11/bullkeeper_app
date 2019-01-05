package sanchez.sanchez.sergio.bullkeeper.events.impl;

import android.content.Intent;
import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.SupportEvent;
import sanchez.sanchez.sergio.bullkeeper.events.handler.IKidLocationEventVisitor;

/**
 * Kid Location Updated Event
 */
public final class KidLocationUpdatedEvent extends SupportEvent<IKidLocationEventVisitor> {

    /**
     * Args
     */
    private static String KID_ARG = "KID_ARG";
    private static String ADDRESS_ARG = "ADDRESS_ARG";
    private static String LATITUDE_ARG  ="LATITUDE_ARG";
    private static String LONGITUDE_ARG = "LONGITUDE_ARG";

    private String kid;
    private String address;
    private double latitude;
    private double longitude;

    public KidLocationUpdatedEvent(){}

    /**
     *
     * @param kid
     * @param address
     * @param latitude
     * @param longitude
     */
    public KidLocationUpdatedEvent(String kid, String address, double latitude, double longitude) {
        this.kid = kid;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * To intent
     * @return
     */
    @Override
    public Intent toIntent() {
        final Intent notificationIntent = new Intent(getClass().getCanonicalName());
        final Bundle notificationBundle = new Bundle();
        notificationBundle.putString(KID_ARG, kid);
        notificationBundle.putString(ADDRESS_ARG, address);
        notificationBundle.putDouble(LATITUDE_ARG, latitude);
        notificationBundle.putDouble(LONGITUDE_ARG, longitude);
        notificationIntent.putExtras(notificationBundle);
        return notificationIntent;
    }

    /**
     * From Bundle
     * @param notificationBundle
     * @return
     */
    public static KidLocationUpdatedEvent fromBundle(final Bundle notificationBundle) {
        Preconditions.checkNotNull(notificationBundle, "Notification Bundle can not be null");
        final String kid = notificationBundle.getString(KID_ARG, "");
        final String address = notificationBundle.getString(ADDRESS_ARG, "");
        final Double lat = notificationBundle.getDouble(LATITUDE_ARG);
        final Double log = notificationBundle.getDouble(LONGITUDE_ARG);
        return new KidLocationUpdatedEvent(kid, address, lat, log);
    }

    /**
     * Accept
     * @param visitor
     */
    @Override
    public void accept(IKidLocationEventVisitor visitor) {
        visitor.visit(this);
    }
}
