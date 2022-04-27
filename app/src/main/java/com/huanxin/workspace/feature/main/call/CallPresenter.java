package com.huanxin.workspace.feature.main.call;

import static com.huanxin.workspace.Consts.HTTP_SUCCESS;

import com.huanxin.workspace.base.BasePresenter;
import com.huanxin.workspace.data.CallListBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public class CallPresenter extends BasePresenter<CallContract.Model, CallContract.View>
        implements CallContract.Presenter {

    @Override
    public void getCallList() {
        if (isViewAttached()) {
//            getView().showLoading();
            getModule().getCallList(
                    getView().getPageNum(),
                    10,
                    new ProgressErrorSubscriber<CallListBean>() {
                        @Override
                        public void onNext(CallListBean userBean) {
//                            getView().dismissLoading();
                            if (userBean.getCode() == HTTP_SUCCESS) {
                                getView().getListSuccess(userBean.getData().getRecords());
                            } else {
                                getView().getListFail(userBean);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
//                            getView().dismissLoading();
                            dispatchException(e);
                        }
                    });
//            }
        }
    }


    @Override
    protected CallModel createModule() {
        return new CallModel();
    }

    @Override
    public void start() {

    }
}