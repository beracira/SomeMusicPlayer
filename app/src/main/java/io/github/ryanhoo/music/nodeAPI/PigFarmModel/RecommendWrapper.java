package io.github.ryanhoo.music.nodeAPI.PigFarmModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RecommendWrapper {

    @SerializedName("code")
    @Expose
    public Integer code;
    @SerializedName("recommend")
    @Expose
    public List<Recommend> recommend = null;

}