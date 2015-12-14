package com.example.cj.myapplication;

import android.graphics.RectF;

/**
 * Created by CJ on 2015-12-05.
 */
public class Bullet {
    private float pointX;
    private float pointY;
    private int speed;
    private boolean direction;
    private int state;
    private int stateNum;
    private int width = 50;
    private int height =  50;
    RectF vBullet;

    Bullet(float x, float y, int s, boolean d, int num){
        pointX=x;
        pointY=y;
        speed = s;
        direction = d;
        state=num;
        vBullet = new RectF();

        //stateNum = num;
    }

    public void update(){
        if(direction) {
            pointX += speed;
        }
        else{
            pointX -= speed;
            //state = stateNum;
        }
        vBullet.set(pointX, pointY, pointX+width, pointY+height);
    }

    public float getX(){
        return pointX;
    }

    public float getY(){
        return pointY;
    }

    public void setX(float x){
        pointX=x;
    }

    public void setY(float y){
        pointY=y;
    }

    public void setPosition(float x, float y){
        pointX=x;
        pointY=y;
    }
    public RectF getBound(){
        return vBullet;
    }

    public int getBulletState(){
        return state;
    }
}
