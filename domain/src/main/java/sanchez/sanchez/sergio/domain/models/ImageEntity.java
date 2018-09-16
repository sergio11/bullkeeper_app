package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Image Entity
 */
public final class ImageEntity implements Serializable {

    private String identity;
    private Integer size;
    private String contentType;
    private String url;

    public ImageEntity(String identity, Integer size, String contentType, String url) {
        this.identity = identity;
        this.size = size;
        this.contentType = contentType;
        this.url = url;
    }

    public ImageEntity(String identity, Integer size, String contentType) {
        this.identity = identity;
        this.size = size;
        this.contentType = contentType;
    }

    public ImageEntity(){}

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
