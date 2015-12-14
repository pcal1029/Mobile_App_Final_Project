package com.example.cj.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by CJ on 2015-11-26.
 */

public class IntroState implements  IState {
    View view;
    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int DOG_NUM = 2;
    public static final int HEDGE_NUM = 3;
    public static final int CAT_IMG_NUM = 8;
    public static final int DOG_IMG_NUM = 6;
    public static final int HEDGE_IMG_NUM = 2;
    public static final int BUTTON_IMG_NUM = 2;
    public static final int BOTTOM_OBSTACLE_NUM = 5;

    float xMax;
    float yMax;

    float x;
    float y;
    Bitmap backGround;

    Cat cat;
    Bitmap cat_Ani[];

    Dog dog[];
    Bitmap dog_Ani[];

    Hedge hedge[];
    Bitmap hedge_Ani[];

    Bitmap catImg;

    boolean cat_Act = false;
    int cat_count = 0;
    int count = 0;

    int laneNum;
    float laneLength;
    int ani = 0;
    int temp = 0;
    Lane lane[][];
    int ranNum;

    int obsNum;
    Paint paint;
    int stage;
    private LaneManager laneManager;

    //private ArrayList<Fish> fish_list;
    private Bitmap fish_img[];
    private Bitmap bone_img[];


    private float buttonX;
    private float buttonY;
    private Bitmap button_Ani[];
    private Bitmap bottom_img[];
    private Bitmap bottom_Img;
    private Bitmap top_Img[];

    float bottom_limit;
    float top_limit;
    int score;
    int totalScore;

    RectF lifeBar;
    RectF case1;
    RectF case2;
    RectF case3;
    RectF case4;
    RectF top_lim;
    RectF bottom_lim;
    Bitmap life_img;

    int sound;
    private int flag;

