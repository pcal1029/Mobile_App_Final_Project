package com.example.cj.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by CJ on 2015-11-27.
 */

public class SpriteAnimation extends GraphicObject {
    private Rect m_Rect;
    private int m_fps;
    private int m_iFrames;
    private int m_FrameTimer;   //

    //애니메이션 얼마나 진행되었는지
    private int m_CurrentFrame;
    private int m_SpriteWidth;
    private int m_SpriteHeight;

    public SpriteAnimation(Bitmap bitmap) {
        super (bitmap);
        m_Rect = new Rect(0,0,0,0);
        m_FrameTimer=0;
        m_CurrentFrame=0;
    }

    public void InitSpriteData(int _width, int _height, int _fps, int iFrame){
        m_SpriteWidth = _width;
        m_SpriteHeight = _height;
        m_Rect.top = 0;
        m_Rect.bottom = m_SpriteHeight;
        m_Rect.left = 0;
        m_Rect.right = m_SpriteWidth;
        m_fps = 1000/_fps;
        m_iFrames = iFrame;
    }

    @Override
    public void Draw(Canvas canvas){
        Rect dest = new Rect(m_x, m_y, m_x + m_SpriteWidth, m_y + m_SpriteHeight);
        canvas.drawBitmap(m_bitmap, m_Rect, dest ,null);
    }
}
