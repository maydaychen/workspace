package com.ming.workspace.feature.main;

import com.ming.workspace.http.HttpJsonMethod;
import com.ming.workspace.http.ProgressErrorSubscriber;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2020/1/10  17:44
 * desc   :
 * version: 1.0
 */

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