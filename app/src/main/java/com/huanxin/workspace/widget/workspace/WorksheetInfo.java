package com.huanxin.workspace.widget.workspace;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.huanxin.workspace.R;
import com.huanxin.workspace.feature.workspace.detail.PicAdapter;

import java.util.ArrayList;
import java.util.List;

public class WorksheetInfo extends LinearLayout {
    private TextView tvTitle;
    private TextView tvCreator;
    private TextView tvName;
    private TextView tvTime;
    private TextView tvType;
    private TextView tvDesc;
    private RecyclerView rvPic;
    private PicAdapter picAdapter;
    List<String> picList = new ArrayList<>();

    public WorksheetInfo(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);
    }

    //初始化视图
    private void initView(final Context context, AttributeSet attributeSet) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_worksheet_info, this);
        tvTitle = inflate.findViewById(R.id.tv_worksheet_title);
        tvCreator = inflate.findViewById(R.id.tv_worksheet_creator);
        tvName = inflate.findViewById(R.id.tv_worksheet_client);
        tvTime = inflate.findViewById(R.id.tv_worksheet_time);
        tvType = inflate.findViewById(R.id.tv_worksheet_type);
        tvDesc = inflate.findViewById(R.id.tv_worksheet_desc);
        rvPic = inflate.findViewById(R.id.rv_worksheet_pic);
        rvPic.setLayoutManager(new GridLayoutManager(getContext(), 3));
        picAdapter = new PicAdapter(R.layout.item_pic, picList);
        rvPic.setAdapter(picAdapter);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setCreator(String creator) {
        tvCreator.setText(creator);
    }

    public void setName(String name) {
        tvName.setText(name);
    }

    public void setTime(String time) {
        tvTime.setText(time);
    }

    public void setType(String type) {
        tvType.setText(type);
    }

    public void setDesc(String desc) {
        tvDesc.setText(desc);
    }

    public void setPicList(List<String> list) {
        picList.addAll(list);
        picAdapter.notifyDataSetChanged();
    }
}
