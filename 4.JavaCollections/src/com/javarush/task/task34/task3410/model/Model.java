package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Set;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    LevelLoader levelLoader;

    public Model() {
        try {
            levelLoader = new LevelLoader(Paths.get(getClass().getResource("../res/levels.txt").toURI()));
        } catch (URISyntaxException ignored) {
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        restartLevel(++currentLevel);
    }


    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        for (Wall wall : getGameObjects().getWalls()) {
            if (gameObject.isCollision(wall, direction)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        Player player = gameObjects.getPlayer();
        Set<Box> boxes = gameObjects.getBoxes();
        Set<Wall> walls = gameObjects.getWalls();
        for (Box box :
                boxes) {
            if (player.isCollision(box, direction)) {
                for (Box box1 :
                        boxes) {
                    if (box != box1) {
                        if (box.isCollision(box1, direction)) {
                            return true;
                        }
                    }
                }
                for (Wall wall :
                        walls) {
                    if (box.isCollision(wall, direction)) {
                        return true;
                    }
                }
                int dx = box.getX() - player.getX();
                int dy = box.getY() - player.getY();
                box.move(dx, dy);
            }
        }
        return false;
    }

    public void checkCompletion() {
        for (Home home : gameObjects.getHomes()) {
            boolean boxAtHome = false;
            for (Box box : gameObjects.getBoxes()) {
                if (home.getX() == box.getX() && home.getY() == box.getY()) {
                    boxAtHome = true;
                }
            }
            if (!boxAtHome) {
                return;
            }
        }
        eventListener.levelCompleted(currentLevel);
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction)
                || checkBoxCollisionAndMoveIfAvailable(direction)) {
            return;
        }
        int dx = 0;
        int dy = 0;
        switch (direction) {
            case LEFT:
                dx = -Model.FIELD_CELL_SIZE;
                break;
            case RIGHT:
                dx = Model.FIELD_CELL_SIZE;
                break;
            case UP:
                dy = -Model.FIELD_CELL_SIZE;
                break;
            case DOWN:
                dy = Model.FIELD_CELL_SIZE;
                break;
        }
        player.move(dx, dy);
        checkCompletion();
    }
}
