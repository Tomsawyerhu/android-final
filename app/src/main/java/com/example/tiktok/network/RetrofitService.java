package com.example.tiktok.network;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.tiktok.memory.Storage;
import com.example.tiktok.model.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitService {

    private NetworkService tikTokService = ServiceCreator.create(NetworkService.class);
    private Handler handler;
    Storage storage=Storage.getStorage();

    public RetrofitService(Handler handler){
        this.handler=handler;
    }

    public void fetchVideos(){
        tikTokService.getVideos().enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                //todo 获取到视频列表后
                List<Video> list=response.body();
                if(list!=null){
                    for(Video v:list){
                        storage.addVideo(v);
                    }
                }
                //提醒已获得视频列表
                System.out.println(Storage.getStorage().getVideoList().size());

                Message message=new Message();
                message.what=1;
                message.arg1=list!=null?list.size():0;
                handler.sendMessage(message);

            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                //todo 失败时的处理
                Log.e("VIDEO_ERROR","VIDEO_CANNOT_ACCESS");
                //提醒已获得视频列表
                System.out.println(Storage.getStorage().getVideoList().size());
                handler.sendEmptyMessage(1);
            }
        });
    }

}
