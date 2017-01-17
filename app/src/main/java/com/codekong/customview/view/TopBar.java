package com.codekong.customview.view;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.codekong.customview.R;

/**
 * Created by szh on 2017/1/17.
 */


public class TopBar extends RelativeLayout {
    private TopbarClickListener mTopbarClickListener;

    private String mTitle;
    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mLeftText;
    private float mLeftTextSize;
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mRightText;
    private float mRightTextSize;
    private int mRightTextColor;
    private Drawable mRightBackground;

    private Button mLeftButton;
    private Button mRightButton;
    private TextView mTitleView;

    private LayoutParams mLeftParams;
    private LayoutParams mRightParams;
    private LayoutParams mTitleParams;

    public TopBar(Context context) {
        this(context, null);
    }

    public TopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        mTitle = typedArray.getString(R.styleable.TopBar_title);
        mTitleTextSize = typedArray.getDimensionPixelSize(R.styleable.TopBar_titleTextSize, 15);
        mTitleTextColor = typedArray.getColor(R.styleable.TopBar_titleTextColor, Color.parseColor("#000000"));

        mLeftText = typedArray.getString(R.styleable.TopBar_leftText);
        mLeftTextSize = typedArray.getDimensionPixelSize(R.styleable.TopBar_leftTextSize, 15);
        mLeftTextColor = typedArray.getColor(R.styleable.TopBar_leftTextColor, Color.parseColor("#000000"));
        mLeftBackground = typedArray.getDrawable(R.styleable.TopBar_leftBackground);

        mRightText = typedArray.getString(R.styleable.TopBar_rightText);
        mRightTextSize = typedArray.getDimensionPixelSize(R.styleable.TopBar_rightTextSize, 15);
        mRightTextColor = typedArray.getColor(R.styleable.TopBar_rightTextColor, Color.parseColor("#000000"));
        mRightBackground = typedArray.getDrawable(R.styleable.TopBar_rightBackground);

        typedArray.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        mLeftButton.setText(mLeftText);
        mLeftButton.setTextSize(mLeftTextSize);
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);


        mRightButton.setText(mRightText);
        mRightButton.setTextSize(mRightTextSize);
        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);

        mTitleView.setText(mTitle);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setGravity(Gravity.CENTER);

        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(ALIGN_PARENT_LEFT, TRUE);
        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mRightParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton, mRightParams);

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(CENTER_IN_PARENT, TRUE);
        addView(mTitleView, mTitleParams);

        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopbarClickListener != null){
                    mTopbarClickListener.leftClick(v);
                }
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTopbarClickListener != null){
                    mTopbarClickListener.rightClick(v);
                }
            }
        });
    }

    public interface TopbarClickListener{
        void leftClick(View v);
        void rightClick(View v);
    }

    public void setTopbarClickListener(TopbarClickListener topbarClickListener){
        this.mTopbarClickListener = topbarClickListener;
    }

    public void setBtnVisibility(int id, boolean flag){
        if (flag){
            if (id == 0){
                mLeftButton.setVisibility(View.VISIBLE);
            }else if (id == 1){
                mRightButton.setVisibility(View.VISIBLE);
            }
        }else{
            if (id == 0){
                mLeftButton.setVisibility(View.GONE);
            }else if (id == 1){
                mRightButton.setVisibility(View.GONE);
            }
        }
    }
}
