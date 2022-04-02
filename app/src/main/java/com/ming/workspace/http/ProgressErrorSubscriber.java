package com.ming.workspace.http;

import rx.Subscriber;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2019/8/19 17:31
 * desc   :
 * version: 1.0
 */

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