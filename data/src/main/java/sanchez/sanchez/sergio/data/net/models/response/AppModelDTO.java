package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

/**
 * App Model DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class AppModelDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Package Name
     */
    @JsonProperty("package_name")
    private String packageName;

    /**
     * Category
     */
    @JsonProperty("category")
    private AppModelCategoryDTO category;

    /**
     * Cat Keys
     */
    @JsonProperty("cat_keys")
    private List<String> catKeys;

    /**
     * Cat Type
     */
    @JsonProperty("cat_type")
    private Integer catType;

    /**
     * Title
     */
    @JsonProperty("title")
    private String title;

    /**
     * Description
     */
    @JsonProperty("description")
    private String description;

    /**
     * Short Desc
     */
    @JsonProperty("short_desc")
    private String shortDesc;

    /**
     * Icon
     */
    @JsonProperty("icon")
    private String icon;

    /**
     * Icon 72
     */
    @JsonProperty("icon_72")
    private String icon72;

    /**
     * Market URL
     */
    @JsonProperty("market_url")
    private String marketUrl;

    /**
     * What Is New
     */
    @JsonProperty("what_is_new")
    private String whatIsNew;

    /**
     * Downloads
     */
    @JsonProperty("downloads")
    private String downloads;

    /**
     * Downloads Min
     */
    @JsonProperty("downloads_min")
    private String downloadsMin;

    /**
     * Dowloads Max
     */
    @JsonProperty("downloads_max")
    private String downloadsMax;

    /**
     * Promo Video
     */
    @JsonProperty("promo_video")
    private String promoVideo;

    /**
     * Promo Image
     */
    @JsonProperty("promo_image")
    private String promoImage;

    /**
     * Ratings
     */
    @JsonProperty("rating")
    private Double rating;

    /**
     * Size
     */
    @JsonProperty("size")
    private Integer size;

    /**
     * Screen Shots
     */
    @JsonProperty("screenshots")
    private List<String> screenShots;

    /**
     * Version
     */
    @JsonProperty("version")
    private String version;

    /**
     * Website
     */
    @JsonProperty("website")
    private String website;

    /**
     * Developer
     */
    @JsonProperty("developer")
    private String developer;


    /**
     * Content Rating
     */
    @JsonProperty("content_rating")
    private String contentRating;

    /**
     * Number Ratings
     */
    @JsonProperty("number_ratings")
    private Integer numberRatings;

    public AppModelDTO(){}

    /**
     *
     * @param packageName
     * @param category
     * @param catKeys
     * @param catType
     * @param title
     * @param description
     * @param shortDesc
     * @param icon
     * @param icon72
     * @param marketUrl
     * @param whatIsNew
     * @param downloads
     * @param downloadsMin
     * @param downloadsMax
     * @param promoVideo
     * @param promoImage
     * @param rating
     * @param size
     * @param screenShots
     * @param version
     * @param website
     * @param developer
     * @param contentRating
     * @param numberRatings
     */
    public AppModelDTO(String packageName, AppModelCategoryDTO category, List<String> catKeys, Integer catType, String title, String description, String shortDesc, String icon, String icon72, String marketUrl, String whatIsNew, String downloads, String downloadsMin, String downloadsMax, String promoVideo, String promoImage, Double rating, Integer size, List<String> screenShots, String version, String website, String developer, String contentRating, Integer numberRatings) {
        this.packageName = packageName;
        this.category = category;
        this.catKeys = catKeys;
        this.catType = catType;
        this.title = title;
        this.description = description;
        this.shortDesc = shortDesc;
        this.icon = icon;
        this.icon72 = icon72;
        this.marketUrl = marketUrl;
        this.whatIsNew = whatIsNew;
        this.downloads = downloads;
        this.downloadsMin = downloadsMin;
        this.downloadsMax = downloadsMax;
        this.promoVideo = promoVideo;
        this.promoImage = promoImage;
        this.rating = rating;
        this.size = size;
        this.screenShots = screenShots;
        this.version = version;
        this.website = website;
        this.developer = developer;
        this.contentRating = contentRating;
        this.numberRatings = numberRatings;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public AppModelCategoryDTO getCategory() {
        return category;
    }

    public void setCategory(AppModelCategoryDTO category) {
        this.category = category;
    }

    public List<String> getCatKeys() {
        return catKeys;
    }

    public void setCatKeys(List<String> catKeys) {
        this.catKeys = catKeys;
    }

    public Integer getCatType() {
        return catType;
    }

    public void setCatType(Integer catType) {
        this.catType = catType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon72() {
        return icon72;
    }

    public void setIcon72(String icon72) {
        this.icon72 = icon72;
    }

    public String getMarketUrl() {
        return marketUrl;
    }

    public void setMarketUrl(String marketUrl) {
        this.marketUrl = marketUrl;
    }

    public String getWhatIsNew() {
        return whatIsNew;
    }

    public void setWhatIsNew(String whatIsNew) {
        this.whatIsNew = whatIsNew;
    }

    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    public String getDownloadsMin() {
        return downloadsMin;
    }

    public void setDownloadsMin(String downloadsMin) {
        this.downloadsMin = downloadsMin;
    }

    public String getDownloadsMax() {
        return downloadsMax;
    }

    public void setDownloadsMax(String downloadsMax) {
        this.downloadsMax = downloadsMax;
    }

    public String getPromoVideo() {
        return promoVideo;
    }

    public void setPromoVideo(String promoVideo) {
        this.promoVideo = promoVideo;
    }

    public String getPromoImage() {
        return promoImage;
    }

    public void setPromoImage(String promoImage) {
        this.promoImage = promoImage;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<String> getScreenShots() {
        return screenShots;
    }

    public void setScreenShots(List<String> screenShots) {
        this.screenShots = screenShots;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public Integer getNumberRatings() {
        return numberRatings;
    }

    public void setNumberRatings(Integer numberRatings) {
        this.numberRatings = numberRatings;
    }

    @Override
    public String toString() {
        return "AppModelDTO{" +
                "packageName='" + packageName + '\'' +
                ", category=" + category +
                ", catKeys=" + catKeys +
                ", catType=" + catType +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", icon='" + icon + '\'' +
                ", icon72='" + icon72 + '\'' +
                ", marketUrl='" + marketUrl + '\'' +
                ", whatIsNew='" + whatIsNew + '\'' +
                ", downloads='" + downloads + '\'' +
                ", downloadsMin='" + downloadsMin + '\'' +
                ", downloadsMax='" + downloadsMax + '\'' +
                ", promoVideo='" + promoVideo + '\'' +
                ", promoImage='" + promoImage + '\'' +
                ", rating=" + rating +
                ", size=" + size +
                ", screenShots=" + screenShots +
                ", version='" + version + '\'' +
                ", website='" + website + '\'' +
                ", developer='" + developer + '\'' +
                ", contentRating='" + contentRating + '\'' +
                ", numberRatings=" + numberRatings +
                '}';
    }
}
