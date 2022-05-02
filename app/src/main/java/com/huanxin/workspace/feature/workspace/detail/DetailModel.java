package com.huanxin.workspace.feature.workspace.detail;

import com.huanxin.workspace.http.HttpJsonMethod;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public class DetailModel implements DetailContract.Model {

    @Override
    public void getDetail(String id, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().getWorkspaceDetail(
                callback, id);
    }

    @Override
    public void getDeviceDetail(String id, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().getDeviceDetail(
                callback, id);
    }
}