package me.zed.countryselect;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountryList {

    @SerializedName("result")
    @Expose
    private List<Result> result = null;


    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

}

