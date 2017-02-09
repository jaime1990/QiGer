package com.rickwan.qiger.mvp.model;

import com.rickwan.qiger.beans.Environment;
import com.rickwan.qiger.net.NetworkRequest;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * author wanqiang
 * date 16/12/19
 * desc${TODO}
 */

public class EnvironmentModelImp implements EnvironmentModel {

    private EnvironmentModelLisenter mLisenter;

    public EnvironmentModelImp(EnvironmentModelLisenter lisenter) {
        this.mLisenter = lisenter;
    }

    @Override
    public Subscription getWeatherData(String key, String city, String province) {

        Observable<Environment> observable = NetworkRequest.getInstance().queryEnvieronment(key, city, province);
        Subscription subscription = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Environment>() {
                    @Override
                    public void call(Environment environment) {

                        if (mLisenter != null) {
                            mLisenter.environmentLoadingSuccess(environment);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                        if (mLisenter != null) {
                            mLisenter.environmentLoadingFailure("");
                        }

                    }
                });


        return subscription;

    }

    public interface EnvironmentModelLisenter {

        void environmentLoadingSuccess(Environment environment);

        void environmentLoadingFailure(String message);
    }
}