    public IntroState(View v, float xm, float ym) {
        view = v;
        //Display display =  ((WindowManager)Context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        //aniThread = new MotionThread(this);
        // aniThread.start();
        xMax = xm;
        yMax = ym;
        stage = 0;
        score = 0;
        totalScore = 0;
        flag=1;

        backGround = BitmapFactory.decodeResource(view.getResources(), R.drawable.snowback_re);

        bottom_Img = BitmapFactory.decodeResource(view.getResources(), R.drawable.bottom_img);
        life_img = BitmapFactory.decodeResource(view.getResources(), R.drawable.life);

        top_Img = new Bitmap[3];
        top_Img[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.top25);
        top_Img[1] = BitmapFactory.decodeResource(view.getResources(), R.drawable.top27);
        top_Img[2] = BitmapFactory.decodeResource(view.getResources(), R.drawable.top38);

        button_Ani = new Bitmap[BUTTON_IMG_NUM];
        button_Ani[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.flower);
        button_Ani[1] = BitmapFactory.decodeResource(view.getResources(), R.drawable.flower_2);

        buttonX = button_Ani[0].getWidth() / 2 + 10;
        buttonY = yMax - button_Ani[0].getHeight() / 2 - 90;

        bottom_limit = buttonY - button_Ani[0].getHeight();
        top_limit = top_Img[0].getHeight() + 70;

        cat_Ani = new Bitmap[CAT_IMG_NUM];

        cat_Ani[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.cat_right);
        cat_Ani[1] = BitmapFactory.decodeResource(view.getResources(), R.drawable.cat_right_1);
        cat_Ani[2] = BitmapFactory.decodeResource(view.getResources(), R.drawable.cat_right_2);
        cat_Ani[3] = BitmapFactory.decodeResource(view.getResources(), R.drawable.cat_right_3);
        cat_Ani[4] = BitmapFactory.decodeResource(view.getResources(), R.drawable.cat_left);
        cat_Ani[5] = BitmapFactory.decodeResource(view.getResources(), R.drawable.cat_left_1);
        cat_Ani[6] = BitmapFactory.decodeResource(view.getResources(), R.drawable.cat_left_2);
        cat_Ani[7] = BitmapFactory.decodeResource(view.getResources(), R.drawable.cat_left_3);

        cat = new Cat(xMax, yMax, cat_Ani[0].getWidth(), cat_Ani[0].getHeight());
        fish_img = new Bitmap[2];
        fish_img[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.fish);
        fish_img[1] = BitmapFactory.decodeResource(view.getResources(), R.drawable.fish_1);

        bone_img = new Bitmap[3];
        bone_img[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.bone);
        bone_img[1] = BitmapFactory.decodeResource(view.getResources(), R.drawable.bone_1);
        bone_img[2] = BitmapFactory.decodeResource(view.getResources(), R.drawable.bone_2);


        dog_Ani = new Bitmap[DOG_IMG_NUM];
        dog_Ani[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.dog_right);
        dog_Ani[1] = BitmapFactory.decodeResource(view.getResources(), R.drawable.dog_right_1);
        dog_Ani[2] = BitmapFactory.decodeResource(view.getResources(), R.drawable.dog_right_2);
        dog_Ani[3] = BitmapFactory.decodeResource(view.getResources(), R.drawable.dog_left);
        dog_Ani[4] = BitmapFactory.decodeResource(view.getResources(), R.drawable.dog_left_1);
        dog_Ani[5] = BitmapFactory.decodeResource(view.getResources(), R.drawable.dog_left_2);

        dog = new Dog[DOG_NUM];
        for (int i = 0; i < DOG_NUM; i++)
            dog[i] = new Dog(xMax, yMax, dog_Ani[0].getWidth(), dog_Ani[0].getHeight());


        hedge_Ani = new Bitmap[HEDGE_IMG_NUM];
        hedge_Ani[0] = BitmapFactory.decodeResource(view.getResources(), R.drawable.hedge_right);
        hedge_Ani[1] = BitmapFactory.decodeResource(view.getResources(), R.drawable.hedge_left);

        hedge = new Hedge[HEDGE_NUM];
        for (int i = 0; i < HEDGE_NUM; i++)
            hedge[i] = new Hedge(xMax, yMax, hedge_Ani[0].getWidth(), hedge_Ani[0].getHeight());

        laneLength = dog_Ani[0].getHeight() / 4 + cat_Ani[0].getHeight();

        laneNum = (int) (bottom_limit - top_limit) / (int) laneLength;

        laneManager = new LaneManager(laneNum, laneLength, top_limit);


        laneManager.laneRandomAssign(LEFT, dog[0], 0);
        laneManager.laneRandomAssign(LEFT, dog[1], 0);


        for (int i = 0; i < HEDGE_NUM; i++)
            laneManager.laneFirstAssign(RIGHT, hedge[i], xMax - hedge_Ani[0].getWidth());


        //fish_list = new ArrayList<Fish>();
        //fish_img = BitmapFactory.decodeResource(view.getResources(), R.drawable.fish);

        lifeBar = new RectF();
        case1 = new RectF();
        case2 = new RectF();
        case3 = new RectF();
        case4 = new RectF();
        top_lim = new RectF();
        bottom_lim = new RectF();
    }

    @Override
    public void Destory() {

    }

    @Override
    public void Init() {

        //cat = new Cat();
        //catImg = AppManager.getInstace().getBitmap(R.drawable.small);
    }

    @Override
    public void Render(Canvas canvas) {
        canvas.drawBitmap(backGround, 0, 0, null);
        canvas.drawBitmap(button_Ani[1], buttonX - button_Ani[0].getWidth() / 2, buttonY - button_Ani[0].getHeight() / 2, null);
        canvas.drawBitmap(bottom_Img, buttonX + button_Ani[0].getWidth() / 2 + 30, buttonY - button_Ani[0].getHeight() / 2, null);
        canvas.drawBitmap(top_Img[count % 60 / 20], 0, 0, null);

        paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setTextSize(150);
        if(totalScore<10)
            canvas.drawText(totalScore + " ", xMax/2, yMax/2, paint);
        else
            canvas.drawText(totalScore + " ", xMax/2-75, yMax/2, paint);

        canvas.drawBitmap(cat_Ani[cat.getState()], cat.getPointX() - cat_Ani[cat.getState()].getWidth() / 2, cat.getPointY() - cat_Ani[cat.getState()].getHeight() / 2, null);

        for (int i = 0; i < DOG_NUM; i++)
            canvas.drawBitmap(dog_Ani[dog[i].getState()], dog[i].getPointX(), dog[i].getPointY(), null);

        for (int i = 0; i < HEDGE_NUM; i++)
            canvas.drawBitmap(hedge_Ani[hedge[i].getState()], hedge[i].getPointX(), hedge[i].getPointY(), null);

        for (Bullet bullet : cat.getWeapon().getBulletList()) {
            canvas.drawBitmap(fish_img[bullet.getBulletState()], bullet.getX(), bullet.getY(), null);
        }
        for (int i = 0; i < DOG_NUM; i++) {
            for (Bullet bullet : dog[i].getWeapon().getBulletList()) {
                canvas.drawBitmap(bone_img[count % 60 / 20], bullet.getX(), bullet.getY(), null);
            }
        }


        if (cat.getAni())
            cat_count++;

        count++;

        for (int i = 0; i < cat.getLife(); i++)
            canvas.drawBitmap(life_img, cat.getPointX() - cat_Ani[0].getWidth() / 2 - 1.5f * (life_img.getWidth() + 2) + (life_img.getWidth() + 2) * i, cat.getPointY() - cat_Ani[0].getHeight() - 10, null);
    }

