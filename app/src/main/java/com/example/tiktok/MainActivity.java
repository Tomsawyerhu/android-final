package com.example.tiktok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.widget.Adapter;

import com.example.tiktok.R;
import com.example.tiktok.adapter.VideoViewPagerAdapter;
import com.example.tiktok.model.Video;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.viewPager2)
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initViewPager();
    }


    /*
     * 初始化轮播组件
     */
    private void initViewPager(){
        List<Video> list=new ArrayList<>();
        list.add(new Video());
        VideoViewPagerAdapter adapter=new VideoViewPagerAdapter(list);
        viewPager2.setAdapter(adapter);

        //禁止滚动true为可以滑动false为禁止
        viewPager2.setUserInputEnabled(true);
        //设置垂直滚动ORIENTATION_VERTICAL，横向的为
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
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
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }

}