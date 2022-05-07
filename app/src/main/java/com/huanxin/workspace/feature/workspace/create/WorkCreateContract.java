package com.huanxin.workspace.feature.workspace.create;

import com.huanxin.workspace.base.IBaseModel;
import com.huanxin.workspace.base.IBaseView;
import com.huanxin.workspace.data.BaseBean;
import com.huanxin.workspace.data.DeviceDetailBean;
import com.huanxin.workspace.data.EngineerListBean;
import com.huanxin.workspace.data.request.WorkspaceAddBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;

import java.util.List;


public interface WorkCreateContract {

    interface Model extends IBaseModel {
        void getEngineerList(ProgressErrorSubscriber callback);

        void createWorkspace(WorkspaceAddBean workspaceAddBean, ProgressErrorSubscriber callback);

        void getDeviceDetail(String id, ProgressErrorSubscriber callback);


    }

    interface View extends IBaseView {

        WorkspaceAddBean getBean();

        String getDeviceId();

        void createWorkListSuccess(BaseBean userBean);

        void getDeviceDetailSuccess(DeviceDetailBean.DataBean userBean);

        void getEngineerListSuccess(List<EngineerListBean.DataBean> beanList);
    }

    interface Presenter {
        void getDeviceDetail();

        void getEngineerList();

        void createWorkspace();
    }
}
