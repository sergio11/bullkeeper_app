package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.AppInstalledRuleDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppInstalledDTO;
import sanchez.sanchez.sergio.data.net.services.IAppRulesService;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledRuleEntity;
import sanchez.sanchez.sergio.domain.repository.IAppRulesRepository;

/**
 * App Rules Repository Impl
 */
public final class AppRulesRepositoryImpl implements IAppRulesRepository {

    /**
     * App Installed Data Mapper
     */
    private final AbstractDataMapper<AppInstalledDTO, AppInstalledEntity> appInstalledDataMapper;

    /**
     * App Rules Service
     */
    private final IAppRulesService appRulesService;

    /**
     * App Installed Rule Data Mapper
     */
    private final AbstractDataMapper<AppInstalledRuleDTO, AppInstalledRuleEntity> appInstalledRuleDataMapper;

    /**
     * App Rules Repository Impl
     *
     * @param appInstalledDataMapper
     * @param appRulesService
     * @param appInstalledRuleDataMapper
     */
    public AppRulesRepositoryImpl(final AbstractDataMapper<AppInstalledDTO, AppInstalledEntity> appInstalledDataMapper,
                                  final IAppRulesService appRulesService,
                                  final AbstractDataMapper<AppInstalledRuleDTO, AppInstalledRuleEntity> appInstalledRuleDataMapper) {
        this.appInstalledDataMapper = appInstalledDataMapper;
        this.appRulesService = appRulesService;
        this.appInstalledRuleDataMapper = appInstalledRuleDataMapper;
    }

    /**
     * Get App Installed By Child
     *
     * @param childId
     * @param terminalId
     * @return
     */
    @Override
    public Observable<List<AppInstalledEntity>> getAppInstalledByChild(final String childId, final String terminalId) {
        Preconditions.checkNotNull(childId, "Child id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(terminalId, "Terminal Id can not be null");
        Preconditions.checkState(!terminalId.isEmpty(), "Terminal Id can not be empty");

        return appRulesService.getAppInstalledByChild(childId, terminalId)
                .map(response -> response != null && response.getData() != null
                        ? response.getData() : null)
                .map(appInstalledDataMapper::transform);
    }

    /**
     * Update App Installed Rules By Child
     * @param childId
     * @param terminalId
     * @param appInstalledRuleEntities
     * @return
     */
    @Override
    public Observable<String> updateAppInstalledRulesByChild(final String childId, final String terminalId,
                                                             final List<AppInstalledRuleEntity> appInstalledRuleEntities) {
        Preconditions.checkNotNull(childId, "Child id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(terminalId, "Terminal Id can not be null");
        Preconditions.checkState(!terminalId.isEmpty(), "Terminal Id can not be empty");
        Preconditions.checkNotNull(appInstalledRuleEntities, "App Installed Rule can not be null");
        Preconditions.checkState(!appInstalledRuleEntities.isEmpty(), "App Installed Rules can not be empty");

        return appRulesService.updateAppInstalledRulesByChild(childId, terminalId,
                appInstalledRuleDataMapper.transformInverse(appInstalledRuleEntities))
                    .map(response -> response != null && response.getData() != null ?
                        response.getData(): null);
    }

}