    @Override
    public void Update() {
        totalScore = stage + score;
        top_lim.set(0, top_limit - 75, xMax, top_limit - 80);
        bottom_lim.set(0, bottom_limit + 70, xMax, bottom_limit + 75);

        cat.move();
        if (Cresh(bottom_lim, cat.getBounds()))
            cat.setLife(0);
        if (Cresh(top_lim, cat.getBounds()))
            cat.setLife(0);

        //Cat animation logic
        if (cat.getAni()) {
            if (cat_count < 80)
                cat.setState(cat_count % 40 / 10);
            if (cat_count >= 80) {
                cat.setState(0);
                cat.setAni(false);
                cat_count = 0;
            }
        }

        //Dog moev
        for (int i = 0; i < DOG_NUM; i++) {
            dog[i].move();
            //Dog animation logic
            dog[i].setState(count % 30 / 10);

            dog[count % 60 / 30].fire();
            for (Bullet bullet : cat.getWeapon().getBulletList()) {
                if (Cresh(dog[i].getBounds(), bullet.getBound())) {
                    if (dog[i].getLane().isRight()) {
                        laneManager.laneFree(dog[i]);
                        laneManager.laneRandomAssign(LEFT, dog[i], 0);
                    } else {
                        laneManager.laneFree(dog[i]);
                        laneManager.laneRandomAssign(RIGHT, dog[i], xMax - dog_Ani[0].getWidth());
                    }
                    cat.getWeapon().getBulletList().remove(bullet);
                    score++;
                }
            }

            for (Bullet bullet : dog[i].getWeapon().getBulletList()) {
                if (Cresh(cat.getBounds(), bullet.getBound())) {
                    dog[i].getWeapon().getBulletList().remove(bullet);
                    cat.setLife(cat.getLife() - 1);
                }
            }

            if (Cresh(cat.getBounds(), dog[i].getBounds())) {
                if (dog[i].getLane().isRight()) {
                    laneManager.laneFree(dog[i]);
                    laneManager.laneRandomAssign(LEFT, dog[i], 0);
                } else {
                    laneManager.laneFree(dog[i]);
                    laneManager.laneRandomAssign(RIGHT, dog[i], xMax - dog_Ani[0].getWidth());
                }
                cat.setLife(cat.getLife() - 2);
            }
        }

        //Hedge move
        if (cat.getStage() != stage) {
            Lane l = hedge[0].getLane();
            Lane a = hedge[1].getLane();

            if (l.isRight() != a.isRight())
                System.out.println("Hedge move Error in IntroState.java");

            if (l.isRight()) {
                for (int k = 0; k < HEDGE_NUM; k++) {
                    laneManager.laneFree(hedge[k]);
                    laneManager.laneRandomAssign(LEFT, hedge[k], 0);
                }
            } else {
                for (int k = 0; k < HEDGE_NUM; k++) {
                    laneManager.laneFree(hedge[k]);
                    laneManager.laneRandomAssign(RIGHT, hedge[k], xMax - hedge_Ani[0].getWidth());
                }
            }
            stage++;
        }

        //Hedge move
        for (int i = 0; i < HEDGE_NUM; i++) {
            hedge[i].move();
            if (Cresh(cat.getBounds(), hedge[i].getBounds())) {
                cat.setLife(0);
            }
        }

        lifeBar.set(cat.getPointX() - cat_Ani[cat.getState()].getWidth() / 2, cat.getPointY() - cat_Ani[cat.getState()].getHeight() / 2, cat.getPointX() - cat_Ani[cat.getState()].getWidth() / 2 + (cat_Ani[0].getWidth() - 10) * cat.getLife() / cat.getTotalLife(), cat.getPointY() - cat_Ani[cat.getState()].getHeight() / 2 - 10);
        case1.set(cat.getPointX() - cat_Ani[cat.getState()].getWidth() / 2, cat.getPointY() - cat_Ani[cat.getState()].getHeight() / 2, cat.getPointX() - cat_Ani[cat.getState()].getWidth() / 2 + 3, cat.getPointY() - cat_Ani[cat.getState()].getHeight() / 2 - 10);
        case2.set(cat.getPointX() - cat_Ani[cat.getState()].getWidth() / 2, cat.getPointY() - cat_Ani[cat.getState()].getHeight() / 2, cat.getPointX() + cat_Ani[cat.getState()].getWidth() / 2 - 10, cat.getPointY() - cat_Ani[cat.getState()].getHeight() / 2 - 3);
        case3.set(cat.getPointX() - cat_Ani[cat.getState()].getWidth() / 2, cat.getPointY() - cat_Ani[cat.getState()].getHeight() / 2 - 7, cat.getPointX() + cat_Ani[cat.getState()].getWidth() / 2 - 10, cat.getPointY() - cat_Ani[cat.getState()].getHeight() / 2 - 10);
        case4.set(cat.getPointX() + cat_Ani[cat.getState()].getWidth() / 2 - 7, cat.getPointY() - cat_Ani[cat.getState()].getHeight() / 2, cat.getPointX() + cat_Ani[cat.getState()].getWidth() / 2 - 10, cat.getPointY() - cat_Ani[cat.getState()].getHeight() / 2 - 10);
        /*
        for(Fish bullet : fish_list){
            bullet.Update();
        }*/

        //bullets = cat.getWeapon().getBulletList();
        // for(Bullet bullet : bullets)
        //    bullet.update();

        if (cat.getLife() <= 0) {
            //System.exit(0);
            flag=0;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final int action = event.getAction();

        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                float currentX = event.getX();
                float currentY = event.getY();
                if (currentX > (buttonX - button_Ani[0].getWidth() / 2) && currentX < (buttonX + button_Ani[0].getWidth() / 2) && currentY > (buttonY - button_Ani[0].getHeight() / 2) && currentY < (buttonY + button_Ani[0].getHeight() / 2)) {
                    //fish_list.add(new Fish(fish_img, cat.getPointX(), cat.getPointY()));
                    cat.fire();
                } else {
                    cat.update();
                    cat.setAni(true);
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                float multiX = event.getX(1);
                float multiY = event.getY(1);
                if (multiX > (buttonX - button_Ani[0].getWidth() / 2) && multiX < (buttonX + button_Ani[0].getWidth() / 2) && multiY > (buttonY - button_Ani[0].getHeight() / 2) && multiY < (buttonY + button_Ani[0].getHeight() / 2)) {
                    //fish_list.add(new Fish(fish_img, cat.getPointX(), cat.getPointY()));
                    cat.fire();
                } else {
                    cat.update();
                    cat.setAni(true);
                }
                break;
        }
        return true;
    }


    public boolean Cresh(RectF obj_1, RectF obj_2)
    {
        if(obj_1.intersect( obj_2 ))
            return true;
        return false;
    }

    @Override
    public void CheckAnimation(boolean plus){
        //cat.setState(cat.);
    }

    /*
    public boolean laneAssign(int right, Enemy enemy){
        int ranNum =new Random().nextInt(laneNum);
        while(lane[right][ranNum].getIsFull()==true)
            ranNum=new Random().nextInt(laneNum);


        enemy.setLane(lane[right][ranNum]);
        return true;
    }

    public boolean laneFree(Enemy enemy){
        Lane l = enemy.getLane();

        if(l.getIsFull()==false)    //Error
            return false;

        l.setIsFull(false);

        return true;
    }
    */

    public int exitState(){
        return flag;
    }

    public int getScore(){return totalScore;}
}
