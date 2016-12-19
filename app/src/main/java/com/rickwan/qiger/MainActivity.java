package com.rickwan.qiger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rickwan.qiger.beans.Environment;
import com.rickwan.qiger.mvp.pesenter.EnvieronmentPressenterImp;
import com.rickwan.qiger.mvp.view.EnvironmentView;

public class MainActivity extends AppCompatActivity implements EnvironmentView {


    private EnvieronmentPressenterImp envieronmentPressenterImp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }

    private void test() {

        envieronmentPressenterImp = new EnvieronmentPressenterImp(this);
        envieronmentPressenterImp.getWeatherData("1a0c3b7345c16", "成都", "四川");

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loadWeather(Environment environment) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        envieronmentPressenterImp.onUnsubscribe();
    }
}
