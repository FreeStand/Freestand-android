package com.freestand.ranu.fsmark2.data.model.home;

/**
 * Created by prateek on 25/02/18.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeData {

    @SerializedName("isEmpty")
    @Expose
    private Boolean isEmpty;
    @SerializedName("surveyType")
    @Expose
    private String surveyType;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("survey")
    @Expose
    private Survey survey;

    public Boolean getIsEmpty() {
        return isEmpty;
    }

    public void setIsEmpty(Boolean isEmpty) {
        this.isEmpty = isEmpty;
    }

    public String getSurveyType() {
        return surveyType;
    }

    public void setSurveyType(String surveyType) {
        this.surveyType = surveyType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

}