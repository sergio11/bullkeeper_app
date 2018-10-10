package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.AlertsStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.DimensionEntity;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.SentimentAnalysisStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaActivityStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;

/**
 * Children Repository
 */
public interface IChildrenRepository {

    /**
     * Get Son By Id
     * @param sonId
     * @return
     */
    Observable<SonEntity> getSonById(final String sonId);

    /**
     * Add Son To Self Parent Interact
     * @param firstName
     * @param lastName
     * @param birthdate
     * @param school
     * @return
     */
    Observable<SonEntity> addSonToSelfParentInteract(final String firstName, final String lastName,
                                                     final String birthdate, final String school);

    /**
     * Save Son Information
     * @param identity
     * @param firstName
     * @param lastName
     * @param birthdate
     * @param school
     * @return
     */
    Observable<SonEntity> saveSonInformation(final String identity, final String firstName,
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

}
