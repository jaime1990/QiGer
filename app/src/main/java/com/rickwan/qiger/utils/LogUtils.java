package com.rickwan.qiger.utils;

import android.text.TextUtils;

import com.orhanobut.logger.Logger;

/**
 * author 万强
 * date 16/6/16 上午10:07
 * desc ${LOG工具类}
 */
public class LogUtils {

    private static String TAG = "TAG";

    private static boolean DEBUG = false;

    private static LogUtils logUtils;

    private LogUtils() {
        Logger.init(TAG).hideThreadInfo();
    }

    public static LogUtils getInstance() {

        if (logUtils == null) {
            logUtils = new LogUtils();
        }

        return logUtils;
    }

    public void setDeBugModle(boolean DEBUG) {
        this.DEBUG = DEBUG;
    }

    public static void i(String message) {

        if (DEBUG && !TextUtils.isEmpty(message)) {
            Logger.i(message);
        }

    }

    public static void i(String message, Object... args) {

        if (DEBUG && !TextUtils.isEmpty(message)) {
            Logger.i(message, args);
        }
    }

    public static void e(String message) {

        if (DEBUG && !TextUtils.isEmpty(message)) {
            Logger.e(message);
        }
    }

    public static void e(String message, Object... args) {

        if (DEBUG && !TextUtils.isEmpty(message)) {
            Logger.e(message, args);
        }
    }

    public static void d(String message) {

        if (DEBUG && !TextUtils.isEmpty(message)) {
            Logger.d(message);
        }
    }

    public static void d(String message, Object... args) {

        if (DEBUG && !TextUtils.isEmpty(message)) {
            Logger.d(message, args);
        }
    }
}
