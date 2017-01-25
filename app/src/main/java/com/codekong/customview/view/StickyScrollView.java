package com.codekong.customview.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by szh on 2017/1/25.
 * 有黏性的ScrollView
 */

public class StickyScrollView extends ViewGroup {
    //屏幕高度
    private int mScreenHeight;
    //上一次滑动的y坐标
    private float mLastY;
    //开始的坐标
    private float mStartY;
    //结束的坐标
    private float mEndY;
    //滚动协助
    private Scroller mScroller;

    public StickyScrollView(Context context) {
        this(context, null);
    }

    public StickyScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScreenHeight = getScreenHeight((Activity) context);
        mScroller = new Scroller(context);

    }

    /**
     * 得到屏幕的高度
     * @param activity
     * @return
     */
    public int getScreenHeight(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        //通知子View测量自身
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        //每个子View占一个屏幕
        mlp.height = mScreenHeight * childCount;
        setLayoutParams(mlp);

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE){
                childView.layout(l, i * mScreenHeight, r, (i + 1) * mScreenHeight);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                mStartY = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dy;
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                dy = mLastY - y;
                if (getScrollY() < 0){
                    dy = 0;
                }
                if (getScrollY() > getHeight() - mScreenHeight){
                    dy = 0;
                }
                scrollBy(0, (int) dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                mEndY = getScrollY();
                float dScrollY = mEndY - mStartY;
                if (dScrollY > 0){
                    if (dScrollY < mScreenHeight / 3){
                        mScroller.startScroll(0, getScrollY(), 0, (int) -dScrollY);
                    }else{
                        mScroller.startScroll(0, getScrollY(), 0, (int) (mScreenHeight - dScrollY));
                    }
                }else{
                    if (-dScrollY < mScreenHeight / 3){
                        mScroller.startScroll(0, getScrollY(), 0, (int) -dScrollY);
                    }else{
                        mScroller.startScroll(0, getScrollY(), 0, (int) (-mScreenHeight - dScrollY));
                    }
                }
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()){
            scrollTo(0, mScroller.getCurrY());
            postInvalidate();
        }
    }
}
