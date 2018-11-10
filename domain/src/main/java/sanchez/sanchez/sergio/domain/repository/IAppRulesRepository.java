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
     * @param childId
     * @param terminalId
     * @return
     */
    Observable<List<AppInstalledEntity>> getAppInstalledByChild(final String childId,
                                                                final String terminalId);

    /**
     * Update App Installed Rules By Child
     * @param childId
     * @param terminalId
     * @param appInstalledRuleEntities
     * @return
     */
    Observable<String> updateAppInstalledRulesByChild(final String childId, final String terminalId,
                                                final List<AppInstalledRuleEntity> appInstalledRuleEntities);


}
