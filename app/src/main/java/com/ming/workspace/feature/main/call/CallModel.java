package com.ming.workspace.feature.main.call;

import com.ming.workspace.http.HttpJsonMethod;
import com.ming.workspace.http.ProgressErrorSubscriber;

public class CallModel implements CallContract.Model {

    @Override
    public void getCallList(int pageNum, int pageSize, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().getMissedRecord(
                callback, pageNum, pageSize);
    }
}