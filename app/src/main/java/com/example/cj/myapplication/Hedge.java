package com.example.cj.myapplication;

import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/**
 * Created by CJ on 2015-11-30.
 */
public class Hedge implements Enemy{

    public static final int HEDGE_RIGHT=0;
    public static final int HEDGE_LEFT=1;

    public int hedge_state;

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
    private Rect bounds;
    boolean live=true;
    int ani_state;
    int ori_state;
    boolean animation;
    int stage;
    private Lane lane;
    RectF vHedge;
    //int laneNum;

    public Hedge(float x, float y, float wid, float hei) {
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
        ori_state = HEDGE_RIGHT;
        hedge_state = ori_state + ani_state;
        animation=false;
        //lane = new Lane(0,0,false,false);
        vHedge = new RectF();
    }


    public void update()
    {

    }

    public void move(){
        vHedge.set(pointX, pointY, pointX + width, pointY+ height);
        if(pointX<centX)
            ori_state = HEDGE_RIGHT;
        else
            ori_state = HEDGE_LEFT;

        hedge_state=ori_state+ani_state;
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
        return hedge_state;
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

    boolean isRight(){
        if(hedge_state==0)
            return true;
        else
            return false;
    }

    @Override
    public void setLane(Lane l){
        lane=l;
        l.setIsFull(true);
    }

    @Override
    public Lane getLane(){
        return lane;
    }

    RectF getBounds()
    {
        return vHedge;
    }
}
