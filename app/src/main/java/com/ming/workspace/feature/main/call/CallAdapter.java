package com.ming.workspace.feature.main.call;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ming.workspace.R;
import com.ming.workspace.data.CallListBean;

import java.util.List;

public class CallAdapter extends BaseQuickAdapter<CallListBean.DataBean.RecordsBean, BaseViewHolder> {

    public CallAdapter(int layoutResId, List<CallListBean.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CallListBean.DataBean.RecordsBean item) {
        helper.setText(R.id.tv_useless_number, item.getUserPhone())
                .setText(R.id.tv_call_time, item.getCreateTime())
                .setText(R.id.tv_call_device_name, item.getDeviceName())
                .setText(R.id.tv_call_device_number, item.getDeviceId())
                .setText(R.id.tv_call_time2, item.getCreateTime());
//                .addOnClickListener(R.id.products_pic);
//        if (item.getImagePath() != null) {
//            Glide.with(mContext).load(FILE_URL + item.getImagePath()).into((ImageView) helper.getView(R.id.products_pic));
//        }
    }
}