package com.huanxin.workspace.feature.workspace.distribute;

import com.huanxin.workspace.base.IBaseModel;
import com.huanxin.workspace.base.IBaseView;
import com.huanxin.workspace.data.BaseBean;
import com.huanxin.workspace.data.DeviceDetailBean;
import com.huanxin.workspace.data.EngineerListBean;
import com.huanxin.workspace.data.WorkspaceDetailBean;
import com.huanxin.workspace.data.request.EngineerDistributeBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;

import java.util.List;


public interface DistributeContract {

    interface Model extends IBaseModel {
        void getDetail(String id, ProgressErrorSubscriber callback);

        void getEngineerList(ProgressErrorSubscriber callback);

        void getDeviceDetail(String id, ProgressErrorSubscriber callback);

        void distributeEngineer(EngineerDistributeBean engineerDistributeBean, ProgressErrorSubscriber callback);

    }

    interface View extends IBaseView {

        EngineerDistributeBean getBean();

        String getId();

        String getDeviceId();

        /**
         * 登陆成功
         *
         * @param userBean 登陆成功后返回结果实体类
         */
        void getDetailSuccess(WorkspaceDetailBean.DataBean userBean);

        void getDeviceDetailSuccess(DeviceDetailBean.DataBean userBean);

        void getEngineerListSuccess(List<EngineerListBean.DataBean> beanList);

        void distributeEngineerSuccess(BaseBean baseBean);

    }

    interface Presenter {
        void getDeviceDetail();

        void getDetail();

        void getEngineerList();

        void distributeEngineer();


    }
}
