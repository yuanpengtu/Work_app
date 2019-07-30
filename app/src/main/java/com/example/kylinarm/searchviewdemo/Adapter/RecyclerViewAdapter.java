package com.example.kylinarm.searchviewdemo.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.transition.Transition;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.kylinarm.searchviewdemo.Bean.NewTrendBean;
import com.example.kylinarm.searchviewdemo.Bean.NowBean;
import com.example.kylinarm.searchviewdemo.Bean.GuessLikeBean;
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
    Context mContext;//当前上下文对象
    List<String> mDatas;//RecyclerView填充Item数据的List对象
    private List<NewTrendBean> mNewTrendList;//新房走势的item数据
    private static final int TYPE_ViewPager = 0;//viewpager滑动
    private static final int TYPE_Recycler = 1;//新盘走势
    private static final int TYPE_Special = 2;//特色商铺
    private static final int TYPE_Now = 3;//现场直击_商铺
    private static final int TYPE_Now_2 = 8;//现场直击_写字楼
    private static final int TYPE_Favorite = 4;//猜你喜欢
    private static final int TYPE_Writer = 5;//特色写字楼
    private static final int TYPE_Title_Now = 6;//现场直击title
    private static final int TYPE_Title_Like = 7;//猜你喜欢title
    private static final int PAGE_COUNT=2;//VIEWPAGER的页数
    private static final int DECORATION_NUM=20;//横向RecyclerView的item间的间距
    private final String PRICE_TEXTSIZE="14";//价格字体大小
    public RecyclerViewAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.mDatas = datas;
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
        return Integer.parseInt(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
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
            if (mContext instanceof FragmentActivity) {
                FragmentAdpter fragmentAdpter = new FragmentAdpter(((FragmentActivity) mContext).getSupportFragmentManager(), fragments);
                viewpager.setAdapter(fragmentAdpter);
            }
            viewpager.addOnPageChangeListener(new PageIndicator(mContext, dotHorizontal, PAGE_COUNT));
        }
    }

    class RecyclerItem extends IRecyclerView.ViewHolder {
        RecyclerView mRecycler_item;
        ImageView mTrend;

        public RecyclerItem(@NonNull View itemView) {
            super(itemView);
            mRecycler_item = itemView.findViewById(R.id.testRecycler);
            mNewTrendList = new ArrayList<>();
            NewTrendBean item1 = new NewTrendBean("商铺售价", 33700, "元/㎡", "3.0%", 0, 1);
            NewTrendBean item2 = new NewTrendBean("商铺租金", 5.1, "元/㎡/天", "持平", 2, 0);
            NewTrendBean item3 = new NewTrendBean("写字楼售价", 15000, "元/㎡", "持平", 2, 0);
            NewTrendBean item4 = new NewTrendBean("写字楼租金", 1.8, "元/㎡/天", "持平", 2, 0);
            mNewTrendList.add(item1);
            mNewTrendList.add(item2);
            mNewTrendList.add(item3);
            mNewTrendList.add(item4);
            LinearLayoutManager layoutManager = new LinearLayoutManager((MainActivity) mContext);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            NewTrendAdapter adapter = new NewTrendAdapter(mNewTrendList);
            mRecycler_item.setLayoutManager(layoutManager);
            mRecycler_item.setAdapter(adapter);
            HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
            stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.RIGHT_DECORATION, DECORATION_NUM);//右间距
            mRecycler_item.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));
            mTrend = itemView.findViewById(R.id.trend);
            mTrend.bringToFront();
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
        TextView mTitle;
        TextView mHousetype;
        TextView mStatus;
        TextView mPrice;
        TextView mLocation;
        TextView mDetails;
        TextView mPeronname;
        UrlImageView mHeader_person;
        UrlImageView mTitlehead;
        ImageView mThumbnail;
        public NowShopItem(@NonNull View itemView) throws IOException {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title);
            mHousetype = itemView.findViewById(R.id.housetype);
            mStatus = itemView.findViewById(R.id.status);
            mPrice = itemView.findViewById(R.id.price);
            mLocation = itemView.findViewById(R.id.location);
            mDetails = itemView.findViewById(R.id.details);
            mPeronname = itemView.findViewById(R.id.personname);
            mHeader_person = itemView.findViewById(R.id.header_person);
            mTitlehead = itemView.findViewById(R.id.header_build);
            mThumbnail=itemView.findViewById(R.id.Thumbnail);
            NowBean.ResultBean.ShopBuyDongtaiBean data_shop = Utility.speciallist.getResult().getShop_buy_dongtai().get(Utility.current_item_shop);
            String price_line = data_shop.getLoupan_info().getNew_price_value() + data_shop.getLoupan_info().getNew_price_back();
            String region_line = data_shop.getLoupan_info().getRegion_title() + " " + data_shop.getLoupan_info().getSub_region_title();
            mTitle.setText(data_shop.getLoupan_info().getLoupan_name());
            mHousetype.setText(data_shop.getLoupan_info().getProperty_type());
            mStatus.setText(data_shop.getLoupan_info().getSale_title());
            mPrice.setText(price_line);
            mLocation.setText(region_line);
            mDetails.setText(data_shop.getDongtai_info().getContent());
            mPeronname.setText(data_shop.getConsultant_info().getName());
            Glide.with(mContext).load(data_shop.getConsultant_info().getImage()).into(mHeader_person);
            Glide.with(mContext).load(data_shop.getLoupan_info().getDefault_image()).into(mTitlehead);
            Glide.with(mContext)
                    .load(data_shop.getLoupan_info().getDefault_image())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>(324,175) {		//设置宽高
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            Drawable drawable = new BitmapDrawable(resource);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                mThumbnail.setBackground(drawable);	//设置背景
                            }
                        }
                    });
            Utility.current_item_shop++;
        }
    }

    class NowWriteItem extends IRecyclerView.ViewHolder {
        TextView mTitle;
        TextView mHousetype;
        TextView mStatus;
        TextView mPrice;
        TextView mLocation;
        TextView mDetails;
        TextView mPeronname;
        UrlImageView mHeader_person;
        UrlImageView mTitlehead;
        ImageView mThumbnail;
        public NowWriteItem(@NonNull View itemView) throws IOException {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title);
            mHousetype = itemView.findViewById(R.id.housetype);
            mStatus = itemView.findViewById(R.id.status);
            mPrice = itemView.findViewById(R.id.price);
            mLocation = itemView.findViewById(R.id.location);
            mDetails = itemView.findViewById(R.id.details);
            mPeronname = itemView.findViewById(R.id.personname);
            mHeader_person = itemView.findViewById(R.id.header_person);
            mTitlehead = itemView.findViewById(R.id.header_build);
            mThumbnail=itemView.findViewById(R.id.Thumbnail);
            NowBean.ResultBean.OfficeRentDongtaiBean data_office= Utility.speciallist.getResult().getOffice_rent_dongtai().get(Utility.current_item_write);
            String price_line = data_office.getLoupan_info().getNew_price_value() + data_office.getLoupan_info().getNew_price_back();
            String region_line = data_office.getLoupan_info().getRegion_title() + " " + data_office.getLoupan_info().getSub_region_title();
            mTitle.setText(data_office.getLoupan_info().getLoupan_name());
            mHousetype.setText(data_office.getLoupan_info().getProperty_type());
            mStatus.setText(data_office.getLoupan_info().getSale_title());
            mPrice.setText(price_line);
            mLocation.setText(region_line);
            mDetails.setText(data_office.getDongtai_info().getContent());
            mPeronname.setText(data_office.getConsultant_info().getName());

            Glide.with(mContext).load(data_office.getConsultant_info().getImage()).into(mHeader_person);
            Glide.with(mContext).load(data_office.getLoupan_info().getDefault_image()).into(mTitlehead);
            Glide.with(mContext)
                    .load(data_office.getLoupan_info().getDefault_image())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>(324,175) {		//设置宽高
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            Drawable drawable = new BitmapDrawable(resource);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                mThumbnail.setBackground(drawable);	//设置背景
                            }
                        }
                    });
            Utility.current_item_write++;
        }
    }

    class FavoriteItem extends IRecyclerView.ViewHolder {
        UrlImageView mHeader;
        TextView mTitle;
        TextView mRegion;
        TextView mSub_region;
        TextView mSize;
        TextView mPrice;
        TextView mStatus;
        TextView mHousetype;
        TextView mSpecial1;
        TextView mSepcial2;
        RelativeLayout mStatus_layout;
        RelativeLayout mSpecial1_layout;
        RelativeLayout mSpecial2_layout;
        TextView mRecommend_price_l;
        TextView mRecommend_price_2;
        public FavoriteItem(@NonNull View itemView) {
            super(itemView);
            mHeader=itemView.findViewById(R.id.header);
            mTitle=itemView.findViewById(R.id.guesslike_title);
            mRegion=itemView.findViewById(R.id.guesslike_item1);
            mSub_region=itemView.findViewById(R.id.guesslike_item2);
            mSize=itemView.findViewById(R.id.guesslike_size);
            mPrice=itemView.findViewById(R.id.guesslike_price);
            mStatus=itemView.findViewById(R.id.guesslike_status);
            mHousetype=itemView.findViewById(R.id.guesslike_housetype);
            mSpecial1=itemView.findViewById(R.id.guesslike_special1);
            mSepcial2=itemView.findViewById(R.id.guesslike_special2);
            mStatus_layout=itemView.findViewById(R.id.status_layout);
            mSpecial1_layout=itemView.findViewById(R.id.special1_layout);
            mSpecial2_layout=itemView.findViewById(R.id.special2_layout);
            mRecommend_price_l=itemView.findViewById(R.id.recommend_price_l);
            mRecommend_price_2=itemView.findViewById(R.id.recommend_price_2);
            GuessLikeBean.ResultBean.RowsBean data_like=Utility.likelist.getResult().getRows().get(Utility.current_item);


            Glide.with(mContext).load(data_like.getDefault_image()).into(mHeader);
            mTitle.setText(data_like.getLoupan_name());
            mRegion.setText(data_like.getRegion_title());
            mSub_region.setText(data_like.getSub_region_title());
            String price_line=data_like.getNew_price_value()+data_like.getNew_price_back();
            mPrice.setText(price_line);
            if(data_like.getArea_rage().equals("")){
                ;
            }
            else{
                String area_range="建面"+data_like.getArea_rage();
                mSize.setText(area_range);
            }
            mStatus.setText(data_like.getSale_title());
            mHousetype.setText(data_like.getLoupan_property_type());
            if(data_like.getSale_title().equals("待售")){
                mStatus_layout.setBackgroundColor(getColor(mContext,R.color.readysell));
                mPrice.setText("售价待定");
                mPrice.setTextSize(Float.parseFloat(PRICE_TEXTSIZE));
                mPrice.setTextColor(getColor(mContext,R.color.gray));
                if(data_like.getRecommend_price().getFront()==null){
                    mRecommend_price_l.setText("");
                }
                else{
                    String value=data_like.getRecommend_price().getValue().toString()+data_like.getRecommend_price().getBack();
                    mRecommend_price_2.setText(value);
                }
            }
            else{
                mRecommend_price_l.setText("");
            }
            String tag=data_like.getTags();
            String[] tag_item=tag.split(",");
            int length=tag.split(",").length;
            if(length>1) {
                mSpecial1.setText(tag_item[0]);
                mSepcial2.setText(tag_item[1]);
                if(tag_item[1].equals("今年交房")||tag_item[1].equals("品牌开发商")){
                    mSepcial2.setText("");
                    mSpecial2_layout.setBackgroundColor(getColor(mContext,R.color.white));
                }
            }
            else if(length==1){
                mSpecial1.setText(tag_item[0]);
                mSepcial2.setText("");
                mSpecial2_layout.setBackgroundColor(getColor(mContext,R.color.white));
            }
            else{
                mSpecial1.setText("");
                mSepcial2.setText("");
                mSpecial1_layout.setBackgroundColor(getColor(mContext,R.color.white));
                mSpecial2_layout.setBackgroundColor(getColor(mContext,R.color.white));
            }
            Utility.current_item++;
        }
    }
}