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

}
