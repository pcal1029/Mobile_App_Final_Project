package com.example.cj.myapplication;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by CJ on 2015-12-03.
 */
public class Fish implements Weapon{
    int power;
    CopyOnWriteArrayList<Bullet> bullets;
    int speed;
    long lastTime;
    int img_Num;

    public Fish(){
        bullets = new CopyOnWriteArrayList<Bullet>();
        lastTime = 0;
        power=1;
        speed=8;
        img_Num=1;
    }

    public void fire(float x, float y, boolean direction) {
        long thisTime = System.currentTimeMillis();
        //System.out.println("시간 " + thisTime);

        if(thisTime - lastTime>=700) {
            if (direction)
                bullets.add(new Bullet(x, y, speed, direction, 0));
            else
                bullets.add(new Bullet(x, y, speed, direction, img_Num));

            lastTime=thisTime;
        }
        /*
        if (direction)
            bullets.add(new Bullet(x, y, speed, direction, 0));
        else
            bullets.add(new Bullet(x, y, speed, direction, img_Num));
        */
    }

    public void update(){
        for(Bullet b : bullets){
            b.update();
        }
    }

    public void setPower(int p){
        power = p;
    }

    public void Update(){
        //this.setPosition(this.getX()+8, this.getY());
    }

    public CopyOnWriteArrayList<Bullet> getBulletList(){
        return bullets;
    }
}
