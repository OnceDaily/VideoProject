package com.jy.pre.videoproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.jy.pre.videoproject.R;

public class CircleProgressView extends View {

    private String text;
    private int textColor;
    private float textSize;
    private int circleColor;
    private int arcColor;
    private int startAngle;
    private int sweepAngle;
    private int mCircleXY;
    private float mRadius;
    private Paint mCirclePaint;
    private RectF mRectF;
    private Paint mArcPaint;
    private Paint mTextPaint;


    public CircleProgressView(Context context) {
        super(context,null);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        if (null != ta){
            text = ta.getString(R.styleable.CircleProgressView_text);
            textColor = ta.getColor(R.styleable.CircleProgressView_textColor,0);
            textSize = ta.getDimension(R.styleable.CircleProgressView_textSize,50);
            circleColor = ta.getColor(R.styleable.CircleProgressView_circleColor,0);
            arcColor = ta.getColor(R.styleable.CircleProgressView_arcColor,0);
            startAngle = ta.getInt(R.styleable.CircleProgressView_startAngle,0);
            sweepAngle = ta.getInt(R.styleable.CircleProgressView_sweepAngle,90);
            ta.recycle();
        }
    }

    private void init(int widthMeasureSpec, int heightMeasureSpec) {
        int length = Math.min(widthMeasureSpec, heightMeasureSpec);
        mCircleXY = length / 2;
        mRadius = length * 0.5f / 2;
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setColor(circleColor);
        mRectF = new RectF(length * 0.1f, length * 0.1f, length * 0.9f,
                length * 0.9f);

        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setColor(arcColor);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth((widthMeasureSpec * 0.1f));

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(textColor);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        init(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSth(canvas);
    }


    private void drawSth(Canvas canvas) {
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
        canvas.drawArc(mRectF, startAngle, sweepAngle, false, mArcPaint);
        canvas.drawText(text, 0, text.length(), mCircleXY, mCircleXY + textSize
                / 4, mTextPaint);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }
}