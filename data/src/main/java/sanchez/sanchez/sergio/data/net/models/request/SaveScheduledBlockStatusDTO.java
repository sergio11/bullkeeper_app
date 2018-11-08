package sanchez.sanchez.sergio.data.net.models.request;

import java.io.Serializable;

/**
 * Save Scheduled Block Status DTO
 */
public final class SaveScheduledBlockStatusDTO implements Serializable {

    private String identity;
    private boolean enable;

    public SaveScheduledBlockStatusDTO(){}

    public SaveScheduledBlockStatusDTO(String identity, boolean enable) {
        this.identity = identity;
        this.enable = enable;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
