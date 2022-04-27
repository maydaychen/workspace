package com.huanxin.workspace.feature.call;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huanxin.workspace.R;
import com.huanxin.workspace.data.CallBean;

import java.util.List;


public class CallAdapter extends BaseQuickAdapter<CallBean.DataBean.ItemsBean, BaseViewHolder> {

    public CallAdapter(int layoutResId, List<CallBean.DataBean.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CallBean.DataBean.ItemsBean item) {
        helper.setText(R.id.tv_useless_number, item.getUserPhone())
                .setText(R.id.tv_call_time, item.getCreateTime())
                .setText(R.id.tv_call_device_name, item.getDeviceName())
                .setText(R.id.tv_call_device_number, item.getDeviceId())
                .setText(R.id.tv_call_time2, item.getCreateTime())
                .setGone(R.id.item_bt_create_space,  item.getWorkTicketId() == null)
                .setGone(R.id.item_bt_check_space, item.getState() == 1 && item.getWorkTicketId() != null)
                .addOnClickListener(R.id.tv_useless_number)
                .addOnClickListener(R.id.item_bt_create_space)
                .addOnClickListener(R.id.item_bt_check_space);
        switch (item.getState()) {
            case 0:
                helper.setText(R.id.tv_call_status, "发起通话");
                break;
            case 1:
                helper.setText(R.id.tv_call_status, "已接通");
                break;
            case -1:
                helper.setText(R.id.tv_call_status, "未接通 ");
                break;
        }
//        if (item.getImagePath() != null) {
//            Glide.with(mContext).load(FILE_URL + item.getImagePath()).into((ImageView) helper.getView(R.id.products_pic));
//        }
    }
}