package edu.feicui.app.phone.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

import edu.feicui.app.phone.R;

/**
 * Created by Administrator on 2017/1/5.
 */

public class DrawView extends View {
    private boolean flag = true;
    public int count;

    public DrawView(Context context) {
        super(context);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private RectF rf = null;
    private RectF rf1 = null;

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewWhidth = MeasureSpec.getSize(widthMeasureSpec);
        int viewHeight = MeasureSpec.getSize(heightMeasureSpec);
        rf = new RectF(0, 0, viewWhidth, viewHeight);
        rf1 = new RectF(1, 1, viewWhidth - 1, viewHeight - 1);
    }

    private float f2 = 0;
    private float f2Raw = 0;
    private float f1 = 0;
    private float f1Raw = 0;
    private int number = 0;

    //    public void setParamsWithAnim(float f11) {
//        this.f1Raw = f11;
//        Thread thread = new Thread() {
//            @Override
//            public void run() {
//                while (flag) {
//                    f1 = f1 + 4;
//                    postInvalidate();
//                    if (f1 >= f1Raw) {
//                        f1 = f1Raw;
//                    }
//                    if (f1 == f1Raw) {
//                        flag = false;
//                    }
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        };
//        thread.start();
//    }
    public void setParamsWithAnim(float f11) {
        this.f1 = f11;
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                count = 1;
                if (!flag) {
                    f2Raw -= 5;
                    if (f2Raw <= 0) {
                        f1Raw = 0;
                        flag = true;
                    }
                } else {
                    f1Raw += 5;
                    if (f1Raw >= f1) {
                        f1Raw = f1;
                        f2Raw = f1;
                    }
                    if (f1Raw == f1) {
                        timer.cancel();
                        flag = false;
                        count = 0;
                    }
                }
                postInvalidate();
                number = 1;
            }
        };
        timer.schedule(timerTask, 10, 10);
    }

    public void getParamsWithAnim(float f11, float f22) {
        this.f1Raw = f11;
        this.f2Raw = f22;
        final Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                f1 += 5;
                if (f1 >= f1Raw) {
                    f1 = f1Raw;
                    f2 += 5;
                    if (f2 >= f2Raw) {
                        f2 = f2Raw;
                    }
                }
                postInvalidate();
                if (f1 == f1Raw && f2 == f2Raw) {
                    timer.cancel();
                }
            }
        };
        timer.schedule(timerTask, 100, 100);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        if (number == 1) {
            p.setColor(getResources().getColor(R.color.black));
            p.setAntiAlias(true);
            canvas.drawArc(rf1, 0, f1, true, p);
            if (!flag) {
                p.setColor(getResources().getColor(R.color.colorAccent));
                p.setAntiAlias(true);
                canvas.drawArc(rf, f1Raw, f2Raw - f1Raw, true, p);
            }
        } else {
            p.setColor(getResources().getColor(R.color.black));
            p.setAntiAlias(true);
            canvas.drawArc(rf, 0, f1Raw, true, p);
            p.setColor(getResources().getColor(R.color.colorPrimaryDark));
            canvas.drawArc(rf, f1Raw, f2Raw, true, p);
        }
    }


}
