package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Suggested Place Entity
 */
public final class SuggestedPlaceEntity implements Serializable {

    private final String placeId;
    private final String primaryText;
    private final String secondaryText;

    public SuggestedPlaceEntity(final String placeId, final String primaryText, final String secondaryText) {
        this.placeId = placeId;
        this.primaryText = primaryText;
        this.secondaryText = secondaryText;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getPrimaryText() {
        return primaryText;
    }

    public String getSecondaryText() {
        return secondaryText;
    }
}
