package com.huanxin.workspace.feature.call;

import com.huanxin.workspace.http.HttpJsonMethod;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public class CallModel implements CallContract.Model {

    @Override
    public void getCallList(int pageNum, int pageSize, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().getCallList(callback, pageNum, pageSize);
    }
}