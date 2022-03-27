package com.geekbrains;

public class Robot implements Movable {
    private int height;
    private int speed;
    private String name;
    private boolean result;


    Robot(int height, int speed, String name){
        this.height = height;
        this.speed = speed;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public boolean getResult(){ return this.result; }

    public boolean Jump(int height) {
        if(this.height > height){
            System.out.printf("%s прыгает на высоту %d \n", this.name,this.height);
            result = true;
            return true;
        }
        else {
            System.out.printf("%s не смог прыгнуть на высоту %d и выбыл из соревнований \n", this.name, height);
            result = false;
            return false;
        }

    }

    public boolean run(int speed) {
        if(this.speed > speed){
            System.out.printf("%s пробежал со скоростью %d \n", this.name,this.speed);
            result = true;
            return true;
        }
        else {
            System.out.printf("%s не смог пробежать со скоростью %d и выбыл из соревнований \n", this.name, speed);
            result = false;
            return false;
        }

    }

}
