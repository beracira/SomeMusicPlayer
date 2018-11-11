package io.github.ryanhoo.music.nodeAPI.PigFarmModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MMusic {

    @SerializedName("name")
    @Expose
    public Object name;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("size")
    @Expose
    public String size;
    @SerializedName("extension")
    @Expose
    public String extension;
    @SerializedName("sr")
    @Expose
    public String sr;
    @SerializedName("dfsId")
    @Expose
    public String dfsId;
    @SerializedName("bitrate")
    @Expose
    public String bitrate;
    @SerializedName("playTime")
    @Expose
    public String playTime;
    @SerializedName("volumeDelta")
    @Expose
    public Double volumeDelta;

}