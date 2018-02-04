package com.freestand.ranu.fsmark2.data.model.checkqr;

/**
 * Created by prateek on 04/02/18.
 */

import com.google.gson.annotations.SerializedName;

public class CheckQr {

    @SerializedName("dict")
    private Dict dict;
    @SerializedName("status")
    private String status;

    public Dict getDict() {
        return dict;
    }

    public void setDict(Dict dict) {
        this.dict = dict;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}