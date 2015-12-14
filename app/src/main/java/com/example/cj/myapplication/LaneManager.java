package com.example.cj.myapplication;

import java.util.Random;

/**
 * Created by CJ on 2015-12-02.
 */
public class LaneManager {
    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    private Lane lane[][];
    private float laneLength;
    private int number;

    LaneManager(int laneNum, float length, float startingPoint){
        lane = new Lane[2][laneNum];
        laneLength = length;
        number = laneNum;

        for(int i= 0; i<laneNum; i++){
            lane[RIGHT][i] = new Lane(laneLength, startingPoint + laneLength*i, true, false);
            lane[LEFT][i] = new Lane(laneLength, startingPoint + laneLength*i, false, false);
        }
    }

    public boolean isFull(int n){
        if(n!=0 && n!=1) {
            System.out.println("LaneManager's Function 'isFull' is error ");
            return false;
        }

        for(int i=0; i<number; i++){
            if(!lane[n][number].isFull)
                return false;
        }

        return true;
    }
    public boolean laneFirstAssign(int direction, Enemy enemy, float xPos) {
        int ranNum=0;

        while(lane[direction][ranNum].getIsFull()==true)
            ranNum++;

        if(lane[direction][ranNum].getIsFull())
            System.out.println("LaneManager's Function 'laneFirstAssign' is error ");

        enemy.setLane(lane[direction][ranNum]);
        enemy.setPointY(lane[direction][ranNum].getPoint());
        enemy.setPointX(xPos);

        lane[direction][ranNum].setIsFull(true);

        return true;
    }

    public boolean laneRandomAssign(int direction, Enemy enemy, float xPos){
        if(direction!=0 && direction!=1) {
            System.out.println("LaneManager's Function 'laneRandomAssign'-1 is error ");
            return false;
        }

        int ranNum =new Random().nextInt(number);
        while(lane[direction][ranNum].getIsFull()==true)
            ranNum=new Random().nextInt(number);

        if(lane[direction][ranNum].getIsFull())
            System.out.println("LaneManager's Function 'laneRandomAssign'-2 is error ");

        enemy.setLane(lane[direction][ranNum]);
        enemy.setPointY(lane[direction][ranNum].getPoint());
        enemy.setPointX(xPos);

        lane[direction][ranNum].setIsFull(true);

        return true;
    }

    public boolean laneFree(Enemy enemy){
        Lane l = enemy.getLane();

        if(l.getIsFull()==false)    //Error
            return false;

        l.setIsFull(false);

        return true;
    }

    public Lane getLane(int direction, int num){
        return lane[direction][num];
    }
}
