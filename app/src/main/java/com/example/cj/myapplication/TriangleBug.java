package com.example.cj.myapplication;
//package com.mobile_app_catching_bug.myapplication;

/**
 * Created by CJ on 2015-10-13.
 */
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Rect;
        import android.util.Log;

        import java.util.Random;


        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Path;
        import android.graphics.Rect;
        import android.util.Log;

        import java.util.Random;


public class TriangleBug {
    private int width = 30;
    private int height=30;
    private int halfwidth = width/2;
    private int halfheight = height/2;
    float speedX = 0;
    float speedY = 3;
    int i = 0;

    float centX;
    float centY;
    int circleX;
    int circleY;
    float x;
    float y;
    int currentSelfAngle;
    int goAngle;
    int xMin, yMin;

    private Paint paint;
    private Rect bounds;
    private Rect realBug;
    boolean live=true;
    int sx;
    int sy;
    int rx;
    int ry;
    Path path = new Path();

    private Integer[] colorArray = {Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED, Color.MAGENTA, Color.CYAN};
    public TriangleBug(int color) {
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
        path.reset();
        path.moveTo(centX + 50, centY + height);

        path.lineTo(centX + 50 + halfwidth, centY);
        path.lineTo(centX + 50 - halfwidth, centY);
        path.close();

        canvas.rotate(goAngle, circleX, circleY);
        canvas.rotate(currentSelfAngle, centX, centY);
        canvas.drawPath(path, paint);

        canvas.rotate(-currentSelfAngle, centX, centY);
        canvas.rotate(-goAngle, circleX, circleY);

        PointOnCircle(goAngle, (int) centX - circleX);
        PointOnRotation(currentSelfAngle + goAngle, 50 );

        realBug.set(rx - halfwidth, ry - halfheight, rx + halfwidth, ry + halfheight);
        //canvas.drawRect(realBug, paint);

        currentSelfAngle += (new Random().nextInt(5)-3)/2;
        centX += (new Random().nextInt(5)-3)/2;
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
