package com.huanxin.workspace.feature.device.bind;

import com.huanxin.workspace.http.HttpJsonMethod;
import com.huanxin.workspace.http.ProgressErrorSubscriber;
import com.huanxin.workspace.requestBean.BindReuest;

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