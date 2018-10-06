package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.List;

/**
 * Alerts Statistics Entity
 */
public class AlertsStatisticsEntity implements Serializable {

    /**
     * Title
     */
    private String title;

    /**
     * Data
     */
    private List<AlertLevelEntity> data;

    public AlertsStatisticsEntity(){}

    /**
     *
     * @param title
     * @param data
     */
    public AlertsStatisticsEntity(String title, List<AlertLevelEntity> data) {
        this.title = title;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AlertLevelEntity> getData() {
        return data;
    }

    public void setData(List<AlertLevelEntity> data) {
        this.data = data;
    }

    /**
     * Alert Level Entity
     */
    public static class AlertLevelEntity implements Serializable {

        /**
         * Level
         */
        private AlertLevelEnum level;

        /**
         * Total
         */
        private Integer total;

        /**
         * Label
         */
        private String label;

        public AlertLevelEntity(){}

        public AlertLevelEntity(AlertLevelEnum level, Integer total, String label) {
            this.level = level;
            this.total = total;
            this.label = label;
        }

        public AlertLevelEnum getLevel() {
            return level;
        }

        public void setLevel(AlertLevelEnum level) {
            this.level = level;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return "AlertLevelEntity{" +
                    "level=" + level +
                    ", total=" + total +
                    ", label='" + label + '\'' +
                    '}';
        }
    }



}
