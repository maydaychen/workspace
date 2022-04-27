package com.huanxin.workspace.widget.workspace;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huanxin.workspace.R;

public class DeviceInfo extends LinearLayout {

    private TextView tvName;
    private TextView tvSubTitle;
    private TextView tvTitle;
    private TextView tvOwner;
    private ImageView ivLogo;
    private TextView tvAddress;

    public DeviceInfo(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);
    }

    //初始化视图
    private void initView(final Context context, AttributeSet attributeSet) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_device_info, this);
        tvSubTitle = inflate.findViewById(R.id.tv_device_sub_title);
        tvName = inflate.findViewById(R.id.tv_device_name);
        tvOwner = inflate.findViewById(R.id.tv_device_owner);
        tvTitle = inflate.findViewById(R.id.tv_device_title);
        tvAddress = inflate.findViewById(R.id.tv_device_address);
        ivLogo = inflate.findViewById(R.id.iv_device_logo);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setSubTitle(String subtitle) {
        tvSubTitle.setText(subtitle);
    }

    public void setName(String name) {
        tvName.setText(name);
    }

    public void setOwner(String owner) {
        tvOwner.setText(owner);
    }

    public void setAddress(String address) {
        tvAddress.setText(address);
    }

    public void setLogo(String url) {
        Glide.with(getContext()).load(url).into(ivLogo);
    }
}
