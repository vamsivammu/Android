package com.example.dcf.game;

import java.util.Random;

public class Obstacles {

    private float left;
    private float top;
    private float right;
    private float x ;
    private int bottom;
    Random r;
    private int j;

    public Obstacles(int xmax,int ymax,int leftx){

        left = leftx;
        r = new Random();

        top = 0;
        right = left +30;
        bottom = r.nextInt(400)+200;

    }

    public float getLeft() {
        return (float)left;
    }

    public float getTop() {
        return (float)top;
    }

    public float getRight() {
        return (float)right;
    }

    public float getBottom() {
        return (float)bottom;
    }

    public void move(){

        left = left - 2;
        right= right -2;



    }
    public void update(){
        j = j+1;
        left = 8230 + j*200;
        right = left +30;
        bottom = r.nextInt(400)+200;

    }

}
