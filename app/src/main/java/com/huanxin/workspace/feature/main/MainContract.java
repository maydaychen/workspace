package com.huanxin.workspace.feature.main;

import com.huanxin.workspace.base.IBaseModel;
import com.huanxin.workspace.base.IBaseView;
import com.huanxin.workspace.data.CallListBean;
import com.huanxin.workspace.data.WorkListBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;

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
