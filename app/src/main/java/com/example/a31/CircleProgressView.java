package com.example.a31;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Create by 张瀛煜 on 2020-04-17 ：）
 */
public class CircleProgressView extends View {
    private int mMeasureHeight;
    private int mMeasureWidth;

    private Paint mCirclePaint;
    private float mCircleXY;
    private float mRadius;

    private Paint mArcPaint;
    private RectF mArcRectF;
    private float mSweepAngel;
    private float mSweepValue = 66;

    private Paint mTextPaint;
    private String mShowText;
    private float mShowTextSize;

    public CircleProgressView(Context context) {
        super(context);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);
        mMeasureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mMeasureWidth,mMeasureHeight);
        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆
        canvas.drawCircle(mCircleXY,mCircleXY,mRadius,mCirclePaint);
        //绘制弧线
        canvas.drawArc(mArcRectF,270,mSweepAngel,false,mArcPaint);
        //绘制文字
        canvas.drawText(mShowText,0,mShowText.length()
                ,mCircleXY,mCircleXY+(mShowTextSize/4),mTextPaint);

    }

    private void initView() {
        float length = 0 ;
        if (mMeasureHeight>=mMeasureWidth){
            length = mMeasureWidth;
        }else{
            length = mMeasureHeight;
        }
        mCircleXY = length/2;
        mRadius = (float) (length * 0.5/2);
        


    }
}
