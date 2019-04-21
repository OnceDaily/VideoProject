package com.jy.pre.videoproject.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.jy.pre.videoproject.R;
import com.jy.pre.videoproject.Util;

public class CircleProgressView extends View {

    private Paint progressPaint;
    private float progressSweepAngle;//进度条圆弧扫过的角度
    private float progressNum;//可以更新的进度条数值
    private float progressMaxNum;//进度条的最大值
    private int progressColor;

    private Paint bgPaint;
    private float startAngle;//背景圆弧的起始角度
    private float bgSweepAngle;// 背景圆弧扫过的角度
    private int bgColor;

    private Paint textPaint;
    private String text;
    private int textColor;
    private float textSize;

    private Paint textBgPaint;
    private int textBgColor;
    private int textBgXY;

    private CircleBarAnim anim;

    private RectF rectF;//控制圆弧大小的矩形
    private float barWidth;//进度条宽度
    private int defaultSize;//默认的圆弧大小

    private OnProgressListener onProgressListener;

    public CircleProgressView(Context context) {
        this(context,null);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attrs){
        rectF = new RectF();
        defaultSize = Util.dip2px(context,100);
        barWidth = Util.dip2px(context,10);

        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.CircleProgressView);
        progressColor = ta.getColor(R.styleable.CircleProgressView_circleColor,Color.GREEN);
        bgColor = ta.getColor(R.styleable.CircleProgressView_arcColor,Color.GRAY);
        startAngle = ta.getFloat(R.styleable.CircleProgressView_startAngle,0);
        bgSweepAngle = ta.getFloat(R.styleable.CircleProgressView_sweepAngle,360);
        barWidth = ta.getDimension(R.styleable.CircleProgressView_bar_width,10);
        textBgColor = ta.getColor(R.styleable.CircleProgressView_arcColor,Color.RED);
        textColor = ta.getColor(R.styleable.CircleProgressView_textColor,Color.BLACK);
        textSize = ta.getDimension(R.styleable.CircleProgressView_textSize,30);
        ta.recycle();

        progressPaint = new Paint();
        progressPaint.setStyle(Paint.Style.STROKE);//只描边，不填充
        progressPaint.setColor(progressColor);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);//设置首尾为圆弧状
        progressPaint.setAntiAlias(true);//抗锯齿
        progressPaint.setStrokeWidth(barWidth);

        bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setColor(bgColor);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        bgPaint.setAntiAlias(true);
        bgPaint.setStrokeWidth(barWidth);

        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);

        textBgPaint = new Paint();
        textBgPaint.setColor(textBgColor);
        textBgPaint.setAntiAlias(true);

        progressNum = 0;
        progressMaxNum = 100;
        anim = new CircleBarAnim();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = measureSize(defaultSize,heightMeasureSpec);
        int width = measureSize(defaultSize,widthMeasureSpec);
        int min = Math.min(height,width);
        setMeasuredDimension(min,min);

        textBgXY = min/2;

        if (min >= barWidth * 2)
            rectF.set(barWidth/2,barWidth/2,min - barWidth/2, min-barWidth/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(rectF,startAngle,bgSweepAngle,false,bgPaint);

        canvas.drawArc(rectF,startAngle,progressSweepAngle,false,progressPaint);

        canvas.drawCircle(textBgXY,textBgXY,textBgXY * 0.5f,textBgPaint);

        canvas.drawText(text,0,text.length(),textBgXY,textBgXY + textSize/4,textPaint);
    }

    private int measureSize(int defaultSize,int measureSpec){
        int result = defaultSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY)
            result = specSize;
        else if (specMode == MeasureSpec.AT_MOST)
            result = Math.min(result,specSize);
        return result;
    }

    public class CircleBarAnim extends Animation{
        public CircleBarAnim(){

        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            text = String.valueOf(Math.round(interpolatedTime * 100)) + "%";
            progressSweepAngle = interpolatedTime * bgSweepAngle * progressNum/ progressMaxNum;
            postInvalidate();
            if (null != onProgressListener)
                onProgressListener.onProgress(interpolatedTime);
        }
    }

    public void setProgressNum(float progressNum,int time){
        this.progressNum = progressNum;
        anim.setDuration(time);
        this.startAnimation(anim);
    }

    public void setOnProgressListener(OnProgressListener onProgressListener){
        this.onProgressListener = onProgressListener;
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);
        startAnimation(anim);
    }

    public interface OnProgressListener {
        void onProgress(float interpolatedTime);
    }
}