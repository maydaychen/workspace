package com.huanxin.workspace.feature.workspace.distribute;

import static com.huanxin.workspace.Consts.HTTP_SUCCESS;

import com.huanxin.workspace.base.BasePresenter;
import com.huanxin.workspace.data.BaseBean;
import com.huanxin.workspace.data.DeviceDetailBean;
import com.huanxin.workspace.data.EngineerListBean;
import com.huanxin.workspace.data.WorkspaceDetailBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public class DistributePresenter extends BasePresenter<DistributeContract.Model, DistributeContract.View>
        implements DistributeContract.Presenter {

    @Override
    protected DistributeModel createModule() {
        return new DistributeModel();
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

    @Override
    public void getEngineerList() {
        if (isViewAttached()) {
            getModule().getEngineerList(new ProgressErrorSubscriber<EngineerListBean>() {
                @Override
                public void onNext(EngineerListBean engineerListBean) {
                    if (engineerListBean.getCode() == HTTP_SUCCESS) {
                        getView().getEngineerListSuccess(engineerListBean.getData());
                    }
                }

                @Override
                public void onError(Throwable e) {
                    dispatchException(e);
                }
            });
        }
    }

    @Override
    public void distributeEngineer() {
        if (isViewAttached()) {
            getView().showLoading();
            getModule().distributeEngineer(
                    getView().getBean(), new ProgressErrorSubscriber<BaseBean>() {
                        @Override
                        public void onNext(BaseBean baseBean) {
                            getView().dismissLoading();
                            if (baseBean.getCode() == HTTP_SUCCESS) {
                                getView().distributeEngineerSuccess(baseBean);
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