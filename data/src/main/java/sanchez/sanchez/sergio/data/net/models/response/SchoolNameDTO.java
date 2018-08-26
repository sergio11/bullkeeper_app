package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * School Name DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SchoolNameDTO implements Serializable {

    @JsonProperty("identity")
    private String identity;

    @JsonProperty("name")
    private String name;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SchoolNameDTO{" +
                "identity='" + identity + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
