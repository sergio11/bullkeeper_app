package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.AppInstalledRuleDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppInstalledByTerminalDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppInstalledDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppInstalledDetailDTO;
import sanchez.sanchez.sergio.data.net.models.response.AppStatsDTO;
import sanchez.sanchez.sergio.data.net.services.IAppRulesService;
import sanchez.sanchez.sergio.domain.models.AppInstalledByTerminalEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledDetailEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledRuleEntity;
import sanchez.sanchez.sergio.domain.models.AppStatsEntity;
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
     * App Stats Entity Mapper
     */
    private final AbstractDataMapper<AppStatsDTO, AppStatsEntity> appStatsEntityAbstractDataMapper;

    /**
     * App Installed By Terminal
     */
    private final AbstractDataMapper<AppInstalledByTerminalDTO, AppInstalledByTerminalEntity> appInstalledByTerminalMapper;

    /**
     * App Installed Detail
     */
    private final AbstractDataMapper<AppInstalledDetailDTO, AppInstalledDetailEntity> appInstalledDetailEntityAbstractDataMapper;

    /**
     * App Rules Repository Impl
     *
     * @param appInstalledDataMapper
     * @param appRulesService
     * @param appInstalledRuleDataMapper
     * @param appStatsEntityAbstractDataMapper
     * @param appInstalledByTerminalMapper
     * @param appInstalledDetailEntityAbstractDataMapper
     */
    public AppRulesRepositoryImpl(
            final AbstractDataMapper<AppInstalledDTO, AppInstalledEntity> appInstalledDataMapper,
            final IAppRulesService appRulesService,
            final AbstractDataMapper<AppInstalledRuleDTO, AppInstalledRuleEntity> appInstalledRuleDataMapper,
            final AbstractDataMapper<AppStatsDTO, AppStatsEntity> appStatsEntityAbstractDataMapper,
            final AbstractDataMapper<AppInstalledByTerminalDTO, AppInstalledByTerminalEntity> appInstalledByTerminalMapper,
            final AbstractDataMapper<AppInstalledDetailDTO, AppInstalledDetailEntity> appInstalledDetailEntityAbstractDataMapper) {
        this.appInstalledDataMapper = appInstalledDataMapper;
        this.appRulesService = appRulesService;
        this.appInstalledRuleDataMapper = appInstalledRuleDataMapper;
        this.appStatsEntityAbstractDataMapper = appStatsEntityAbstractDataMapper;
        this.appInstalledByTerminalMapper = appInstalledByTerminalMapper;
        this.appInstalledDetailEntityAbstractDataMapper = appInstalledDetailEntityAbstractDataMapper;
    }

    /**
     * Get App Installed By Child
     *
     * @param kid
     * @param terminal
     * @return
     */
    @Override
    public Observable<List<AppInstalledEntity>> getAppInstalledByChild(final String kid, final String terminal, final String query) {
        Preconditions.checkNotNull(kid, "Child id can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal Id can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal Id can not be empty");

        return appRulesService.getAppInstalledByChild(kid, terminal, query)
                .map(response -> response != null && response.getData() != null
                        ? response.getData() : null)
                .map(appInstalledDataMapper::transform);
    }

    /**
     * Get All App Installed By Child
     * @param kid
     * @param query
     * @return
     */
    @Override
    public Observable<List<AppInstalledByTerminalEntity>> getAllAppInstalledByChild(final String kid, final String query) {
        Preconditions.checkNotNull(kid, "Child id can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Child id can not be empty");

        return appRulesService.getAllAppInstalledByChild(kid, query)
                .map(response -> response != null && response.getData() != null
                        ? response.getData() : null)
                .map(appInstalledByTerminalMapper::transform);
    }

    /**
     * Update App Installed Rules By Child
     * @param kid
     * @param terminalId
     * @param appInstalledRuleEntities
     * @return
     */
    @Override
    public Observable<String> updateAppInstalledRulesByChild(final String kid, final String terminalId,
                                                             final List<AppInstalledRuleEntity> appInstalledRuleEntities) {
        Preconditions.checkNotNull(kid, "Child id can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(terminalId, "Terminal Id can not be null");
        Preconditions.checkState(!terminalId.isEmpty(), "Terminal Id can not be empty");
        Preconditions.checkNotNull(appInstalledRuleEntities, "App Installed Rule can not be null");
        Preconditions.checkState(!appInstalledRuleEntities.isEmpty(), "App Installed Rules can not be empty");

        return appRulesService.updateAppInstalledRulesByChild(kid, terminalId,
                appInstalledRuleDataMapper.transformInverse(appInstalledRuleEntities))
                    .map(response -> response != null && response.getData() != null ?
                        response.getData(): null);
    }

    /**
     * Update Single App Installed Rules By Child
     * @param kid
     * @param terminal
     * @param app
     * @param appInstalledRuleEntity
     * @return
     */
    @Override
    public Observable<String> updateSingleAppInstalledRulesByChild(
            final String kid,
            final String terminal,
            final String app,
            final AppInstalledRuleEntity appInstalledRuleEntity) {
        Preconditions.checkNotNull(kid, "Child id can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal  can not be empty");
        Preconditions.checkNotNull(app, "App can not be null");
        Preconditions.checkState(!app.isEmpty(), "App can not be empty");
        Preconditions.checkNotNull(appInstalledRuleEntity, "App Installed can not be null");

        return appRulesService.updateSingleAppInstalledRulesByChild(kid, terminal, app,
                appInstalledRuleDataMapper.transformInverse(appInstalledRuleEntity))
        .map(response -> response != null && response.getData() != null ?
            response.getData(): null);

    }

    /**
     *
     * @param kid
     * @param terminal
     * @param app
     * @return
     */
    @Override
    public Observable<AppInstalledDetailEntity> getAppInstalledDetail(final String kid, final String terminal,
                                                                      final String app) {
        Preconditions.checkNotNull(kid, "Child id can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal Id can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal Id can not be empty");
        Preconditions.checkNotNull(app, "App can not be null");
        Preconditions.checkState(!app.isEmpty(), "App can not be empty");

        return appRulesService.getAppInstalledDetail(kid, terminal, app)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null)
                .map(appInstalledDetailEntityAbstractDataMapper::transform);
    }

    /**
     * Switch App Status In The Terminal
     * @param kid
     * @param terminal
     * @param app
     * @param status
     * @return
     */
    @Override
    public Observable<String> switchAppStatusInTheTerminal(final String kid, final String terminal,
                                                           final String app, final Boolean status) {
        Preconditions.checkNotNull(kid, "Child id can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal Id can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal Id can not be empty");
        Preconditions.checkNotNull(app, "App can not be null");
        Preconditions.checkState(!app.isEmpty(), "App can not be empty");
        Preconditions.checkNotNull(status, "Status can not be null");

        return (status ?
                    appRulesService.enableAppInTheTerminal(kid, terminal, app) :
                    appRulesService.disableAppInTheTerminal(kid, terminal, app))
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null);
    }

    /**
     * Get Statistics Of The Five Most Used Applications
     * @param kid
     * @param terminal
     * @return
     */
    @Override
    public Observable<List<AppStatsEntity>> getStatisticsOfTheFiveMostUsedApplications(final String kid, final String terminal) {
        Preconditions.checkNotNull(kid, "Child id can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal Id can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal Id can not be empty");

        return appRulesService.getStatsForAllAppsInstalled(kid, terminal, 5)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(appStatsEntityAbstractDataMapper::transform);
    }


}


