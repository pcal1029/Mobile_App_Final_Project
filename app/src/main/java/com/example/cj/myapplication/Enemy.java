package com.example.cj.myapplication;

/**
 * Created by CJ on 2015-12-02.
 */
public interface Enemy {
    public void setLane(Lane lane);
    public Lane getLane();
    public void setPointX(float x);
    public void setPointY(float y);
}
