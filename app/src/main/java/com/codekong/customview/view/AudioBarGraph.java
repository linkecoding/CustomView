package com.codekong.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.codekong.customview.R;

/**
 * Created by szh on 2017/1/24.
 * 音频条形图
 */

public class AudioBarGraph extends View {
    //渐变色顶部颜色
    private int mTopColor;
    //渐变色底部颜色
    private int mBottomColor;
    //View重绘延时时间
    private int mDelayTime;

    //小矩形的数目
    private int mRectCount;
    //每个小矩形的宽度
    private int mRectWidth;
    //每个小矩形的高度
    private int mRectHeight;
    //每个小矩形之间的偏移量
    private float mOffset;
    //绘制小矩形的画笔
    private Paint mPaint;
    //View的宽度
    private int mViewWidth;
    //产生渐变效果
    private LinearGradient mLinearGradient;
    //每个小矩形的当前高度
    private float[] mCurrentHeight;
    public AudioBarGraph(Context context) {
        this(context, null);
    }

    public AudioBarGraph(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AudioBarGraph(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mOffset = 5;
        mRectCount = 10;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.AudioBarGraph);
        mRectCount = ta.getInt(R.styleable.AudioBarGraph_rectCount, 10);
        mOffset = ta.getFloat(R.styleable.AudioBarGraph_rectOffset, 3);
        mDelayTime = ta.getInt(R.styleable.AudioBarGraph_delayTime, 300);
        mTopColor = ta.getColor(R.styleable.AudioBarGraph_topColor, Color.YELLOW);
        mBottomColor = ta.getColor(R.styleable.AudioBarGraph_bottomColor, Color.BLUE);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width = 200;
        int height = 200;

        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width,
                heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCurrentHeight != null){
            for (int i = 0; i < mRectCount; i++) {
                int random = (int) (Math.random() * 50);
                canvas.drawRect((float) (mViewWidth * 0.4 / 2 + mRectWidth * i + mOffset), mCurrentHeight[i] + random,
                        (float) (mViewWidth * 0.4 / 2 + mRectWidth * (i + 1)), mRectHeight, mPaint);
            }
        }else{
            for (int i = 0; i < mRectCount; i++) {
                int currentHeight = 0;
                canvas.drawRect((float) (mViewWidth * 0.4 / 2 + mRectWidth * i + mOffset), currentHeight,
                        (float) (mViewWidth * 0.4 / 2 + mRectWidth * (i + 1)), mRectHeight, mPaint);
            }
        }
        postInvalidateDelayed(mDelayTime);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = getMeasuredWidth();
        mRectHeight = getMeasuredHeight();
        mRectWidth = (int) (mViewWidth * 0.6 / mRectCount);
        mLinearGradient = new LinearGradient(0, 0, mRectWidth, mRectHeight,
                mTopColor, mBottomColor, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
    }

    public void setCurrentHeight(float[] currentHeight){
        mCurrentHeight = currentHeight;
    }
}
