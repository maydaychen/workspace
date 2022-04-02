package com.ming.workspace.feature.main.work;

import com.ming.workspace.http.HttpJsonMethod;
import com.ming.workspace.http.ProgressErrorSubscriber;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2020/1/10  17:44
 * desc   :
 * version: 1.0
 */

public class WorkModel implements WorkContract.Model {

    @Override
    public void getWorkList( ProgressErrorSubscriber callback) {
//        HttpJsonMethod.getInstance().login(
//                callback);
    }
}