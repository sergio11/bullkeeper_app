package sanchez.sanchez.sergio.data.net.utils;


import com.fernandocejas.arrow.checks.Preconditions;

/**
 * Api End Points Helper
 */
public final class ApiEndPointsHelper {

    private final String baseUrl;

    /**
     * Api End Points Helper
     * @param baseUrl
     */
    public ApiEndPointsHelper(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * Get Profile URL
     * @param identity
     * @return
     */
    public String getProfileUrl(final String identity) {
        return baseUrl + String.format("images/%s", identity);
    }

    /**
     * Get Kid Profile Url
     * @param identity
     * @return
     */
    public String getKidProfileUrl(final String identity) {
        return baseUrl + String.format("images/children/%s", identity);
    }


    /**
     * Get Guardians Profile Url
     * @param identity
     * @return
     */
    public String getGuardianProfileUrl(final String identity) {
        return baseUrl + String.format("images/guardians/%s", identity);
    }

    /**
     * Get Scheduled Block Image URL
     * @param childId
     * @param scheduledBlockId
     * @param imageId
     * @return
     */
    public String getScheduledBlockImageUrl(final String childId, final String scheduledBlockId,
                                            final String imageId) {
        Preconditions.checkNotNull(childId, "Child Id can not be null");
        Preconditions.checkNotNull(scheduledBlockId, "Scheduled Block id can not be null");
        Preconditions.checkNotNull(imageId, "Image id can not be null");

        return baseUrl + String.format("children/%s/scheduled-blocks/%s/image/%s",
                childId, scheduledBlockId, imageId);
    }

    /**
     * Get Event Subscription URL
     * @param subscriberId
     * @return
     */
    public String getEventSubscriptionUrl(final String subscriberId) {
        Preconditions.checkNotNull(subscriberId, "Subscriber id can not be null");
        Preconditions.checkState(!subscriberId.isEmpty(), "Subscriber id can not be empty");
        return baseUrl + String.format("events/subscribe/%s", subscriberId);
    }

    /**
     * Get Device Photo Url
     * @param kid
     * @param terminal
     * @param photo
     * @return
     */
    public String getDevicePhotoUrl(final String kid, final String terminal, final String photo){
        Preconditions.checkNotNull(kid, "Kid id can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal id can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(photo, "Photo id can not be null");
        Preconditions.checkState(!photo.isEmpty(), "Photo can not be empty");
        return baseUrl + String.format("children/%s/terminal/%s/photos/%s/image",
                kid, terminal, photo);
    }
}
