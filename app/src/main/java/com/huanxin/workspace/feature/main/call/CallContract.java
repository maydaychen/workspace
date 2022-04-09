package com.huanxin.workspace.feature.main.call;

import com.huanxin.workspace.base.IBaseModel;
import com.huanxin.workspace.base.IBaseView;
import com.huanxin.workspace.data.CallListBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;

import java.util.List;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2020/1/10  17:33
 * desc   :
 * version: 1.0
 */
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
        void getListSuccess(List<CallListBean.DataBean.RecordsBean> userBean);

        void getListFail(CallListBean user);

    }

    interface Presenter {

        /**
         * 登录
         */
        void getCallList();
    }
}