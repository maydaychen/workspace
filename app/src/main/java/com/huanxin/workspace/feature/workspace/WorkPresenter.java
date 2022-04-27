package com.huanxin.workspace.feature.workspace;

import static com.huanxin.workspace.Consts.HTTP_SUCCESS;

import com.huanxin.workspace.base.BasePresenter;
import com.huanxin.workspace.data.WorkspaceListBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;



public class WorkPresenter extends BasePresenter<WorkContract.Model, WorkContract.View>
        implements WorkContract.Presenter {

    @Override
    protected WorkModel createModule() {
        return new WorkModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void getWorkList() {
        if (isViewAttached()) {
            getView().showLoading();
            getModule().getWorkList(
                    getView().getEngineerId(),
                    getView().getCreateBy(),
                    getView().getPageNum(),
                    10,
                    new ProgressErrorSubscriber<WorkspaceListBean>() {
                        @Override
                        public void onNext(WorkspaceListBean userBean) {
                            getView().dismissLoading();
                            if (userBean.getCode() == HTTP_SUCCESS) {
                                getView().getWorkListSuccess(userBean.getData().getItems());
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