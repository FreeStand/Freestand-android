package com.freestand.ranu.fsmark2.data.model;

/**
 * Created by prateek on 11/02/18.
 */
import java.util.List;

import com.freestand.ranu.fsmark2.data.model.checkqr.Question;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CouponItemResponse {

    @SerializedName("questions")
    @Expose
    private List<Question> questions = null;
    @SerializedName("couponCode")
    @Expose
    private String couponCode;

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

}