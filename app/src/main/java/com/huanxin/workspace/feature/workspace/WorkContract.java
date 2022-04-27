package com.huanxin.workspace.feature.workspace;

import com.huanxin.workspace.base.IBaseModel;
import com.huanxin.workspace.base.IBaseView;
import com.huanxin.workspace.data.WorkspaceListBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;

import java.util.List;


public interface WorkContract {

    interface Model extends IBaseModel {

        void getWorkList(String engineerId, String createBy, int pageNum, int pageSize, ProgressErrorSubscriber callback);


    }

    interface View extends IBaseView {

        int getPageNum();

        String getCreateBy();

        String getEngineerId();


        void getWorkListSuccess(List<WorkspaceListBean.DataBean.ItemsBean> userBean);


    }

    interface Presenter {

        /**
         * 获取工单列表
         */
        void getWorkList();
    }
}
