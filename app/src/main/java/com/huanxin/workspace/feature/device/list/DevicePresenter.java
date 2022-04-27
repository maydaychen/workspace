package com.huanxin.workspace.feature.device.list;

import static com.huanxin.workspace.Consts.HTTP_SUCCESS;

import com.huanxin.workspace.base.BasePresenter;
import com.huanxin.workspace.data.UserBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public class DevicePresenter extends BasePresenter<DeviceContract.Model, DeviceContract.View>
        implements DeviceContract.Presenter {

    @Override
    public void getDeviceList() {
        if (isViewAttached()) {
//            if (!checkEmail(getView().getEmail())) {
//                getView().showError(getContext().getString(R.string.error_username));
//            } else if ("".equals(getView().getPass())) {
//                getView().showError(getContext().getString(R.string.error_password));
//            } else {
            getView().showLoading();
            getModule().getDeviceList(
                    new ProgressErrorSubscriber<UserBean>() {
                        @Override
                        public void onNext(UserBean userBean) {
                            getView().dismissLoading();
                            if (userBean.getCode() == HTTP_SUCCESS) {
                                getView().getListSuccess(userBean);
                            } else {
                                getView().getListFail(userBean);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            getView().dismissLoading();
                            dispatchException(e);
                        }
                    });
//            }
        }
    }


    @Override
    protected DeviceModel createModule() {
        return new DeviceModel();
    }

    @Override
    public void start() {

    }
}