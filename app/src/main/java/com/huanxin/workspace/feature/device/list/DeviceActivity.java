package com.huanxin.workspace.feature.device.list;

import static android.Manifest.permission.CAMERA;
import static com.huanxin.workspace.Consts.RC_CAMERA_CONTACTS_PERM;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huanxin.workspace.R;
import com.huanxin.workspace.base.BaseMvpActivity;
import com.huanxin.workspace.data.BindedDeviceBean;
import com.huanxin.workspace.data.UserBean;
import com.huanxin.workspace.feature.device.BindRecordAdapter;
import com.huanxin.workspace.feature.device.bind.DeviceBindActivity;
import com.huanxin.workspace.widget.CustomTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class DeviceActivity extends BaseMvpActivity<DevicePresenter> implements DeviceContract.View {
    @BindView(R.id.ct_device)
    CustomTitleBar mCtDevice;
    @BindView(R.id.rv_device_bind_record)
    RecyclerView mRvDeviceList;

    private BindRecordAdapter bindRecordAdapter;
    private List<BindedDeviceBean> bindedDeviceBeans = new ArrayList<>();
    private static final int SHOW_SUBACTIVITY = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_device;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        mCtDevice.setLeftIconOnClickListener(view -> finish());
        mRvDeviceList.setLayoutManager(new LinearLayoutManager(getContext()));
        bindedDeviceBeans.add(new BindedDeviceBean());
        bindRecordAdapter = new BindRecordAdapter(R.layout.item_binded_device, bindedDeviceBeans);
        mRvDeviceList.setAdapter(bindRecordAdapter);
    }

    @Override
    protected DevicePresenter createPresenter() {
        return null;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void getListSuccess(UserBean userBean) {

    }

    @Override
    public void getListFail(UserBean user) {

    }

    @OnClick({R.id.btn_scan})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.btn_scan) {
            if (checkSelfPermission(CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE}, RC_CAMERA_CONTACTS_PERM);
            } else {
                init();
            }
        }
    }

    @AfterPermissionGranted(RC_CAMERA_CONTACTS_PERM)
    public void init() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
//            Intent intent = new Intent(this, CaptureActivity.class);
//            startActivityForResult(intent, SHOW_SUBACTIVITY);
            Intent intent = new Intent(this, DeviceBindActivity.class);
            intent.putExtra("code"," https://qrservice.keyicloud.cn/qrcode?scene=1498497126920065024-1498903103874183170");
            startActivity(intent);
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_camera), RC_CAMERA_CONTACTS_PERM, Manifest.permission.CAMERA);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
//            Toast.makeText(this, data.getStringExtra("code").split("=")[1], Toast.LENGTH_SHORT).show();
//            Toast.makeText(this, "1498497126920065024-1498903103874183170", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DeviceBindActivity.class);
            intent.putExtra("code"," https://qrservice.keyicloud.cn/qrcode?scene=1498497126920065024-1498903103874183170");
            startActivity(intent);
//            HttpJsonMethod.getInstance().scan(
//                    new ProgressSubscriber(scanOnNext, Main2Activity.this),
//                    (String) SharedPreferencesUtils.getParam(this, "session", ""),
//                    data.getStringExtra("code"), (String) SharedPreferencesUtils.getParam(this, "language", "zh"));
        }
    }
}