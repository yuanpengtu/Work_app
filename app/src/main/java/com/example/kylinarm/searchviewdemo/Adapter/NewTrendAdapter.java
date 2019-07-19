package com.example.kylinarm.searchviewdemo.Adapter;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kylinarm.searchviewdemo.Bean.NewTrend;
import com.example.kylinarm.searchviewdemo.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
public class NewTrendAdapter extends RecyclerView.Adapter<NewTrendAdapter.ViewHolder>{

    private List<NewTrend> NewTrendList;
    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView money;
        TextView unit;
        ImageView arrow;
        TextView percentage;
        public ViewHolder(View view){
            super(view);
            title=view.findViewById(R.id.title);
            money=view.findViewById(R.id.money);
            unit=view.findViewById(R.id.unit);
            arrow=view.findViewById(R.id.arrow);
            percentage=view.findViewById(R.id.percentage);
        }
    }

    public NewTrendAdapter(List<NewTrend> mNewTrendList){
        this.NewTrendList=mNewTrendList;
    }
    @Override
    public NewTrendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newtrend_item,parent,false);
        RecyclerView.ViewHolder holder=new ViewHolder(view);
        return (ViewHolder) holder;
    }

    @Override
    public void onBindViewHolder(NewTrendAdapter.ViewHolder holder, int position) {
        NewTrend trenditem=NewTrendList.get(position);
        holder.title.setText(trenditem.getTitle());
        if(trenditem.getMoney()==(int)(trenditem.getMoney())){
            holder.money.setText(String.valueOf((int)(trenditem.getMoney())));
        }
        else{
            holder.money.setText(String.valueOf(trenditem.getMoney()));
        }
        holder.unit.setText(trenditem.getUnit());
        if(trenditem.getUp_flag()==1) {
            holder.arrow.setImageResource(R.drawable.uparrow);
            holder.percentage.setText(trenditem.getPercentage());
        }
        else if(trenditem.getUp_flag()==0){
            holder.arrow.setImageResource(R.drawable.downarrow);
            holder.percentage.setText(trenditem.getPercentage());
            holder.percentage.setTextColor(Color.parseColor("#3cb950"));
        }
        else if(trenditem.getUp_flag()==2) {
            holder.arrow.setVisibility(View.INVISIBLE);
            holder.percentage.setText(trenditem.getPercentage());
            holder.percentage.setTextColor(Color.parseColor("#9aa0a6"));
        }
    }
    @Override
    public int getItemCount() {
        return NewTrendList.size();
    }
}