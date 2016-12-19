package com.rickwan.qiger.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * author 万强
 * date 16/6/16 上午10:07
 * desc ${LOG工具类}
 */
public class L {

    private static String TAG = "TAG";

    private static boolean DEBUG = false;

    private static L logUtils;

    private L() {

    }

    public static L getInstance() {

        if (logUtils == null) {

            logUtils = new L();
        }

        return logUtils;
    }

    public void setDeBugModle(boolean DEBUG) {
        this.DEBUG = DEBUG;
    }

    public static void i(String message) {

        if (DEBUG && !TextUtils.isEmpty(message)) {
            Log.i(TAG, message);
        }

    }

    public static void e(String message) {

        if (DEBUG && !TextUtils.isEmpty(message)) {
            Log.e(TAG, message);
        }
    }

    public static void d(String message) {

        if (DEBUG && !TextUtils.isEmpty(message)) {
            Log.d(TAG, message);
        }
    }
}
