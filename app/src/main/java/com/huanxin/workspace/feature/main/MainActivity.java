package com.huanxin.workspace.feature.main;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.huanxin.workspace.Consts.RC_CAMERA_CONTACTS_PERM;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.tabs.TabLayout;
import com.huanxin.workspace.R;
import com.huanxin.workspace.base.BaseMvpActivity;
import com.huanxin.workspace.data.CallListBean;
import com.huanxin.workspace.data.WorkListBean;
import com.huanxin.workspace.feature.device.list.DeviceActivity;
import com.huanxin.workspace.feature.login.LoginActivity;
import com.huanxin.workspace.feature.main.call.CallFragment;
import com.huanxin.workspace.feature.main.work.WorkFragment;
import com.huanxin.workspace.feature.video.tpnspush.TPNSPushSetting;
import com.tencent.imsdk.BaseConstants;
import com.tencent.imsdk.v2.V2TIMCallback;
import com.tencent.imsdk.v2.V2TIMSDKConfig;
import com.tencent.imsdk.v2.V2TIMSDKListener;
import com.tencent.liteav.basic.IntentUtils;
import com.tencent.liteav.debug.GenerateTestUserSig;
import com.tencent.liteav.trtccalling.TUICallingImpl;
import com.tencent.liteav.trtccalling.model.util.BrandUtil;
import com.tencent.liteav.trtccalling.model.util.PermissionUtil;
import com.tencent.qcloud.tuicore.TUILogin;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseMvpActivity<MainPresenter> implements MainContract.View {
    @BindView(R.id.tl_main)
    TabLayout mTlMain;
    @BindView(R.id.vp_main)
    ViewPager mVpMain;
    @BindView(R.id.tv_unreceive_call)
    TextView mTvCall;
    private final String TAG = "MainActivity";
    private static final int PERMISSION_RESULT_CODE = 1100;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        login();
    }

    @Override
    public void initView() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new CallFragment());
        fragments.add(new WorkFragment());
        HomeTitlesPagerAdapter homeTitlesPagerAdapter = new HomeTitlesPagerAdapter(getSupportFragmentManager(), fragments, new String[]{"未接视频来电", "未完成工单"});
        mVpMain.setAdapter(homeTitlesPagerAdapter);
        mVpMain.setOffscreenPageLimit(2);
        mTlMain.setupWithViewPager(mVpMain);
        presenter.getCallList();
        requestPermission();
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @OnClick({R.id.ll_main_video, R.id.ll_main_work, R.id.ll_main_device})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_main_video:
                String time = String.valueOf(System.currentTimeMillis());
//                Intent intent = new Intent(this, VideoCallingActivity.class);
//                intent.putExtra(Consts.ROOM_ID, "1256732");
//                intent.putExtra(Consts.USER_ID, time.substring(time.length() - 8));
//                startActivity(intent);

//                goActivity(VideoCallingActivity.class);

