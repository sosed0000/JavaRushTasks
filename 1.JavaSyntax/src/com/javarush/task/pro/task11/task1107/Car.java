package com.javarush.task.pro.task11.task1107;

/* 
Двигатель — сердце автомобиля
*/

public class Car {
    //напишите тут ваш код
    Engine engine = new Engine();
    public class Engine {
        private boolean isRunning = false;
        public void start() {isRunning = true;}
        public void stop() {isRunning = false;}
    }

}
