package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Practice11PieChartView extends View {

    private static final String NAME = "饼图";

    private float radius;
    private List<Data> mDataList;
    private Paint paint;
    private RectF rectF;

    private float total;
    private float max;

    // 开始的角度
    float startAngle;
    // 扫过的角度
    float sweptAngle;
    // 当前扇形一半的角度
    float halfAngle;

    // 直线开始的X坐标
    float lineStartX = 0f;
    // 直线开始的Y坐标
    float lineStartY = 0f;
    // 直线结束的X坐标
    float lineEndX;
    // 直线结束的Y坐标
    float lineEndY;

    public Practice11PieChartView(Context context) {
        this(context, null);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDataList = new ArrayList<>();
        mDataList.add(new Data("Froyo", 1f, Color.TRANSPARENT));
        mDataList.add(new Data("GingerBread", 8f, Color.parseColor("#9515a6")));
        mDataList.add(new Data("Ice Cream Sandwich", 6f, Color.parseColor("#949394")));
        mDataList.add(new Data("JellyBean", 50f, Color.parseColor("#008d7d")));
        mDataList.add(new Data("KikKat", 100f, Color.parseColor("#008df1")));
        mDataList.add(new Data("Lollipop", 120f, Color.parseColor("#fa3031")));
        mDataList.add(new Data("Marshmallow", 45f, Color.parseColor("#ffb914")));

        max = Float.MIN_VALUE;
        total = 0;
        for (Data tempData : mDataList) {
            total += tempData.getNumber();
            max = Math.max(max, tempData.getNumber());
        }

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        // title
        paint.setTextSize(48);
        paint.setColor(Color.WHITE);
        canvas.drawText(NAME, (getWidth() - paint.measureText(NAME)) / 2, (float) (getHeight() * 0.92), paint);

        radius = getWidth() / 4;
        rectF = new RectF(-radius, -radius, radius, radius);
        // 将canvas的原点x轴移动canvas.getWidth() / 2 - 100 y轴移动canvas.getHeight() / 2 - 100
        canvas.translate(getWidth() / 2, getHeight() / 2);

        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(20);
        startAngle = 0f;
        for (Data data : mDataList) {
            paint.setColor(data.getColor());
            sweptAngle = data.getNumber() / total * 360f;
            halfAngle = startAngle + sweptAngle / 2;

            // 角度=弧度*180/Math.PI
            // 圆弧中点的X坐标
            lineStartX = radius * (float) Math.cos(halfAngle / 180 * Math.PI);
            // 圆弧中点的Y坐标
            lineStartY = radius * (float) Math.sin(halfAngle / 180 * Math.PI);
            lineEndX = (radius + 50) * (float) Math.cos(halfAngle / 180 * Math.PI);
            lineEndY = (radius + 50) * (float) Math.sin(halfAngle / 180 * Math.PI);

            if (max == data.getNumber()) {
                // 保存当前的状态
                canvas.save();
                // 移动画布的原点
                canvas.translate(0.1f * lineStartX, lineStartY * 0.1f);
                canvas.drawArc(rectF, startAngle, sweptAngle, true, paint);
            } else {
                canvas.drawArc(rectF, startAngle, sweptAngle - 2f, true, paint);
            }

            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, paint);
            if (halfAngle > 90 && halfAngle <= 270) {
                canvas.drawLine(lineEndX, lineEndY, lineEndX - 50, lineEndY, paint);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawText(data.getName(), lineEndX - 50 - 10 - paint.measureText(data.getName()), lineEndY, paint);
            } else {
                canvas.drawLine(lineEndX, lineEndY, lineEndX + 50, lineEndY, paint);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawText(data.getName(), lineEndX + 50 + 10, lineEndY, paint);
            }
            if (max == data.getNumber()) {
                // 恢复save之前的状态
                canvas.restore();
            }
            startAngle += sweptAngle;
        }
    }

    public class Data {
        private String name;
        private float number;
        private int color;

        public Data(String name, float number, int color) {
            this.name = name;
            this.number = number;
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public float getNumber() {
            return number;
        }

        public void setNumber(float number) {
            this.number = number;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }
}
