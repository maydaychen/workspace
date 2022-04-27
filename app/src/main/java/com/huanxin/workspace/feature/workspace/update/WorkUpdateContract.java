package com.huanxin.workspace.feature.workspace.update;

import com.huanxin.workspace.base.IBaseModel;
import com.huanxin.workspace.base.IBaseView;
import com.huanxin.workspace.data.BaseBean;
import com.huanxin.workspace.data.request.WorkspaceUpdateBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public interface WorkUpdateContract {

    interface Model extends IBaseModel {

        void updateWorkspace(WorkspaceUpdateBean workspaceAddBean, ProgressErrorSubscriber callback);

        void getDetail(String id, ProgressErrorSubscriber callback);

    }

    interface View extends IBaseView {
        String getId();

        void updateWorkListSuccess(BaseBean userBean);

        void getDetailSuccess(WorkspaceUpdateBean userBean);


    }

    interface Presenter {

        /**
         * 获取工单列表
         */
        void updateWorkspace();

        void getDetail();

    }
}
