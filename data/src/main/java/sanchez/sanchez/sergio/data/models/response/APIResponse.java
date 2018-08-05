package sanchez.sanchez.sergio.data.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Api Response
 * @param <T>
 */
public class APIResponse<T> {

    @JsonProperty("code")
    private String code;

    @JsonProperty("response_status")
    private String status;

    @JsonProperty("response_http_status")
    private String httpStatus;

    @JsonProperty("response_info_url")
    private String infoUrl;

    @JsonProperty("response_data")
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "APIResponse{" +
                "code='" + code + '\'' +
                ", status='" + status + '\'' +
                ", httpStatus='" + httpStatus + '\'' +
                ", infoUrl='" + infoUrl + '\'' +
                ", data=" + data +
                '}';
    }
}
