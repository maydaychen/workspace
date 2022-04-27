package com.huanxin.workspace.feature.device.list;

import com.huanxin.workspace.base.IBaseModel;
import com.huanxin.workspace.base.IBaseView;
import com.huanxin.workspace.data.UserBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public interface DeviceContract {

    interface Model extends IBaseModel {

        void getDeviceList( ProgressErrorSubscriber callback);
    }

    interface View extends IBaseView {
        void getListSuccess(UserBean userBean);

        void getListFail(UserBean user);

    }

    interface Presenter {

        /**
         * 登录
         */
        void getDeviceList();
    }
}
