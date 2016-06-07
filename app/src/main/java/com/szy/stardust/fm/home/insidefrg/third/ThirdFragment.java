package com.szy.stardust.fm.home.insidefrg.third;

import com.szy.stardust.fm.home.insidefrg.BaseInsidePagerFragment;
import com.szy.stardust.fm.home.myinfo.HomePagerFragment;

/**
 * author: suzeyu on 16/6/8 00:11
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description :  一级页面内部的第三个fragment管理组件
 */
public class ThirdFragment extends HomePagerFragment {
    @Override
    protected void initLoad() {
        //对要显示的布局进行初始化添加
        mDisplayStyle = 1;
    }
}
