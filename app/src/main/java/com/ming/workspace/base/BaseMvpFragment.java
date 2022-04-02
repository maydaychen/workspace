package com.ming.workspace.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.ming.workspace.R;


/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2019/9/5  13:53
 * desc   :
 * version: 1.0
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements IBaseView {
    protected P presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建present
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    //***************************************IBaseView方法实现*************************************
    @Override
    public void showLoading() {
        showLoading("");
    }

    @Override
    public void dismissLoading() {
        dismiss();
    }

    @Override
    public void onEmpty(Object tag) {

    }

    @Override
    public void onError(Object tag, String errorMsg) {

    }
//***************************************IBaseView方法实现*************************************

    /**
     * 创建Presenter
     */
    protected abstract P createPresenter();

    @Override
    public void showError(String errorMsg) {
        dismissLoading();
        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void serviceError() {
        showError(getString(R.string.server_error));
    }

}
