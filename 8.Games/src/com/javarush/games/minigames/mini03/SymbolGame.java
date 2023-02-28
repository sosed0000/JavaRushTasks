package com.javarush.games.minigames.mini03;

import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Color;

/* 
Простая программа
*/

public class SymbolGame extends Game {

    //напишите тут ваш код
    public void initialize() {
        setScreenSize(8,3);
        String result = "JAVARUSH"; //текст, который требуется ввести
        int x = 0; int y = 1; //координаты первой буквы
        //вывод текста
        for (char ch:
             result.toCharArray()) {
            setCellValueEx(x, y, Color.ORANGE,ch + "");
            x++;
        }

    }
}
