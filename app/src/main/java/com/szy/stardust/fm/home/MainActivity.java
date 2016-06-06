package com.szy.stardust.fm.home;


import com.szy.stardust.R;
import com.szy.stardust.base.BaseActivity;
import com.szy.stardust.data.bean.MyInfoBean;
import com.szy.stardust.util.CheckUtil;

public class MainActivity extends BaseActivity implements MainContract.View {


    private MainContract.Persenter mPresenter;                  //控制层的mPresenter

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initLoad() {
        mPresenter.start();
    }

    @Override
    public void clickPager(int pagerIndex) {

    }

    @Override
    public void setMyInfo(boolean isLogin, MyInfoBean myinfo) {

    }

    @Override
    public void setPresenter(MainContract.Persenter presenter) {
        mPresenter = CheckUtil.checkNotNull(presenter);
    }


}
