package com.codekong.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by szh on 2017/1/17.
 */

public class TextFlashingTextView extends TextView{
    private int mViewWidth;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mTranslate;
    private int middleColor = 0xffffff;

    public TextFlashingTextView(Context context) {
        this(context, null);
    }

    public TextFlashingTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextFlashingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mGradientMatrix != null){
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2 * mViewWidth){
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (mViewWidth == 0){
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0){
                Paint mPaint = getPaint();
                int currentTextColor = getCurrentTextColor();
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0,
                        new int[]{currentTextColor, middleColor, currentTextColor}, null,
                        Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    public void setMiddleColor(int middleColor){
        this.middleColor = middleColor;
    }
}
