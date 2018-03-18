package com.freestand.ranu.fsmark2.data.model.college;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prateek on 18/03/18.
 */
public class College {

    @SerializedName("abbreviation")
    private String abbreviation;
    @SerializedName("name")
    private String name;

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}