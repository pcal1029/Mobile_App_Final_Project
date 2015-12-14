package com.example.cj.myapplication;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by CJ on 2015-12-08.
 */
public interface Weapon {
    public void setPower(int p);
    public void fire(float x, float y, boolean d);
    public void update();
    public CopyOnWriteArrayList<Bullet> getBulletList();
}
