package io.github.ryanhoo.music.nodeAPI.PigFarmModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CacheData {

    @SerializedName("data")
    @Expose
    public List<Datum> data = null;
    @SerializedName("code")
    @Expose
    public Integer code;

    public class Datum {
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("br")
        @Expose
        public Integer br;
        @SerializedName("size")
        @Expose
        public Integer size;
        @SerializedName("md5")
        @Expose
        public String md5;
        @SerializedName("code")
        @Expose
        public Integer code;
        @SerializedName("expi")
        @Expose
        public Integer expi;
        @SerializedName("type")
        @Expose
        public String type;
        @SerializedName("gain")
        @Expose
        public Double gain;
        @SerializedName("fee")
        @Expose
        public Integer fee;
        @SerializedName("uf")
        @Expose
        public Object uf;
        @SerializedName("payed")
        @Expose
        public Integer payed;
        @SerializedName("flag")
        @Expose
        public Integer flag;
        @SerializedName("canExtend")
        @Expose
        public Boolean canExtend;
        @SerializedName("freeTrialInfo")
        @Expose
        public Object freeTrialInfo;

    }

}
