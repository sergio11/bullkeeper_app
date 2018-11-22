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
     * Get Son Profile Url
     * @param identity
     * @return
     */
    public String getSonProfileUrl(final String identity) {
        return baseUrl + String.format("images/children/%s", identity);
    }


    /**
     * Get Parent Profile Url
     * @param identity
     * @return
     */
    public String getParentProfileUrl(final String identity) {
        return baseUrl + String.format("images/parents/%s", identity);
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

}
