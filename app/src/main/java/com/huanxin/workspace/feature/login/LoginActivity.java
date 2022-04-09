package com.huanxin.workspace.feature.login;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.huanxin.workspace.Consts.ACCOUNTID;
import static com.huanxin.workspace.Consts.PASSWORD;
import static com.huanxin.workspace.Consts.RC_CAMERA_CONTACTS_PERM;
import static com.huanxin.workspace.Consts.TOKEN;
import static com.huanxin.workspace.Consts.USERNAME;
import static com.huanxin.workspace.util.Base64Util.stringToBitmap;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.huanxin.workspace.R;
import com.huanxin.workspace.base.BaseMvpActivity;
import com.huanxin.workspace.data.CodeBean;
import com.huanxin.workspace.data.UserBean;
import com.huanxin.workspace.feature.main.MainActivity;
import com.huanxin.workspace.requestBean.LoginBean;
import com.huanxin.workspace.util.SharedPreferencesUtils;
import com.tencent.imsdk.v2.V2TIMCallback;
import com.tencent.imsdk.v2.V2TIMManager;
import com.tencent.imsdk.v2.V2TIMSDKConfig;
import com.tencent.imsdk.v2.V2TIMSDKListener;
import com.tencent.imsdk.v2.V2TIMUserFullInfo;
import com.tencent.imsdk.v2.V2TIMValueCallback;
import com.tencent.liteav.basic.UserModel;
import com.tencent.liteav.basic.UserModelManager;
import com.tencent.liteav.debug.GenerateTestUserSig;
import com.tencent.qcloud.tuicore.TUILogin;

import java.util.ArrayList;
import java.util.List;

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
        if (checkSelfPermission(RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{CAMERA, WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RC_CAMERA_CONTACTS_PERM);
        }
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
        V2TIMSDKConfig config = new V2TIMSDKConfig();
        config.setLogLevel(V2TIMSDKConfig.V2TIM_LOG_DEBUG);
        int id = GenerateTestUserSig.SDKAPPID;
        TUILogin.init(this, id, null, new V2TIMSDKListener() {

            @Override
            public void onKickedOffline() {

            }

            @Override
            public void onUserSigExpired() {

            }
        });
//        String userId = getUserId(userBean.getData().getAccess_token());
        String userId = "2222";
        TUILogin.login(userId,
                GenerateTestUserSig.genTestUserSig(userId),
                new V2TIMCallback() {
                    @Override
                    public void onError(int code, String msg) {
                        ToastUtils.showLong(R.string.app_toast_login_fail, code, msg);
                        Log.d("TRTC", "login fail code: " + code + " msg:" + msg);
                    }

                    @Override
                    public void onSuccess() {
                        Log.d("TRTC", "login onSuccess");
                        getUserInfo(userId);
                    }
                });
    }

    private void getUserInfo(String userId) {
        final UserModelManager manager = UserModelManager.getInstance();
        final UserModel userModel = manager.getUserModel();
        //先查询用户是否存在
        List<String> userIdList = new ArrayList<>();
        userIdList.add(userId);
        Log.d("TRTC", "setUserInfo: userIdList = " + userIdList);
        V2TIMManager.getInstance().getUsersInfo(userIdList,
                new V2TIMValueCallback<List<V2TIMUserFullInfo>>() {

                    @Override
                    public void onError(int code, String msg) {
                        Log.e("TRTC", "get group info list fail, code:" + code + " msg: " + msg);
                    }

                    @Override
                    public void onSuccess(List<V2TIMUserFullInfo> resultList) {
                        if (resultList == null || resultList.isEmpty()) {
                            return;
                        }
                        V2TIMUserFullInfo result = resultList.get(0);
                        Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                        cleanGoActivity(MainActivity.class);
                    }
                });
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
