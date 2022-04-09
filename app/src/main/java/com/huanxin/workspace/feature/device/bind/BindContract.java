package com.huanxin.workspace.feature.device.bind;

import com.huanxin.workspace.base.IBaseModel;
import com.huanxin.workspace.base.IBaseView;
import com.huanxin.workspace.data.BaseBean;
import com.huanxin.workspace.data.CodeDetailBean;
import com.huanxin.workspace.data.DeviceListBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;
import com.huanxin.workspace.requestBean.BindReuest;

import java.util.List;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2020/1/10  17:33
 * desc   :
 * version: 1.0
 */
public interface BindContract {

    interface Model extends IBaseModel {
        void getCodeDetail(String url, ProgressErrorSubscriber callback);

        void getDeviceList(String key, ProgressErrorSubscriber callback);

        void bindDevice(BindReuest bindReuest, ProgressErrorSubscriber callback);

    }

    interface View extends IBaseView {

        BindReuest getBindReuest();

        String getKey();

        String getCode();

        /**
         * 登陆成功
         *
         * @param userBean 登陆成功后返回结果实体类
         */
        void getCodeDetailSuccess(CodeDetailBean.DataBean userBean);


        void getDeviceListSuccess(List<DeviceListBean.DataBean> userBean);

        void bindSuccess(BaseBean user);

    }

    interface Presenter {

        /**
         * 登录
         */
        void getCodeDetail();

        void getDeviceList();

        void bindDevice();
    }
}
