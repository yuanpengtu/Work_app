package com.example.kylinarm.searchviewdemo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aspsine.irecyclerview.IRecyclerView;
import com.example.kylinarm.searchviewdemo.Bean.NewTrend;
import com.example.kylinarm.searchviewdemo.Bean.NowBean;
import com.example.kylinarm.searchviewdemo.Bean.SpecialBean;
import com.example.kylinarm.searchviewdemo.Decoration.RecyclerViewSpacesItemDecoration;
import com.example.kylinarm.searchviewdemo.Fragment.fragment_b;
import com.example.kylinarm.searchviewdemo.Fragment.fragment_a;
import com.example.kylinarm.searchviewdemo.Listener.PageIndicator;
import com.example.kylinarm.searchviewdemo.MainActivity;
import com.example.kylinarm.searchviewdemo.R;
import com.example.kylinarm.searchviewdemo.View.UrlImageView;
import com.example.kylinarm.searchviewdemo.common.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.support.v4.content.ContextCompat.getColor;

public class RecyclerViewAdapter extends IRecyclerView.Adapter<IRecyclerView.ViewHolder> {
    //当前上下文对象
    Context context;
    //RecyclerView填充Item数据的List对象
    List<String> datas;
    //新房走势的item数据
    private List<NewTrend> mNewTrendList;


    public int id_test = 15;
    private static final int TYPE_ViewPager = 0;//viewpager滑动
    private static final int TYPE_Recycler = 1;//新盘走势
    private static final int TYPE_Special = 2;//特色商铺
    private static final int TYPE_Now = 3;//现场直击_商铺
    private static final int TYPE_Now_2 = 8;//现场直击_写字楼
    private static final int TYPE_Favorite = 4;//猜你喜欢
    private static final int TYPE_Writer = 5;//特色写字楼
    private static final int TYPE_Title_Now = 6;//现场直击title
    private static final int TYPE_Title_Like = 7;//猜你喜欢title

