package com.example.cj.myapplication;

/*
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;



public class InitialView extends View {
    private int xMax;
    private int yMax;
    private int Score = 0;
    private int halfwidth;
    private int halfheight;
    private Rect ballBounds;
    private Paint paint;
    private Tank tank;
    private boolean isTouch=false;

    int score = 0;
    int s=0;

    public InitialView(Context context) {
        super(context);
        setBackgroundColor(Color.BLUE);
        Display  display =  ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        xMax = display.getWidth();
        halfwidth = xMax / 2;
        yMax = display.getHeight();
        halfheight = yMax / 2;
    }
    protected void onDraw(Canvas canvas) {
        paint.setColor(Color.GRAY);
        canvas.drawCircle(halfwidth, halfheight, halfwidth - 90, paint);

    }
/*
    public boolean onTouchEvent(MotionEvent event){
        float currentX = event.getX();
        float currentY = event.getY();
        float deltaX = 0;
        float deltaY = 0;
        float scalingFactor = 5.0f / ((xMax > yMax)? yMax : xMax);
        switch (event.getAction()){
            case MotionEvent.ACTION_MOVE:
                if((currentX>=90)&&(currentX<=(halfwidth*2 - 90))) {
                    if ((currentY >= (halfheight - halfwidth + 90)) && (currentY <= (halfheight + halfwidth - 90))) {
                        isTouch = true;
                        //tank.update(5);
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                //bar.x = (int)currentX
                if((currentX>=90)&&(currentX<=(halfwidth*2 - 90))) {
                    if ((currentY >= (halfheight - halfwidth + 90)) && (currentY <= (halfheight + halfwidth - 90))) {
                        isTouch = true;
                        //tank.update(5);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                isTouch = false;
                break;
        }
        //if(isTouch)
        //tank.update(5);
        // bar.x = (int)currentX;
        return true;
    }
    boolean getIsTouch(){
        return isTouch;
    }

}
*/

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;


public class InitialView extends View {
    private int xMin = 0;
    //private int xMax;
    private int yMin = 0;
    //private int yMax;
    private float ballRadius = 17;
    private float ballX = ballRadius + 200;
    private float ballY = ballRadius + 330;
    private float ballSpeedX = 10;
    private float ballSpeedY = 10;
    private RectF ballBounds;
    //private Paint paint;
    //private Bar bar;

    private int xMax;
    private int yMax;
    private int Score = 0;
    private int halfwidth;
    private int halfheight;
    private Paint paint;
    private Tank tank;
    private boolean isTouch = false;

    public InitialView(Context context) {
        super(context);
        ballBounds = new RectF();
        paint = new Paint();

        paint.setTypeface(Typeface.MONOSPACE);
        paint.setTextSize(16);

        //bar = new Bar(Color.CYAN);
        this.setFocusableInTouchMode(true);


        setBackgroundColor(Color.BLUE);
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        xMax = display.getWidth();
        halfwidth = xMax / 2;
        yMax = display.getHeight();
        halfheight = yMax / 2;

    }

    protected void onDraw(Canvas canvas) {


        invalidate();
    }

    /*
    private void update() {
        ballX += ballSpeedX;
        ballY += ballSpeedY;
        if (ballX + ballRadius > xMax) {
            ballSpeedX = -ballSpeedX;
            ballX = xMax - ballRadius;
        } else if (ballX - ballRadius < xMin) {
            // ballSpeedX = -ballSpeedX;
            //ballX = xMin+ballRadius;
            //update();
        }
        if (ballY + ballRadius > yMax) {
            ballSpeedY = -ballSpeedY;
            ballY = yMax - ballRadius;
        } else if (ballY - ballRadius < yMin) {
            ballSpeedY = -ballSpeedY;
            ballY = yMin + ballRadius;
        }
        //collision
        if ((ballX >= bar.x - bar.halfwidth && ballX <= bar.x + bar.halfwidth) && (ballY >= bar.y - bar.halfheight && ballY <= bar.y + bar.halfheight)) {
            // i = (i+1) % 6;
            //Log.d("DBG", "COLOR = " + i);
            //ballSpeedY = -ballSpeedY;
            ballSpeedX = -ballSpeedX;

            ballSpeedX += 0.5;
            ballSpeedY += 0.5;
        }
    }

    public void onSizeChanged(int w, int h, int oldw, int oldH) {
        xMax = w - 1;
        yMax = h - 1;
    }
*/

