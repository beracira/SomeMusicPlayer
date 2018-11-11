package io.github.ryanhoo.music.nodeAPI.PigFarmModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Album {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("size")
    @Expose
    public Integer size;
    @SerializedName("picId")
    @Expose
    public String picId;
    @SerializedName("blurPicUrl")
    @Expose
    public String blurPicUrl;
    @SerializedName("companyId")
    @Expose
    public Integer companyId;
    @SerializedName("pic")
    @Expose
    public String pic;
    @SerializedName("picUrl")
    @Expose
    public String picUrl;
    @SerializedName("publishTime")
    @Expose
    public String publishTime;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("tags")
    @Expose
    public String tags;
    @SerializedName("company")
    @Expose
    public String company;
    @SerializedName("briefDesc")
    @Expose
    public String briefDesc;
    @SerializedName("artist")
    @Expose
    public Artist_ artist;
    @SerializedName("songs")
    @Expose
    public List<Object> songs = null;
    @SerializedName("alias")
    @Expose
    public List<Object> alias = null;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("copyrightId")
    @Expose
    public Integer copyrightId;
    @SerializedName("commentThreadId")
    @Expose
    public String commentThreadId;
    @SerializedName("artists")
    @Expose
    public List<Artist__> artists = null;
    @SerializedName("subType")
    @Expose
    public String subType;
    @SerializedName("transName")
    @Expose
    public Object transName;
    @SerializedName("picId_str")
    @Expose
    public String picIdStr;

}