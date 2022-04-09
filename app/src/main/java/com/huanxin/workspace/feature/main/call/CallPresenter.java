package com.huanxin.workspace.feature.main.call;

import static com.huanxin.workspace.Consts.HTTP_SUCCESS;

import com.huanxin.workspace.base.BasePresenter;
import com.huanxin.workspace.data.CallListBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2020/1/10  17:33
 * desc   :
 * version: 1.0
 */

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