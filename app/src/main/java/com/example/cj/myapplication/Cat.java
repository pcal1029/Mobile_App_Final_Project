package com.example.cj.myapplication;

/**
 * Created by CJ on 2015-11-24.
 */

//package com.mobile_app_catching_bug.myapplication;
/**
 * Created by CJ on 2015-10-12.
 */
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.FactoryConfigurationError;

public class Cat {
    public static final int CAT_LIFE=5;
    public static final int CAT_RIGHT=0;
    public static final int CAT_RIGHT_1=1;
    public static final int CAT_RIGHT_2=2;
    public static final int CAT_RIGHT_3=3;

    public static final int CAT_LEFT=4;
    public static final int CAT_LEFT_1=5;
    public static final int CAT_LEFT_2=6;
    public static final int CAT_LEFT_3=7;

    public int cat_state;


    float x;
    float y;
    float xMax;
    float xMin=0;
    float yMax;
    float yMin=0;
    float pointX;
    float pointY;
    float speedX = 4.5f;
    float speedY = (float)(speedX*Math.sqrt(3));
    float pointTap;
    float deltaX;
    float width;
    float height;
    float halfwidth;
    float halfheight;
    boolean semaphore = true;

    float centX;
    float centY;
    private Paint paint;
    private RectF bounds;
    boolean live=true;
    int ani_state;
    int ori_state;
    boolean animation;
    int stage;
    int laneNum;
    private Weapon weapon;
    int life;
    RectF vCat;


    public Cat(float x, float y, float wid, float hei) {
        xMax = x;
        yMax = y;
        centX = x/2;
        centY = y/2;
        width = wid;
        height = hei;
        halfwidth = width/2;
        halfheight = height/2;
        pointX=centX;
        pointY=centY;
        pointTap = pointX;

        //paint = new Paint();
        //paint.setColor(color);
        bounds = new RectF();
        ani_state=0;
        ori_state = CAT_RIGHT;
        cat_state = ori_state + ani_state;
        animation=false;
        stage = 0;
        weapon = new Fish();
        vCat= new RectF();
        life = CAT_LIFE;
    }


    /*
    public  void setMax(int x, int y)
    {
        xMax=x;
        yMax=y;
    }
    */
/*
    public void  set(int xMax, int yMax) {
        x = xMax-width;
        y = yMax-height;
        bounds.set(xMax-width, yMax-height, xMax, yMax);
    }
    */
    /*
    public void SetCenter(int x, int y) {
        centX = x ;
        centY = y ;
        pointX=centX;
        pointY=centY;
        pointTap = pointX;
    }
    */

    /*
    public void PointOnCircle(float angleInDegrees, int Radius) {
        sx = (int) (Math.cos(Math.toRadians(angleInDegrees)) * Radius) + circleX;
        sy = (int) (Math.sin(Math.toRadians(angleInDegrees)) * Radius) + circleY;
    }
    public void PointOnRotation(float angleInDegrees, double Radius) {
        rx = (int) ((Math.cos(Math.toRadians(angleInDegrees)) * Radius) + sx);
        ry = (int) (Math.sin(Math.toRadians(angleInDegrees)) * Radius + sy);
    }
    */

    public void draw(Canvas canvas){
        //deltaX = Math.abs(pointX-pointTap);
        //pointY = deltaX - 30*deltaX*deltaX;


        bounds.set(pointX - halfwidth , pointY - halfheight, pointX + halfwidth, pointY+ halfheight);

        canvas.drawRect(bounds, paint);

        if(pointX + halfwidth > xMax){
            speedX = -speedX;
            pointX = xMax-halfwidth;
        }else if(pointX - halfwidth < xMin){
            speedX = -speedX;
            pointX = xMin+halfwidth;
            //update();
        }
        if(pointY + halfheight > yMax){

        }else if(pointY - halfheight < yMin){

        }


        /*
        //collision
        if ((ballX >= bar.x-bar.halfwidth && ballX <= bar.x+bar.halfwidth) && (ballY >= bar.y-bar.halfheight && ballY <= bar.y+bar.halfheight)) {
            // i = (i+1) % 6;
            //Log.d("DBG", "COLOR = " + i);
            //ballSpeedY = -ballSpeedY;
            ballSpeedX = -ballSpeedX;

            ballSpeedX += 0.5;
            ballSpeedY += 0.5;
        }
        */
        speedY -=0.098*3;
        pointX += speedX;
        pointY -= speedY;   //y좌표는 반대.
    }

    public void update()
    {
        //speedY = (float)(Math.abs(speedX)*Math.sqrt(3));
        speedY = Math.abs(speedX)*2;    //점프
    }

    public void move() {
        vCat.set(pointX - halfwidth + 20, pointY - halfheight + 30, pointX + halfwidth -20, pointY + halfheight-30);
        if(pointX + halfwidth > xMax){
            speedX = -speedX;
            pointX = xMax-halfwidth;
            ori_state = CAT_LEFT;
            stage++;
        }else if(pointX - halfwidth < xMin){
            speedX = -speedX;
            pointX = xMin+halfwidth;
            ori_state = CAT_RIGHT;
            stage++;
            //update();
        }
        if(pointY + halfheight > yMax){

        }else if(pointY - halfheight < yMin){

        }

        speedY -=0.098*3;
        pointX += speedX;
        pointY -= speedY;
        cat_state = ori_state + ani_state;
        weapon.update();
    }
    public void animation(){
        if(semaphore){
            semaphore=false;
            for(int i=0; i<3; i++) {
                cat_state++;
                for(int t=0; t<200; t++);
            }
            for(int j=0; j<3; j++){
                cat_state--;
                for(int t=0; t<200; t++);
            }
            semaphore=true;
        }
    }

    RectF getBounds()
    {
        return vCat;
    }

    void setLive(boolean d)
    {
        live = d;
    }

    boolean getLive()
    {
        return live;
    }

    /*
    float getDistance()
    {
        return ((centX-rx)*(centX-rx)+(centY-ry)*(centY-ry));
    }
    */

    float getPointX(){
        return pointX;
    }

    float getPointY(){
        return pointY;
    }

    int getState() {
        return cat_state;
    }
    void setState(int state) {
        ani_state = state;
    }
    boolean getSema() {
        return semaphore;
    }
    boolean getAni(){
        return animation;
    }
    void setAni(boolean a){
        animation=a;
    }
    int getStage(){
        return stage;
    }

    public void setWeapon(Weapon w){
        weapon=w;
    }
    public void fire(){
        if(ori_state == CAT_RIGHT)
            weapon.fire(pointX, pointY, true);
        else if(ori_state == CAT_LEFT)
            weapon.fire(pointX, pointY, false);
        else
            System.out.println("Cat 내의 fire에서의 에러");
    }
    public Weapon getWeapon(){
        return weapon;
    }

    public int getLife (){
        return life;
    }
    public void setLife(int l){
        life = l;
    }
    public int getTotalLife(){
        return CAT_LIFE;
    }
}
