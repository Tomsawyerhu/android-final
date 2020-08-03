package com.example.tiktok.widget.media;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.tiktok.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.EventLogger;
import com.google.android.exoplayer2.util.Util;

import androidx.annotation.Nullable;

/**
 *封装的视频播放组件
 */
public class VideoPlayer extends LinearLayout {
    VideoPlayerManager videoPlayerManager;
    SimpleExoPlayer player;

    public VideoPlayer(Context context) {
        this(context,null);
    }

    public VideoPlayer(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VideoPlayer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        videoPlayerManager=new VideoPlayerManager(context);
        init(context,attrs);
    }


    /*
     *组件初始化
     */
    private void init(Context context,@Nullable AttributeSet attrs){
        //挂载view到类上
        View view=(View)View.inflate(context, R.layout.view_video_player, this);
        //player view
        PlayerView playerView=view.findViewById(R.id.player_view);

        player=videoPlayerManager.getPlayer();
        playerView.setPlayer(player);

        // todo
    }



}
