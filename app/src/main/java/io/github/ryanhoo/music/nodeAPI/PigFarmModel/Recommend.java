package io.github.ryanhoo.music.nodeAPI.PigFarmModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recommend {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("position")
    @Expose
    public Integer position;
    @SerializedName("alias")
    @Expose
    public List<Object> alias = null;
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("fee")
    @Expose
    public Integer fee;
    @SerializedName("copyrightId")
    @Expose
    public Integer copyrightId;
    @SerializedName("disc")
    @Expose
    public String disc;
    @SerializedName("no")
    @Expose
    public Integer no;
    @SerializedName("artists")
    @Expose
    public List<Artist> artists = null;
    @SerializedName("album")
    @Expose
    public Album album;
    @SerializedName("starred")
    @Expose
    public Boolean starred;
    @SerializedName("popularity")
    @Expose
    public Integer popularity;
    @SerializedName("score")
    @Expose
    public Integer score;
    @SerializedName("starredNum")
    @Expose
    public Integer starredNum;
    @SerializedName("duration")
    @Expose
    public Integer duration;
    @SerializedName("playedNum")
    @Expose
    public Integer playedNum;
    @SerializedName("dayPlays")
    @Expose
    public Integer dayPlays;
    @SerializedName("hearTime")
    @Expose
    public Integer hearTime;
    @SerializedName("ringtone")
    @Expose
    public Object ringtone;
    @SerializedName("crbt")
    @Expose
    public Object crbt;
    @SerializedName("audition")
    @Expose
    public Object audition;
    @SerializedName("copyFrom")
    @Expose
    public String copyFrom;
    @SerializedName("commentThreadId")
    @Expose
    public String commentThreadId;
    @SerializedName("rtUrl")
    @Expose
    public Object rtUrl;
    @SerializedName("ftype")
    @Expose
    public Integer ftype;
    @SerializedName("rtUrls")
    @Expose
    public List<Object> rtUrls = null;
    @SerializedName("copyright")
    @Expose
    public Integer copyright;
    @SerializedName("transName")
    @Expose
    public Object transName;
    @SerializedName("sign")
    @Expose
    public Object sign;
    @SerializedName("hMusic")
    @Expose
    public HMusic hMusic;
    @SerializedName("mMusic")
    @Expose
    public MMusic mMusic;
    @SerializedName("lMusic")
    @Expose
    public LMusic lMusic;
    @SerializedName("bMusic")
    @Expose
    public BMusic bMusic;
    @SerializedName("mvid")
    @Expose
    public Integer mvid;
    @SerializedName("rtype")
    @Expose
    public Integer rtype;
    @SerializedName("rurl")
    @Expose
    public Object rurl;
    @SerializedName("mp3Url")
    @Expose
    public Object mp3Url;
    @SerializedName("reason")
    @Expose
    public String reason;
    @SerializedName("privilege")
    @Expose
    public Privilege privilege;
    @SerializedName("alg")
    @Expose
    public String alg;

}