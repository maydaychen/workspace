package com.ming.workspace.feature.device.bind;

import static com.ming.workspace.Consts.HTTP_SUCCESS;

import com.ming.workspace.base.BasePresenter;
import com.ming.workspace.data.BaseBean;
import com.ming.workspace.data.CodeDetailBean;
import com.ming.workspace.data.DeviceListBean;
import com.ming.workspace.http.ProgressErrorSubscriber;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2020/1/10  17:33
 * desc   :
 * version: 1.0
 */

public class BindPresenter extends BasePresenter<BindContract.Model, BindContract.View>
        implements BindContract.Presenter {

    @Override
    protected BindModel createModule() {
        return new BindModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void getCodeDetail() {
        if (isViewAttached()) {
            getView().showLoading();
            getModule().getCodeDetail(
                    getView().getCode(), new ProgressErrorSubscriber<CodeDetailBean>() {
                        @Override
                        public void onNext(CodeDetailBean codeDetailBean) {
                            getView().dismissLoading();
                            if (codeDetailBean.getCode() == HTTP_SUCCESS) {
                                getView().getCodeDetailSuccess(codeDetailBean.getData());
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
    public void getDeviceList() {
        if (isViewAttached()) {
            getModule().getDeviceList(
                    getView().getKey(), new ProgressErrorSubscriber<DeviceListBean>() {
                        @Override
                        public void onNext(DeviceListBean deviceListBean) {
                            if (deviceListBean.getCode() == HTTP_SUCCESS) {
                                getView().getDeviceListSuccess(deviceListBean.getData());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            dispatchException(e);
                        }
                    });
//            }
        }
    }

    @Override
    public void bindDevice() {
        if (isViewAttached()) {
            getView().showLoading();
            getModule().bindDevice(
                    getView().getBindReuest(), new ProgressErrorSubscriber<BaseBean>() {
                        @Override
                        public void onNext(BaseBean baseBean) {
                            getView().dismissLoading();
                            if (baseBean.getCode() == HTTP_SUCCESS) {
                                getView().bindSuccess(baseBean);
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
}