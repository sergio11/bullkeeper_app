package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledRuleEntity;

/**
 * App Rules Repository
 */
public interface IAppRulesRepository {


    /**
     * Get APP Installed By Child
     * @param kid
     * @param terminal
     * @return
     */
    Observable<List<AppInstalledEntity>> getAppInstalledByChild(final String kid,
                                                                final String terminal);

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

}
