package com.aeliwat.citeproc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Issued {

    @SerializedName("date-parts")
    List dateParts = new ArrayList<>();

    public List getDateParts() {
        return dateParts;
    }

    public void setDateParts(List dateParts) {
        this.dateParts = dateParts;
    }
}
