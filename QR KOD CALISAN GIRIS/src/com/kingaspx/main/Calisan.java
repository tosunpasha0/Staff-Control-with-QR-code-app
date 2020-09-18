package com.kingaspx.main;

/**
 * Author Mehmet Emin Han (tosunPasha) Bıyık
 * Date: 29/07/2020
 */


public class Calisan {

    private String ad;
    private String Soyad;
    private String unvan;

    public Calisan(String ad, String soyad, String unvan) {
        this.ad = ad;
        this.Soyad = soyad;
        this.unvan = unvan;
    }

    // Getter ve Setter Metotlar

    //Ad
    public String getAd() {
        return ad;
    }
    public void setAd(String ad) {
        this.ad = ad;
    }

    //Soyad
    public String getSoyad() {
    return Soyad;
}
    public void setSoyad(String soyad) {
        Soyad = soyad;
    }

    //Unvan
    public String getUnvan() {
        return unvan;
    }
    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }


}
