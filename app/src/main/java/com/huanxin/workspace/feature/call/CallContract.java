package com.huanxin.workspace.feature.call;

import com.huanxin.workspace.base.IBaseModel;
import com.huanxin.workspace.base.IBaseView;
import com.huanxin.workspace.data.CallBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;

import java.util.List;


public interface CallContract {

    interface Model extends IBaseModel {

        void getCallList(int pageNum, int pageSize, ProgressErrorSubscriber callback);

    }

    interface View extends IBaseView {
        int getPageNum();

        /**
         * 登陆成功
         *
         * @param userBean 登陆成功后返回结果实体类
         */
        void getCallListSuccess(List<CallBean.DataBean.ItemsBean> itemsBeans);
    }

    interface Presenter {

        /**
         * 登录
         */
        void getCallList();
    }
}
