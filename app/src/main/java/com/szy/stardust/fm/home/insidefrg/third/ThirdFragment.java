package com.szy.stardust.fm.home.insidefrg.third;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.szy.stardust.fm.myinfo.HomePagerFragment;

/**
 * author: suzeyu on 16/6/8 00:11
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :  一级页面内部的第三个fragment管理组件
 */
public class ThirdFragment extends HomePagerFragment {
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView view = new TextView(getContext());
        view.setText("thirdFragment");
        return view;
    }
}
