package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";
    private int countFlags;
    private boolean isGameStopped;
    private int countClosedTiles = SIDE * SIDE;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellValue(x, y, "");
                setCellColor(x, y, Color.ORANGE);

            }
        }
        countMineNeighbors();
        countFlags = countMinesOnField;
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private void countMineNeighbors() {
        for (GameObject[] gameObjects : //для каждой строки массиива gameField
                gameField) {
            for (GameObject gameObject : //для каждой ячейки
                    gameObjects) {
                if (!gameObject.isMine) //если рассматриваемая ячейка не заминирована
                    //получаем список соседей для этой ячейки и если сосед заминирован инкрементируем countMineNeighbors ячейки
                    for (GameObject neighbor :
                            getNeighbors(gameObject)) {
                        if (neighbor.isMine) gameObject.countMineNeighbors++;
                    }

            }

        }
    }

    private void openTile(int x, int y) {
        GameObject current = gameField[y][x];
        if (isGameStopped || current.isOpen || current.isFlag) return;
        current.isOpen = true;
        countClosedTiles--;
        if (current.isMine) {
            setCellValueEx(x, y, Color.RED, MINE);
            gameOver();
        } else {
            score+=5;
            setScore(score);
            if (countClosedTiles == countMinesOnField) win();
            setCellNumber(x, y, current.countMineNeighbors);
            if (current.countMineNeighbors == 0) {
                setCellValue(x, y, "");
                for (GameObject currentNeighbor :
                        getNeighbors(current)) {
                    if (!currentNeighbor.isOpen)
                        openTile(currentNeighbor.x, currentNeighbor.y);
                }
            }
        }
        setCellColor(x, y, Color.GREEN);
    }

    private void markTile(int x, int y) {
        if (isGameStopped) return;
        GameObject current = gameField[y][x];
        if (!current.isOpen) {
            if (current.isFlag) {
                current.isFlag = false;
                countFlags++;
                setCellValue(current.x, current.y, "");
                setCellColor(current.x, current.y, Color.ORANGE);
            } else {
                if (countFlags > 0) {
                    current.isFlag = true;
                    countFlags--;
                    setCellValue(current.x, current.y, FLAG);
                    setCellColor(current.x, current.y, Color.YELLOW);
                }
            }
        }

    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.NONE, "Game Over!", Color.RED, 80);
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.NONE, "You are WIN!", Color.GOLD, 80);
    }

    private void restart() {
        isGameStopped = false;
        countClosedTiles = SIDE * SIDE;
        score = 0;
        countMinesOnField = 0;
        setScore(0);
        createGame();

    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (!isGameStopped)
        openTile(x, y);
        else restart();
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        markTile(x, y);
    }
}