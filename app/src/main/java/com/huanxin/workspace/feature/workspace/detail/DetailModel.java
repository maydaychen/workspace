package com.huanxin.workspace.feature.workspace.detail;

import com.huanxin.workspace.http.HttpJsonMethod;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public class DetailModel implements DetailContract.Model {

    @Override
    public void getDetail(String url, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().getCodeDetail(
                callback, url);
    }
}