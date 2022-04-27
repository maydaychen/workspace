package com.huanxin.workspace.feature.workspace.create;

import static com.huanxin.workspace.Consts.HTTP_SUCCESS;

import com.huanxin.workspace.base.BasePresenter;
import com.huanxin.workspace.data.BaseBean;
import com.huanxin.workspace.data.DeviceDetailBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public class WorkCreatePresenter extends BasePresenter<WorkCreateContract.Model, WorkCreateContract.View>
        implements WorkCreateContract.Presenter {

    @Override
    protected WorkCreateModel createModule() {
        return new WorkCreateModel();
    }

    @Override
    public void start() {

    }


    @Override
    public void getDeviceDetail() {
        if (isViewAttached()) {
            getView().showLoading();
            getModule().getDeviceDetail(
                    getView().getDeviceId(),
                    new ProgressErrorSubscriber<DeviceDetailBean>() {
                        @Override
                        public void onNext(DeviceDetailBean deviceDetailBean) {
                            getView().dismissLoading();
                            if (deviceDetailBean.getCode() == HTTP_SUCCESS) {
                                getView().getDeviceDetailSuccess(deviceDetailBean.getData());
                            } else {
                                getView().showError("获取失败");
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
    public void createWorkspace() {
        if (isViewAttached()) {
            getView().showLoading();
            getModule().createWorkspace(
                    getView().getBean(),
                    new ProgressErrorSubscriber<BaseBean>() {
                        @Override
                        public void onNext(BaseBean baseBean) {
                            getView().dismissLoading();
                            if (baseBean.getCode() == HTTP_SUCCESS) {
                                getView().createWorkListSuccess(baseBean);
                            } else {
                                getView().showError("获取失败");
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