package com.szy.stardust.fm.myinfo;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szy.stardust.data.bean.DBFragment;

import java.util.List;

/**
 * author: suzeyu on 16/6/6 18:03
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : 主页一级页面的外部fragment
 */
public abstract class HomePagerFragment extends Fragment {


    //初始化内部fragment要展示的布局
    protected abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater, container, savedInstanceState);
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
