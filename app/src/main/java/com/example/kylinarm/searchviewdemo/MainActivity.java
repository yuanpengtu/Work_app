package com.example.kylinarm.searchviewdemo;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.aspsine.irecyclerview.IRecyclerView;
import com.aspsine.irecyclerview.OnLoadMoreListener;
import com.aspsine.irecyclerview.OnRefreshListener;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    List<Fragment> fragments;
    fragment_a fragmentA;
    fragment_b fragmentB;

    ViewPager viewPager;
    ImageView trendImage;


    IRecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //沉浸式狀態欄設置
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        //沉浸式狀態欄設置

        //頂端滑動ViewPager設置
//        fragments = new ArrayList<Fragment>();
//        viewPager = (ViewPager) findViewById(R.id.viewpager_up);
//        fragmentA = new fragment_a();
//        fragmentB = new fragment_b();
//        fragments.add(fragmentA);
//        fragments.add(fragmentB);
//        FragmentAdpter fragmentAdpter = new FragmentAdpter(getSupportFragmentManager(), fragments);
//        viewPager.setAdapter(fragmentAdpter);
       //頂端滑動ViewPager設置



//        trendImage = (ImageView) findViewById(R.id.trend);
//        trendImage.bringToFront();

        recyclerView = (IRecyclerView) findViewById(R.id.Irecyclerview);
        //loadMoreFooterView = (LoadMoreFooterView) recyclerView.getLoadMoreFooterView();
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        List<String> datas = new ArrayList<>();
        datas.add("0");
        recyclerView.setAdapter(new RecyclerViewAdapter(this,datas));

       // ViewPager test=findViewById(R.layout.item_viewpager);


    }



//    public class FragmentAdpter extends FragmentPagerAdapter {
//        List<Fragment> fragments;
//        public FragmentAdpter(FragmentManager fm, List<Fragment> fragments) {
//            super(fm);
//            this.fragments = fragments;
//        }
//        @Override
//        public Fragment getItem(int position) {
//            return fragments.get(position);
//        }
//        @Override
//        public int getCount() {
//            return fragments.size();
//        }
 //   }
//    public FragmentAdpter Builder(List<Fragment> frag){
//        return new FragmentAdpter(getSupportFragmentManager(), frag);
//    }




}
