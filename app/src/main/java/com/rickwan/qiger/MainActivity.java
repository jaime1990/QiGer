package com.rickwan.qiger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.rickwan.qiger.beans.Environment;
import com.rickwan.qiger.mvp.pesenter.EnvieronmentPressenterImp;
import com.rickwan.qiger.mvp.view.EnvironmentView;
import com.rickwan.qiger.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements EnvironmentView {


    @BindView(R.id.data_tv)
    TextView dataTv;

    private EnvieronmentPressenterImp envieronmentPressenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        envieronmentPressenterImp = new EnvieronmentPressenterImp(this);
        envieronmentPressenterImp.getWeatherData("1a0c3b7345c16", "成都", "四川");
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

        if (environment!=null){
            List<Environment.EnvironmentData > datas = environment.result;
            if (datas==null||datas.isEmpty()){
                dataTv.setText("数据加载失败");
            }else {
                Environment.EnvironmentData data = datas.get(0);
                dataTv.setText("城市："+data.city+
                        "\nPM2.5："+data.pm25+
                        "\n空气指数："+data.aqi+
                        "\n空气质量："+data.quality+
                        "\n更新时间："+data.updateTime);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        envieronmentPressenterImp.onUnsubscribe();
    }
}
