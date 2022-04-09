package com.huanxin.workspace.feature.device.bind;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huanxin.workspace.R;
import com.huanxin.workspace.data.DeviceListBean;

import java.util.List;

public class UnbindDeviceAdapter extends BaseQuickAdapter<DeviceListBean.DataBean, BaseViewHolder> {

    public UnbindDeviceAdapter(int layoutResId, List<DeviceListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeviceListBean.DataBean item) {
        helper.setText(R.id.tv_device_name, item.getTenantId())
                .setText(R.id.tv_device_desc, item.getProductName());
//                .addOnClickListener(R.id.products_pic);
        if (item.getBatteryImage() != null) {
            Glide.with(mContext).load(item.getBatteryImage()).into((ImageView) helper.getView(R.id.iv_device_logo));
        }
    }
}