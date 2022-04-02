package com.ming.workspace.feature.login;

import static com.ming.workspace.Consts.ACCOUNTID;
import static com.ming.workspace.Consts.PASSWORD;
import static com.ming.workspace.Consts.TOKEN;
import static com.ming.workspace.Consts.USERNAME;
import static com.ming.workspace.util.Base64Util.stringToBitmap;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ming.workspace.R;
import com.ming.workspace.base.BaseMvpActivity;
import com.ming.workspace.data.CodeBean;
import com.ming.workspace.data.UserBean;
import com.ming.workspace.feature.main.MainActivity;
import com.ming.workspace.requestBean.LoginBean;
import com.ming.workspace.util.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yi.chen
 */
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View {
    @BindView(R.id.et_login_accountId)
    EditText mEtAccount;
    @BindView(R.id.et_login_username)
    EditText mEtUserName;
    @BindView(R.id.et_login_password)
    EditText mEtPassword;
    @BindView(R.id.et_login_code)
    EditText mEtCode;
    @BindView(R.id.iv_login_code)
    ImageView mIvCode;

    private String uuid = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mEtUserName.setText((String) SharedPreferencesUtils.getParam(getApplicationContext(), USERNAME, ""));
        mEtAccount.setText((String) SharedPreferencesUtils.getParam(getApplicationContext(), ACCOUNTID, ""));
        mEtPassword.setText((String) SharedPreferencesUtils.getParam(getApplicationContext(), PASSWORD, ""));
        mEtAccount.setText((String) SharedPreferencesUtils.getParam(getApplicationContext(), ACCOUNTID, ""));
        presenter.sendCode();
    }

    @Override
    public void initView() {

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public LoginBean getLoginBean() {
        return new LoginBean(mEtAccount.getText().toString(), mEtPassword.getText().toString(),
                mEtUserName.getText().toString(), uuid, mEtCode.getText().toString());
    }

    @Override
    public void loginSuccess(UserBean userBean) {
        SharedPreferencesUtils.setParam(getApplicationContext(), USERNAME, mEtUserName.getText().toString());
        SharedPreferencesUtils.setParam(getApplicationContext(), PASSWORD, mEtPassword.getText().toString());
        SharedPreferencesUtils.setParam(getApplicationContext(), ACCOUNTID, mEtAccount.getText().toString());
        SharedPreferencesUtils.setParam(getApplicationContext(), TOKEN, userBean.getData().getAccess_token());
        cleanGoActivity(MainActivity.class);
    }

    @Override
    public void loginFail(UserBean user) {
        Toast.makeText(this, user.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendCodeSuccess(CodeBean codeBean) {
        if (codeBean.getData().getImg() != null) {
            uuid = codeBean.getData().getUuid();
            mIvCode.setImageBitmap(stringToBitmap(codeBean.getData().getImg()));
        }
    }

    @Override
    public void sendFail(CodeBean user) {
//        Toast.makeText(this, user.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @OnClick({R.id.tv_login, R.id.iv_login_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                presenter.login();
//                cleanGoActivity(MainActivity.class);
                break;
            case R.id.iv_login_code:
                presenter.sendCode();
                break;
            default:
        }
    }
}
