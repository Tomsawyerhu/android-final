package com.example.tiktok.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tiktok.model.Video;
import com.example.tiktok.network.ServiceCreator;
import com.example.tiktok.network.TikTokService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private TikTokService tikTokService = ServiceCreator.create(TikTokService.class);

    public void fetchVideos(){
        tikTokService.getVideos().enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                //todo 获取到视频列表后
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                //todo 失败时的处理
            }
        });
    }
}
