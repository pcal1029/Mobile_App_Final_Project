package com.example.cj.myapplication;
//package com.mobile_app_catching_bug.myapplication;

/**
 * Created by CJ on 2015-10-12.
 */
        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Rect;
        import android.util.Log;
        import android.view.Display;
        import android.view.WindowManager;


public class Tank {
    int width = 80;
    int height = 80;
    int halfwidth = width / 2;
    int halfheight = height / 2;
    int xBullet = 0;

    int centX;
    int centY;
    int x;
    int y;

    float currentSelfAngle;
    float bulletAngle;
    int sx;
    int sy;
    int tx;
    int ty;
    boolean bulletLive=true;
    boolean tankLive = true;

    private Paint paint;
    private Rect bounds;
    private Rect gunbounds;
    private Rect bulletbounds;
    private Rect realBullet;
    private Rect realTank;

    public Tank(int color) {
        paint = new Paint();
        paint.setColor(color);
        bounds = new Rect();
        gunbounds = new Rect();
        bulletbounds = new Rect();
        realBullet = new Rect();
        realTank = new Rect();
    }

    public void set(int xMax, int yMax) {
        x = xMax - width;
        y = yMax - height;
        bounds.set(xMax - width, yMax - height, xMax, yMax);
    }

    public void SetCenter(int x, int y) {
        centX = x;
        centY = y;
    }

    public void PointOnCircle(float angleInDegrees, int Radius) {
        sx = (int) (Math.cos(Math.toRadians(angleInDegrees)) * Radius) + centX;
        sy = (int) (Math.sin(Math.toRadians(angleInDegrees)) * Radius) + centY;
    }
    public void PointOnTank(float angleInDegrees, int Radius) {
        tx = (int) (Math.cos(Math.toRadians(angleInDegrees)) * Radius) + centX;
        ty = (int) (Math.sin(Math.toRadians(angleInDegrees)) * Radius) + centY;
    }

    public void draw(Canvas canvas, int rad) {
        // SetCenter(canvas.getWidth() / 2, canvas.getHeight() / 2);
        PointOnTank(currentSelfAngle, rad);
        realTank.set(tx-halfwidth, ty-halfheight, tx+halfwidth, ty+halfheight);
        //canvas.drawRect(realTank,paint);

        bounds.set(centX + rad - halfwidth, centY - halfheight, centX + rad + halfheight, centY + halfheight);

        gunbounds.set(centX + rad - halfwidth - 30, centY - 5, centX + rad - halfwidth, centY + 5);
        if (centX + rad - halfwidth - 40 + xBullet<=centX-rad) {
            xBullet = 0;
            bulletAngle = currentSelfAngle;
            bulletLive = true;
        } else xBullet -= 10;

        canvas.rotate(bulletAngle, centX, centY);
        if(bulletLive) {
            if(tankLive) {
                bulletbounds.set(centX + rad - halfwidth - 40 + xBullet, centY - 5, centX + rad - halfwidth - 30 + xBullet, centY + 5);
                canvas.drawRect(bulletbounds, paint);
                PointOnCircle(bulletAngle, rad - halfwidth - 35 + xBullet);
                realBullet.set(sx - 5, sy - 5, sx + 5, sy + 5);
            }
        }

        canvas.rotate(-bulletAngle, centX, centY);

        canvas.rotate(currentSelfAngle, centX, centY);
        if(tankLive) {
            canvas.drawRect(bounds, paint);
            canvas.drawRect(gunbounds, paint);
            //canvas.drawRect(realTank, paint);
        }
    }

    float getBulletAngle()
    {
        return currentSelfAngle;
    }

    public void update(float angle)
    {
        currentSelfAngle += angle;
    }

    public Rect getBulletBounds()
    {
        return realBullet;
    }

    public Rect getTankBounds()
    {
        return realTank;
    }

    boolean getBulletLive()
    {
        return bulletLive;
    }

    void setBulletLive(boolean d)
    {
        bulletLive = d;
    }

    boolean getTankLive()
    {
        return tankLive;
    }

    void setTankLive(boolean d)
    {
        tankLive = d;
    }
}
