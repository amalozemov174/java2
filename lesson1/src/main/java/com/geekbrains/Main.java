package com.geekbrains;

//1. Создайте три класса Человек, Кот, Робот, которые не наследуются от одного класса. Эти
//        классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в
//        консоль).
//        2. Создайте два класса: беговая дорожка и стена, при прохождении через которые, участники
//        должны выполнять соответствующие действия (бежать или прыгать), результат выполнения
//        печатаем в консоль (успешно пробежал, не смог пробежать и т.д.).
//        3. Создайте два массива: с участниками и препятствиями, и заставьте всех участников пройти
//        этот набор препятствий.
//        4. * У препятствий есть длина (для дорожки) или высота (для стены), а участников ограничения
//        на бег и прыжки. Если участник не смог пройти одно из препятствий, то дальше по списку он
//        препятствий не идет.


public class Main {
    public static void main(String[] args){
        Treadmill tmd = new Treadmill(30);
        Wall wall = new Wall(10);

        Barrier[] barriers = new Barrier[2];
        barriers[0] = tmd;
        barriers[1] = wall;
        Course c = new Course(barriers);
        Team t = new Team(new Cat(5, 40, "Кот1"), new Human(1, 45, "Человек1"), new Robot(50, 50, "Робот1"), new Cat(15, 15, "Кот2"));
        t.getTeamsInfo();
        c.doIt(t);
        t.showResults();






    }
}