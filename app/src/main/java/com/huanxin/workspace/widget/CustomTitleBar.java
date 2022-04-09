package com.huanxin.workspace.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huanxin.workspace.R;


/**
 * @author : yi.chen
 * e-mail : yi.chen@nttdata.com
 * date   : 2019/9/12  13:53
 * desc   :
 * version: 1.0
 */
public class CustomTitleBar extends RelativeLayout {

    private TextView tvBack;
    private TextView tvTitle;
    private TextView tvMore;
    private ImageView ivMore;
    private ImageView ivBack;

    public CustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);
    }

    //初始化视图
    private void initView(final Context context, AttributeSet attributeSet) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_titlebar, this);
        tvBack = inflate.findViewById(R.id.tv_back);
        ivBack = inflate.findViewById(R.id.iv_back);
        tvTitle = inflate.findViewById(R.id.tv_title);
        tvMore = inflate.findViewById(R.id.tv_more);
        ivMore = inflate.findViewById(R.id.iv_more);

        init(context, attributeSet);
    }

    /**
     * 初始化资源文件
     *
     * @param context      the context
     * @param attributeSet the attribute set
     *                     <p>
     *                     title 标题
     *                     leftIcon 左图标
     *                     rightIcon 右边图片
     *                     rightText 右边文字
     *                     titleBarType 标题栏类型,默认为10
     */
//
    public void init(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomTitleBar);
        String title = typedArray.getString(R.styleable.CustomTitleBar_title);
        int leftIcon = typedArray.getResourceId(R.styleable.CustomTitleBar_left_icon, R.mipmap.ic_launcher);
        String leftText = typedArray.getString(R.styleable.CustomTitleBar_left_text);
        int rightIcon = typedArray.getResourceId(R.styleable.CustomTitleBar_right_icon, R.mipmap.ic_launcher);
        String rightText = typedArray.getString(R.styleable.CustomTitleBar_right_text);
        int JUST_LEFT = 10;
        int titleBarType = typedArray.getInt(R.styleable.CustomTitleBar_titlebar_type, JUST_LEFT);
        //赋值进去我们的标题栏
        tvTitle.setText(title);
        tvBack.setText(leftText);
        tvMore.setText(rightText);
        ivMore.setImageResource(rightIcon);

        //可以传入type值,可自定义判断值
        //不传入,默认为10,只显示左边的图标
        //传入11,显示更多图标按钮,隐藏更多 文字
        if (titleBarType == JUST_LEFT) {
            ivMore.setVisibility(View.INVISIBLE);
            tvMore.setVisibility(View.INVISIBLE);
        } else if (titleBarType == 11) {
            tvMore.setVisibility(View.INVISIBLE);
            ivMore.setVisibility(View.VISIBLE);
        } else if (titleBarType == 12) {
            tvMore.setVisibility(View.VISIBLE);
            ivMore.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 左边图片点击事件
     *
     * @param l the l
     */
    public void setLeftIconOnClickListener(OnClickListener l) {
        ivBack.setOnClickListener(l);
    }

    //右边图片点击事件
    public void setRightIconOnClickListener(OnClickListener l) {
        ivMore.setOnClickListener(l);
    }

    //右边文字点击事件
    public void setRightTextOnClickListener(OnClickListener l) {
        tvMore.setOnClickListener(l);
    }    //右边文字点击事件

    public void setTvTitle(String title) {
        tvTitle.setText(title);
    }
}