    public RecyclerViewAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public IRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item;
        IRecyclerView.ViewHolder holder = null;
        if (viewType == TYPE_ViewPager) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_viewpager, parent, false);
            holder = new ViewPagerItem(item);
        }
        if (viewType == TYPE_Recycler) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
            holder = new RecyclerItem(item);

        }
        if (viewType == TYPE_Special) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shape_store, parent, false);
            holder = new ShapeItem(item);
        }
        if (viewType == TYPE_Now) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_now, parent, false);
            try {
                holder = new NowShopItem(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (viewType == TYPE_Now_2) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_now, parent, false);
            try {
                holder = new NowWriteItem(item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (viewType == TYPE_Favorite) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guesslike, parent, false);
            holder = new FavoriteItem(item);
        }
        if (viewType == TYPE_Writer) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shape_write, parent, false);
            holder = new ShapeWriteItem(item);
        }
        if (viewType == TYPE_Title_Now) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nowtitle, parent, false);
            holder = new TitleNowItem(item);
        }
        if (viewType == TYPE_Title_Like) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guesslike_title, parent, false);
            holder = new TitleLikeItem(item);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull IRecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == TYPE_ViewPager) {
            ViewPagerItem holder1 = (ViewPagerItem) holder;
        }
        if (type == TYPE_Recycler) {
            RecyclerItem holder1 = (RecyclerItem) holder;
        }
        if (type == TYPE_Special) {
            ShapeItem holder1 = (ShapeItem) holder;
        }
        if (type == TYPE_Now) {
            NowShopItem holder1 = (NowShopItem) holder;
        }
        if (type == TYPE_Now_2) {
            NowWriteItem holder1 = (NowWriteItem) holder;
        }
        if (type == TYPE_Favorite) {
            FavoriteItem holder1 = (FavoriteItem) holder;
        }
        if (type == TYPE_Writer) {
            ShapeWriteItem holder1 = (ShapeWriteItem) holder;
        }
        if (type == TYPE_Title_Now) {
            TitleNowItem holder1 = (TitleNowItem) holder;
        }
        if (type == TYPE_Title_Like) {
            TitleLikeItem holder1 = (TitleLikeItem) holder;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.parseInt(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewPagerItem extends IRecyclerView.ViewHolder {
        ViewPager viewpager;
        LinearLayout dotHorizontal;

        public ViewPagerItem(@NonNull View itemView) {
            super(itemView);
            viewpager = itemView.findViewById(R.id.viewpager_up_item);
            dotHorizontal = itemView.findViewById(R.id.dot_horizontal);
            fragment_a fragmenta;
            fragment_b fragmentb;
            List<Fragment> fragments = new ArrayList<Fragment>();
            fragmenta = new fragment_a();
            fragmentb = new fragment_b();
            fragments.add(fragmenta);
            fragments.add(fragmentb);
            if (context instanceof FragmentActivity) {
                FragmentAdpter fragmentAdpter = new FragmentAdpter(((FragmentActivity) context).getSupportFragmentManager(), fragments);
                viewpager.setAdapter(fragmentAdpter);
            }

            viewpager.addOnPageChangeListener(new PageIndicator(context, dotHorizontal, 2));
        }
    }

    class RecyclerItem extends IRecyclerView.ViewHolder {
        RecyclerView Recycler_item;
        ImageView trend;

        public RecyclerItem(@NonNull View itemView) {
            super(itemView);
            Recycler_item = itemView.findViewById(R.id.testRecycler);
            mNewTrendList = new ArrayList<>();
            NewTrend item1 = new NewTrend("商铺售价", 33700, "元/㎡", "3.0%", 0, 1);
            NewTrend item2 = new NewTrend("商铺租金", 5.1, "元/㎡/天", "持平", 2, 0);
            NewTrend item3 = new NewTrend("写字楼售价", 15000, "元/㎡", "持平", 2, 0);
            NewTrend item4 = new NewTrend("写字楼租金", 1.8, "元/㎡/天", "持平", 2, 0);
            mNewTrendList.add(item1);
            mNewTrendList.add(item2);
            mNewTrendList.add(item3);
            mNewTrendList.add(item4);
            LinearLayoutManager layoutManager = new LinearLayoutManager((MainActivity) context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            Recycler_item.setLayoutManager(layoutManager);
            NewTrendAdapter adapter = new NewTrendAdapter(mNewTrendList);
            Recycler_item.setAdapter(adapter);
            HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
            stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.RIGHT_DECORATION, 20);//右间距
            Recycler_item.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));
            trend = itemView.findViewById(R.id.trend);
            trend.bringToFront();
        }
    }

    class ShapeItem extends IRecyclerView.ViewHolder {
        ShapeItem Shape_item;

        public ShapeItem(@NonNull View itemView) {
            super(itemView);
        }
    }

    class ShapeWriteItem extends IRecyclerView.ViewHolder {
        ShapeWriteItem Title_NowItem;

        public ShapeWriteItem(@NonNull View itemView) {
            super(itemView);
        }
    }

    class TitleNowItem extends IRecyclerView.ViewHolder {
        ShapeWriteItem ShapeWrite_Item;

        public TitleNowItem(@NonNull View itemView) {
            super(itemView);
        }
    }

    class TitleLikeItem extends IRecyclerView.ViewHolder {
        TitleLikeItem Title_Like;

        public TitleLikeItem(@NonNull View itemView) {
            super(itemView);
        }
    }

    class NowShopItem extends IRecyclerView.ViewHolder {
        TextView Title;
        TextView housetype;
        TextView status;
        TextView price;
        TextView location;
        TextView details;
        TextView peronname;
        UrlImageView header_person;
        UrlImageView titlehead;

        public NowShopItem(@NonNull View itemView) throws IOException {
            super(itemView);
            Title = itemView.findViewById(R.id.title);
            housetype = itemView.findViewById(R.id.housetype);
            status = itemView.findViewById(R.id.status);
            price = itemView.findViewById(R.id.price);
            location = itemView.findViewById(R.id.location);
            details = itemView.findViewById(R.id.details);
            peronname = itemView.findViewById(R.id.personname);
            header_person = itemView.findViewById(R.id.header_person);
            titlehead = itemView.findViewById(R.id.header_build);
            NowBean.ResultBean.ShopBuyDongtaiBean data_shop = Utility.speciallist.getResult().getShop_buy_dongtai().get(Utility.current_item_shop);
            Title.setText(data_shop.getLoupan_info().getLoupan_name());
            housetype.setText(data_shop.getLoupan_info().getProperty_type());
            status.setText(data_shop.getLoupan_info().getSale_title());
            String price_line = data_shop.getLoupan_info().getNew_price_value() + data_shop.getLoupan_info().getNew_price_back();
            price.setText(price_line);
            String region_line = data_shop.getLoupan_info().getRegion_title() + " " + data_shop.getLoupan_info().getSub_region_title();
            location.setText(region_line);
            details.setText(data_shop.getDongtai_info().getContent());
            peronname.setText(data_shop.getConsultant_info().getName());
            header_person.setImageURL(data_shop.getConsultant_info().getImage());
            titlehead.setImageURL(data_shop.getLoupan_info().getDefault_image());
            Utility.current_item_shop++;
        }
    }

    class NowWriteItem extends IRecyclerView.ViewHolder {
        TextView Title;
        TextView housetype;
        TextView status;
        TextView price;
        TextView location;
        TextView details;
        TextView peronname;
        UrlImageView header_person;
        UrlImageView titlehead;

        public NowWriteItem(@NonNull View itemView) throws IOException {
            super(itemView);
            Title = itemView.findViewById(R.id.title);
            housetype = itemView.findViewById(R.id.housetype);
            status = itemView.findViewById(R.id.status);
            price = itemView.findViewById(R.id.price);
            location = itemView.findViewById(R.id.location);
            details = itemView.findViewById(R.id.details);
            peronname = itemView.findViewById(R.id.personname);
            header_person = itemView.findViewById(R.id.header_person);
            titlehead = itemView.findViewById(R.id.header_build);
            NowBean.ResultBean.OfficeRentDongtaiBean data_office= Utility.speciallist.getResult().getOffice_rent_dongtai().get(Utility.current_item_write);
            Title.setText(data_office.getLoupan_info().getLoupan_name());
            housetype.setText(data_office.getLoupan_info().getProperty_type());
            status.setText(data_office.getLoupan_info().getSale_title());
            String price_line = data_office.getLoupan_info().getNew_price_value() + data_office.getLoupan_info().getNew_price_back();
            price.setText(price_line);
            String region_line = data_office.getLoupan_info().getRegion_title() + " " + data_office.getLoupan_info().getSub_region_title();
            location.setText(region_line);
            details.setText(data_office.getDongtai_info().getContent());
            peronname.setText(data_office.getConsultant_info().getName());

            header_person.setImageURL(data_office.getConsultant_info().getImage());
            titlehead.setImageURL(data_office.getLoupan_info().getDefault_image());
            Utility.current_item_write++;
        }
    }

    class FavoriteItem extends IRecyclerView.ViewHolder {
        UrlImageView header;
        TextView title;
        TextView region;
        TextView sub_region;
        TextView size;
        TextView price;
        TextView status;
        TextView housetype;
        TextView special1;
        TextView sepcial2;
        RelativeLayout status_layout;
        RelativeLayout special1_layout;
        RelativeLayout special2_layout;
        TextView recommend_price_l;
        TextView recommend_price_2;
        public FavoriteItem(@NonNull View itemView) {
            super(itemView);
            header=itemView.findViewById(R.id.header);
            title=itemView.findViewById(R.id.guesslike_title);
            region=itemView.findViewById(R.id.guesslike_item1);
            sub_region=itemView.findViewById(R.id.guesslike_item2);
            size=itemView.findViewById(R.id.guesslike_size);
            price=itemView.findViewById(R.id.guesslike_price);
            status=itemView.findViewById(R.id.guesslike_status);
            housetype=itemView.findViewById(R.id.guesslike_housetype);
            special1=itemView.findViewById(R.id.guesslike_special1);
            sepcial2=itemView.findViewById(R.id.guesslike_special2);
            status_layout=itemView.findViewById(R.id.status_layout);
            special1_layout=itemView.findViewById(R.id.special1_layout);
            special2_layout=itemView.findViewById(R.id.special2_layout);
            recommend_price_l=itemView.findViewById(R.id.recommend_price_l);
            recommend_price_2=itemView.findViewById(R.id.recommend_price_2);
            SpecialBean.ResultBean.RowsBean data_like=Utility.likelist.getResult().getRows().get(Utility.current_item);

            header.setImageURL(data_like.getDefault_image());

            title.setText(data_like.getLoupan_name());
            region.setText(data_like.getRegion_title());
            sub_region.setText(data_like.getSub_region_title());
            String price_line=data_like.getNew_price_value()+data_like.getNew_price_back();
            price.setText(price_line);
            if(data_like.getArea_rage().equals("")){

            }
            else{
                String area_range="建面"+data_like.getArea_rage();
                size.setText(area_range);
            }


            status.setText(data_like.getSale_title());
            housetype.setText(data_like.getLoupan_property_type());
            if(data_like.getSale_title().equals("待售")){
                status_layout.setBackgroundColor(getColor(context,R.color.readysell));
                price.setText("售价待定");
                price.setTextSize(Float.parseFloat("14"));
                price.setTextColor(getColor(context,R.color.gray));
                if(data_like.getRecommend_price().getFront()==null){
                    recommend_price_l.setText("");
                }
                else{
                    String value=data_like.getRecommend_price().getValue().toString()+data_like.getRecommend_price().getBack();
                    recommend_price_2.setText(value);
                }

            }
            else{
                recommend_price_l.setText("");
            }


            String tag=data_like.getTags();
            String[] tag_item=tag.split(",");
            int length=tag.split(",").length;
            if(length>1) {
                special1.setText(tag_item[0]);
                sepcial2.setText(tag_item[1]);
                if(tag_item[1].equals("今年交房")||tag_item[1].equals("品牌开发商")){
                    sepcial2.setText("");
                    special2_layout.setBackgroundColor(getColor(context,R.color.white));
                }
            }
            else if(length==1){
                special1.setText(tag_item[0]);
                sepcial2.setText("");
                special2_layout.setBackgroundColor(getColor(context,R.color.white));
            }
            else{
                special1.setText("");
                sepcial2.setText("");
                special1_layout.setBackgroundColor(getColor(context,R.color.white));
                special2_layout.setBackgroundColor(getColor(context,R.color.white));
            }

            Utility.current_item++;
        }
    }







}