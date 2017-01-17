package com.codekong.customview.view;

import android.app.ActionBar;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
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
        mTitleTextSize = typedArray.getDimension(R.styleable.TopBar_titleTextSize, 10f);
        mTitleTextColor = typedArray.getColor(R.styleable.TopBar_titleTextColor, 0);

        mLeftText = typedArray.getString(R.styleable.TopBar_leftText);
        mLeftTextSize = typedArray.getDimension(R.styleable.TopBar_leftTextSize, 10f);
        mLeftTextColor = typedArray.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = typedArray.getDrawable(R.styleable.TopBar_leftBackground);

        mRightText = typedArray.getString(R.styleable.TopBar_rightText);
        mRightTextSize = typedArray.getDimension(R.styleable.TopBar_rightTextSize, 10f);
        mRightTextColor = typedArray.getColor(R.styleable.TopBar_rightTextColor, 0);
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
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
