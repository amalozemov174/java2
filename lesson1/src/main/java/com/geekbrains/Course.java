package com.geekbrains;

import java.util.Collections;

public class Course {
    private Barrier[] barriers;

    Course(Barrier[] barriers){
        this.barriers = barriers;
    }

    public void doIt(Team team){
        for (Movable m: team.getTeam()) {

            for(Barrier b : barriers){
                if(b.getClass().equals(Treadmill.class)){
                    Treadmill t = (Treadmill)b;
                    if(!m.run(t.getSpeed())){
                        break;
                    }
                }
                if(b.getClass().equals(Wall.class)){
                    Wall w = (Wall) b;
                    if(!m.Jump(w.getHeight())){
                        break;
                    }
                }
            }

        }
    }


}
