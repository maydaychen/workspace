package com.huanxin.workspace.feature.main.work;

import static com.huanxin.workspace.Consts.HTTP_SUCCESS;

import com.huanxin.workspace.base.BasePresenter;
import com.huanxin.workspace.data.UserBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;



public class WorkPresenter extends BasePresenter<WorkContract.Model, WorkContract.View>
        implements WorkContract.Presenter {

    @Override
    public void getWorkList() {
        if (isViewAttached()) {
            getView().showLoading();
            getModule().getWorkList(
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
        }
    }


    @Override
    protected WorkModel createModule() {
        return new WorkModel();
    }

    @Override
    public void start() {

    }
}