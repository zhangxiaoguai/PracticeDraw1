package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice3DrawRectView extends View {

    private Paint paint;

    public Practice3DrawRectView(Context context) {
        this(context, null);
    }

    public Practice3DrawRectView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice3DrawRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);

        Rect rect = new Rect(100, 100, 200, 200);
        RectF rectF = new RectF(100, 100, 200, 200);
        /**
         * Rect int精度，final类，不可继承
         * RectF float精度，非final类，可继承类
         */
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawRect() 方法画矩形
        paint.setColor(Color.BLACK);
        canvas.drawRect(getWidth() / 2 - 200, getHeight() / 2 - 200, getWidth() / 2 + 200, getHeight() / 2 + 200, paint);
    }
}