//                String[] userIDs = new String[1];
//                userIDs[0] = "1498497126920065024";
//                TUICallingImpl.sharedInstance(this).call(userIDs, TUICalling.Type.VIDEO);
                break;
            case R.id.ll_main_work:
                break;
            case R.id.ll_main_device:
                goActivity(DeviceActivity.class);
                break;
            default:
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void getCallListSuccess(CallListBean.DataBean callListBean) {
        mTvCall.setText(callListBean.getTotal() + "");
        mTlMain.getTabAt(0).setText(String.format(getString(R.string.main_unreceive_call_num), callListBean.getTotal() + ""));

    }

    @Override
    public void getWorkListSuccess(WorkListBean.DataBean workListBean) {

    }

    //进入主界面,登录成功后注册,注册TPNS离线推送服务;
    //退出或注销登录时,需要进行反注册
    private void registerTPNSPush() {
        TPNSPushSetting.getInstance().init();
        TPNSPushSetting.getInstance().bindUserID(TUILogin.getUserId());
    }

    //反注册TPNS服务
    private void unregisterTPNSPush() {
        TPNSPushSetting.getInstance().unBindUserID(TUILogin.getUserId());
        TPNSPushSetting.getInstance().unInit();
    }

    private void login() {
        // 初始化并登录
        TUICallingImpl.sharedInstance(this);
        V2TIMSDKConfig config = new V2TIMSDKConfig();
        config.setLogLevel(V2TIMSDKConfig.V2TIM_LOG_DEBUG);
        TUILogin.init(this, GenerateTestUserSig.SDKAPPID, null, new V2TIMSDKListener() {

            @Override
            public void onKickedOffline() {
                Log.d(TAG, "You have been kicked off the line. Please login again!");
                ToastUtils.showLong(getString(R.string.trtccalling_user_kicked_offline));
                startLoginActivity();
            }

            @Override
            public void onUserSigExpired() {
                Log.d(TAG, "Your user signature information has expired");
                ToastUtils.showLong(getString(R.string.trtccalling_user_sig_expired));
                startLoginActivity();
            }
        });
//        String userid = getUserId((String) Objects.requireNonNull(SharedPreferencesUtils.getParam(getApplicationContext(), TOKEN, "")));
        String userid = "2222";
        TUILogin.login(userid,
                GenerateTestUserSig.genTestUserSig(userid), new V2TIMCallback() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.d(TAG, "login fail code: " + code + " msg:" + msg);
                        //userSig过期,需要重新登录;userSig具有时效性,具体请查看GenerateTestUserSig.java文件
                        if (BaseConstants.ERR_USER_SIG_EXPIRED == code) {
                            ToastUtils.showLong(R.string.user_sig_expired);
                            startLoginActivity();
                        }
                    }

                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "login onSuccess");
                        //注册TPNS
                        registerTPNSPush();
                    }
                });
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void requestPermission() {
        if (checkSelfPermission(RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{CAMERA, WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RC_CAMERA_CONTACTS_PERM);
        }
        if (!PermissionUtil.mHasPermissionOrHasHinted) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_logo)//设置标题的图片
                    .setTitle("权限申请")//设置对话框的标题
                    .setMessage("需要同意相关权限才能接通后台音视频对话")//设置对话框的内容
                    //设置对话框的按钮
                    .setNegativeButton("取消", (dialog12, which) -> dialog12.dismiss())
                    .setPositiveButton("确定", (dialog1, which) -> checkAndRequestPermission()).create();
            dialog.show();
        }
    }

    /**
     * 申请后台打开应用的权限
     * 不同厂商的权限名称不一致,例如小米:后台弹出界面; 华为:悬浮窗; 其他:锁屏界面弹框控制等.
     */
    private void checkAndRequestPermission() {
        if (!PermissionUtil.hasPermission(this)) {
            //vivo的后台权限界面跳转
            if (BrandUtil.isBrandVivo()) {
                Intent localIntent;
                if (((Build.MODEL.contains("Y85")) && (!Build.MODEL.contains("Y85A")))
                        || (Build.MODEL.contains("vivo Y53L"))) {
                    localIntent = new Intent();
                    localIntent.setClassName("com.vivo.permissionmanager",
                            "com.vivo.permissionmanager.activity.PurviewTabActivity");
                    localIntent.putExtra("packagename", getPackageName());
                    localIntent.putExtra("tabId", "1");
                    IntentUtils.safeStartActivity(this, localIntent);
                } else {
                    localIntent = new Intent();
                    localIntent.setClassName("com.vivo.permissionmanager",
                            "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity");
                    localIntent.setAction("secure.intent.action.softPermissionDetail");
                    localIntent.putExtra("packagename", getPackageName());
                    IntentUtils.safeStartActivity(this, localIntent);
                }
                return;
            } else if (BrandUtil.isBrandXiaoMi()) {
                final Dialog dialog = new Dialog(this, R.style.logoutDialogStyle);
                dialog.setContentView(R.layout.app_show_tip_dialog_confirm);
                TextView tvMessage = dialog.findViewById(R.id.tv_message);
                Button btnOk = dialog.findViewById(R.id.btn_negative);
                tvMessage.setText(R.string.app_permission_hint);
                btnOk.setOnClickListener(v -> dialog.dismiss());
                dialog.setCancelable(false);
                dialog.show();
                //弹出一次提示后,应用未杀死前不再进行提示了
                PermissionUtil.mHasPermissionOrHasHinted = true;
                return;
            }
            //其他厂商
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, PERMISSION_RESULT_CODE);
            }
        } else {
            //已经有权限
            PermissionUtil.mHasPermissionOrHasHinted = true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PERMISSION_RESULT_CODE) {
            if (PermissionUtil.hasPermission(this)) {
                PermissionUtil.mHasPermissionOrHasHinted = true;
            } else {
                PermissionUtil.mHasPermissionOrHasHinted = false;
                ToastUtils.showLong("应用程序处于后台时无法打开视频通话");
            }
        }
    }
}
