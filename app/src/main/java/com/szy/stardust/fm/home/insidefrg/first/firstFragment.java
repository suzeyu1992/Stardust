package com.szy.stardust.fm.home.insidefrg.first;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.szy.stardust.R;
import com.szy.stardust.data.bean.DBFragment;
import com.szy.stardust.fm.myinfo.HomePagerFragment;

import java.util.ArrayList;

/**
 * author: suzeyu on 16/6/8 00:11
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :  一级页面内部的第一个fragment管理组件
 */
public class firstFragment extends HomePagerFragment implements View.OnClickListener {
    protected TabLayout tl_tab;                     //二级tab菜单栏
    protected ViewPager vp_main;                    //内部viewpager
    protected ArrayList<DBFragment> fragments;
    private ImageView iv_tab_func;                  //决定内部要显示的tab数量和viewpager数量的功能键
    public static final String[] displayArrs = {"widgets","graphics","popups","animations","layout"};

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_main_first, container, false);
        tl_tab = (TabLayout) view.findViewById(R.id.tl_tab);
        vp_main = (ViewPager) view.findViewById(R.id.vp_main);
        iv_tab_func = (ImageView) view.findViewById(R.id.iv_tab_func);


        //为点击事件添加监听
        iv_tab_func.setOnClickListener(this);

        //判断第一层fragment的viewpager要填充的集合是否为空为空就创建并追加一个fragment
        if (fragments == null){
            fragments = new ArrayList<>();
            for (int x=0; x<displayArrs.length; x++){
                DBFragment dFragment = new DBFragment();
                dFragment.setmString(displayArrs[x]);
                LatestUpdateFragment frag_1 = new LatestUpdateFragment();
                Bundle bundle_1 = new Bundle();
                if(x == 0){
                    bundle_1.putInt("display_status",0);
                } else{
                    bundle_1.putInt("display_status",1) ;
                    bundle_1.putString("display_type",displayArrs[x]);
                }

                frag_1.setArguments(bundle_1);
                dFragment.setmFragment(frag_1);
                fragments.add(dFragment);
            }



        }
        //为第一个fragment中的viewpager添加fragment
        FratPagerAdapter fratPagerAdapter = new FratPagerAdapter(getChildFragmentManager(), getContext(), fragments);
        vp_main.setAdapter(fratPagerAdapter);

        tl_tab.setupWithViewPager(vp_main);
        tl_tab.setTabMode(TabLayout.MODE_SCROLLABLE);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //选择要显示tab数量的功能键
            case R.id.iv_tab_func:
                Snackbar.make(v,"click ok",Snackbar.LENGTH_LONG).show();

                break;
        }
    }
}
