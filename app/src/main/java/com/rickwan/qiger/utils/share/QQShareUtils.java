package com.rickwan.qiger.utils.share;

import android.app.Activity;
import android.os.Bundle;

import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;

import java.util.ArrayList;

/**
 * author 万强
 * date 16/12/21 下午3:30
 * desc ${QQ分享}
 * 要获取QQ分享回调，必须在activity的onActivityResult方法中执行Tencent.onActivityResultData(requestCode, resultCode, data, null);
 */
public class QQShareUtils {

    private Activity mActivity;

    private Tencent mTencent;

    public QQShareUtils(Activity activity) {
        this.mActivity = activity;
        this.mTencent = Tencent.createInstance("1105796790", mActivity);
    }

    public void shareToQQ(IUiListener iUiListener) {

        Bundle bundle = new Bundle();
        //这条分享消息被好友点击后的跳转URL。
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://connect.qq.com/");
        //分享的标题。注：PARAM_TITLE、PARAM_IMAGE_URL、PARAM_	SUMMARY不能全为空，最少必须有一个是有值的。
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, "我在测试");
        //分享的图片URL
        bundle.putString(QQShare.SHARE_TO_QQ_IMAGE_URL,
                "http://img3.cache.netease.com/photo/0005/2013-03-07/8PBKS8G400BV0005.jpg");
        //分享的消息摘要，最长50个字
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, "测试");
        mTencent.shareToQQ(mActivity, bundle, iUiListener);
    }

    private void shareToQQZone(IUiListener iUiListener) {
        Tencent mTencent = Tencent.createInstance("1105796790", mActivity);
        Bundle bundle = new Bundle();
        //这条分享消息被好友点击后的跳转URL。
        bundle.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://connect.qq.com/");
        //分享的标题。注：PARAM_TITLE、PARAM_IMAGE_URL、PARAM_	SUMMARY不能全为空，最少必须有一个是有值的。
        bundle.putString(QQShare.SHARE_TO_QQ_TITLE, "我在测试");
        //分享的图片URL
        ArrayList<String> imageUrls = new ArrayList<>();
        imageUrls.add("http://img3.cache.netease.com/photo/0005/2013-03-07/8PBKS8G400BV0005.jpg");
        bundle.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);

        //分享的消息摘要，最长50个字
        bundle.putString(QQShare.SHARE_TO_QQ_SUMMARY, "测试");
        mTencent.shareToQzone(mActivity, bundle, iUiListener);
    }
}
