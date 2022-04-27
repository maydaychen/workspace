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

public class HandleResult extends LinearLayout {

    private TextView tvResult;
    private TextView tvRemark;
    private TextView tvTitle;
    private PicAdapter picAdapter;
    List<String> picList = new ArrayList<>();

    public HandleResult(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);
    }

    //初始化视图
    private void initView(final Context context, AttributeSet attributeSet) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_handle_result, this);
        tvResult = inflate.findViewById(R.id.tv_handle_result);
        tvRemark = inflate.findViewById(R.id.tv_handle_remark);
        tvTitle = inflate.findViewById(R.id.tv_result_title);
        RecyclerView rvPic = inflate.findViewById(R.id.rv_handle_pic);
        rvPic.setLayoutManager(new GridLayoutManager(getContext(), 3));
        picAdapter = new PicAdapter(R.layout.item_pic, picList);
        rvPic.setAdapter(picAdapter);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setResult(String result) {
        tvResult.setText(result);
    }

    public void setRemark(String remark) {
        tvRemark.setText(remark);
    }

    public void setPicList(List<String> list) {
        picList.addAll(list);
        picAdapter.notifyDataSetChanged();
    }
}
