package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;


public final class ImageEntity implements Serializable {

    private String identity;
    private Integer size;
    private String contentType;

    public ImageEntity(){}

    public ImageEntity(String identity, Integer size, String contentType) {
        this.identity = identity;
        this.size = size;
        this.contentType = contentType;
    }

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

}
