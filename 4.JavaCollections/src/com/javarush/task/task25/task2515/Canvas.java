package com.javarush.task.task25.task2515;

import java.util.Arrays;

public class Canvas {
    private int width;


    private int height;
    private char[][] matrix;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new char[height][width];
    }

    public void setPoint(double x, double y, char c) {
        int dx = (int) Math.round(x);
        int dy = (int) Math.round(y);
        if (dx >= 0 && dx < matrix[0].length && dy >= 0 && dy < matrix.length) {
            matrix[dy][dx] = c;
        }
    }

    public void drawMatrix(double x, double y, int[][] matrix, char c) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    setPoint(x + j, y + i, c);
                }
            }
        }
    }

    public void clear() {
        this.matrix = new char[height][width];
    }

    public void print() {
        for (char[] chars : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.println(chars[j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getMatrix() {
        return matrix;
    }


}