    /*
    public boolean onTouchEvent(MotionEvent event) {
        float currentX = event.getX();
        float currentY = event.getY();
        float previousX = bar.x;
        float previousY = bar.y;
        float deltaX = 0;
        float deltaY = 0;
        float scalingFactor = 5.0f / ((xMax > yMax) ? yMax : xMax);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                deltaX = currentX - previousX;
                deltaY = currentY - previousY;
                //bar.x = currentX;
                //bar.y = currentY;

                //ballSpeedX += deltaX * scalingFactor;
                //ballSpeedY += deltaY * scalingFactor;

                bar.moveWithCollisionDetection(xMax, yMax, deltaY * scalingFactor *130, ballX, ballY);
                break;
            case MotionEvent.ACTION_DOWN:
                previousX = currentX;
                previousY = currentY;
                break;
            case MotionEvent.ACTION_UP:
                bar.moveWithCollisionDetection(xMax, yMax, deltaY * scalingFactor *130, ballX, ballY);
                break;
        }
        previousX = currentX;
        previousY = currentY;
        return true;
    }
    */
    public boolean onTouchEvent(MotionEvent event) {
        float currentX = event.getX();
        float currentY = event.getY();
        float deltaX = 0;
        float deltaY = 0;
        float scalingFactor = 5.0f / ((xMax > yMax) ? yMax : xMax);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if ((currentX >= 90) && (currentX <= (halfwidth * 2 - 90))) {
                    if ((currentY >= (halfheight - halfwidth + 90)) && (currentY <= (halfheight + halfwidth - 90))) {
                        isTouch = true;
                        //tank.update(5);
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                //bar.x = (int)currentX
                if ((currentX >= 90) && (currentX <= (halfwidth * 2 - 90))) {
                    if ((currentY >= (halfheight - halfwidth + 90)) && (currentY <= (halfheight + halfwidth - 90))) {
                        isTouch = true;
                        //tank.update(5);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                //isTouch = false;
                break;
        }
        //if(isTouch)
        //tank.update(5);
        // bar.x = (int)currentX;
        return true;
    }

    boolean getIsTouch() {
        return isTouch;
    }
}

/*
    public class Bar {
        float width = 60;
        float height = 300;
        float halfwidth = width/2;
        float halfheight = height/2;
        float speedX = 0;
        float speedY = 3;
        int i = 0;

        float x = halfwidth;
        float y = halfheight;

        int xMin, yMin;
        private Paint paint;
        private RectF bounds = new RectF();

        private Integer[] colorArray = {Color.YELLOW, Color.BLUE, Color.GREEN, Color.RED, Color.MAGENTA, Color.CYAN};
        public Bar(int color) {
            paint = new Paint();
            paint.setColor(color);
        }

        public void  set(int xMax, int yMax) {
            x = xMax-width;
            y = yMax-height;
            bounds.set(xMax - width, yMax - height, xMax, yMax);
        }

        public void draw(Canvas canvas){
            bounds.set(x-halfwidth, y-halfheight, x+halfwidth, y+halfheight);
            canvas.drawRect(bounds, paint);
        }
        public void moveWithCollisionDetection(int xMax, int yMax, float deltaY, float BallX, float BallY) {
            //x += speedX;

            if(x+halfwidth > xMax) {
                speedX = -speedX;
                x = xMax-halfwidth;
            } else if (x-halfwidth < xMin) {
                speedX = -speedX;
                x = xMin+halfwidth;
            }

            y += deltaY;

            if(y + halfheight > yMax) {
                speedY = -speedY;
                y = yMax - halfheight;
            } else if(y - halfheight < yMin) {
                speedY = -speedY;
                y = yMin + halfheight;
            }

            if ((BallX >= x-width && BallX <= x+halfwidth) && (BallY >= y-halfheight && BallY <= y+halfheight)) {
                i = (i+1) % 6;
                Log.d("DBG", "COLOR = " + i);
                //ballSpeedY = -ballSpeedY;
                //ballSpeedX = -ballSpeedX;
                //paint.setColor(colorArray[i]);
            }
        }
    }
}
*/