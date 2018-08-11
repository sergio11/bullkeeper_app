package sanchez.sanchez.sergio.data.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


/**
 * Comments By Son DTO
 */
public final class CommentsBySonDTO implements Serializable {

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("value")
    private Long comments;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getComments() {
        return comments;
    }

    public void setComments(Long comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "CommentsBySonDTO{" +
                "fullName='" + fullName + '\'' +
                ", comments=" + comments +
                '}';
    }
}
