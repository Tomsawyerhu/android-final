package com.example.tiktok;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.tiktok.adapter.VideoViewPagerAdapter;
import com.example.tiktok.model.Storage;
import com.example.tiktok.network.RetrofitService;
import com.google.android.exoplayer2.SimpleExoPlayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.viewPager2)
    ViewPager2 viewPager2;

    Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    initViewPager(msg.arg1);
                    break;
            }
        }
    };
    RetrofitService retrofitService=new RetrofitService(handler);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        fetchVideos();
    }

    private void fetchVideos(){
        retrofitService.fetchVideos();
    }


    /*
     * 初始化轮播组件
     */
    private void initViewPager(int size){
        VideoViewPagerAdapter adapter=new VideoViewPagerAdapter(Storage.getStorage().getVideoList());
        adapter.setContext(this);
        //System.out.println(Storage.getStorage().getVideoList().size());
        viewPager2.setAdapter(adapter);

        //禁止滚动true为可以滑动false为禁止
        viewPager2.setUserInputEnabled(true);
        //设置垂直滚动ORIENTATION_VERTICAL
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        //切换到指定页，是否展示过渡中间页
        viewPager2.setCurrentItem(0,true);

        //todo 页面监听
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                System.out.println("position"+position);
                SimpleExoPlayer before=null;
                SimpleExoPlayer after=Storage.getStorage().getPlayer((position+1)%size);
                if(position>0){
                    before=Storage.getStorage().getPlayer((position-1)%size);
                }

                if(before!=null){
                    before.setPlayWhenReady(false);
                }
                if(after!=null){
                    after.setPlayWhenReady(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

}