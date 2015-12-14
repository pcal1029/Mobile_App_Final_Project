package com.example.cj.myapplication;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import static java.sql.DriverManager.println;

/**
 * Created by CJ on 2015-11-26.
 */
public class GameViewThread extends  Thread{
    private SurfaceHolder m_surfaceHolder;
    private GameView m_gameview;
    private boolean m_run = false;
    int flag=1;

    public GameViewThread(SurfaceHolder surfaceHolder, GameView gameview){
        m_surfaceHolder = surfaceHolder;
        m_gameview = gameview;
    }

    public void setRunning(boolean run) {
        m_run = run;
    }

    @Override
    public void run() {
        Canvas _canvas;
        while(m_run) {
            _canvas = null;

            try {this.sleep(7);}
            catch(InterruptedException e){
            }
            try {
                m_gameview.update();
                _canvas = m_surfaceHolder.lockCanvas(null);
                synchronized (m_surfaceHolder) {
                    m_gameview.onDraw(_canvas);
                }
            } finally {
                if(_canvas != null)
                    m_surfaceHolder.unlockCanvasAndPost(_canvas);
            }
        }
    }
}
