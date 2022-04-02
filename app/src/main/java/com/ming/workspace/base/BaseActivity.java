package com.ming.workspace.base;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.ming.workspace.R;
import com.ming.workspace.util.DialogUtils;
import com.ming.workspace.widget.StatusBarCompat;

import java.util.Objects;

import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2020/3/24  14:08
 * desc   :
 * version: 1.0
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected Dialog loadingDialog;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);

        StatusBarCompat.compat(this, ContextCompat.getColor(this, R.color.colorPrimary));
        Objects.requireNonNull(getSupportActionBar()).hide();
//        if (Build.VERSION.SDK_INT >= 21) {
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
////            getWindow().setStatusBarColor(getColor(R.color.black));
////            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//
//        }
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        loadingDialog = DialogUtils.createLoadingDialog(this, getString(R.string.loading));
        initData(savedInstanceState);
        initView();

    }

    public void goActivity(Class c) {
        startActivity(new Intent(this, c));
    }

    public void cleanGoActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
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

    public void dismiss() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    /**
     * 获取状态栏的高度，以便计算布局
     *
     * @return 状态栏的高度
     */
    public int getStatusBarHeight() {
        Resources resources = getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }
}