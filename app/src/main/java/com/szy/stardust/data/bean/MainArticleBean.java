package com.szy.stardust.data.bean;

/**
 * author: suzeyu on 16/6/20 11:27
 * github: https://github.com/suzeyu1992
 * -------------------------------------
 * class description : 文章存储信息javabean
 */
public class MainArticleBean {

    private String mTitle;              //文章的内容名称
    private String mTile;               //文章创建时间
    private int mImgRes;                //图片资源文件地址
    private String mClassName;          //需要跳转的类名

    public String getmClassName() {
        return mClassName;
    }

    public void setmClassName(String mClassName) {
        this.mClassName = mClassName;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmTile() {
        return mTile;
    }

    public void setmTile(String mTile) {
        this.mTile = mTile;
    }

    public int getmImgRes() {
        return mImgRes;
    }

    public void setmImgRes(int mImgRes) {
        this.mImgRes = mImgRes;
    }
}
