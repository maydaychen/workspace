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

public class PeopleInfo extends LinearLayout {

    private TextView tvTitle;
    private TextView tvTelephont;
    private TextView tvName;
    private ImageView ivEngineerLogo;

    public PeopleInfo(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);
    }

    //初始化视图
    private void initView(final Context context, AttributeSet attributeSet) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_engineer_info, this);
        tvTitle = inflate.findViewById(R.id.tv_engineer_title);
        tvTelephont = inflate.findViewById(R.id.tv_engineer_telephone);
        tvName = inflate.findViewById(R.id.tv_engineer_name);
        ivEngineerLogo = inflate.findViewById(R.id.iv_engineer_logo);
    }

    public void setTvTitle(String title) {
        tvTitle.setText(title);
    }

    public void setTvTelephont(String title) {
        tvTelephont.setText(title);
    }

    public void setTvName(String title) {
        tvName.setText(title);
    }

    public void setPic(String url) {
        Glide.with(getContext()).load(url).into(ivEngineerLogo);
    }
}
