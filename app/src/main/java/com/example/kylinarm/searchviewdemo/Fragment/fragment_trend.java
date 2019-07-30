package com.example.kylinarm.searchviewdemo.Fragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kylinarm.searchviewdemo.MainActivity;
import com.example.kylinarm.searchviewdemo.R;

/**
 * Created by Administrator on 2019/6/17.
 */

public class fragment_trend extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //获取fragmenta这个布局文件
        View view = inflater.inflate(R.layout.fragment_trend,null);

        return view;
    }

}