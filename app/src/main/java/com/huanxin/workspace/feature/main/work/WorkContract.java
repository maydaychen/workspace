package com.huanxin.workspace.feature.main.work;

import com.huanxin.workspace.base.IBaseModel;
import com.huanxin.workspace.base.IBaseView;
import com.huanxin.workspace.data.UserBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public interface WorkContract {

    interface Model extends IBaseModel {

        void getWorkList(ProgressErrorSubscriber callback);
    }

    interface View extends IBaseView {

        void getListSuccess(UserBean userBean);

        void getListFail(UserBean user);

    }

    interface Presenter {

        /**
         * 登录
         */
        void getWorkList();
    }
}
