package com.huanxin.workspace.http;

import rx.Subscriber;



public class ProgressErrorSubscriber<T> extends Subscriber<T> {

    public ProgressErrorSubscriber() {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onCompleted() {
//        Toast.makeText(context, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {

    }
}