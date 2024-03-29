package com.example.kylinarm.searchviewdemo.Adapter;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kylinarm.searchviewdemo.Bean.NewTrendBean;
import com.example.kylinarm.searchviewdemo.R;

import java.util.List;

public class NewTrendAdapter extends RecyclerView.Adapter<NewTrendAdapter.ViewHolder>{

    private List<NewTrendBean> NewTrendList;
    private static int FLATTREND=2;
    private static int UPTREND=1;
    private static int DOWNTREND=0;
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

    public NewTrendAdapter(List<NewTrendBean> mNewTrendList){
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
        NewTrendBean trenditem=NewTrendList.get(position);
        holder.title.setText(trenditem.getTitle());
        if(trenditem.getMoney()==(int)(trenditem.getMoney())){
            holder.money.setText(String.valueOf((int)(trenditem.getMoney())));
        }
        else{
            holder.money.setText(String.valueOf(trenditem.getMoney()));
        }
        holder.unit.setText(trenditem.getUnit());
        if(trenditem.getUp_flag()==DOWNTREND){
            holder.arrow.setImageResource(R.drawable.downarrow);
            holder.percentage.setText(trenditem.getPercentage());
            holder.percentage.setTextColor(Color.parseColor("#3cb950"));
        }
        else if(trenditem.getUp_flag()==UPTREND) {
            holder.arrow.setImageResource(R.drawable.uparrow);
            holder.percentage.setText(trenditem.getPercentage());
        }
        else if(trenditem.getUp_flag()==FLATTREND) {
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