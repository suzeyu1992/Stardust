package com.szy.stardust.adapter.re;

import android.view.View;

/**
 * item 点击回调接口
 *
 * Created by suzeyu on 16/5/11.
 */
public interface ItemClickListener {

    /**
     * 普通点击
     * @param view
     * @param postion
     */
    public void onItemClikc(View view, int postion);

    /**
     * 长按点击
     * @param view
     * @param postion
     */
    public void onItemLongClikc(View view, int postion);


    /**
     * 内部子控件点击
     * @param view
     * @param positon
     */
   public  void onItemSubClick(View view, int positon);
}
