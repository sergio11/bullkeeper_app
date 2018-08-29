package sanchez.sanchez.sergio.data.net.utils;


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
        return baseUrl + String.format("/images/children/%s", identity);
    }


}
