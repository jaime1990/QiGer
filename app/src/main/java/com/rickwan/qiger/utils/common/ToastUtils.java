package com.rickwan.qiger.utils.common;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rickwan.qiger.R;


/**
 * author 万强
 * date 16/6/27 上午11:09
 * desc ${Toast工具类}
 */
public class ToastUtils {

    private static Context mContext;

    private static ToastUtils mInstance;

    private Toast mToast;

    private TextView mToastView;

    private ToastUtils(Context context) {
        mContext = context;
    }


    public static ToastUtils init(Context context) {
        if (mInstance == null) {
            synchronized (ToastUtils.class) {
                if (mInstance == null) {
                    mInstance = new ToastUtils(context);
                }
            }
        }

        return  mInstance;

    }

    public void show(String message) {

        if (TextUtils.isEmpty(message)) {
            return;
        }

        if (mToast==null){
            View layout = LayoutInflater.from(mContext).inflate(R.layout.toast_layout, null);
            mToastView= (TextView) layout.findViewById(R.id.message_tv);
            layout.setAlpha(0.6f);
            mToast= new Toast(mContext);
            mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setView(layout);
        }
        mToastView.setText(message);

        mToast.show();

    }

}
