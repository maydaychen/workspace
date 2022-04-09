package com.huanxin.workspace.feature.main;

import static com.huanxin.workspace.Consts.HTTP_SUCCESS;

import com.huanxin.workspace.base.BasePresenter;
import com.huanxin.workspace.data.CallListBean;
import com.huanxin.workspace.data.WorkListBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2020/1/10  17:33
 * desc   :
 * version: 1.0
 */

public class MainPresenter extends BasePresenter<MainContract.Model, MainContract.View>
        implements MainContract.Presenter {

    @Override
    public void getWorkList() {
        if (isViewAttached()) {
//            if (!checkEmail(getView().getEmail())) {
//                getView().showError(getContext().getString(R.string.error_username));
//            } else if ("".equals(getView().getPass())) {
//                getView().showError(getContext().getString(R.string.error_password));
//            } else {
            getModule().getWorkList(
                    new ProgressErrorSubscriber<WorkListBean>() {
                        @Override
                        public void onNext(WorkListBean userBean) {
                            if (userBean.getCode() == HTTP_SUCCESS) {
                                getView().getWorkListSuccess(userBean.getData());
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
    public void getCallList() {
        if (isViewAttached()) {
            getModule().getCallList(1, 10, new ProgressErrorSubscriber<CallListBean>() {
                @Override
                public void onNext(CallListBean callListBean) {
                    if (callListBean.getCode() == HTTP_SUCCESS) {
                        getView().getCallListSuccess(callListBean.getData());
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
    protected MainModel createModule() {
        return new MainModel();
    }

    @Override
    public void start() {

    }
}