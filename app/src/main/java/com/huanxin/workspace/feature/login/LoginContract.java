package com.huanxin.workspace.feature.login;

import com.huanxin.workspace.base.IBaseModel;
import com.huanxin.workspace.base.IBaseView;
import com.huanxin.workspace.data.CodeBean;
import com.huanxin.workspace.data.UserBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;
import com.huanxin.workspace.requestBean.LoginBean;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2020/1/10  17:33
 * desc   :
 * version: 1.0
 */
public interface LoginContract {

    interface Model extends IBaseModel {
        void login(LoginBean loginBean, ProgressErrorSubscriber callback);

        void sendCode(ProgressErrorSubscriber callback);
    }

    interface View extends IBaseView {

        LoginBean getLoginBean();

        /**
         * 登陆成功
         *
         * @param userBean 登陆成功后返回结果实体类
         */
        void loginSuccess(UserBean userBean);

        void loginFail(UserBean user);

        void sendCodeSuccess(CodeBean userBean);

        void sendFail(CodeBean user);

    }

    interface Presenter {

        /**
         * 登录
         */
        void login();

        void sendCode();
    }
}
