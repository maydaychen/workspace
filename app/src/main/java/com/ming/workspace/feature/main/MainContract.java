package com.ming.workspace.feature.main;

import com.ming.workspace.base.IBaseModel;
import com.ming.workspace.base.IBaseView;
import com.ming.workspace.data.CallListBean;
import com.ming.workspace.data.WorkListBean;
import com.ming.workspace.http.ProgressErrorSubscriber;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2020/1/10  17:33
 * desc   :
 * version: 1.0
 */
public interface MainContract {

    interface Model extends IBaseModel {
        void getCallList(int pageNum, int pageSize, ProgressErrorSubscriber callback);

        void getWorkList(ProgressErrorSubscriber callback);
    }

    interface View extends IBaseView {
        void getCallListSuccess(CallListBean.DataBean userBean);

        void getWorkListSuccess(WorkListBean.DataBean userBean);

    }

    interface Presenter {

        /**
         * 登录
         */
        void getCallList();

        void getWorkList();
    }
}
