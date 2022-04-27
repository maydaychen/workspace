package com.huanxin.workspace.feature.workspace.detail;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huanxin.workspace.R;

import java.util.List;

public class PicAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PicAdapter(int layoutResId, List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
//        helper.setText(R.id.tv_call_device_name, item.getMessage())
//                .addOnClickListener(R.id.products_pic);
//        if (item.getImagePath() != null) {
        Glide.with(mContext).load(item).into((ImageView) helper.getView(R.id.iv_pic));
//        }
    }
}