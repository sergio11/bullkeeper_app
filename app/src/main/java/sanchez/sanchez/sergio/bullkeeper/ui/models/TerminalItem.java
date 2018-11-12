package sanchez.sanchez.sergio.bullkeeper.ui.models;

import org.parceler.Parcel;
import java.io.Serializable;

/**
 * Terminal Item
 */
@Parcel
public final class TerminalItem implements Serializable {

    protected String identity;
    protected String deviceName;

    public TerminalItem(){}

    public TerminalItem(String identity, String deviceName) {
        this.identity = identity;
        this.deviceName = deviceName;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public String toString() {
        return deviceName;
    }
}
