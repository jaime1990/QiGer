package com.rickwan.qiger.mvp.pesenter;

/**
 * author wanqiang
 * date 16/12/19
 * desc${TODO}
 */

public abstract class EnvieronmentPressenter extends BasePresenter {

    public abstract void getWeatherData(String key, String city, String province);
}
