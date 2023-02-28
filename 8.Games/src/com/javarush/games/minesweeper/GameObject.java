package com.javarush.games.minesweeper;

public class GameObject {
    public int x; //координата х (колонка)
    public int y; //координата y (строка)
    public boolean isMine; //мина/не мина
    public int countMineNeighbors; //количество замнированных соседей
    public boolean isOpen;
    public boolean isFlag;
    GameObject(int x, int y, boolean isMine) {
        this.x = x;
        this.y = y;
        this.isMine = isMine;
    }
}
