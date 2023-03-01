package com.javarush.task.pro.task17.task1716;

/* 
Дорожное движение
*/

public interface Vehicle {
    default public void start() {
        System.out.println("Начинаю движение.");
    }

    public void move();

    default public void stop() {
        System.out.println("Останавливаюсь.");
    }
}
