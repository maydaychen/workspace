package com.huanxin.workspace.feature.login;

import com.huanxin.workspace.http.HttpJsonMethod;
import com.huanxin.workspace.http.ProgressErrorSubscriber;
import com.huanxin.workspace.requestBean.LoginBean;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2020/1/10  17:44
 * desc   :
 * version: 1.0
 */

public class LoginModel implements LoginContract.Model {

    @Override
    public void login(LoginBean loginBean, ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().login(
                callback, loginBean);
    }

    @Override
    public void sendCode(ProgressErrorSubscriber callback) {
        HttpJsonMethod.getInstance().sendSMS(
                callback);
    }
}