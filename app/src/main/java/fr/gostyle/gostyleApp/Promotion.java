package fr.gostyle.gostyleApp;

import com.google.firebase.Timestamp;

public class Promotion {
    private String code;
    private String description;
    private Timestamp dateLimit;
    private String magasin;

    public  Promotion(){
        //empty constructor
    }
    public Promotion(String code, String description, Timestamp dateLimit, String magasin){
        this.code = code;
        this.description = description;
        this.dateLimit = dateLimit;
        this.magasin = magasin;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getDateLimit() {
        return dateLimit;
    }

    public String getMagasin() {
        return magasin;
    }
}
