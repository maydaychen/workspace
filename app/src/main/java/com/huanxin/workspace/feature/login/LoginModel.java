package com.huanxin.workspace.feature.login;

import com.huanxin.workspace.http.HttpJsonMethod;
import com.huanxin.workspace.http.ProgressErrorSubscriber;
import com.huanxin.workspace.requestBean.LoginBean;

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