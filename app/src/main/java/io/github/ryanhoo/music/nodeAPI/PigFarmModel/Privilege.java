package io.github.ryanhoo.music.nodeAPI.PigFarmModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Privilege {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("fee")
    @Expose
    public Integer fee;
    @SerializedName("payed")
    @Expose
    public Integer payed;
    @SerializedName("st")
    @Expose
    public Integer st;
    @SerializedName("pl")
    @Expose
    public Integer pl;
    @SerializedName("dl")
    @Expose
    public Integer dl;
    @SerializedName("sp")
    @Expose
    public Integer sp;
    @SerializedName("cp")
    @Expose
    public Integer cp;
    @SerializedName("subp")
    @Expose
    public Integer subp;
    @SerializedName("cs")
    @Expose
    public Boolean cs;
    @SerializedName("maxbr")
    @Expose
    public Integer maxbr;
    @SerializedName("fl")
    @Expose
    public Integer fl;
    @SerializedName("toast")
    @Expose
    public Boolean toast;
    @SerializedName("flag")
    @Expose
    public Integer flag;
    @SerializedName("preSell")
    @Expose
    public Boolean preSell;

}