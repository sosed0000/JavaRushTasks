package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

import java.util.Arrays;

public class Game2048 extends Game {

    private static final int SIDE = 4;
    private int[][] gameField;
    private boolean isGameStopped = false;
    private int score;


    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if (isGameStopped && key == Key.SPACE) {
            isGameStopped = false;
            createGame();
            drawScene();
            return;
        }
        if (isGameStopped) {
            return;
        }
        if (!canUserMove()) {
            gameOver();
            return;
        }
        switch (key) {
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case UP:
                moveUp();
                break;
            case DOWN:
                moveDown();
                break;
            default:
                break;

        }
        drawScene();
    }

    private void createGame() {
        gameField = new int[SIDE][SIDE];
        score = 0;
        setScore(score);
        createNewNumber();
        createNewNumber();

    }

    private void drawScene() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                setCellColoredNumber(x, y, gameField[y][x]);
            }
        }
    }

    private void createNewNumber() {
        int y, x;
        if (getMaxTileValue() == 2048) {
            win();
            return;
        }
        do {
            y = getRandomNumber(SIDE);
            x = getRandomNumber(SIDE);
        }
        while (gameField[y][x] != 0);
        gameField[y][x] = getRandomNumber(10) > 0 ? 2 : 4;
    }

    private Color getColorByValue(int value) {
        switch (value) {
            case 0:
                return Color.WHITE;
            case 2:
                return Color.RED;
            case 4:
                return Color.GREEN;
            case 8:
                return Color.MAROON;
            case 16:
                return Color.BISQUE;
            case 32:
                return Color.ORANGE;
            case 64:
                return Color.OLIVE;
            case 128:
                return Color.CHARTREUSE;
            case 256:
                return Color.FIREBRICK;
            case 512:
                return Color.LAVENDER;
            case 1024:
                return Color.AZURE;
            case 2048:
                return Color.DARKCYAN;
            default:
                return null;
        }
    }

    private void setCellColoredNumber(int x, int y, int value) {
        setCellValueEx(x, y, getColorByValue(value), value == 0 ? "" : value + "");
    }

    private boolean compressRow(int[] row) {
        int insertPosition = 0;
        boolean result = false;
        for (int x = 0; x < SIDE; x++) {
            if (row[x] > 0) {
                if (x != insertPosition) {
                    row[insertPosition] = row[x];
                    row[x] = 0;
                    result = true;
                }
                insertPosition++;
            }
        }
        return result;


        /*
        int[] tempRow = new int[SIDE];
        boolean isModified = false;
        int tempInd = 0;
        for (int x = 0; x < SIDE; x++) {
            if (row[x] != 0) {
                tempRow[tempInd] = row[x];
                tempInd++;
            }
        }
        if (!Arrays.equals(row, tempRow)) {
            isModified = true;
        }
        row = tempRow;
        return isModified;



        for (int index1 = 0; index1 < row.length; index1++) {
            boolean isModified = false;
            int val = row[index1];
            if (val == 0) {
                for (int index2 = index1++; index2 < row.length; index2++) {
                    if (row[index2] != 0);
                    row[index1] = row[index2];
                    row[index2] = 0;
                    isModified = true;
                }
            }
        }
        return true;

         */
    }

    private boolean mergeRow(int[] row) {
        int insertPosition = 0;
        boolean result = false;
        for (int x = 1; x < SIDE; x++) {
            if (row[x] == row[x - 1] && row[x] > 0) {
                row[x - 1] *= 2;
                score = score + row[x - 1];
                setScore(score);
                row[x] = 0;
                result = true;
            }
        }
        return result;
    }

    private void rotateClockwise() {
        int[][] rotatedGameField = new int[SIDE][SIDE];
        int coordinateX = 0;
        int coordinateY = 3;
        for (int[] row :
                gameField) {
            for (int value :
                    row) {
                rotatedGameField[coordinateX++][coordinateY] = value;
            }
            coordinateX = 0;
            coordinateY--;
        }
        gameField = rotatedGameField;
    }

    private void moveLeft() {
        boolean b = false;
        for (int[] row :
                gameField) {
            if (
                    compressRow(row)
                            | mergeRow(row)
                            | compressRow(row)) {
                b = true;
            }
        }
        if (b) {
            createNewNumber();
        }

    }

    private void moveRight() {
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    private void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }

    private void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private int getMaxTileValue() {
        int max = 0;
        for (int[] row :
                gameField) {
            for (int value :
                    row) {
                if (value > max) {
                    max = value;
                }
            }
        }
        return max;
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.NONE, "YOU WIN", Color.BURLYWOOD, 75);
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.NONE, "GAME OVER", Color.RED, 75);

    }


    private boolean canUserMove() {
        for (int[] row :
                gameField) {
            for (int value :
                    row) {
                if (value == 0) {
                    return true;
                }
            }
        }
        int insertPosition = 0;
        for (int[] row :
                gameField) {
            for (int x = 1; x < SIDE; x++) {
                if (row[x] == row[x - 1] && row[x] > 0) {
                    return true;
                }
            }
        }
        rotateClockwise();
        for (int[] row :
                gameField) {
            for (int x = 1; x < SIDE; x++) {
                if (row[x] == row[x - 1] && row[x] > 0) {
                    return true;
                }
            }
        }
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        return false;
    }
}
