package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<GameObject> snakeParts = new ArrayList<>(3);
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    public boolean isAlive = true;
    private Direction direction = Direction.LEFT;

    Snake(int x, int y) {
        snakeParts.add(new GameObject(x, y));
        snakeParts.add(new GameObject(x + 1, y));
        snakeParts.add(new GameObject(x + 2, y));
    }

    public void draw(Game game) {
        for (GameObject snakePart :
                snakeParts) {
            if (snakeParts.get(0).equals(snakePart)) {
                game.setCellValueEx(
                        snakePart.x,
                        snakePart.y,
                        Color.NONE,
                        HEAD_SIGN,
                        isAlive ? Color.BLUE : Color.RED,
                        75);
            } else {
                game.setCellValueEx(
                        snakePart.x,
                        snakePart.y,
                        Color.NONE,
                        BODY_SIGN,
                        isAlive ? Color.BLUE : Color.RED,
                        75);
            }
        }
    }

    public void setDirection(Direction direction) {
        if ((this.direction == Direction.UP && direction == Direction.DOWN)
                || (this.direction == Direction.DOWN && direction == Direction.UP)
                || (this.direction == Direction.RIGHT && direction == Direction.LEFT)
                || (this.direction == Direction.LEFT && direction == Direction.RIGHT)
                || (this.direction == Direction.LEFT && snakeParts.get(0).x == snakeParts.get(1).x)
                || (this.direction == Direction.RIGHT && snakeParts.get(0).x == snakeParts.get(1).x)
                || (this.direction == Direction.UP && snakeParts.get(0).y == snakeParts.get(1).y)
                || (this.direction == Direction.DOWN && snakeParts.get(0).y == snakeParts.get(1).y)

        ) {
            return;
        }
        this.direction = direction;
    }

    public void move(Apple apple) {
        GameObject newHead = createNewHead();
        if (checkCollision(newHead)) {
            isAlive = false;
            return;
        }
        if (newHead.x < 0 || newHead.x > SnakeGame.WIDTH - 1 || newHead.y < 0 || newHead.y > SnakeGame.HEIGHT - 1) {
            isAlive = false;
            return;
        }
        snakeParts.add(0, newHead);
        if (newHead.x == apple.x && newHead.y == apple.y) {
            apple.isAlive = false;
        }
        else {
            removeTail();
        }
    }

    public GameObject createNewHead() {
        int headX = snakeParts.get(0).x;
        int headY = snakeParts.get(0).y;
        switch (direction) {
            case UP:
                headY--;
                break;
            case DOWN:
                headY++;
                break;
            case LEFT:
                headX--;
                break;
            case RIGHT:
                headX++;
                break;
            default:
                break;
        }


        /*
        switch (direction) {
            case UP -> headY--;
            case DOWN -> headY++;
            case LEFT -> headX--;
            case RIGHT -> headX++;
        }

         */
        return new GameObject(headX, headY);
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }

    public boolean checkCollision(GameObject gameObject) {
        for (GameObject snakePart:
             snakeParts) {
            if (gameObject.x == snakePart.x && gameObject.y == snakePart.y) {
                isAlive = false;
                return true;
            }
        }
        return false;
    }

    public int getLength() {
        return snakeParts.size();
    }

}
