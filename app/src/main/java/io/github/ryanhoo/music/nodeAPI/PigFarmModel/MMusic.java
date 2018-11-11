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
    public Integer size;
    @SerializedName("extension")
    @Expose
    public String extension;
    @SerializedName("sr")
    @Expose
    public Integer sr;
    @SerializedName("dfsId")
    @Expose
    public Integer dfsId;
    @SerializedName("bitrate")
    @Expose
    public Integer bitrate;
    @SerializedName("playTime")
    @Expose
    public Integer playTime;
    @SerializedName("volumeDelta")
    @Expose
    public Double volumeDelta;

}