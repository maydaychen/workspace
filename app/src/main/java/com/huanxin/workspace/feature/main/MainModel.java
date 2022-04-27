package com.huanxin.workspace.feature.main;

import com.huanxin.workspace.http.HttpJsonMethod;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public class MainModel implements MainContract.Model {

    @Override
    public void getCallList(int pageNum, int pageSize, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().getMissedRecord(
                callback, pageNum, pageSize);
    }

    @Override
    public void getWorkList(ProgressErrorSubscriber callback) {
//        HttpJsonMethod.getInstance().getWorkList(
//                callback);
    }
}