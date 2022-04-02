package com.ming.workspace.base;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.ming.workspace.R;
import com.ming.workspace.util.DialogUtils;
import com.ming.workspace.widget.StatusBarCompat;

import java.util.Objects;

import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * @author yi.chen
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    protected P presenter;
    protected Dialog loadingDialog;
    protected int mWidth;

    /**
     * 获取映射页面id
     *
     * @return the layout id
     */
    protected abstract int getLayoutId();

    /**
     * 加载数据
     *
     * @param savedInstanceState the saved instance state
     */
    public abstract void initData(Bundle savedInstanceState);


    /**
     * 加载布局页面
     */
    public abstract void initView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        ButterKnife.bind(this);
        StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.colorPrimary));
        Objects.requireNonNull(getSupportActionBar()).hide();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        mWidth = dm.widthPixels;
//        View decorView = getWindow().getDecorView();
//        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//        decorView.setSystemUiVisibility(option);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
//        Objects.requireNonNull(getSupportActionBar()).hide();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //创建present
        presenter = createPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        }
        loadingDialog = DialogUtils.createLoadingDialog(this, getString(R.string.loading));
        initData(savedInstanceState);
        initView();
    }

    /**
     * 在页面结束的生命周期注销消息接收，防止内存泄漏
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
            presenter = null;
        }
    }

    public void goActivity(Class c) {
        startActivity(new Intent(this, c));
    }

    public void cleanGoActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //***************************************IBaseView方法实现*************************************
    @Override
    public void showLoading() {
        showLoading("");
    }

    //展示加载框
    public void showLoading(String msg) {
        if (!loadingDialog.isShowing()) {
            if (!TextUtils.isEmpty(msg)) {
                loadingDialog.setTitle(msg);
            }
            loadingDialog.show();
        }
    }

    @Override
    public void dismissLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void onEmpty(Object tag) {

    }

    @Override
    public void onError(Object tag, String errorMsg) {

    }

    /**
     * 展示错误信息，并且dismiss加载框
     *
     * @param errorMsg 错误信息
     */
    @Override
    public void showError(String errorMsg) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void serviceError() {
        showError(getString(R.string.server_error));
    }

    //***************************************IBaseView方法实现*************************************

    /**
     * 创建Presenter
     */
    protected abstract P createPresenter();
}
