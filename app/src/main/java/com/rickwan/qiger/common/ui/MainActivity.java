package com.rickwan.qiger.common.ui;

import android.widget.TextView;

import com.rickwan.qiger.R;
import com.rickwan.qiger.base.BaseActivity;
import com.rickwan.qiger.beans.Environment;
import com.rickwan.qiger.base.BasePresenter;
import com.rickwan.qiger.common.ui.mvp.pesenter.EnvieronmentPressenterImp;
import com.rickwan.qiger.common.ui.mvp.view.EnvironmentView;
import com.rickwan.qiger.utils.common.ToastUtils;

import java.util.List;

import butterknife.BindView;
import cn.magicbeans.android.ipmanager.utils.MBShakeUtils;

public class MainActivity extends BaseActivity implements EnvironmentView {


    @BindView(R.id.data_tv)
    TextView dataTv;

    EnvieronmentPressenterImp envieronmentPressenterImp;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter getPresenter() {

        envieronmentPressenterImp = new EnvieronmentPressenterImp(this);
        envieronmentPressenterImp.getWeatherData("1a0c3b7345c16", "成都", "四川");
        return envieronmentPressenterImp;
    }


    @Override
    public void showProgress() {

        ToastUtils.init(this).show("数据加载中...");
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadWeather(Environment environment) {
        ToastUtils.init(this).show("数据加载成功");

        if (environment != null) {
            List<Environment.EnvironmentData> datas = environment.result;
            if (datas == null || datas.isEmpty()) {
                dataTv.setText("数据加载失败");
            } else {
                Environment.EnvironmentData data = datas.get(0);
                dataTv.setText("城市：" + data.city +
                        "\nPM2.5：" + data.pm25 +
                        "\n空气指数：" + data.aqi +
                        "\n空气质量：" + data.quality +
                        "\n更新时间：" + data.updateTime);
            }
        }

    }



    MBShakeUtils shakeUtils;

    @Override
    protected void onStart() {
        super.onStart();
        shakeUtils = new MBShakeUtils(this);
        shakeUtils.init();
    }

    @Override
    protected void onStop() {
        super.onStop();
        shakeUtils.unRegister();
    }




}
