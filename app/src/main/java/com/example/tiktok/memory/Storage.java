package com.example.tiktok.memory;

import com.example.tiktok.model.Video;
import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存类
 */
public class Storage {
    private static Storage storage=new Storage();
    private List<Video> videoList=new ArrayList<>();
    private SimpleExoPlayer[] players=new SimpleExoPlayer[1000];

    private Storage(){}

    public static Storage getStorage(){
        return storage;
    }

    public void addVideo(Video video){
        this.videoList.add(video);
    }

    public List<Video> getVideoList(){
        return this.videoList;
    }

    public int getVideoListSize(){
        return this.videoList.size();
    }

    public void savePlayers(int index,SimpleExoPlayer player){
        this.players[index]=player;
    }

    public void releasePlayer(int index){
        if(this.players[index]!=null){
            this.players[index].release();
            this.players[index]=null;
        }
    }

    public SimpleExoPlayer getPlayer(int index){
        if(index<this.players.length){
            return this.players[index];
        }
        return null;
    }


}
