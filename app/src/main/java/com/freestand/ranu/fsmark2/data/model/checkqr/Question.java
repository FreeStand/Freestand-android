package com.freestand.ranu.fsmark2.data.model.checkqr;

/**
 * Created by prateek on 04/02/18.
 */

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("option1")
    private String option1;
    @SerializedName("option2")
    private String option2;
    @SerializedName("option3")
    private String option3;
    @SerializedName("question")
    private String question;
    @SerializedName("type")
    private String type;
    @SerializedName("option4")
    private String option4;
    @SerializedName("option5")
    private String option5;

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

}