package com.example.tiktok.network;

import com.example.tiktok.model.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NetworkService {

    @GET("video")
    Call<List<Video>> getVideos();
}
