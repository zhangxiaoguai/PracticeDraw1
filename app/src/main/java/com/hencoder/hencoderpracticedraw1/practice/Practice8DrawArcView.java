package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {

    private Paint paint;

    public Practice8DrawArcView(Context context) {
        this(context, null);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        RectF rectF = new RectF(getWidth() / 2 - 200, getHeight() / 2 - 200, getWidth() / 2 + 200, getHeight() / 2 + 200);
        canvas.drawArc(rectF, -110, 100, true, paint);
        canvas.drawArc(rectF, 20, 140, false, paint);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rectF, -180, 60, false, paint);
    }
}
