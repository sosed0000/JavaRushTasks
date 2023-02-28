package com.javarush.games.minigames.mini05;

import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Color;

/* 
Цвета радуги
*/

public class RainbowGame extends Game {

    //напишите тут ваш код
    public void initialize () {
        setScreenSize(10, 7);
        Color lineColor = Color.NONE;
        for (int y = 0; y < 7; y++) {
            switch (y){
                case 0 : lineColor = Color.RED; break;
                case 1 : lineColor = Color.ORANGE; break;
                case 2 : lineColor = Color.YELLOW; break;
                case 3 : lineColor = Color.GREEN; break;
                case 4 : lineColor = Color.BLUE; break;
                case 5 : lineColor = Color.INDIGO; break;
                case 6 : lineColor = Color.VIOLET; break;
            }
            for (int x = 0; x < 10; x++) {
                setCellColor(x, y, lineColor);

            }
        }
    };
}
