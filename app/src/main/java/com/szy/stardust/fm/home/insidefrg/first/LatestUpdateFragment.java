package com.szy.stardust.fm.home.insidefrg.first;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.szy.stardust.R;
import com.szy.stardust.adapter.re.ItemClickListener;
import com.szy.stardust.data.bean.MainArticleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author: suzeyu on 16/6/17 15:48
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : 一级页面中第一模块中的内部小碎片fragment
 */
public class LatestUpdateFragment extends Fragment {

    private static final String TAG = LatestUpdateFragment.class.getSimpleName();
    private int mDisplayStatus;     //内部fragment要显示的样式模块
    private final int DIS_COMPLEXITY = 0;               //显示最新更新类型的列表
    private final int DIS_PLAIN = 1;               //显示功能类型类型的列表
    private ArrayList<MainArticleBean> mDisplayDatas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDisplayStatus = getArguments().getInt("display_status");
        String display_type = getArguments().getString("display_type");
        mDisplayDatas = getTypeDatas(display_type);
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.page_fragment_inside_home, container, false);
        RecyclerView rv_main = (RecyclerView) view.findViewById(R.id.rv_main);

        //创建一个布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //关联布局管理器
        rv_main.setLayoutManager(linearLayoutManager);






        MyAdapter myAdapter = new MyAdapter(getContext(), mDisplayDatas);

        //添加自定义的点击事件
        myAdapter.setOnClickListener(new ItemClickListener() {
            @Override
            public void onItemClikc(View view, int position) {
                MainArticleBean mainArticleBean = mDisplayDatas.get(position);
                String className = mainArticleBean.getmClassName();

//                    Class<?> aClass = Class.forName(className);
                    //添加点击item跳转到指定class类中
                    Intent intent = new Intent();
                    intent.setClassName("com.szy.stardust",className);

                    getContext().startActivity(intent);



            }

            @Override
            public void onItemLongClikc(View view, int postion) {
                Toast.makeText(getContext(), "长按了条目" + postion, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemSubClick(View view, int positon) {

            }
        });

        //添加分割线
        //判断是否是最新更新的布局  还是简单布局 来决定是否添加分割线
        if (DIS_COMPLEXITY == mDisplayStatus) {
            rv_main.addItemDecoration(new MyItemDecoration(getContext(), R.drawable.shape_bg_line_gray, LinearLayoutManager.VERTICAL));
        }


        //关联数据适配器
        rv_main.setAdapter(myAdapter);

        //给RecyclerView添加动画
        rv_main.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    /**
     * RecyclerView的数据适配器
     */
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MViewHolder> {

        private Context context;
        private List<MainArticleBean> mList;
        private ItemClickListener mItemClickListener;       //自定义点击监听


        public MyAdapter(Context context, List<MainArticleBean> mList) {
            this.context = context;
            this.mList = mList;
        }

        public void setOnClickListener(ItemClickListener mItemClickListener) {
            this.mItemClickListener = mItemClickListener;
        }


        @Override
        public MyAdapter.MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate;
            if (mDisplayStatus == DIS_COMPLEXITY) {
                inflate = getLayoutInflater(null).inflate(R.layout.item_recycle_complexity, parent, false);
            } else {
                inflate = getLayoutInflater(null).inflate(R.layout.item_recycle_plain, parent, false);
            }

            MViewHolder mViewHolder = new MViewHolder(inflate);
            Log.d(TAG, "onCreateViewHolder:");


            return mViewHolder;
        }

        @Override
        public void onBindViewHolder(MyAdapter.MViewHolder holder, int position) {
            Log.d(TAG, "onBindViewHolder: --position:" + position);
            //根据不同的布局样式标记,来绑定不同的数据
            if (mDisplayStatus == DIS_COMPLEXITY) {

            } else if (mDisplayStatus == DIS_PLAIN){
                MainArticleBean mainArticleBean = mList.get(position);
                holder.tv_title.setText(mainArticleBean.getmTitle());
            }

        }


        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class MViewHolder extends RecyclerView.ViewHolder {
            public ImageView mImageView;           //连接图片展示
            public TextView tv_title;              //标题头
            public TextView tv_time;               //文章时间


            public MViewHolder(View itemView) {
                super(itemView);

                //根据不同的布局样式标记,来查找不同的控件
                if (mDisplayStatus == DIS_COMPLEXITY) {

                } else if (mDisplayStatus == DIS_PLAIN){
                    tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                    tv_time = (TextView) itemView.findViewById(R.id.tv_time);
                    mImageView = (ImageView) itemView.findViewById(R.id.iv_simple);
                }
                //给item布局添加点击事件
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != mItemClickListener) {
                            mItemClickListener.onItemClikc(v, getLayoutPosition());

                        }
                    }
                });

                //给item布局添加长按点击事件
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {

                        if (null != mItemClickListener) {
                            mItemClickListener.onItemLongClikc(v, getLayoutPosition());
                        }
                        return true;
                    }
                });
            }
        }

    }


    /**
     * ItemDecoration 复写实现
     */
    class MyItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;
        private int mOritation;

        public MyItemDecoration(Context context, int resId, int oritation) {

            mDivider = context.getResources().getDrawable(resId);

            this.mOritation = oritation;

            Log.i("ItemDecorationDivider", "mOritation=" + mOritation);

        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            if (mOritation == LinearLayoutManager.VERTICAL) {
                final int left = parent.getPaddingLeft();
                final int right = parent.getWidth() - parent.getPaddingRight();

                final int childCount = parent.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    final View child = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                            .getLayoutParams();

                    final int top = child.getBottom() + params.bottomMargin;
                    final int bottom = top + mDivider.getIntrinsicHeight();
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }
            } else if (mOritation == LinearLayoutManager.HORIZONTAL) {

                final int top = parent.getPaddingTop();
                // final int bottom = parent.getHeight() -
                // parent.getPaddingBottom();

                final int childCount = parent.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    final View child = parent.getChildAt(i);
                    final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                            .getLayoutParams();
                    final int left = child.getRight() + params.rightMargin;
                    final int right = left + mDivider.getIntrinsicHeight();

                    final int bottom = child.getBottom();
                    mDivider.setBounds(left, top, right, bottom);
                    mDivider.draw(c);
                }
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (mOritation == LinearLayoutManager.VERTICAL) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else if (mOritation == LinearLayoutManager.HORIZONTAL) {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }


        }

    }


    //通过fragment创建时传入的
    private ArrayList<MainArticleBean> getTypeDatas(String display_type) {


        //如果传入类型有错误, 直接返回一个空集合
        if (display_type == null || TextUtils.isEmpty(display_type)){
            return new ArrayList<MainArticleBean>();
        }

        ArrayList<MainArticleBean> datas;

        //graphics数据集合初始化
        if (display_type.equals(firstFragment.displayArrs[1])){

            datas = new ArrayList<>();

            //临时创建数据对象bean放入集合中
            MainArticleBean mainArticleBean1 = new MainArticleBean();
            mainArticleBean1.setmTitle("绘图篇(1):Canvas和Paint的基本使用展示");
            mainArticleBean1.setmClassName("com.szy.stardust.fm.home.insidefrg.first.graphics.drawing.SuGraphicsBaseCanvasPaintActivity");
            datas.add(mainArticleBean1);

            MainArticleBean mainArticleBean2 = new MainArticleBean();
            mainArticleBean2.setmTitle("绘图篇(2):");
            mainArticleBean2.setmClassName("com.szy.stardust.fm.home.insidefrg.first.graphics.drawing.SuGraphicsDrawing2drawTextActivity");
            datas.add(mainArticleBean2);

            MainArticleBean mainArticleBean3 = new MainArticleBean();
            mainArticleBean3.setmTitle("绘图篇(3):drawText练习");
            mainArticleBean3.setmClassName("com.szy.stardust.fm.home.insidefrg.first.graphics.drawing.SuGraphicsDrawing2drawTextActivity");
            datas.add(mainArticleBean3);

            MainArticleBean mainArticleBean4 = new MainArticleBean();
            mainArticleBean4.setmTitle("绘图篇(4):贝塞尔实现手势轨迹");
            mainArticleBean4.setmClassName("com.szy.stardust.fm.home.insidefrg.first.graphics.drawing.SuGraphicsGestureTracksActivity");
            datas.add(mainArticleBean4);

            MainArticleBean mainArticleBean5 = new MainArticleBean();
            mainArticleBean5.setmTitle("绘图篇(5):paint之ColorMatrix过滤");
            mainArticleBean5.setmClassName("com.szy.stardust.fm.home.insidefrg.first.graphics.drawing.SuGraphicsColorMatrixActivity");
            datas.add(mainArticleBean5);

            MainArticleBean mainArticleBean6 = new MainArticleBean();
            mainArticleBean6.setmTitle("绘图篇(6):paint之ColorFilter与PorterDuffColorFilter");
            mainArticleBean6.setmClassName("com.szy.stardust.fm.home.insidefrg.first.graphics.drawing.SuGraphicsColorFilterActivity");
            datas.add(mainArticleBean6);

            MainArticleBean mainArticleBean7 = new MainArticleBean();
            mainArticleBean7.setmTitle("绘图篇(7):paint之setXfermode");
            mainArticleBean7.setmClassName("com.szy.stardust.fm.home.insidefrg.first.graphics.drawing.SuGraphicsSetXfermodeActivity");
            datas.add(mainArticleBean7);

            MainArticleBean mainArticleBean8 = new MainArticleBean();
            mainArticleBean8.setmTitle("绘图篇(8):paint之setXfermode 使用场景1");
            mainArticleBean8.setmClassName("com.szy.stardust.fm.home.insidefrg.first.graphics.drawing.SuGraphicsSetXfermodeHighActivity");
            datas.add(mainArticleBean8);


        }else{
            datas = new ArrayList<>();

        }

        return datas;
    }
}
