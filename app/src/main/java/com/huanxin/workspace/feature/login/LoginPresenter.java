package com.huanxin.workspace.feature.login;

import static com.huanxin.workspace.Consts.HTTP_SUCCESS;

import com.huanxin.workspace.base.BasePresenter;
import com.huanxin.workspace.data.CodeBean;
import com.huanxin.workspace.data.UserBean;
import com.huanxin.workspace.http.ProgressErrorSubscriber;

public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View>
        implements LoginContract.Presenter {

    @Override
    public void login() {
        if (isViewAttached()) {
//            if (!checkEmail(getView().getEmail())) {
//                getView().showError(getContext().getString(R.string.error_username));
//            } else if ("".equals(getView().getPass())) {
//                getView().showError(getContext().getString(R.string.error_password));
//            } else {
            getView().showLoading();
            getModule().login(
                    getView().getLoginBean(),new ProgressErrorSubscriber<UserBean>() {
                        @Override
                        public void onNext(UserBean userBean) {
                            getView().dismissLoading();
                            if (userBean.getCode() == HTTP_SUCCESS) {
                                getView().loginSuccess(userBean);
                            } else {
                                getView().loginFail(userBean);
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

    @Override
    public void sendCode() {
        if (isViewAttached()) {
            getView().showLoading();
            getModule().sendCode(new ProgressErrorSubscriber<CodeBean>() {
                @Override
                public void onNext(CodeBean userBean) {
                    getView().dismissLoading();
                    if (userBean.getCode() == HTTP_SUCCESS) {
                        getView().sendCodeSuccess(userBean);
                    } else {
                        getView().sendFail(userBean);
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

    @Override
    protected LoginModel createModule() {
        return new LoginModel();
    }

    @Override
    public void start() {

    }
}