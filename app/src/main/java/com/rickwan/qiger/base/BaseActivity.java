package com.rickwan.qiger.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.rickwan.qiger.mvp.pesenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * author 万强
 * date 17/2/9 上午9:59
 * desc ${TODO}
 */
public abstract  class BaseActivity extends Activity {

    protected  BasePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        ButterKnife.bind(this);
        mPresenter = getPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.unSubscribe();
        }
    }

    protected abstract int getLayoutID();

    protected abstract BasePresenter getPresenter();
}
