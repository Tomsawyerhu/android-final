package com.example.tiktok.utils;

import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tiktok.R;
import com.example.tiktok.widget.media.VideoPlayerManager;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class VideoPlayerLoader extends ViewLoader {
    private String videoUrl;
    private String picUrl;
    private int starNum=0;
    private int msgNum=0;
    private int shareNum=0;

    private VideoPlayerManager videoPlayerManager;


    public VideoPlayerLoader(VideoPlayerManager videoPlayerManager){
        super();
        this.videoPlayerManager=videoPlayerManager;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

    public void setMsgNum(int msgNum) {
        this.msgNum = msgNum;
    }

    public void setShareNum(int shareNum) {
        this.shareNum = shareNum;
    }

    @Override
    public void loadData() {
        //加载头像
        if(picUrl!=null)
            Glide.with(this.getContext()).load(picUrl).into((CircleImageView) this.getView().findViewById(R.id.bg_pic));
        //加载点赞数
        ((TextView)this.getView().findViewById(R.id.star_text)).setText(String.valueOf(this.starNum));
        //加载评论数
        ((TextView)this.getView().findViewById(R.id.message_text)).setText(String.valueOf(this.msgNum));
        //加载转发数
        ((TextView)this.getView().findViewById(R.id.share_text)).setText(String.valueOf(this.shareNum));
        ((PlayerView)this.getView().findViewById(R.id.player_view)).setPlayer(this.videoPlayerManager.getPlayer());
        loadPlayer();
    }

    private void loadPlayer(){
        if(this.videoUrl!=null){
            VideoPlayerManager.DEFAULT_MEDIA_URI=this.videoUrl;
        }
        if(this.videoPlayerManager!=null){
            SimpleExoPlayer player=this.videoPlayerManager.getPlayer();

            videoPlayerManager.preparePlayer();
            videoPlayerManager.initializePlayer();
            videoPlayerManager.play();
        }
    }
}
