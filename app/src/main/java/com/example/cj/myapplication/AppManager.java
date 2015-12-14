package com.example.cj.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by CJ on 2015-11-26.
 */
public class AppManager {
    private GameView m_gameview;
    private Resources m_resources;

    private static AppManager s_instance;

    public static AppManager getInstace() {
        if(s_instance == null) {
            s_instance = new AppManager();
        }
        return s_instance;
    }

    void setGameView(GameView m_gameview){
        m_gameview = m_gameview;
    }

    void setResources (Resources _resources) {
        m_resources = _resources;
    }
    public GameView getGameView() {
        return m_gameview;
    }

    public Resources getResources(){
        return m_resources;
    }

    public Bitmap getBitmap(int r) {
        return BitmapFactory.decodeResource(m_resources, r);
    }
}
