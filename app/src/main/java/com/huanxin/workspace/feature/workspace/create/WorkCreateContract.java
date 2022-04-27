package com.huanxin.workspace.feature.workspace.create;

import com.huanxin.workspace.base.IBaseModel;
import com.huanxin.workspace.base.IBaseView;
import com.huanxin.workspace.data.BaseBean;
import com.huanxin.workspace.data.DeviceDetailBean;
import com.huanxin.workspace.data.request.WorkspaceAddBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public interface WorkCreateContract {

    interface Model extends IBaseModel {

        void createWorkspace(WorkspaceAddBean workspaceAddBean, ProgressErrorSubscriber callback);

        void getDeviceDetail(String id, ProgressErrorSubscriber callback);


    }

    interface View extends IBaseView {

        WorkspaceAddBean getBean();

        String getDeviceId();

        void createWorkListSuccess(BaseBean userBean);

        void getDeviceDetailSuccess(DeviceDetailBean.DataBean userBean);


    }

    interface Presenter {
        void getDeviceDetail();

        /**
         * 获取工单列表
         */
        void createWorkspace();
    }
}
