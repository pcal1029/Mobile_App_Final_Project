
package com.example.cj.myapplication;
//package com.mobile_app_catching_bug.myapplication;

/**
 * Created by CJ on 2015-10-12.
 */
        import android.content.Context;
        import android.content.Intent;
        import android.content.res.Resources;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.Rect;
        import android.graphics.RectF;
        import android.graphics.Typeface;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Display;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.MotionEvent;
        import android.view.SurfaceView;
        import android.view.View;
        import android.view.WindowManager;

//import com.example.android_book_cj.mobile_catching_bug.Tank;

public class InitialActivity extends AppCompatActivity {
    GameView view;
    InitialActivity act = InitialActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        view = new GameView(this,act);
        super.onCreate(savedInstanceState);
        setContentView(view);
        //setContentView(new GraphicsView(this));
        Intent intent=new Intent(this.getIntent());
    }

    @Override
    public void onResume()
    {
        Log.d("start", "onResume");
        if(view.exitState()==0)
            finish();
        super.onResume();
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/


    public class GraphicsView extends View {
        private int xMax;
        private int yMax;
        private int Score = 0;
        private int halfwidth;
        private int halfheight;
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
        //Bitmap catImg;

        public GraphicsView(Context context){
            super(context);
            setBackgroundColor(Color.BLACK);
            Display  display =  ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

            xMax = display.getWidth();
            halfwidth = xMax / 2;
            yMax = display.getHeight();
            halfheight = yMax / 2;

            paint = new Paint();
            paint.setTypeface(Typeface.MONOSPACE);
            paint.setTextSize(16);

            //cat = new Cat(Cat.CAT_RIGHT);
            //cat.SetCenter(halfwidth, halfheight);
            //cat.setMax(xMax,yMax);
            bug = new BoxBug(Color.RED);
            bug.SetCenter(halfwidth,halfheight);
            //catImg = BitmapFactory.decodeResource(getResources(), R.drawable.small);

            //catImg.getWidth();
        }


        protected void onDraw(Canvas canvas) {
            //BitmapFactory.options option = getBitmapSize(imgFile);
            //canvas.drawBitmap(catImg, cat.getPointX() - catImg.getWidth() / 2, cat.getPointY() - catImg.getHeight() / 2, null);
            //canvas.drawBitmap(bitmap, halfwidth, halfheight, null);

            /*
            paint.setColor(Color.GREEN);
            paint.setTextSize(30);
            canvas.drawText("잡은 벌레수  : " + score, halfwidth, 120, paint);

            paint.setColor(Color.GRAY);
            canvas.drawCircle(halfwidth, halfheight, halfwidth - 90, paint);
            paint.setColor(Color.WHITE);
            canvas.drawCircle(halfwidth * 2 - 100, halfheight * 2 - 120, 70, paint);
            canvas.drawCircle(100, halfheight * 2 - 120, 70, paint);
            //  liveZone(halfwidth - 90)
            */


            //cat.draw(canvas);
            bug.draw(canvas);


            update();

            //invalidate();

        }

        public boolean Cresh(Rect obj_1, Rect obj_2)
        {
            if(obj_1.intersect( obj_2 ))
                return true;
            return false;
        }


        private void update(){
            cat.move();

            invalidate();
        }
        public void onSizeChanged(int w, int h, int oldw, int oldh){
            //super.onSizeChanged(w, h, oldw, oldh);
            xMax = w-1;
            yMax = h-1;
        }

        public boolean onTouchEvent(MotionEvent event){
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    cat.update();
                    break;
            }
            return true;
        }

        /*
        public boolean onTouchEvent(MotionEvent event){
            float currentX = event.getX();
            float currentY = event.getY();
            float deltaX = 0;
            float deltaY = 0;
            float scalingFactor = 5.0f / ((xMax > yMax)? yMax : xMax);
            switch (event.getAction()){
                case MotionEvent.ACTION_MOVE:
                    if((currentX>=(halfwidth*2 - 100-70))&&(currentX<=(halfwidth*2 - 100+70))) {
                        if ((currentY >= (halfheight*2 - 120 - 70)) && (currentY <= (halfheight*2 - 120 + 70))) {
                            isTouchB=true;
                            //tank.update(5);
                        }
                        else
                            isTouchB = false;
                    }

                    else
                        isTouchB = false;

                    if((currentX>=100-70)&&(currentX<=100+70)) {
                        if ((currentY >= (halfheight*2 - 120 - 70)) && (currentY <= (halfheight*2 - 120 + 70))) {
                            isTouchA=true;
                            //tank.update(5);
                        }
                        else
                            isTouchA = false;
                    }
                    else
                        isTouchA = false;

                    break;
                case MotionEvent.ACTION_DOWN:
                    //bar.x = (int)currentX
                    if((currentX>=(halfwidth*2 - 100-70))&&(currentX<=(halfwidth*2 - 100+70))) {
                        if ((currentY >= (halfheight*2 - 120 - 70)) && (currentY <= (halfheight*2 - 120 + 70))) {
                            isTouchB=true;
                            //tank.update(5);
                        }
                        else
                            isTouchB = false;
                    }
                    else
                        isTouchB = false;

                    if((currentX>=100-70)&&(currentX<=100+70)) {
                        if ((currentY >= (halfheight*2 - 120 - 70)) && (currentY <= (halfheight*2 - 120 + 70))) {
                            isTouchA=true;
                            //tank.update(5);
                        }
                        else
                            isTouchA = false;
                    }
                    else
                        isTouchA = false;

                    break;
                case MotionEvent.ACTION_UP:
                    isTouchA = false;
                    isTouchB = false;
                    break;
            }
            //if(isTouch)
            //tank.update(5);
            // bar.x = (int)currentX;

            return true;
        }*/
    }
}