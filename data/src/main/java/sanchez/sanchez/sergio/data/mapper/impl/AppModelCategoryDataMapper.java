package sanchez.sanchez.sergio.data.mapper.impl;


import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AppModelCategoryDTO;
import sanchez.sanchez.sergio.domain.models.AppModelCategoryEntity;
import sanchez.sanchez.sergio.domain.models.AppModelCategoryEnum;
import sanchez.sanchez.sergio.domain.models.AppRuleEnum;

/**
 * App Model Category Data Mapper
 */
public final class AppModelCategoryDataMapper extends AbstractDataMapper<AppModelCategoryDTO, AppModelCategoryEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public AppModelCategoryEntity transform(AppModelCategoryDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final AppModelCategoryEntity categoryEntity = new AppModelCategoryEntity();

        try {
            categoryEntity.setCatKey(AppModelCategoryEnum.valueOf(originModel.getCatKey()));
        } catch (final Exception ex) {
            categoryEntity.setCatKey(AppModelCategoryEnum.UNKNOWN);
        }

        try {
            categoryEntity.setDefaultAppRule(AppRuleEnum.valueOf(originModel.getDefaultAppRule()));
        } catch (final Exception ex) {
            categoryEntity.setDefaultAppRule(AppRuleEnum.PER_SCHEDULER);
        }

        categoryEntity.setName(originModel.getName());

        return categoryEntity;
    }

    /**
     * Tranform Inverse
     * @param originModel
     * @return
     */
    @Override
    public AppModelCategoryDTO transformInverse(AppModelCategoryEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final AppModelCategoryDTO appModelCategoryDTO = new AppModelCategoryDTO();
        appModelCategoryDTO.setCatKey(originModel.getCatKey().name());
        appModelCategoryDTO.setName(originModel.getName());
        appModelCategoryDTO.setDefaultAppRule(originModel.getDefaultAppRule().name());
        return appModelCategoryDTO;
    }
}
