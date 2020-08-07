package com.example.tiktok;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.tiktok.adapter.VideoViewPagerAdapter;
import com.example.tiktok.memory.Storage;
import com.example.tiktok.network.RetrofitService;
import com.google.android.exoplayer2.SimpleExoPlayer;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    private long lastClick = 0;
    private static final long INTERVAL = 1000;

    @BindView(R.id.viewPager2)
    ViewPager2 viewPager2;

    @BindView(R.id.relative_layout)
    RelativeLayout relativeLayout;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    initViewPager(msg.arg1);
                    break;
            }
        }
    };
    RetrofitService retrofitService = new RetrofitService(handler);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        fetchVideos();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //监听快速点击事件
        if (this.lastClick == 0) {
            lastClick = new Date().getTime();
        } else {
            if (new Date().getTime() - lastClick <= INTERVAL) {
                triggerAnimation(ev.getRawX(), ev.getRawY());
            }
            lastClick = 0;
        }
        return super.dispatchTouchEvent(ev);
    }


    private void triggerAnimation(float x, float y) {
        System.out.println("触发快速点击事件");
        ImageView heart = new ImageView(this);
        heart.setImageResource(R.drawable.heart_on);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        params.leftMargin = (int) x - 100;
        params.topMargin = (int) y - 100;

        addContentView(heart, params);

        //ObjectAnimator transY=ObjectAnimator.ofFloat(heart,"translationY",2000,5000);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(heart, "scaleX", 1, 2);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(heart, "scaleY", 1, 2);
        ObjectAnimator opacity = ObjectAnimator.ofFloat(heart, "alpha", 1f, 0.7f, 0f);
        opacity.setDuration(2000);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(scaleX, scaleY, opacity);
        animatorSet.setDuration(2000);
        animatorSet.start();
        relativeLayout.removeView(heart);
    }

    private void fetchVideos() {
        retrofitService.fetchVideos();
    }


    /*
     * 初始化轮播组件
     */
    private void initViewPager(int size) {
        VideoViewPagerAdapter adapter = new VideoViewPagerAdapter(Storage.getStorage().getVideoList());
        adapter.setContext(this);
        //System.out.println(Storage.getStorage().getVideoList().size());
        viewPager2.setAdapter(adapter);

        //禁止滚动true为可以滑动false为禁止
        viewPager2.setUserInputEnabled(true);
        //设置垂直滚动ORIENTATION_VERTICAL
        viewPager2.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        //切换到指定页，是否展示过渡中间页
        viewPager2.setCurrentItem(0, true);

        //todo 页面监听
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                System.out.println("position" + position);
                SimpleExoPlayer before = null;
                SimpleExoPlayer after = Storage.getStorage().getPlayer((position + 1) % size);
                if (position > 0) {
                    before = Storage.getStorage().getPlayer((position - 1) % size);
                }

                if (before != null) {
                    before.setPlayWhenReady(false);
                }
                if (after != null) {
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