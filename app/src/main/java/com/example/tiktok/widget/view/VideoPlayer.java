package com.example.tiktok.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.tiktok.R;

import androidx.annotation.Nullable;

/**
 *封装的视频播放组件
 */
public class VideoPlayer extends LinearLayout {
    public VideoPlayer(Context context) {
        this(context,null);
    }

    public VideoPlayer(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VideoPlayer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    /*
     *组件初始化
     */
    private void init(Context context,@Nullable AttributeSet attrs){
        //挂载view到类上
        View.inflate(context, R.layout.view_video_player, this);
        // todo
    }
}
