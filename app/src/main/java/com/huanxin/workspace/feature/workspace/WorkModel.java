package com.huanxin.workspace.feature.workspace;

import com.huanxin.workspace.http.HttpJsonMethod;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public class WorkModel implements WorkContract.Model {

    @Override
    public void getWorkList(String engineerId, String createBy, int pageNum, int pageSize, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().getWorkList(
                callback, engineerId, createBy, pageNum, pageSize);
    }
}