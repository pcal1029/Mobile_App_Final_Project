package com.example.cj.myapplication;

//package com.mobile_app_catching_bug.myapplication;
/**
 * Created by CJ on 2015-10-12.
 */
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Rect;
        import android.util.Log;

        import java.util.Random;

public class BoxBug {
    int width = 24;
    int height = 24;
    int halfwidth = width / 2;
    int halfheight = height / 2;

    float centX;
    float centY;
    int circleX;
    int circleY;
    float x;
    float y;
    int currentSelfAngle;
    int goAngle;

    private Paint paint;
    private Rect bounds;
    private Rect realBug;
    boolean live=true;
    int sx;
    int sy;
    int rx;
    int ry;

    public BoxBug(int color) {
        paint = new Paint();
        paint.setColor(color);
        bounds = new Rect();
        realBug = new Rect();
        currentSelfAngle = new Random().nextInt(360);
        goAngle = new Random().nextInt(360);
    }

    public void  set(int xMax, int yMax) {
        x = xMax-width;
        y = yMax-height;
        bounds.set(xMax-width, yMax-height, xMax, yMax);
    }
    public void SetCenter(int x, int y) {
        circleX = x ;
        circleY = y ;
        centX = circleX + new Random().nextInt(50);
        centY = circleY;
    }

    public void PointOnCircle(float angleInDegrees, int Radius) {
        sx = (int) (Math.cos(Math.toRadians(angleInDegrees)) * Radius) + circleX;
        sy = (int) (Math.sin(Math.toRadians(angleInDegrees)) * Radius) + circleY;
    }
    public void PointOnRotation(float angleInDegrees, double Radius) {
        rx = (int) ((Math.cos(Math.toRadians(angleInDegrees)) * Radius) + sx);
        ry = (int) (Math.sin(Math.toRadians(angleInDegrees)) * Radius + sy);
    }

    public void draw(Canvas canvas){
        bounds.set((int)centX + 50 - halfwidth * 2, (int)centY - halfheight, (int)centX + 50, (int)centY + halfheight);

        canvas.rotate(goAngle, circleX, circleY);
        canvas.rotate(currentSelfAngle, centX, centY);

        PointOnCircle(goAngle, (int) centX -circleX);
        PointOnRotation(currentSelfAngle+goAngle, 50 - halfwidth);

        realBug.set(rx - halfwidth, ry - halfheight, rx + halfwidth , ry + halfheight);
        canvas.drawRect(bounds, paint);
        canvas.rotate(-currentSelfAngle, centX, centY);
        canvas.rotate(-goAngle, circleX, circleY);
        currentSelfAngle+=4;
        centX+=0.3;

        //canvas.drawRect(realBug, paint);
        //centY+=0.3;
    }

    float goAngle()
    {
        return goAngle;
    }

    Rect getBounds()
    {
        return realBug;
    }

    void setLive(boolean d)
    {
        live = d;
    }

    boolean getLive()
    {
        return live;
    }

    float getDistance()
    {
        return ((circleX-rx)*(circleX-rx)+(circleY-ry)*(circleY-ry));
    }
}