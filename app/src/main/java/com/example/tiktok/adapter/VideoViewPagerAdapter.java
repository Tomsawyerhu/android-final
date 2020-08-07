package com.example.tiktok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tiktok.R;
import com.example.tiktok.model.Video;
import com.example.tiktok.utils.VideoPlayerLoader;
import com.example.tiktok.widget.media.VideoPlayerManager;

import java.util.List;

/**
 * 视频轮播组件适配器
 * viewpager2基于recyclerview
 * 实现无限循环播放：将getItemCount设为Integer.MAX_VALUE
 */
public class VideoViewPagerAdapter extends RecyclerView.Adapter<VideoViewPagerAdapter.ViewHolder> {

    //是否是循环轮播
    private boolean isLoop = true;

    private List<Video> dataList;
    private int viewHolderCount = 0;

    private Context context;


    public VideoViewPagerAdapter(List<Video> list) {
        this.dataList = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.view_video_player, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.setIndex(this.viewHolderCount);

        this.viewHolderCount += 1;
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video video = dataList.get(isLoop() ? position % this.dataList.size() : position);
        holder.fillData(video);

    }

    @Override
    public int getItemCount() {
        return this.isLoop ? Integer.MAX_VALUE : this.dataList.size();
    }

    public boolean isLoop() {
        return isLoop;
    }

    public void setLoop(boolean loop) {
        isLoop = loop;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private int index;
        private View videoPlayer;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.videoPlayer = itemView;
        }

        public void fillData(Video video) {
            VideoPlayerManager manager = new VideoPlayerManager(context);
            VideoPlayerLoader loader = new VideoPlayerLoader(manager);
            loader.setContext(context);
            loader.setView(videoPlayer);
            loader.setVideoUrl(video.feedUrl);
            loader.setPicUrl(video.avatar);
            loader.setStarNum(video.likeCount);
            loader.setMsgNum(video.likeCount);
            loader.setShareNum(video.likeCount);
            loader.setNickname(video.nickname);
            loader.setDescription(video.description);
            loader.setIndex(this.index);
            loader.loadData();
            //System.out.println(video);
        }

    }


}
