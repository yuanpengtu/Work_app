package com.example.kylinarm.searchviewdemo;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.irecyclerview.IRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends IRecyclerView.Adapter<IRecyclerView.ViewHolder> {
    //当前上下文对象
    Context context;
    //RecyclerView填充Item数据的List对象
    List<String> datas;
    public int id_test=15;


    private static final int TYPE_ViewPager = 0;//viewpager滑动
    private static final int TYPE_Recycler = 1;//新盘走势
    private static final int TYPE_Special= 2;//特色商铺
    private static final int TYPE_Now = 3;//现场直击
    private static final int TYPE_Favorite = 4;//猜你喜欢
    //private ArrayList<>

    public RecyclerViewAdapter(Context context, List<String> datas){
        this.context = context;
        this.datas = datas;
    }

    //创建ViewHolder
    @NonNull
    @Override
    public IRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //实例化得到Item布局文件的View对象
        View item;
        IRecyclerView.ViewHolder holder = null;
        if(viewType==TYPE_ViewPager){
            item= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewpager,parent,false);
            holder=new ViewPagerItem(item);
//            holder.itemView.setId(id_test);
//            id_test++;
        }
        if(viewType==TYPE_Recycler){

        }
        if(viewType==TYPE_Special){

        }
        if(viewType==TYPE_Now){

        }
        if(viewType==TYPE_Favorite){

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull IRecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if(type==TYPE_ViewPager){
            ViewPagerItem holder1 = (ViewPagerItem) holder;
        }
        if(type==TYPE_Recycler){

        }
        if(type==TYPE_Special){

        }
        if(type==TYPE_Now){

        }
        if(type==TYPE_Favorite){

        }
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }


    //返回Item的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewPagerItem extends IRecyclerView.ViewHolder {
        ViewPager viewpager;
        public ViewPagerItem(@NonNull View itemView) {
            super(itemView);
            viewpager = itemView.findViewById(R.id.viewpager_up_item);
            fragment_a fragmenta;
            fragment_b fragmentb;
            List<Fragment> fragments = new ArrayList<Fragment>();
            fragmenta = new fragment_a();
            fragmentb = new fragment_b();
            fragments.add(fragmenta);
            fragments.add(fragmentb);
            if(context instanceof  FragmentActivity){
                FragmentAdpter fragmentAdpter = new FragmentAdpter(((FragmentActivity)context).getSupportFragmentManager(), fragments);
                viewpager.setAdapter(fragmentAdpter);
            }
        }
    }


}
