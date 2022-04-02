package com.ming.workspace.feature.device.bind;

import com.ming.workspace.http.HttpJsonMethod;
import com.ming.workspace.http.ProgressErrorSubscriber;
import com.ming.workspace.requestBean.BindReuest;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2020/1/10  17:44
 * desc   :
 * version: 1.0
 */

public class BindModel implements BindContract.Model {
    @Override
    public void getCodeDetail(String url, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().getCodeDetail(
                callback, url);
    }

    @Override
    public void getDeviceList(String key, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().getDeviceList(
                callback, key);
    }


    @Override
    public void bindDevice(BindReuest bindReuest, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().bindDevice(callback, bindReuest);
    }
}