package com.codekong.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.codekong.customview.R;

/**
 * Created by szh on 2017/1/24.
 * 百分比图(比例图)
 */

public class PercentageGraph extends View {
    //实心圆的画笔
    private Paint mCirclePaint;
    //外面圆弧的画笔
    private Paint mArcPaint;
    //内部文字的画笔
    private Paint mTextPaint;

    private int mCircleColor;
    private int mArcColor;

    private int mTextColor;
    private float mTextSize;

    //文字内容
    private String mTextContent = "HelloWorld";

    private int mCircleX;
    private int mCircleY;
    private float mRadius;

    private int mViewWidth;
    private int mViewHeight;

    private RectF mArcRectF;
    //开始的角度
    private float mStartAngle;
    //扫过的角度
    private float mSweepAngle;
    private float mArcPaintStrokeWidth;

    public PercentageGraph(Context context) {
        this(context, null);
    }

    public PercentageGraph(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PercentageGraph(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PercentageGraph);
        mCircleColor = ta.getColor(R.styleable.PercentageGraph_innerCircleColor, Color.parseColor("#abcdef"));
        mArcColor = ta.getColor(R.styleable.PercentageGraph_outerCircleColor, Color.parseColor("#fedcba"));
        mArcPaintStrokeWidth = ta.getDimension(R.styleable.PercentageGraph_outerCircleStrokeWidth, 50);
        mStartAngle = ta.getFloat(R.styleable.PercentageGraph_startAngle, 0);
        mSweepAngle = ta.getFloat(R.styleable.PercentageGraph_sweepAngle, 270);
        mTextContent = ta.getString(R.styleable.PercentageGraph_textContent);
        mTextColor = ta.getColor(R.styleable.PercentageGraph_textColor, Color.parseColor("black"));
        mTextSize = ta.getDimension(R.styleable.PercentageGraph_textSize, 30);

        ta.recycle();

        mCirclePaint = new Paint();
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setAntiAlias(true);

        mArcPaint = new Paint();
        mArcPaint.setColor(mArcColor);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
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
        //画出内部的实心圆
        canvas.drawCircle(mCircleX, mCircleY, mRadius, mCirclePaint);
        //绘制外层环
        canvas.drawArc(mArcRectF, mStartAngle, mSweepAngle, false, mArcPaint);
        if (!TextUtils.isEmpty(mTextContent)){
            //绘制最中间的文字
            canvas.drawText(mTextContent, 0, mTextContent.length(),
                    mCircleX, mCircleY, mTextPaint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
        }
        if (mViewHeight == 0) {
            mViewHeight = getMeasuredHeight();
        }
        if (mViewWidth > 0 && mViewHeight > 0) {
            //获得圆心坐标
            mCircleX = getPaddingLeft() + mViewWidth / 2;
            mCircleY = getPaddingTop() + mViewHeight / 2;
            //半径为宽高的较小值除去内边距的0.4倍
            if (mViewHeight >= mViewWidth) {
                mRadius = (float) ((mViewWidth / 2 - getPaddingLeft()) * 0.5);
            } else {
                mRadius = (float) ((mViewHeight / 2 - getPaddingTop()) * 0.5);
            }
        }

        mArcRectF = new RectF((float) (mViewWidth * 0.1), (float) (mViewHeight * 0.1),
                (float) (mViewWidth * 0.9), (float) (mViewHeight * 0.9));
        mArcPaint.setStrokeWidth(mArcPaintStrokeWidth);
    }
}
