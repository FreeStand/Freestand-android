package com.freestand.ranu.fsmark2.data.model.home;

/**
 * Created by prateek on 25/02/18.
 */
import java.util.List;

import com.freestand.ranu.fsmark2.data.model.checkqr.Question;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Survey {

    @SerializedName("campaignID")
    @Expose
    private String campaignID;
    @SerializedName("imgURL")
    @Expose
    private String imgURL;
    @SerializedName("products")
    @Expose
    private List<Product> products = null;
    @SerializedName("questions")
    @Expose
    private List<Question> questions = null;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("surveyID")
    @Expose
    private String surveyID;
    @SerializedName("title")
    @Expose
    private String title;

    public String getCampaignID() {
        return campaignID;
    }

    public void setCampaignID(String campaignID) {
        this.campaignID = campaignID;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(String surveyID) {
        this.surveyID = surveyID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}