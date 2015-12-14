package com.example.cj.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

/**
 * Created by CJ on 2015-11-26.
 */
public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    private GameViewThread m_thread;
    private IState m_state;

    int uFlag=0;

    private float xMax;
    private float yMax;
    private int Score = 0;
    private float halfwidth;
    private float halfheight;
    private Rect ballBounds;
    private Paint paint;
    private Tank tank;
    private boolean isTouchB=false;
    private boolean isTouchA=false;
    private BoxBug boxBugs[];
    private TriangleBug triBugs[];
    private CircleBug cirBugs[];
    private Cat cat;
    private BoxBug bug;
    Rect liveZone;
    int score = 0;
    int s=0;
    Bitmap catImg;
    private GraphicObject m_Image;
    private SpriteAnimation m_spr;
    private int flag;

    SoundPool soundPool;
    MediaPlayer sound_1;
    MediaPlayer sound_2;
    MediaPlayer sound_back;
    InitialActivity act;
    Context m_context;

    public GameView(Context context, InitialActivity a) {
        super(context);
        m_context = context;
        getHolder().addCallback(this);
        m_thread = new GameViewThread(getHolder(), this);
        flag = 1;
        act=a;

        soundPool=new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        sound_back = MediaPlayer.create(context, R.raw.background);
        sound_back.start();

        //setFocusable(true);

        AppManager.getInstace().setGameView(this);
        AppManager.getInstace().setResources(getResources());

        getHolder().addCallback(this);

        setBackgroundColor(Color.BLACK);
        Display display =  ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        xMax = display.getWidth();
        halfwidth = xMax / 2;
        yMax = display.getHeight();
        halfheight = yMax / 2;

        ChangeGameState(new IntroState(this,xMax, yMax));

        paint = new Paint();
        paint.setTypeface(Typeface.MONOSPACE);
        paint.setTextSize(16);


    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        m_thread.setRunning(true);
        m_thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        m_thread.setRunning(false);
        Log.d("destory", "쓰레드 중지 전");

        while(retry) {
            try {
                //m_thread.interrupt();
                //Thread.interrupted();
                m_thread.join();
                retry = false;
                Log.d("destory", "쓰레드 중지 -> 조인은 됨.");
            } catch (InterruptedException e) {
                Log.d("destory", "Exception");
            }
        }
        Log.d("destory", "쓰레드 중지 끝.");
    }

    protected void onDraw(Canvas canvas) {
        //canvas.drawBitmap(catImg, cat.getPointX() - catImg.getWidth() / 2, cat.getPointY() - catImg.getHeight() / 2, null);
       // m_spr.Draw(canvas);

        //update();
        //m_Image.Draw(canvas);

        if(canvas!=null)
         m_state.Render(canvas);
    }

    public void update(){
        //cat.move();
        m_state.Update();
        postInvalidate();

        if(uFlag==0) {
            if (m_state.exitState() == 0) {
                //Intent intent = new Intent(act, FinalActivity.class);
                //act.startActivity(intent);
                Intent intent = new Intent(m_context, FinalActivity.class);
                intent.putExtra("stage", m_state.getScore());
                m_context.startActivity(intent);
                act.finish();
                uFlag = 1;
            }
        }
    }

    public void ChangeGameState(IState _state) {
        if(m_state!=null)
            m_state.Destory();
        _state.Init();
        m_state = _state;
    }

    public void onSizeChanged(int w, int h, int oldw, int oldh){
        //super.onSizeChanged(w, h, oldw, oldh);
        xMax = w-1;
        yMax = h-1;
    }


    public boolean onTouchEvent(MotionEvent event){
        m_state.onTouchEvent(event);
        /*
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                m_state.onTouchEvent(event);
                break;
        }*/

        //m_state.CheckAnimation();
        //m_state.CheckAnimation();
        return true;
    }

    public int exitState(){
        return flag;
    }
}
