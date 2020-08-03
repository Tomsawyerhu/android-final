package com.example.tiktok.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tiktok.R;
import com.example.tiktok.model.Video;
import com.example.tiktok.widget.media.VideoPlayer;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 视频轮播组件适配器
 * viewpager2基于recyclerview
 * 实现无限循环播放：将getItemCount设为Integer.MAX_VALUE
 */
public class VideoViewPagerAdapter extends RecyclerView.Adapter<VideoViewPagerAdapter.ViewHolder> {

    //是否是循环轮播
    private boolean isLoop=true;

    private List<Video> dataList;
    private int viewHolderCount=0;

    public VideoViewPagerAdapter(List<Video> list){
        this.dataList=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        View view=LayoutInflater.from(context).inflate(R.layout.view_video_player,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        viewHolder.setIndex(this.viewHolderCount);

        this.viewHolderCount+=1;
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Video video=dataList.get(this.isLoop?position%this.dataList.size():position);
        holder.fillData(video);
    }

    @Override
    public int getItemCount() {
        return this.isLoop?Integer.MAX_VALUE:this.dataList.size();
    }

    public boolean isLoop() {
        return isLoop;
    }

    public void setLoop(boolean loop) {
        isLoop = loop;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private int index;
        private VideoPlayer videoPlayer;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.videoPlayer=(VideoPlayer)itemView;
        }

        public void fillData(Video video){
            //todo 将数据填充到控件上
        }
    }
}
