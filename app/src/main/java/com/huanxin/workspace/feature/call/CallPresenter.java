package com.huanxin.workspace.feature.call;

import static com.huanxin.workspace.Consts.HTTP_SUCCESS;

import com.huanxin.workspace.base.BasePresenter;
import com.huanxin.workspace.data.CallBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;


public class CallPresenter extends BasePresenter<CallContract.Model, CallContract.View>
        implements CallContract.Presenter {

    @Override
    protected CallModel createModule() {
        return new CallModel();
    }

    @Override
    public void start() {

    }

    @Override
    public void getCallList() {
        if (isViewAttached()) {
            getView().showLoading();
            getModule().getCallList(
                    getView().getPageNum(), 10, new ProgressErrorSubscriber<CallBean>() {
                        @Override
                        public void onNext(CallBean baseBean) {
                            getView().dismissLoading();
                            if (baseBean.getCode() == HTTP_SUCCESS) {
                                getView().getCallListSuccess(baseBean.getData().getItems());
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