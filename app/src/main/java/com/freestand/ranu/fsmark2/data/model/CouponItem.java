package com.freestand.ranu.fsmark2.data.model;

/**
 * Created by prateek on 11/02/18.
 */
import com.google.gson.annotations.SerializedName;

public class CouponItem {

    @SerializedName("brandName")
    private String brandName;
    @SerializedName("generalCouponCode")
    private String generalCouponCode;
    @SerializedName("imgURL")
    private String imgURL;
    @SerializedName("isUnique")
    private Boolean isUnique;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("title")
    private String title;
    @SerializedName("couponID")
    private String couponID;
    @SerializedName("redeem")
    private String redeem;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getGeneralCouponCode() {
        return generalCouponCode;
    }

    public void setGeneralCouponCode(String generalCouponCode) {
        this.generalCouponCode = generalCouponCode;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Boolean getIsUnique() {
        return isUnique;
    }

    public void setIsUnique(Boolean isUnique) {
        this.isUnique = isUnique;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCouponID() {
        return couponID;
    }

    public void setCouponID(String couponID) {
        this.couponID = couponID;
    }

    public String getRedeem() {
        return redeem;
    }

    public void setRedeem(String redeem) {
        this.redeem = redeem;
    }

}