package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    private List<Horse> horses;
    static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run() {
        for (int i = 0; i < 80; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        //horses.forEach(Horse::move);
        for (Horse horse : horses) {
            horse.move();
        }
    }

    public void print() {
        //horses.forEach(Horse::print);
        for (Horse horse : horses) {
            horse.print();
        }
        System.out.println();
        System.out.println();
    }

    public Horse getWinner() {
        Horse winner = horses.get(0);
        for (int i = 1; i < horses.size(); i++) {
            Horse h = horses.get(i);
            if (h.getDistance() > winner.getDistance()) {
                winner = h;
            }
        }
        return winner;
    }

    public void printWinner() {
        System.out.printf("Winner is %s!", getWinner().getName());
    }


    public static void main(String[] args) {

        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Кот", 3, 0));
        horses.add(new Horse("Буся", 3, 0));
        horses.add(new Horse("Джесси", 3, 0));

        game = new Hippodrome(horses);
        game.run();
        game.printWinner();


    }

}
