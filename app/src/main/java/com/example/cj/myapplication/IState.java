package com.example.cj.myapplication;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by CJ on 2015-11-26.
 */
public interface IState {

    public void Init(); // 이 상태르 바뀌었을 때 실행할 것듣

    public void Destory();  // 다른 상태로 바뀔 때 실행할 것들 상태가 소멸될 때

    public void Update();   // 지속적으로 수행할 것들

    public void Render(Canvas canvas);  // 그려야 할 것들

    public boolean onTouchEvent(MotionEvent event); // 터치 입력 처리

    public void CheckAnimation(boolean plus); //애니메이션 처리

    public int exitState();

    public int getScore();
}
