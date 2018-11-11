package io.github.ryanhoo.music.nodeAPI.PigFarmModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Artist__ {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("picId")
    @Expose
    public String picId;
    @SerializedName("img1v1Id")
    @Expose
    public String img1v1Id;
    @SerializedName("briefDesc")
    @Expose
    public String briefDesc;
    @SerializedName("picUrl")
    @Expose
    public String picUrl;
    @SerializedName("img1v1Url")
    @Expose
    public String img1v1Url;
    @SerializedName("albumSize")
    @Expose
    public String albumSize;
    @SerializedName("alias")
    @Expose
    public List<Object> alias = null;
    @SerializedName("trans")
    @Expose
    public String trans;
    @SerializedName("musicSize")
    @Expose
    public String musicSize;

}