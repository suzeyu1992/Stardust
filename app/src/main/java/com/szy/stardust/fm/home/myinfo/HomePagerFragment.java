package com.szy.stardust.fm.home.myinfo;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szy.stardust.R;
import com.szy.stardust.data.bean.DBFragment;
import com.szy.stardust.fm.home.insidefrg.BaseInsidePagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author: suzeyu on 16/6/6 18:03
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : 主页一级页面的外部gragment
 */
public abstract class HomePagerFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private TabLayout tl_tab;
    private ViewPager vp_main;
    private ArrayList<DBFragment> fragments;
    protected int mDisplayStyle;        //此fragment具体要显示的内容


    //初始化内部fragment要展示的类型
    protected abstract void initLoad();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mDisplayStyle == 1){
            View view = inflater.inflate(R.layout.page_fragment_myinfo, container, false);
            tl_tab = (TabLayout) view.findViewById(R.id.tl_tab);
            vp_main = (ViewPager) view.findViewById(R.id.vp_main);

            //判断第一层fragment的viewpager要填充的集合是否为空为空就创建并追加一个fragment
            if (fragments == null){
                fragments = new ArrayList<>();
                DBFragment dFragment = new DBFragment();
                dFragment.setmString("首页");
                dFragment.setmFragment(new BaseInsidePagerFragment());
                fragments.add(dFragment);
                DBFragment dFragment1 = new DBFragment();
                dFragment1.setmString("Android");
                dFragment1.setmFragment(new BaseInsidePagerFragment());
                fragments.add(dFragment1);
            }
            //为第一个fragment中的viewpager添加gragment
            FratPagerAdapter fratPagerAdapter = new FratPagerAdapter(getChildFragmentManager(), getContext(), fragments);
            vp_main.setAdapter(fratPagerAdapter);

            tl_tab.setupWithViewPager(vp_main);
            tl_tab.setTabMode(TabLayout.MODE_SCROLLABLE);
            return view;
        }else {
            View view = inflater.inflate(R.layout.page_main_myinfo, container, false);

            return view;

        }





    }

    public class FratPagerAdapter extends FragmentPagerAdapter {

        private final List<DBFragment> listData;
        private Context context;

        public FratPagerAdapter(FragmentManager fm, Context context, List<DBFragment> list) {
            super(fm);
            this.context = context;
            this.listData = list;
        }


        @Override
        public Fragment getItem(int position) {
            return listData.get(position).getmFragment();
        }

        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return listData.get(position).getmString();
        }


    }
}
