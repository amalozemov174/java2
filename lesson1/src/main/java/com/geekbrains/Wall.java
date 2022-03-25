package com.geekbrains;

public class Wall extends Barrier {
    private int height;

    Wall(int height){
        this.height = height;
    }

    public int getHeight(){
        return this.height;
    }

//    public void Jump(Jumpable jump) {
//        if (jump.getClass().equals(Cat.class)){
//            if(((Cat)jump).getHeight() > this.height){
//                System.out.printf(((Cat)jump).getName() + " успешно ");
//                ((Cat)jump).Jump();
//            }
//            else {
//                System.out.println(((Cat)jump).getName() + " не смог перепрыгнуть");
//            }
//        }
//    }
}
