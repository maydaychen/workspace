package com.huanxin.workspace.feature.workspace.detail;

import static com.huanxin.workspace.Consts.HTTP_SUCCESS;

import com.huanxin.workspace.base.BasePresenter;
import com.huanxin.workspace.data.CodeDetailBean;
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
    public void getDetail() {
        if (isViewAttached()) {
            getView().showLoading();
            getModule().getDetail(
                    getView().getId(), new ProgressErrorSubscriber<CodeDetailBean>() {
                        @Override
                        public void onNext(CodeDetailBean codeDetailBean) {
                            getView().dismissLoading();
                            if (codeDetailBean.getCode() == HTTP_SUCCESS) {
                                getView().getDetailSuccess(codeDetailBean.getData());
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