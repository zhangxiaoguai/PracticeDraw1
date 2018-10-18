package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {

    private Paint paint;
    private Path path;

    public Practice10HistogramView(Context context) {
        this(context, null);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        // 1.轴
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        path.moveTo(150, 100);
        // y轴
        path.rLineTo(0, 500);
        // x轴
        path.rLineTo(getWidth() - 300, 0);
        canvas.drawPath(path, paint);

        // 2.柱
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#62b319"));
        int divider = (getWidth() - 300) / 36;
        int yItemHeight = (400 - 20) / 70;
        canvas.drawRect(150 + divider, 600 - yItemHeight, 150 + divider * 5, 600 - 2, paint);
        canvas.drawRect(150 + divider * 6, 600 - yItemHeight * 5, 150 + divider * 10, 600 - 2, paint);
        canvas.drawRect(150 + divider * 11, 600 - yItemHeight * 5, 150 + divider * 15, 600 - 2, paint);
        canvas.drawRect(150 + divider * 16, 600 - yItemHeight * 35, 150 + divider * 20, 600 - 2, paint);
        canvas.drawRect(150 + divider * 21, 600 - yItemHeight * 60, 150 + divider * 25, 600 - 2, paint);
        canvas.drawRect(150 + divider * 26, 600 - yItemHeight * 70, 150 + divider * 30, 600 - 2, paint);
        canvas.drawRect(150 + divider * 31, 600 - yItemHeight * 30, 150 + divider * 35, 600 - 2, paint);

        // 3.字
        paint.setTextSize(23);
        paint.setColor(Color.WHITE);
        canvas.drawText("Froyo", 150 + divider + (divider * 4 - paint.measureText("Froyo")) / 2, 600 + 25, paint);
        canvas.drawText("GB", 150 + divider * 6 + (divider * 4 - paint.measureText("GB")) / 2, 600 + 25, paint);
        canvas.drawText("ICS", 150 + divider * 11 + (divider * 4 - paint.measureText("ICS")) / 2, 600 + 25, paint);
        canvas.drawText("JB", 150 + divider * 16 + (divider * 4 - paint.measureText("JB")) / 2, 600 + 25, paint);
        canvas.drawText("KikKat", 150 + divider * 21 + (divider * 4 - paint.measureText("KitKat")) / 2, 600 + 25, paint);
        canvas.drawText("L", 150 + divider * 26 + (divider * 4 - paint.measureText("L")) / 2, 600 + 25, paint);
        canvas.drawText("M", 150 + divider * 31 + (divider * 4 - paint.measureText("M")) / 2, 600 + 25, paint);

        // 4.题
        paint.setTextSize(48);
        canvas.drawText("直方图", (getWidth() - paint.measureText("直方图")) / 2, 750, paint);

        // FIXME: 2018/9/25  onDraw内存在大量计算，这些计算都可以在init方法内提前计算好，init方法内可以提取一个initData方法用来初始化这些数据
    }
}
