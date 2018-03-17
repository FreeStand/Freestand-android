package com.freestand.ranu.fsmark2.data.model.checkqr;

/**
 * Created by prateek on 04/02/18.
 */

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Dict {

    @SerializedName("imgURL")
    private String imgURL;
    @SerializedName("questions")
    private List<Question> questions = null;
    @SerializedName("subtitle")
    private String subtitle;
    @SerializedName("surveyID")
    private String surveyID;
    @SerializedName("taken")
    private Boolean taken;
    @SerializedName("title")
    private String title;

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
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

    public Boolean getTaken() {
        return taken;
    }

    public void setTaken(Boolean taken) {
        this.taken = taken;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

