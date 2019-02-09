package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.ArrayList;
import java.util.List;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AppModelCategoryDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppModelDTO;
import sanchez.sanchez.sergio.domain.models.AppModelCategoryEntity;
import sanchez.sanchez.sergio.domain.models.AppModelCategoryEnum;
import sanchez.sanchez.sergio.domain.models.AppModelCategoryTypeEnum;
import sanchez.sanchez.sergio.domain.models.AppModelEntity;
import timber.log.Timber;

/**
 * App Model Data Mapper
 */
public final class AppModelDataMapper extends AbstractDataMapper<AppModelDTO, AppModelEntity> {

    /**
     * App Model Category Data Mapper
     */
    private final AbstractDataMapper<AppModelCategoryDTO, AppModelCategoryEntity> appModelCategoryEntityAbstractDataMapper;

    /**
     *
     * @param appModelCategoryEntityAbstractDataMapper
     */
    public AppModelDataMapper(final AbstractDataMapper<AppModelCategoryDTO, AppModelCategoryEntity> appModelCategoryEntityAbstractDataMapper) {
        this.appModelCategoryEntityAbstractDataMapper = appModelCategoryEntityAbstractDataMapper;
    }

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public AppModelEntity transform(final AppModelDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final AppModelEntity appModelEntity = new AppModelEntity();
        // packageName
        appModelEntity.setPackageName(originModel.getPackageName());
        // category
        appModelEntity.setCategory(
                appModelCategoryEntityAbstractDataMapper.transform(originModel.getCategory()));

        // catKeys
        final List<AppModelCategoryEnum> categoryEnums = new ArrayList<>();
        for (final String appCatKey : originModel.getCatKeys()) {
            try {
                categoryEnums.add(AppModelCategoryEnum.valueOf(appCatKey));
            } catch(final Exception ex){
                Timber.d("Unknow App Cat Key");
            }
        }

        appModelEntity.setCatKeys(categoryEnums);

        // catType
        try {
            appModelEntity.setCatType(AppModelCategoryTypeEnum.values()[originModel.getCatType()]);
        } catch (final Exception ex) {
            appModelEntity.setCatType(AppModelCategoryTypeEnum.UNKNOWN);
        }

        // title
        appModelEntity.setTitle(originModel.getTitle());
        // shortDesc
        appModelEntity.setShortDesc(originModel.getShortDesc());
        // size
        appModelEntity.setSize(originModel.getSize());
        // contentRating
        appModelEntity.setContentRating(originModel.getContentRating());
        // description
        appModelEntity.setDescription(originModel.getDescription());
        // developer
        appModelEntity.setDeveloper(originModel.getDeveloper());
        // downloads
        appModelEntity.setDownloads(originModel.getDownloads());
        // downloadsMax
        appModelEntity.setDownloadsMax(originModel.getDownloadsMax());
        // downloadsMin
        appModelEntity.setDownloadsMin(originModel.getDownloadsMin());
        // icon
        appModelEntity.setIcon(originModel.getIcon());
        // icon72
        appModelEntity.setIcon72(originModel.getIcon72());
        // marketUrl
        appModelEntity.setMarketUrl(originModel.getMarketUrl());
        // rating
        appModelEntity.setNumberRatings(originModel.getNumberRatings());
        // promoImage
        appModelEntity.setPromoImage(originModel.getPromoImage());
        // promoVideo
        appModelEntity.setPromoVideo(originModel.getPromoVideo());
        // rating
        appModelEntity.setRating(originModel.getRating());
        // screenShots
        appModelEntity.setScreenShots(originModel.getScreenShots());
        // description
        appModelEntity.setDescription(originModel.getDescription());
        // version
        appModelEntity.setVersion(originModel.getVersion());
        // website
        appModelEntity.setWebsite(originModel.getWebsite());
        // whatIsNew
        appModelEntity.setWhatIsNew(originModel.getWhatIsNew());
        // Size
        appModelEntity.setSize(originModel.getSize());
        // Developer
        appModelEntity.setDeveloper(originModel.getDeveloper());
        // Content Rating
        appModelEntity.setContentRating(originModel.getContentRating());
        // Number Ratings
        appModelEntity.setNumberRatings(originModel.getNumberRatings());
        return appModelEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public AppModelDTO transformInverse(AppModelEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final AppModelDTO appModelDTO = new AppModelDTO();
        // packageName
        appModelDTO.setPackageName(originModel.getPackageName());
        // Category
        appModelDTO.setCategory(appModelCategoryEntityAbstractDataMapper.transformInverse(
                originModel.getCategory()));
        // Cat Type
        appModelDTO.setCatType(originModel.getCatType().ordinal());

        final List<String> catKeys = new ArrayList<>();
        for(final AppModelCategoryEnum appModelCategoryEnum: originModel.getCatKeys()) {
            catKeys.add(appModelCategoryEnum.name());
        }
        // Keys
        appModelDTO.setCatKeys(catKeys);

        // Title
        appModelDTO.setTitle(originModel.getTitle());
        // Description
        appModelDTO.setDescription(originModel.getDescription());
        // Short Desc
        appModelDTO.setShortDesc(originModel.getShortDesc());
        // Icon
        appModelDTO.setIcon(originModel.getIcon());
        // Icon 72
        appModelDTO.setIcon72(originModel.getIcon72());
        // Market URL
        appModelDTO.setMarketUrl(originModel.getMarketUrl());
        // What is new
        appModelDTO.setWhatIsNew(originModel.getWhatIsNew());
        // Downloads
        appModelDTO.setDownloads(originModel.getDownloads());
        // Downloads Min
        appModelDTO.setDownloadsMin(originModel.getDownloadsMin());
        // Downloads Max
        appModelDTO.setDownloadsMax(originModel.getDownloadsMax());
        // Promo Video
        appModelDTO.setPromoVideo(originModel.getPromoVideo());
        // Promo Image
        appModelDTO.setPromoImage(originModel.getPromoImage());
        // Rating
        appModelDTO.setRating(originModel.getRating());
        // Size
        appModelDTO.setSize(originModel.getSize());
        // Screen Shots
        appModelDTO.setScreenShots(originModel.getScreenShots());
        // Version
        appModelDTO.setVersion(originModel.getVersion());
        // Website
        appModelDTO.setWebsite(originModel.getWebsite());
        // Developer
        appModelDTO.setDeveloper(originModel.getDeveloper());
        // Content Ratings
        appModelDTO.setContentRating(originModel.getContentRating());
        // Number Ratings
        appModelDTO.setNumberRatings(originModel.getNumberRatings());

        return appModelDTO;
    }
}
