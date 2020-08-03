package com.example.tiktok.widget.media;

import android.content.Context;
import android.net.Uri;

import com.example.tiktok.R;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class VideoPlayerManager {
    private static SimpleExoPlayer player;
    private static final String DEFAULT_MEDIA_URI =
            "https://storage.googleapis.com/exoplayer-test-media-1/mkv/android-screens-lavf-56.36.100-aac-avc-main-1280x720.mkv";

    private Context context;
    private DataSource.Factory dataSourceFactory;
    private MediaSource mediaSource;

    public VideoPlayerManager(Context context){
        this.context=context;
        if(player==null)
            player = new SimpleExoPlayer.Builder(context).build();
        preparePlayer();
        initializePlayer();

    }

    public SimpleExoPlayer getPlayer(){
        return player;
    }

    private void preparePlayer(){
        dataSourceFactory=new DefaultDataSourceFactory(
                context, Util.getUserAgent(context, context.getString(R.string.app_name)));

        //准备好media source
        Uri uri=Uri.parse(DEFAULT_MEDIA_URI);
        mediaSource = new DashMediaSource.Factory(dataSourceFactory)
                .createMediaSource(uri, null, null);
    }

    private void initializePlayer() {
        player.setRepeatMode(Player.REPEAT_MODE_ALL);
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);
    }
}
