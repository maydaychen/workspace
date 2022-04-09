package com.huanxin.workspace.feature.device;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huanxin.workspace.data.BindedDeviceBean;

import java.util.List;

public class BindRecordAdapter extends BaseQuickAdapter<BindedDeviceBean, BaseViewHolder> {

    public BindRecordAdapter(int layoutResId, List<BindedDeviceBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BindedDeviceBean item) {
//        helper.setText(R.id.tv_call_device_name, item.getMessage())
//                .addOnClickListener(R.id.products_pic);
//        if (item.getImagePath() != null) {
//            Glide.with(mContext).load(FILE_URL + item.getImagePath()).into((ImageView) helper.getView(R.id.products_pic));
//        }
    }
}