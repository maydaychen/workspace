package com.huanxin.workspace.feature.workspace.detail;

import com.huanxin.workspace.base.IBaseModel;
import com.huanxin.workspace.base.IBaseView;
import com.huanxin.workspace.data.DeviceDetailBean;
import com.huanxin.workspace.data.WorkspaceDetailBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public interface DetailContract {

    interface Model extends IBaseModel {
        void getDetail(String id, ProgressErrorSubscriber callback);

        void getDeviceDetail(String id, ProgressErrorSubscriber callback);

    }

    interface View extends IBaseView {


        String getId();

        String getDeviceId();

        /**
         * 登陆成功
         *
         * @param userBean 登陆成功后返回结果实体类
         */
        void getDetailSuccess(WorkspaceDetailBean.DataBean userBean);

        void getDeviceDetailSuccess(DeviceDetailBean.DataBean userBean);

    }

    interface Presenter {
        void getDeviceDetail();

        void getDetail();


    }
}
