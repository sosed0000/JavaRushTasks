package com.javarush.games.minigames.mini04;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

/* 
Считаем клетки
*/

public class CountGame extends Game {

    @Override
    public void initialize() {
        setScreenSize(10, 5);
        showGrid(false);
        for (int j = 0; j < 50; j++) {
            int x = getRandomNumber(10);
            int y = getRandomNumber(5);
            setCellColor(x, y, Color.GREEN);
            setCellNumber(x, y, getRandomNumber(100));
        }
        showResult();
    }

    public void showResult() {
        //напишите тут ваш код
        int sum = 0; //сумма цисел в зелёных клетках
        int greenCellsCount = 0; //количество зелёных клеток
       for (int x = 0; x < getScreenWidth(); x++) {
           for (int y = 0; y < getScreenHeight(); y++){
           if (getCellColor(x, y) == (Color.GREEN)) {
           sum = sum + getCellNumber(x, y);
           greenCellsCount++;
           }
           }
       }
       printSum(sum);
       printCountOfGreenCells(greenCellsCount);
    }

    private void printSum(int sum) {
        System.out.println("Сумма всех чисел = " + sum);
    }

    private void printCountOfGreenCells(int count) {
        System.out.println("Количество зеленых клеток = " + count);
    }
}
