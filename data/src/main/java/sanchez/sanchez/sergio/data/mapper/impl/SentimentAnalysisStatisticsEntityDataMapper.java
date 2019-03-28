package sanchez.sanchez.sergio.data.mapper.impl;

import java.util.ArrayList;
import java.util.List;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.SentimentAnalysisStatisticsDTO;
import sanchez.sanchez.sergio.domain.models.SentimentAnalysisStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SentimentLevelEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;
import timber.log.Timber;

/**
 * Sentiment Analysis Statistics Entity Data Mapper
 */
public class SentimentAnalysisStatisticsEntityDataMapper extends
        AbstractDataMapper<SentimentAnalysisStatisticsDTO, SentimentAnalysisStatisticsEntity>{

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public SentimentAnalysisStatisticsEntity transform(SentimentAnalysisStatisticsDTO originModel) {
        final SentimentAnalysisStatisticsEntity sentimentAnalysisStatisticsEntity = new SentimentAnalysisStatisticsEntity();
        sentimentAnalysisStatisticsEntity.setTitle(originModel.getTitle());
        sentimentAnalysisStatisticsEntity.setSubtitle(originModel.getSubtitle());
        final List<SentimentAnalysisStatisticsEntity.SentimentEntity> sentimentEntities = new ArrayList<>();
        for(final SentimentAnalysisStatisticsDTO.SentimentDTO sentimentDTO: originModel.getSentimentData()) {
            final SentimentAnalysisStatisticsEntity.SentimentEntity sentimentEntity = new SentimentAnalysisStatisticsEntity.SentimentEntity();
            try {
                sentimentEntity.setSentimentLevelEnum(SentimentLevelEnum.valueOf(sentimentDTO.getType()));
            } catch (IllegalArgumentException ex) {
                Timber.e("Sentiment Level Type unknow -> %s" , sentimentDTO.getType());
                sentimentEntity.setSentimentLevelEnum(SentimentLevelEnum.UNKNOWN);
            }
            sentimentEntity.setLabel(sentimentDTO.getLabel());
            sentimentEntity.setScore(sentimentDTO.getScore());
            sentimentEntities.add(sentimentEntity);
        }
        sentimentAnalysisStatisticsEntity.setSentimentData(sentimentEntities);
        return sentimentAnalysisStatisticsEntity;
    }


    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public SentimentAnalysisStatisticsDTO transformInverse(SentimentAnalysisStatisticsEntity originModel) {
        throw new UnsupportedOperationException();
    }
}
