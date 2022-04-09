package com.huanxin.workspace.feature.device.bind;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.huanxin.workspace.R;
import com.huanxin.workspace.base.BaseMvpActivity;
import com.huanxin.workspace.data.BaseBean;
import com.huanxin.workspace.data.CodeDetailBean;
import com.huanxin.workspace.data.DeviceListBean;
import com.huanxin.workspace.requestBean.BindReuest;
import com.huanxin.workspace.widget.CustomTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DeviceBindActivity extends BaseMvpActivity<BindPresenter> implements BindContract.View {
    @BindView(R.id.ct_device)
    CustomTitleBar mCtDevice;
    @BindView(R.id.iv_device_code)
    ImageView mIvCode;
    @BindView(R.id.tv_device_num)
    TextView mTvNum;
    @BindView(R.id.et_device_name)
    EditText mEtDeviceName;
    @BindView(R.id.tv_device_branch)
    TextView mTvBranch;
    @BindView(R.id.tv_bind_device)
    TextView mTvBind;
    @BindView(R.id.rv_unbind_device)
    RecyclerView mRvDevice;
    private UnbindDeviceAdapter unbindDeviceAdapter;
    private List<DeviceListBean.DataBean> dataBeans = new ArrayList<>();
    private BindReuest bindReuest = new BindReuest();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_bind;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        bindReuest.setQrcodeUrl(getCode());
        unbindDeviceAdapter = new UnbindDeviceAdapter(R.layout.item_unbind_device, dataBeans) {
            int selPosition = -1;
            //临时记录上次选择的位置
            int temp = -1;

            @Override
            protected void convert(BaseViewHolder baseViewHolder, DeviceListBean.DataBean item) {
                super.convert(baseViewHolder, item);
                ((CheckBox) baseViewHolder.getView(R.id.checkBox)).setChecked(baseViewHolder.getLayoutPosition() == selPosition);
                ((CheckBox) baseViewHolder.getView(R.id.checkBox)).setOnCheckedChangeListener((compoundButton, b) -> {
                    dataBeans.get(baseViewHolder.getLayoutPosition()).setChecked(b);
                    if (b) {
                        selPosition = baseViewHolder.getLayoutPosition();
                        bindReuest.setId(Long.parseLong(item.getId()));
                        notifyItemChanged(temp);
                    }
                });

            }
        };

    }

    @Override
    public void initView() {
        mCtDevice.setLeftIconOnClickListener(view -> finish());
        mRvDevice.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvDevice.setAdapter(unbindDeviceAdapter);
        presenter.getCodeDetail();
        RxTextView.textChanges(mEtDeviceName).subscribe(charSequence -> {
            dataBeans.clear();
            unbindDeviceAdapter.notifyDataSetChanged();
            presenter.getDeviceList();
        });
    }

    @Override
    protected BindPresenter createPresenter() {
        return new BindPresenter();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public BindReuest getBindReuest() {
        return bindReuest;
    }

    @Override
    public String getKey() {
        return mEtDeviceName.getText().toString();
    }

    @Override
    public String getCode() {
        return getIntent().getStringExtra("code");
    }

    @Override
    public void getCodeDetailSuccess(CodeDetailBean.DataBean dataBean) {
        mTvNum.setText(dataBean.getTenantId());
        mTvBranch.setText(dataBean.getBatchId());
        if (dataBean.getFilePath() != null) {
            Glide.with(this).load(dataBean.getFilePath()).into(mIvCode);
        }
    }

    @Override
    public void getDeviceListSuccess(List<DeviceListBean.DataBean> dataBeanList) {
        dataBeans.addAll(dataBeanList);
        unbindDeviceAdapter.notifyDataSetChanged();
        mTvBind.setVisibility(dataBeanList.size() > 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public void bindSuccess(BaseBean baseBean) {
        Toast.makeText(this, baseBean.getMsg(), Toast.LENGTH_SHORT).show();
        finish();
    }

    @OnClick({R.id.tv_bind_device})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.tv_bind_device) {
            presenter.bindDevice();
        }
    }
}