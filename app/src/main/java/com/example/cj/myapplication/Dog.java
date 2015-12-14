package com.example.cj.myapplication;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by CJ on 2015-11-29.
 */
public class Dog implements Enemy {
    public static final int DOG_RIGHT=0;
    public static final int DOG_RIGHT_1=1;
    public static final int DOG_RIGHT_2=2;
    public static final int DOG_LEFT=3;
    public static final int DOG_LEFT_1=4;
    public static final int DOG_LEFT_2=5;

    public int dog_state;

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
    float width = 24;
    float height = 24;
    float halfwidth = width / 2;
    float halfheight = height / 2;
    boolean semaphore = true;

    float centX;
    float centY;
    private Paint paint;
    private Rect bounds;
    boolean live=true;
    int ani_state;
    int ori_state;
    boolean animation;
    int stage;
    private Lane lane;
    private Weapon weapon;
    //int laneNum;
    RectF vDog;

    public Dog(float x, float y, float wid, float hei) {
        xMax = x;
        yMax = y;
        centX = x/2;
        centY = y/2;
        pointX=xMax;
        pointY=centY;
        pointTap = pointX;
        width = wid;
        height = hei;
        halfwidth = width/2;
        halfheight = height/2;

        bounds = new Rect();
        ani_state=0;
        ori_state = DOG_RIGHT;
        dog_state = ori_state + ani_state;
        animation=false;
        weapon = new Bone();
        vDog = new RectF();
    }


    public void update()
    {

    }

    public void move(){
        vDog.set(pointX, pointY, pointX + width, pointY+ height);
        if(pointX<centX)
            ori_state = DOG_RIGHT;
        else
            ori_state = DOG_LEFT;

        dog_state=ori_state+ani_state;
        weapon.update();
    }

    void setLive(boolean d)
    {
        live = d;
    }

    boolean getLive()
    {
        return live;
    }


    float getPointX(){
        return pointX;
    }
    float getPointY(){
        return pointY;
    }

    @Override
    public void setPointX(float x) {pointX=x;}

    @Override
    public void setPointY(float y) {pointY=y;}

    int getState() {
        return dog_state;
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

    @Override
    public void setLane(Lane l){
        lane = l;
    }

    @Override
    public Lane getLane(){
        return lane;
    }

    public void setWeapon(Weapon w){
        weapon=w;
    }
    public void fire(){
        if(ori_state == DOG_RIGHT)
            weapon.fire(pointX, pointY, true);
        else if(ori_state == DOG_LEFT)
            weapon.fire(pointX, pointY, false);
        else
            System.out.println("Cat 내의 fire에서의 에러");
    }
    public Weapon getWeapon(){
        return weapon;
    }
    RectF getBounds()
    {
        return vDog;
    }
}
