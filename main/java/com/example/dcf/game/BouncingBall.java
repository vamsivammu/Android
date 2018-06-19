package com.example.dcf.game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.opengl.GLSurfaceView;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class BouncingBall extends View {
    private int xmin=0;
    private int ymin =0;
    private int xmax;
    private int ymax;
    private float e=0.8f;
    private float ballspeedy = 0;
    private float ballspeedx = 0;
    private float ballacc = 1;
    private float ballrad = 40;
    private float ballcx = ballrad+100;
    private  float ballcy = ballrad+300;
    private Paint paint;
    private RectF bounds;
    private ArrayList<BGObjects> objs;
    private ArrayList<Obstacles> obst;
    private boolean b = true;
    public BouncingBall(Context context,int x,int y) {
        super(context);
        xmax=x;
        ymax=y;
        objs= new ArrayList<BGObjects>();
        obst = new ArrayList<Obstacles>();
        bounds = new RectF();
        paint = new Paint();
        for(int i =0;i<80;i++){

            BGObjects o = new BGObjects(x,y,  ballspeedx);
            objs.add(o);
            //this.setFocusableInTouchMode(b);

        }
        for(int j =0;j<40;j++){
            Random r = new Random();

            Obstacles ob = new Obstacles(x,y,400+j*200);
            obst.add(ob);
        }

    }
    @Override
    protected void onDraw(Canvas canvas){
        bounds.set(ballcx-ballrad,ballcy-ballrad,ballcx+ballrad,ballcy+ballrad);
paint.setAntiAlias(true);
paint.setColor(Color.GREEN);
        try {
            Thread.sleep(0);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        canvas.drawOval(bounds,paint);

        for(BGObjects o : objs){
            paint.setStrokeWidth(4);
            paint.setColor(Color.WHITE);
            canvas.drawPoint(o.getX(),o.getY(),paint);
        }
        for(Obstacles ob:obst){

            paint.setColor(Color.RED);
            canvas.drawRect(ob.getLeft(),ob.getTop(),ob.getRight(),ob.getBottom(),paint);
        }
//        for(BGObjects o:objs){
//            if(o.getX()<xmin){
//                objs.remove(o);
//
//                BGObjects bgObjects = new BGObjects(xmax,ymax);
//                objs.add(bgObjects);
//            }
//
//        }
        for(BGObjects o : objs){

            o.objmove();
        }
        for(Obstacles o : obst){

            o.move();
            if(collide(o)){
                b=false;
                ballacc = 3;
            }
            if(!collide(o) && isRight(o) && b){

                o.update();

            }

        }


        update();

    invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(b){
        ballspeedy = -20;
        return true;}
        else {return false;}

    }

    public boolean isRight(Obstacles o){

        if(Math.abs(ballcx-ballrad)>o.getRight()){

            return true;
        }

     else return false;

    }

    public boolean collide(Obstacles o){

        float closex,closey;

        closex = Math.max(o.getLeft(),Math.min(ballcx,o.getRight()));
        closey = Math.max(o.getTop(),Math.min(ballcy,o.getBottom()));


        float distx = (closex-ballcx)*(closex-ballcx);
        float disty = (closey-ballcy)*(closey-ballcy);

        if(distx+disty<=(ballrad*ballrad)){

            return true;

        }
        else {
            return false;
        }


    }


    public void update(){
        ballspeedy= ballacc + ballspeedy;
        ballcx = ballcx + ballspeedx;
        ballcy = ballcy + ballspeedy;
        if(ballcy + ballrad>ymax){
            ballcy = ymax- ballrad;
            ballspeedy = -ballspeedy * e;
        }else if(ballcy - ballrad<ymin){

            ballcy =ymin +ballrad;
            ballspeedy = - ballspeedy * e;

        }



    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        xmax = w-1;
        ymax = h-1;
    }


}
