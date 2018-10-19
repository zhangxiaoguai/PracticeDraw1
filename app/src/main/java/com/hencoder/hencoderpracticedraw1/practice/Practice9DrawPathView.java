package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    private Paint paint;
    private Path path;

    public Practice9DrawPathView(Context context) {
        this(context, null);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        path = new Path();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形
//        paint.setStyle(Paint.Style.STROKE);// 空心
        paint.setStyle(Paint.Style.FILL);// 实心
        // addArc默认是true，抬笔
        path.addArc(getWidth() / 2 - 200, getHeight() / 2 - 100, getWidth() / 2, getHeight() / 2 + 100, -225, 225);
        // arcTo中false，不抬笔一路画过去，一笔画过去，close时与第一点连线封闭
        // arcTo中true，中间抬笔重新画，close时与上次落笔处连线封闭
        // 是否直接到画弧线的位置，true直接移动到要画弧线的位置，false画线移动到要画弧线的位置
        path.arcTo(getWidth() / 2, getHeight() / 2 - 100, getWidth() / 2 + 200, getHeight() / 2 + 100, 180, 225, false);
        path.lineTo(getWidth() / 2, getHeight() / 2 + 250);
//        path.close();// paint是FILL模式会自动闭合当前图形，即：停笔处与上次落笔处连线闭合
        canvas.drawPath(path, paint);
    }
}
