package com.huanxin.workspace.feature.main;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.huanxin.workspace.Consts.LOGED;
import static com.huanxin.workspace.Consts.RC_CAMERA_CONTACTS_PERM;
import static com.huanxin.workspace.Consts.TOKEN;
import static com.huanxin.workspace.util.pageUtils.getUserId;

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
import com.huanxin.workspace.feature.call.CallActivity;
import com.huanxin.workspace.feature.device.list.DeviceActivity;
import com.huanxin.workspace.feature.login.LoginActivity;
import com.huanxin.workspace.feature.main.call.CallFragment;
import com.huanxin.workspace.feature.main.work.WorkFragment;
import com.huanxin.workspace.feature.video.tpnspush.TPNSPushSetting;
import com.huanxin.workspace.feature.workspace.WorkspaceActivity;
import com.huanxin.workspace.util.SharedPreferencesUtils;
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
import java.util.Objects;

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
        HomeTitlesPagerAdapter homeTitlesPagerAdapter = new HomeTitlesPagerAdapter(getSupportFragmentManager(), fragments, new String[]{"??????????????????", "???????????????"});
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
                goActivity(CallActivity.class);

//                String time = String.valueOf(System.currentTimeMillis());
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
                goActivity(WorkspaceActivity.class);
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
        // todo ?????????????????????????????????????????????
        mTlMain.getTabAt(0).setText(String.format(getString(R.string.main_unreceive_call_num), callListBean.getTotal() + ""));
    }

    @Override
    public void getWorkListSuccess(WorkListBean.DataBean workListBean) {

    }

    //???????????????,?????????????????????,??????TPNS??????????????????;
    //????????????????????????,?????????????????????
    private void registerTPNSPush() {
        TPNSPushSetting.getInstance().init();
        TPNSPushSetting.getInstance().bindUserID(TUILogin.getUserId());
    }

    //?????????TPNS??????
    private void unregisterTPNSPush() {
        TPNSPushSetting.getInstance().unBindUserID(TUILogin.getUserId());
        TPNSPushSetting.getInstance().unInit();
    }

    private void login() {
        // ??????????????????
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
        String userid = getUserId((String) Objects.requireNonNull(SharedPreferencesUtils.getParam(getApplicationContext(), TOKEN, "")));
        TUILogin.login(userid,
                GenerateTestUserSig.genTestUserSig(userid), new V2TIMCallback() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.d(TAG, "login fail code: " + code + " msg:" + msg);
                        //userSig??????,??????????????????;userSig???????????????,???????????????GenerateTestUserSig.java??????
                        if (BaseConstants.ERR_USER_SIG_EXPIRED == code) {
                            ToastUtils.showLong(R.string.user_sig_expired);
                            startLoginActivity();
                        }
                    }

                    @Override
                    public void onSuccess() {
                        Log.d(TAG, "login onSuccess");
                        //??????TPNS
                        registerTPNSPush();
                    }
                });
    }

    private void startLoginActivity() {
        SharedPreferencesUtils.setParam(getApplicationContext(), LOGED, false);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void requestPermission() {
        if (checkSelfPermission(RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{CAMERA, WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RC_CAMERA_CONTACTS_PERM);
        }
        if (!PermissionUtil.mHasPermissionOrHasHinted) {
            checkAndRequestPermission();
        }
    }

    /**
     * ?????????????????????????????????
     * ????????????????????????????????????,????????????:??????????????????; ??????:?????????; ??????:???????????????????????????.
     */
    private void checkAndRequestPermission() {
        if (!PermissionUtil.hasPermission(this)) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setIcon(R.drawable.ic_logo)//?????????????????????
                    .setTitle("????????????")//????????????????????????
                    .setMessage("?????????????????????????????????????????????????????????")//????????????????????????
                    //????????????????????????
                    .setNegativeButton("??????", (dialog12, which) -> dialog12.dismiss())
                    .setPositiveButton("??????", (dialog1, which) -> {
                        //vivo???????????????????????????
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
                            final Dialog midialog = new Dialog(this, R.style.logoutDialogStyle);
                            midialog.setContentView(R.layout.app_show_tip_dialog_confirm);
                            TextView tvMessage = midialog.findViewById(R.id.tv_message);
                            Button btnOk = midialog.findViewById(R.id.btn_negative);
                            tvMessage.setText(R.string.app_permission_hint);
                            btnOk.setOnClickListener(v -> midialog.dismiss());
                            midialog.setCancelable(false);
                            midialog.show();
                            //?????????????????????,???????????????????????????????????????
                            PermissionUtil.mHasPermissionOrHasHinted = true;
                            return;
                        }
                        //????????????
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            startActivityForResult(intent, PERMISSION_RESULT_CODE);
                        }
                    }).create();
            dialog.show();
        } else {
            //???????????????
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
                ToastUtils.showLong("???????????????????????????????????????????????????");
            }
        }
    }
}
