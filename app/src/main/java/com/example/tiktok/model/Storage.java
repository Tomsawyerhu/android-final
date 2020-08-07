package com.example.tiktok.model;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static Storage storage=new Storage();
    private List<Video> videoList=new ArrayList<>();

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
}
