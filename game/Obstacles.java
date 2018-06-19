package com.example.dcf.game;

import java.util.Random;

public class Obstacles {

    private int left;
    private int top;
    private int right;
    private float x ;
    private int bottom;
    Random r;

    public Obstacles(int xmax,int ymax,int leftx){

        left = leftx;
        r = new Random();
        x = r.nextFloat();
        top = 0;
        right = left +30;
        bottom = r.nextInt(600)+200;

    }

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getRight() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }

    public void move(){

        left = left - 2;
        right= right -2;

    }


}
