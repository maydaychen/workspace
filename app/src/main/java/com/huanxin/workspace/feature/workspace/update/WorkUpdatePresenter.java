package com.huanxin.workspace.feature.workspace.update;

import static com.huanxin.workspace.Consts.HTTP_SUCCESS;

import com.huanxin.workspace.base.BasePresenter;
import com.huanxin.workspace.data.BaseBean;
import com.huanxin.workspace.data.DeviceDetailBean;
import com.huanxin.workspace.data.WorkspaceDetailBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public class WorkUpdatePresenter extends BasePresenter<WorkUpdateContract.Model, WorkUpdateContract.View>
        implements WorkUpdateContract.Presenter {

    @Override
    protected WorkUpdateModel createModule() {
        return new WorkUpdateModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void closeWorkspace() {
        if (isViewAttached()) {
            getView().showLoading();
            getModule().closeWorkspace(
                    getView().getBean(),
                    new ProgressErrorSubscriber<BaseBean>() {
                        @Override
                        public void onNext(BaseBean baseBean) {
                            getView().dismissLoading();
                            if (baseBean.getCode() == HTTP_SUCCESS) {
                                getView().closeWorkSuccess(baseBean);
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
        }
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
        }
    }
}