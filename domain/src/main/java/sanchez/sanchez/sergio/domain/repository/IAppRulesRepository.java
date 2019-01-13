package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.AppInstalledByTerminalEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledRuleEntity;
import sanchez.sanchez.sergio.domain.models.AppStatsEntity;

/**
 * App Rules Repository
 */
public interface IAppRulesRepository {


    /**
     * Get APP Installed By Child
     * @param kid
     * @param terminal
     * @param query
     * @return
     */
    Observable<List<AppInstalledEntity>> getAppInstalledByChild(
            final String kid,
            final String terminal,
            final String query);

    /**
     * Get All APP Installed By Child
     * @param kid
     * @param query
     * @return
     */
    Observable<List<AppInstalledByTerminalEntity>> getAllAppInstalledByChild(
            final String kid,
            final String query);


    /**
     * Update App Installed Rules By Child
     * @param kid
     * @param terminal
     * @param appInstalledRuleEntities
     * @return
     */
    Observable<String> updateAppInstalledRulesByChild(final String kid, final String terminal,
                                                final List<AppInstalledRuleEntity> appInstalledRuleEntities);


    /**
     * Update App Installed Rules By Child
     * @param kid
     * @param terminal
     * @param app
     * @param appInstalledRuleEntity
     * @return
     */
    Observable<String> updateSingleAppInstalledRulesByChild(
            final String kid,
            final String terminal,
            final String app,
            AppInstalledRuleEntity appInstalledRuleEntity);

    /**
     *
     * @param kid
     * @param terminal
     * @param app
     * @return
     */
    Observable<AppInstalledEntity> getAppInstalledDetail(final String kid, final String terminal,
                                                         final String app);

    /**
     * Switch App Status In The Terminal
     * @param kid
     * @param terminal
     * @param app
     * @return
     */
    Observable<String> switchAppStatusInTheTerminal(
            final String kid, final String terminal,
            final String app, final Boolean status
    );


    /**
     * Get Statistics Of The Five Most Used Applications
     * @param kid
     * @param terminal
     * @return
     */
    Observable<List<AppStatsEntity>> getStatisticsOfTheFiveMostUsedApplications(
            final String kid,
            final String terminal);

}
