package com.jacky.option.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * 有宽高比的ImageView
 */
public class RatioImageView extends android.support.v7.widget.AppCompatImageView {
    // 默认宽高比例
    private float ratio = 1.2f; // 比例值

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public RatioImageView(Context context) {
        super(context);
    }

    public RatioImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // 参数1 命名控件 参数2 属性的名字 参数3 默认的值
        float r = attrs.getAttributeFloatValue(
                "http://schemas.android.com/apk/res-auto",
                "ratio_iv", ratio);
        setRatio(r);
    }

    public RatioImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // 测量当前布局
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // widthMeasureSpec 宽度的规则 包含了两部分 模式 值
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec); // 模式
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);// 宽度大小
        int width = widthSize - getPaddingLeft() - getPaddingRight();// 去掉左右两边的padding

        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec); // 模式
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);// 高度大小
        int height = heightSize - getPaddingTop() - getPaddingBottom();// 去掉上下两边的padding

        if (widthMode == View.MeasureSpec.EXACTLY
                && heightMode != View.MeasureSpec.EXACTLY) {
            // 修正一下 高度的值 让高度=宽度/比例
            height = (int) (width / ratio + 0.5f); // 保证4舍五入
        } else if (widthMode != View.MeasureSpec.EXACTLY
                && heightMode == View.MeasureSpec.EXACTLY) {
            // 由于高度是精确的值 ,宽度随着高度的变化而变化
            width = (int) ((height * ratio) + 0.5f);
        }

        // 重新制作了新的规则
        widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(width + getPaddingLeft() + getPaddingRight(),
                View.MeasureSpec.EXACTLY);
        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height + getPaddingTop() + getPaddingBottom(),
                View.MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
