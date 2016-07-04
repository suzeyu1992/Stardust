package com.szy.stardust.fm.home.insidefrg.second;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.szy.stardust.R;
import com.szy.stardust.adapter.re.ItemClickListener;
import com.szy.stardust.fm.myinfo.HomePagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author: suzeyu on 16/6/8 00:11
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :  一级页面内部的第二个fragment管理组件
 */
public class SecondFragment extends HomePagerFragment {
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.page_main_second, null);



        //找到需要的控件
        RecyclerView rv_main = (RecyclerView) rootView.findViewById(R.id.rv_main);

        //创建一个布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        //关联布局管理器
        rv_main.setLayoutManager(linearLayoutManager);

        //临时模拟数据集合
        ArrayList<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
//        strings.add("3");
//        strings.add("4");

        MyAdapter myAdapter = new MyAdapter(getContext(), strings);

        //添加自定义的点击事件
        myAdapter.setOnClickListener(new ItemClickListener() {
            @Override
            public void onItemClikc(View view, int postion) {
                Snackbar.make(view, "点击了条目:" + postion, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClikc(View view, int postion) {
            }

            @Override
            public void onItemSubClick(View view, int positon) {

            }
        });



        //关联数据适配器
        rv_main.setAdapter(myAdapter);

        return rootView;
    }

    /**
     * RecyclerView的数据适配器
     */
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MViewHolder> {

        private Context context;
        private List<String> mList;
        private ItemClickListener mItemClickListener;       //自定义点击监听


        public MyAdapter(Context context, List<String> mList) {
            this.context = context;
            this.mList = mList;
        }

        public void setOnClickListener(ItemClickListener mItemClickListener) {
            this.mItemClickListener = mItemClickListener;
        }


        @Override
        public MyAdapter.MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View inflate;

            inflate = getLayoutInflater(null).inflate(R.layout.item_recycle_blog, parent, false);


            MViewHolder mViewHolder = new MViewHolder(inflate);


            return mViewHolder;
        }

        @Override
        public void onBindViewHolder(MyAdapter.MViewHolder holder, int position) {
            holder.viewById.setText(mList.get(position));

        }


        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class MViewHolder extends RecyclerView.ViewHolder {


            public final TextView viewById;

            public MViewHolder(View itemView) {
                super(itemView);
                viewById =
                        (TextView) itemView.findViewById(R.id.tv_title);

                //给item布局添加点击事件
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (null != mItemClickListener) {
                            mItemClickListener.onItemClikc(v, getLayoutPosition());
                        }
                    }
                });


            }
        }

    }

}
