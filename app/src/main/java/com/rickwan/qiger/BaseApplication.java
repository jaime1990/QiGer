package com.rickwan.qiger;

import android.app.Application;

import com.rickwan.qiger.utils.common.LogUtils;

/**
 * author 万强
 * date 16/12/20 下午12:01
 * desc ${TODO}
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.getInstance().setDeBugModle(true);
    }
}
