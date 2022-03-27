package com.geekbrains;

public class Team {
    private String name;
    private Movable[] team = new Movable[4];

    Team(Movable teamMate1, Movable teamMate2, Movable teamMate3, Movable teamMate4){
        team[0] = teamMate1;
        team[1] = teamMate2;
        team[2] = teamMate3;
        team[3] = teamMate4;
    }

    public void getTeamsInfo(){
        System.out.println("Все участники команды:");
        for (Movable m: team) {
            if(m.getClass().equals(Cat.class)){
                System.out.println(((Cat) m).getName());
            }
            if(m.getClass().equals(Human.class)){
                System.out.println(((Human) m).getName());
            }
            if(m.getClass().equals(Robot.class)){
                System.out.println(((Robot) m).getName());
            }
        }
        System.out.println("******************");
    }

    public void showResults(){
        System.out.println("Участники, прошедшие препядствия:");
        for (Movable m: team) {
            if (m.getResult()){
                if(m.getClass().equals(Cat.class)){
                    System.out.println(((Cat) m).getName());
                }
                if(m.getClass().equals(Human.class)){
                    System.out.println(((Human) m).getName());
                }
                if(m.getClass().equals(Robot.class)){
                    System.out.println(((Robot) m).getName());
                }
            }
        }
        System.out.println("******************");
    }

    public Movable[] getTeam(){
        return this.team;
    }



}
