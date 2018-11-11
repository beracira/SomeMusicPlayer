package io.github.ryanhoo.music.nodeAPI.PigFarmModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Privilege {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("fee")
    @Expose
    public String fee;
    @SerializedName("payed")
    @Expose
    public String payed;
    @SerializedName("st")
    @Expose
    public String st;
    @SerializedName("pl")
    @Expose
    public String pl;
    @SerializedName("dl")
    @Expose
    public String dl;
    @SerializedName("sp")
    @Expose
    public String sp;
    @SerializedName("cp")
    @Expose
    public String cp;
    @SerializedName("subp")
    @Expose
    public String subp;
    @SerializedName("cs")
    @Expose
    public Boolean cs;
    @SerializedName("maxbr")
    @Expose
    public String maxbr;
    @SerializedName("fl")
    @Expose
    public String fl;
    @SerializedName("toast")
    @Expose
    public Boolean toast;
    @SerializedName("flag")
    @Expose
    public String flag;
    @SerializedName("preSell")
    @Expose
    public Boolean preSell;

}