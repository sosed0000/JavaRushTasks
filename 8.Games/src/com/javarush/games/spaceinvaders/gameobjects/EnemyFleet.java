package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.ArrayList;
import java.util.List;

public class EnemyFleet {
    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 10;
    private static final int STEP = ShapeMatrix.ENEMY.length + 1;
    private List<EnemyShip> ships;
    private Direction direction = Direction.RIGHT;

    public EnemyFleet() {
        createShips();
    }


    private void createShips() {
        ships = new ArrayList<>();
        for (int x = 0; x < COLUMNS_COUNT; x++) {
            for (int y = 0; y < ROWS_COUNT; y++) {
                ships.add(new EnemyShip(x * STEP, (y * STEP + 12)));
            }
        }
        ships.add(new Boss(STEP * COLUMNS_COUNT / 2 - ShapeMatrix.BOSS_ANIMATION_FIRST.length / 2 - 1, 5));
    }

    public void draw(Game game) {
        ships.forEach(enemyShip -> enemyShip.draw(game));
    }

    private double getLeftBorder() {
        double leftBorder = ships.get(0).x;
        for (EnemyShip ship :
                ships) {
            if (leftBorder > ship.x) {
                leftBorder = ship.x;
            }
        }
        return leftBorder;

    }

    private double getRightBorder() {
        double rightBorder = ships.get(0).x + ships.get(0).width;
        for (EnemyShip ship :
                ships) {
            if (rightBorder < (ship.x + ship.width)) {
                rightBorder = (ship.x + ship.width);
            }
        }
        return rightBorder;

    }

    private double getSpeed() {
        if (3.0 / ships.size() < 2.0) {
            return 3.0 / ships.size();
        }
        return 2.0;
    }

    public void move() {
        if (ships.isEmpty()) {
            return;
        }
        if (direction == Direction.LEFT && getLeftBorder() < 0) {
            direction = Direction.RIGHT;
            ships.forEach(enemyShip -> enemyShip.move(Direction.DOWN, getSpeed()));
        } else if (direction == Direction.RIGHT && getRightBorder() > SpaceInvadersGame.WIDTH) {
            direction = Direction.LEFT;
            ships.forEach(enemyShip -> enemyShip.move(Direction.DOWN, getSpeed()));
        } else {
            ships.forEach(enemyShip -> enemyShip.move(direction, getSpeed()));
        }
        getSpeed();
    }

    public Bullet fire(Game game) {
        if (ships.isEmpty()) {
            return null;
        }
        int isFire = game.getRandomNumber(100 / SpaceInvadersGame.COMPLEXITY);
        if (isFire > 0) {
            return null;
        }
        int firingShip = game.getRandomNumber(ships.size());
        return ships.get(firingShip).fire();
    }

    public int verifyHit(List<Bullet> bullets) {
        if (bullets.isEmpty()) {
            return 0;
        }
        int turnScore = 0;
        for (EnemyShip ship :
                ships) {
            for (Bullet bullet :
                    bullets) {
                if (ship.isCollision(bullet)) {
                    if (ship.isAlive && bullet.isAlive) {
                        if (ship instanceof Boss boss) { //как-то коряво, кажется
                            turnScore += boss.score;
                        } else {
                            turnScore += ship.score;
                        }
                        ship.kill();
                        bullet.kill();

                    }
                }

            }

        }
        return turnScore;
    }

    public void deleteHiddenShips() {
        ships.removeIf(enemyShip -> !enemyShip.isVisible());
    }

    public double getBottomBorder() {
        if (ships.isEmpty()) {
            return 0.0;
        }
        double bottomBorder = ships.get(0).y + ships.get(0).height;
        for (EnemyShip ship :
                ships) {
            if (bottomBorder < ship.y + ship.height) {
                bottomBorder = ship.y + ship.height;
            }
            ;
        }
        return bottomBorder;
    }

    public int getShipsCount() {
        return ships.size();
    }


}
