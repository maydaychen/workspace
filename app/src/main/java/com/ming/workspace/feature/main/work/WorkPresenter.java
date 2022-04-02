package com.ming.workspace.feature.main.work;

import static com.ming.workspace.Consts.HTTP_SUCCESS;

import com.ming.workspace.base.BasePresenter;
import com.ming.workspace.data.UserBean;
import com.ming.workspace.http.ProgressErrorSubscriber;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2020/1/10  17:33
 * desc   :
 * version: 1.0
 */

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