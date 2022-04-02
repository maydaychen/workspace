package com.ming.workspace.feature.device.list;

import com.ming.workspace.base.IBaseModel;
import com.ming.workspace.base.IBaseView;
import com.ming.workspace.data.UserBean;
import com.ming.workspace.http.ProgressErrorSubscriber;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2020/1/10  17:33
 * desc   :
 * version: 1.0
 */
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
