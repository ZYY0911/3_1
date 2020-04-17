package com.example.a31;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Create by 张瀛煜 on 2020-04-17 ：）
 */
public class MyTextView extends android.support.v7.widget.AppCompatTextView {


    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(),paint);
        paint.setColor(Color.YELLOW);
        canvas.drawRect(10,10,getMeasuredWidth()-10,getMeasuredHeight()-10,paint);
        paint.setColor(Color.MAGENTA);
        canvas.drawRect(20,20,getMeasuredWidth()-20,getMeasuredHeight()-20,paint);
        canvas.save();
        canvas.translate(10,0);
        super.onDraw(canvas);
        canvas.restore();
        if (matrix!=null){
            myTranslate +=mViewWith/5;
            if (myTranslate>2*mViewWith){
                myTranslate = -mViewWith;
            }
            matrix.setTranslate(myTranslate,0);
            linearGradient.setLocalMatrix(matrix);
            postInvalidateDelayed(10);
        }
    }
    int myTranslate = 0;
    int mViewWith = 0;
    LinearGradient linearGradient;
    Matrix matrix;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWith==0){
            mViewWith = getMeasuredWidth();
            if (mViewWith>0){
                linearGradient = new LinearGradient(0,0,mViewWith,0
                        ,new int[]{Color.WHITE,Color.BLACK,Color.GREEN}
                        ,null, Shader.TileMode.CLAMP);
                getPaint().setShader(linearGradient);
                matrix = new Matrix();
            }
        }
    }
}
