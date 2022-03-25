package com.geekbrains;

public class Treadmill extends Barrier {
    private int speed;

    Treadmill(int speed){
        this.speed = speed;
    }

    public int getSpeed(){
        return this.speed;
    }

}
