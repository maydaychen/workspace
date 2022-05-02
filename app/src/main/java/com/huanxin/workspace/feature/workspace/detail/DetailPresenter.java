package com.huanxin.workspace.feature.workspace.detail;

import static com.huanxin.workspace.Consts.HTTP_SUCCESS;

import com.huanxin.workspace.base.BasePresenter;
import com.huanxin.workspace.data.DeviceDetailBean;
import com.huanxin.workspace.data.WorkspaceDetailBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public class DetailPresenter extends BasePresenter<DetailContract.Model, DetailContract.View>
        implements DetailContract.Presenter {

    @Override
    protected DetailModel createModule() {
        return new DetailModel();
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
    public void getDetail() {
        if (isViewAttached()) {
            getView().showLoading();
            getModule().getDetail(
                    getView().getId(), new ProgressErrorSubscriber<WorkspaceDetailBean>() {
                        @Override
                        public void onNext(WorkspaceDetailBean workspaceDetailBean) {
                            getView().dismissLoading();
                            if (workspaceDetailBean.getCode() == HTTP_SUCCESS) {
                                getView().getDetailSuccess(workspaceDetailBean.getData());
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