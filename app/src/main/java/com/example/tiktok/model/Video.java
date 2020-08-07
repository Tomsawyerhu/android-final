package com.example.tiktok.model;

import com.google.gson.annotations.SerializedName;

/**
 * 视频自定义类
 */
public class Video {
    @SerializedName("_id")
    public String id;
    @SerializedName("feedurl")
    public String feedUrl;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("description")
    public String description;
    @SerializedName("likecount")
    public int likeCount;
    @SerializedName("avatar")
    public String avatar;

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", feedUrl='" + feedUrl + '\'' +
                ", nickname='" + nickname + '\'' +
                ", description='" + description + '\'' +
                ", likeCount=" + likeCount +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
