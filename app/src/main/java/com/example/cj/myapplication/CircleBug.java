package com.example.cj.myapplication;
//package com.mobile_app_catching_bug.myapplication;

        import android.graphics.Canvas;
        import android.graphics.Paint;
        import android.graphics.Rect;

        import java.util.Random;

/**
 * Created by CJ on 2015-10-13.
 */


public class CircleBug {
    int radi = 12;

    float centX;
    float centY;
    int circleX;
    int circleY;
    int currentSelfAngle;
    int goAngle;

    private Rect realBug;
    boolean live=true;
    int sx;
    int sy;
    int rx;
    int ry;
    //boolean aCurve = true;

    private Paint paint;

    public CircleBug(int color) {
        paint = new Paint();
        paint.setColor(color);
        currentSelfAngle = new Random().nextInt(360);
        goAngle = new Random().nextInt(360);
        realBug = new Rect();
    }

    public void SetCenter(int x, int y) {
        circleX = x ;
        circleY = y ;
        centX = circleX + new Random().nextInt(50);;
        centY = circleY ;
    }

    public void PointOnCircle(float angleInDegrees, int Radius) {
        sx = (int) (Math.cos(Math.toRadians(angleInDegrees)) * Radius) + circleX;
        sy = (int) (Math.sin(Math.toRadians(angleInDegrees)) * Radius) + circleY;
    }

    public void PointOnRotation(float angleInDegrees, double Radius) {
        rx = (int) ((Math.cos(Math.toRadians(angleInDegrees)) * Radius)+sx);
        ry = (int) (Math.sin(Math.toRadians(angleInDegrees)) * Radius)+sy;
    }

    public void draw(Canvas canvas) {
        canvas.rotate(goAngle, circleX, circleY);
        canvas.rotate(currentSelfAngle, centX, centY);

        canvas.drawCircle((int) centX + 30, (int) centY, radi, paint);

        PointOnCircle(goAngle, (int) centX - circleX);
        PointOnRotation(currentSelfAngle+goAngle, 30);
        realBug.set(rx- radi, ry - radi, rx + radi, ry + radi);

        canvas.rotate(-currentSelfAngle, centX, centY);
        canvas.rotate(-goAngle, circleX, circleY);

        //canvas.drawRect(realBug, paint);

        currentSelfAngle+=4;
        if(currentSelfAngle%720 >= 540)
            centX+=1;
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