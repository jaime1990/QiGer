package com.rickwan.qiger.mvp.model;

import rx.Subscription;

/**
 * author wanqiang
 * date 16/12/19
 * desc${TODO}
 */

public interface EnvironmentModel {

    Subscription getWeatherData(String key, String city, String province);
}
