package com.example.kylinarm.searchviewdemo;


import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.example.kylinarm.searchviewdemo.View.LoadMoreFooterView;
import com.example.kylinarm.searchviewdemo.Adapter.RecyclerViewAdapter;
import com.example.kylinarm.searchviewdemo.Bean.NowBean;
import com.example.kylinarm.searchviewdemo.Bean.SpecialBean;
import com.example.kylinarm.searchviewdemo.Decoration.RecyclerViewSpacesItemDecoration;
import com.example.kylinarm.searchviewdemo.Fragment.fragment_b;
import com.example.kylinarm.searchviewdemo.Fragment.fragment_a;
import com.example.kylinarm.searchviewdemo.common.Utility;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnLoadMoreListener{
    List<Fragment> fragments;
    fragment_a fragmentA;
    fragment_b fragmentB;
    ViewPager viewPager;
    ImageView trendImage;
    IRecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private boolean isRecyclerScroll;
    //记录上一次位置，防止在同一内容块里滑动 重复定位到tablayout
    private int lastPos;
    //用于recyclerView滑动到指定的位置
    private boolean canScroll;
    private int scrollToPosition;
    private LoadMoreFooterView loadMoreFooterView;
    private int mPage=0;
    List<String> datas;
    RecyclerViewAdapter mAdapter;
    int runCount;
    TabLayout locate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar();
        initTabLayout();
        initDatas();
        initIRecyclerView();
    }
    public String convertStraemToString(InputStream inputStream){

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return  stringBuilder.toString();
    }
    public void moveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int position) {
        // 第一个可见的view的位置
        int firstItem = manager.findFirstVisibleItemPosition();
        // 最后一个可见的view的位置
        int lastItem = manager.findLastVisibleItemPosition();
        if (position <= firstItem) {
            // 如果跳转位置firstItem 之前(滑出屏幕的情况)，就smoothScrollToPosition可以直接跳转，
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 跳转位置在firstItem 之后，lastItem 之间（显示在当前屏幕），smoothScrollBy来滑动到指定位置
            int top = mRecyclerView.getChildAt(position - firstItem).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        } else {
            // 如果要跳转的位置在lastItem 之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用当前moveToPosition方法，执行上一个判断中的方法
            mRecyclerView.smoothScrollToPosition(position);
            scrollToPosition = position;
            canScroll = true;
        }
    }
    public void LoadMore() {
        loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
        if (mPage==5) {
            loadMoreFooterView.setStatus(LoadMoreFooterView.Status.THE_END);

        } else {
            mPage++;
        }
    }
    @Override
    public void onLoadMore() {
        final Handler handler = new Handler();
        loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
        runCount = 0;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (runCount == 1) {
                    handler.removeCallbacks(this);
                }
                handler.postDelayed(this, 150);
                runCount++;
                LoadMore();
            }
        };
        handler.postDelayed(runnable, 1000);// 打开定时器，执行操作
    }
    public void initDatas(){
        datas = new ArrayList<>();
        InputStream inputStream =null;
        try {
            inputStream = getAssets().open("like.json");
            String teachersData = convertStraemToString(inputStream);
            Gson gson = new Gson();
            SpecialBean common = gson.fromJson(teachersData, SpecialBean.class);
            Utility.likelist=common;
            Utility.current_item=0;

        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream inputStream_write =null;
        try {
            inputStream_write = getAssets().open("city_house.json");
            String teachersData = convertStraemToString(inputStream_write);
            Gson gson = new Gson();
            NowBean common = gson.fromJson(teachersData, NowBean.class);
            Utility.speciallist=common;
            Utility.current_item_shop=0;
            Utility.current_item_write=0;

        } catch (IOException e) {
            e.printStackTrace();
        }
        datas.add("0");
        datas.add("1");
        datas.add("2");
        datas.add("6");
        for(int i=0;i<3;i++){
            datas.add("3");
        }
        datas.add("5");
        datas.add("6");
        for(int i=0;i<3;i++){
            datas.add("8");
        }
        datas.add("7");
        for(int i=0;i<Utility.likelist.getResult().getRows().size();i++){
            datas.add("4");
        }
    }
    public void initStatusBar(){
        //沉浸式狀態欄設置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        //沉浸式狀態欄設置
    }
    private void initTabLayout() {
        locate=findViewById(R.id.tab);
        locate.addTab(locate.newTab().setText("商铺"));
        locate.addTab(locate.newTab().setText("写字楼"));
        locate.addTab(locate.newTab().setText("猜你喜欢"));
    }
    private void initIRecyclerView() {
        recyclerView =findViewById(R.id.Irecyclerview);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION,0);
        recyclerView.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));
        mAdapter=new RecyclerViewAdapter(this,datas);
        recyclerView.setIAdapter(mAdapter);
        locate.setVisibility(View.INVISIBLE);

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //当滑动由recyclerView触发时，isRecyclerScroll 置true
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    isRecyclerScroll = true;
                }
                return false;
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isRecyclerScroll) {
                    //第一个可见的view的位置，即tablayou需定位的位置

                    int position = linearLayoutManager.findFirstVisibleItemPosition();
                    Log.i("location_selected",String.valueOf(position));
                    if(position<=3){
                        locate.setVisibility(View.INVISIBLE);
                    }
                    if (3<position&&position<=7) {
                        locate.setVisibility(View.VISIBLE);
                        locate.bringToFront();
                        locate.setScrollPosition(0, 0, true);
                    }
                    if (8<position&&position<13) {
                        locate.setVisibility(View.VISIBLE);
                        locate.bringToFront();
                        locate.setScrollPosition(1, 0, true);
                    }
                    if (14<=position) {
                        locate.setVisibility(View.VISIBLE);
                        locate.bringToFront();
                        locate.setScrollPosition(2, 0, true);
                    }
                    //2,7,12
                }
            }
        });
        locate.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //点击标签，使recyclerView滑动，isRecyclerScroll置false
                int pos = tab.getPosition();
                isRecyclerScroll = false;
                Log.i("location_selected",String.valueOf(pos));

                if(locate.getSelectedTabPosition()==0){
                    moveToPosition(linearLayoutManager, recyclerView, 4);
                }
                if(locate.getSelectedTabPosition()==1){
                    moveToPosition(linearLayoutManager, recyclerView, 9);
                }
                if(locate.getSelectedTabPosition()==2){
                    moveToPosition(linearLayoutManager, recyclerView, 14);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (canScroll) {
                    canScroll = false;
                    moveToPosition(linearLayoutManager, recyclerView, scrollToPosition);
                }
            }
        });
        loadMoreFooterView = (LoadMoreFooterView) recyclerView.getLoadMoreFooterView();
        recyclerView.setOnLoadMoreListener(this);
        recyclerView.setLoadMoreEnabled(true);
    }
}
