package com.example.tiktok.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCreator {
    private static final String BASE_URL="https://beiyou.bytedance.com/api/invoke/video/invoke/";
    private static Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    public static <S> S create(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }
}
