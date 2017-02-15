package com.rickwan.qiger.common.ui.mvp.view;

import com.rickwan.qiger.beans.Environment;

/**
 * author wanqiang
 * date 16/12/19
 * desc${TODO}
 */

public interface EnvironmentView  {


    void showProgress();
    void hideProgress();
    void loadWeather(Environment environment);

}
