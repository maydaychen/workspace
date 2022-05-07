package com.huanxin.workspace.feature.workspace.update;

import com.huanxin.workspace.base.IBaseModel;
import com.huanxin.workspace.base.IBaseView;
import com.huanxin.workspace.data.BaseBean;
import com.huanxin.workspace.data.DeviceDetailBean;
import com.huanxin.workspace.data.WorkspaceDetailBean;
import com.huanxin.workspace.data.request.WorkspaceCloseBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public interface WorkUpdateContract {

    interface Model extends IBaseModel {

        void closeWorkspace(WorkspaceCloseBean workspaceAddBean, ProgressErrorSubscriber callback);

        void getDetail(String id, ProgressErrorSubscriber callback);

        void getDeviceDetail(String id, ProgressErrorSubscriber callback);


    }

    interface View extends IBaseView {
        String getId();

        String getDeviceId();

        WorkspaceCloseBean getBean();

        void closeWorkSuccess(BaseBean userBean);

        void getDetailSuccess(WorkspaceDetailBean.DataBean userBean);

        void getDeviceDetailSuccess(DeviceDetailBean.DataBean userBean);


    }

    interface Presenter {

        void closeWorkspace();

        void getDeviceDetail();

        void getDetail();

    }
}
