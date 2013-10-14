package com.ffg.shelter.model;

import javax.persistence.*;
import java.util.Arrays;


@Entity
public class Image {
    private Long id;
    private byte[] imageContent;
    private Long clientId;
    private Long campId;

    public Long getCampId() {
        return campId;
    }

    public void setCampId(Long campId) {
        this.campId = campId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (clientId != null ? !clientId.equals(image.clientId) : image.clientId != null) return false;
        if (id != null ? !id.equals(image.id) : image.id != null) return false;
        if (!Arrays.equals(imageContent, image.imageContent)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (imageContent != null ? Arrays.hashCode(imageContent) : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        return result;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Lob
    public byte[] getImageContent() {
        return imageContent;
    }

    public void setImageContent(byte[] imageContent) {
        this.imageContent = imageContent;
    }


}
