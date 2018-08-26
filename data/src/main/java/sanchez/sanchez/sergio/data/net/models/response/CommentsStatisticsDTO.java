package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Comments Statistics DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class CommentsStatisticsDTO implements Serializable {

    @JsonProperty("title")
    private String title;

    @JsonProperty("comments")
    private List<CommentsPerDateDTO> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CommentsPerDateDTO> getData() {
        return data;
    }

    public void setData(List<CommentsPerDateDTO> data) {
        this.data = data;
    }

    /**
     * Comments Per Date DTO
     */
    class CommentsPerDateDTO {

        @JsonProperty("date")
        private Date date;

        @JsonProperty("total")
        private Integer total;

		@JsonProperty("label")
        private String label;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
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
            return "CommentsPerDateDTO{" +
                    "date=" + date +
                    ", total=" + total +
                    ", label='" + label + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CommentsStatisticsDTO{" +
                "title='" + title + '\'' +
                ", data=" + data +
                '}';
    }
}
