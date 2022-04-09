package com.huanxin.workspace.feature.main.work;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huanxin.workspace.data.WorkListBean;

import java.util.List;

public class WorkAdapter extends BaseQuickAdapter<WorkListBean, BaseViewHolder> {

    public WorkAdapter(int layoutResId, List<WorkListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkListBean item) {
//        helper.setText(R.id.tv_call_device_name, item.getMessage())
//                .addOnClickListener(R.id.products_pic);
//        if (item.getImagePath() != null) {
//            Glide.with(mContext).load(FILE_URL + item.getImagePath()).into((ImageView) helper.getView(R.id.products_pic));
//        }
    }
}