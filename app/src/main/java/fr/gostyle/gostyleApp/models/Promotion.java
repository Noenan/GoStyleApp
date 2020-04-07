package fr.gostyle.gostyleApp.models;

import com.google.firebase.Timestamp;

/**
 * Class representing the promotion documents contained in the firebase store
 */

public class Promotion {
    private int code;
    private String description, imgUrl;
    private Timestamp dateLimit;
    private String magasin;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getDateLimit() {
        return dateLimit;
    }

    public void setDateLimit(Timestamp dateLimit) {
        this.dateLimit = dateLimit;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getMagasin() {
        return magasin;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
