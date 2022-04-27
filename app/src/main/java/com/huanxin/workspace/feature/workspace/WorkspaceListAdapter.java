package com.huanxin.workspace.feature.workspace;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.huanxin.workspace.R;
import com.huanxin.workspace.data.WorkspaceListBean;

import java.util.List;

public class WorkspaceListAdapter extends BaseQuickAdapter<WorkspaceListBean.DataBean.ItemsBean, BaseViewHolder> {

    public WorkspaceListAdapter(int layoutResId, List<WorkspaceListBean.DataBean.ItemsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkspaceListBean.DataBean.ItemsBean item) {
        helper.setText(R.id.tv_work_type, item.getTicketType())
                .setText(R.id.tv_work_desc, item.getTicketContent())
                .setText(R.id.tv_work_phone, item.getReporterPhone());
        if (item.getDeviceImage() != null) {
            Glide.with(mContext).load(item.getDeviceImage()).into((ImageView) helper.getView(R.id.iv_main_work));
        }
    }
}