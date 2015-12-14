package com.example.cj.myapplication;

/**
 * Created by CJ on 2015-11-29.
 */
public class Lane {
    float length;
    float pointY;
    boolean right;
    boolean isFull;

    public Lane(float len, float poi, boolean rig, boolean isF){
        length = len;
        pointY = poi;
        right = rig;
        isFull = isF;
    }

    public void setLength(float l){
        length = l;
    }
    public float getLength(){
        return length;
    }

    public void setPoint(float p){
        pointY=p;
    }
    public float getPoint(){
        return pointY;
    }
    public void setRight(boolean rig){
        right=rig;
    }
    public boolean isRight(){
        return right;
    }
    public void setIsFull(boolean f){
        isFull=f;
    }
    public boolean getIsFull(){
        return isFull;
    }
}
