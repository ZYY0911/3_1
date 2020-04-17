package com.example.a31;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Create by 张瀛煜 on 2020-04-17 ：）
 */
public class TopBar extends RelativeLayout {

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs,defStyleAttr);
    }

    private int mLeftTextColor, mRightTextColor, mTitletextColor;
    private Drawable mLeftBackground, mRightBackground;
    private String mLeftText, mRightText, mTitle;
    private float mTitleTextSize;


    // 包含topbar上的元素：左按钮、右按钮、标题
    private Button mLeftButton, mRightButton;
    private TextView mTitleView;

    private LayoutParams mLeftParams, mRightParams, mTitleParams;

    private topbarClickListenter mListener;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs );
        setBackgroundColor(0xFFF59563);
        // 通过这个方法，将你在atts.xml中定义的declare-styleable
        // 的所有属性的值存储到TypedArray中
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        // 从TypedArray中取出对应的值来为要设置的属性赋值
        mLeftTextColor = typedArray.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = typedArray.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = typedArray.getString(R.styleable.TopBar_leftText);

        mRightTextColor = typedArray.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = typedArray.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = typedArray.getString(R.styleable.TopBar_rightText);

        mTitle = typedArray.getString(R.styleable.TopBar_title);
        mTitletextColor = typedArray.getColor(R.styleable.TopBar_titleTextColor, 0);
        mTitleTextSize = typedArray.getDimension(R.styleable.TopBar_titleTextSize, 10);

        // 获取完TypedArray的值后，一般要调用
        // recycle方法来避免重新创建的时候的错误
        typedArray.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        // 为创建的组件元素赋值
        // 值就来源于我们在引用的xml文件中给对应属性的赋值
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitletextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT
                , LayoutParams.WRAP_CONTENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        //添加到ViewGroup
        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT
                , LayoutParams.WRAP_CONTENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        //添加到ViewGroup
        addView(mRightButton, mRightParams);

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT
                , LayoutParams.WRAP_CONTENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        //添加到ViewGroup
        addView(mTitleView, mTitleParams);


        // 按钮的点击事件，不需要具体的实现，
        // 只需调用接口的方法，回调的时候，会有具体的实现
        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });

        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });
    }

    // 暴露一个方法给调用者来注册接口回调
    // 通过接口来获得回调者对接口方法的实现
    public void setOnTopbarClickListener(topbarClickListenter mListener){
        this.mListener = mListener;
    }

    /**
     * 设置按钮是否显示 通过id区分按钮 flag区分是否显示
     * @param id id
     * @param flag 是否显示
     */
    public void setButtonVisable(int id,boolean flag){
        if (flag){
            if (id==0){
                mLeftButton.setVisibility(VISIBLE);
            }else {
                mRightButton.setVisibility(VISIBLE);
            }
        }else{
            if (id==0){
                mLeftButton.setVisibility(GONE);
            }else{
                mRightButton.setVisibility(GONE);
            }
        }
    }

    public interface topbarClickListenter {
        void leftClick();

        void rightClick();
    }

}
