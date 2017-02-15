package com.rickwan.qiger.base;


import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class BasePresenter {

    private CompositeSubscription mCompositeSubscription;


    /**
     * RxJava取消注册，以避免内存泄露
     */
    public void unSubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }


    /**
     * RxJava注册
     *
     * @param subscription
     */
    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }
}
