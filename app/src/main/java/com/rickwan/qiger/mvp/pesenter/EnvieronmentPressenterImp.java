package com.rickwan.qiger.mvp.pesenter;

import com.rickwan.qiger.beans.Environment;
import com.rickwan.qiger.mvp.model.EnvironmentModelImp;
import com.rickwan.qiger.mvp.view.EnvironmentView;

/**
 * author wanqiang
 * date 16/12/19
 * desc${TODO}
 */

public class EnvieronmentPressenterImp extends  EnvieronmentPressenter implements EnvironmentModelImp.EnvironmentModelLisenter {

    private EnvironmentModelImp mEnvironmentModelImp;

    private EnvironmentView mEnvironmentView;

    public EnvieronmentPressenterImp(EnvironmentView environmentView) {
        this.mEnvironmentModelImp = new EnvironmentModelImp(this);
        this.mEnvironmentView = environmentView;

    }


    @Override
    public void getWeatherData(String key, String city, String province) {
        if (this.mEnvironmentView != null) {
            this.mEnvironmentView.showProgress();
        }

        addSubscription(this.mEnvironmentModelImp.getWeatherData(key, city, province));
    }

    @Override
    public void environmentLoadingSuccess(Environment environment) {

        if (this.mEnvironmentView != null) {
            this.mEnvironmentView.loadWeather(environment);
        }
    }

    @Override
    public void environmentLoadingFailure(String message) {
        if (this.mEnvironmentView != null) {
            this.mEnvironmentView.hideProgress();
        }

    }

}
