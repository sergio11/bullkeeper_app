package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.AlertsStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.DimensionEntity;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;
import sanchez.sanchez.sergio.domain.models.LocationEntity;
import sanchez.sanchez.sergio.domain.models.SentimentAnalysisStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaActivityStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SummaryMyKidResultEntity;

/**
 * Children Repository
 */
public interface IChildrenRepository {

    /**
     * Get Kid By Id
     * @param sonId
     * @return
     */
    Observable<KidEntity> getKidById(final String sonId);

    /**
     * Add Kid To Self Guardian
     * @param firstName
     * @param lastName
     * @param birthdate
     * @param school
     * @return
     */
    Observable<KidEntity> addKidToSelfGuardian(final String firstName, final String lastName,
                                               final String birthdate, final String school);

    /**
     * Save Kid Information
     * @param identity
     * @param firstName
     * @param lastName
     * @param birthdate
     * @param school
     * @return
     */
    Observable<KidEntity> saveKidInformation(final String identity, final String firstName,
                                             final String lastName, final String birthdate, final String school);


    /**
     * Upload Profile Image
     * @param sonId
     * @param profileImageUri
     * @return
     */
    Observable<ImageEntity> uploadProfileImage(final String sonId, final String profileImageUri);

    /**
     * Get Dimensions Statistics By Child
     * @param sonId
     * @return
     */
    Observable<List<DimensionEntity>> getDimensionsStatisticsByChild(final String sonId, final int daysAgo);

    /**
     * Get Social Media Activity Statistics
     * @param kidIdentity
     * @param daysAgo
     * @return
     */
    Observable<SocialMediaActivityStatisticsEntity> getSocialMediaActivityStatistics(final String kidIdentity,
                                                                                     final int daysAgo);


    /**
     * Get Sentiment Analysis Statistics
     * @param kidIdentity
     * @param daysAgo
     * @return
     */
    Observable<SentimentAnalysisStatisticsEntity> getSentimentAnalysisStatistics(final String kidIdentity,
                                                                                 final int daysAgo);

    /**
     * Get Alerts Statistics
     * @param kidIdentity
     * @param daysAgo
     * @return
     */
    Observable<AlertsStatisticsEntity> getAlertsStatistics(final String kidIdentity, final int daysAgo);

    /**
     * Save Guardians
     * @param kid
     * @param guardianEntities
     * @return
     */
    Observable<List<KidGuardianEntity>> saveGuardians(
            final String kid,
            final List<KidGuardianEntity> guardianEntities);


    /**
     * Get Guardians
     * @param kid
     * @return
     */
    Observable<List<KidGuardianEntity>> getGuardians(final String kid);

    /**
     * Get Kid Location
     * @param kid
     * @return
     */
    Observable<LocationEntity> getKidLocation(final String kid);

}
