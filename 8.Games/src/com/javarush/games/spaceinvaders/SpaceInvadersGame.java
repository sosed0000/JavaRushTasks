package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.*;
import com.javarush.games.spaceinvaders.gameobjects.*;

import java.util.ArrayList;
import java.util.List;

public class  SpaceInvadersGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int COMPLEXITY = 5;
    private List<Star> stars;
    private EnemyFleet enemyFleet;
    private List<Bullet> enemyBullets;
    private List<Bullet> playerBullets;
    private PlayerShip playerShip;
    private boolean isGameStopped;
    private int animationsCount;
    private static final int PLAYER_BULLETS_MAX = 1;
    private int score;

    @Override
    public void onTurn(int step) {

        Bullet bullet = enemyFleet.fire(this);
        if (bullet != null) {
            enemyBullets.add(bullet);
        }
        moveSpaceObjects();
        check();
        setScore(score);
        drawScene();
    }

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onKeyPress(Key key) {
        if (key == Key.SPACE && isGameStopped == true) {
            createGame();
            return;
        }
        if (key == Key.LEFT) {playerShip.setDirection(Direction.LEFT);}
        if (key == Key.RIGHT) {playerShip.setDirection(Direction.RIGHT);}
        if (key == Key.SPACE) {
            Bullet playerBullet = playerShip.fire();
            if (playerBullet != null && playerBullets.size() < PLAYER_BULLETS_MAX) {
                playerBullets.add(playerBullet);
            }
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        if (key == Key.LEFT && playerShip.getDirection() == Direction.LEFT) {
            playerShip.setDirection(Direction.UP);
        }
        if (key == Key.RIGHT && playerShip.getDirection() == Direction.RIGHT) {
            playerShip.setDirection(Direction.UP);
        }
    }

    @Override
    public void setCellValueEx(int x, int y, Color cellColor, String value) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) {return;}
        super.setCellValueEx(x, y, cellColor, value);
    }

    private void createGame() {
        createStars();
        enemyFleet = new EnemyFleet();
        enemyBullets = new ArrayList<>();
        playerBullets = new ArrayList<>();
        playerShip = new PlayerShip();
        isGameStopped = false;
        animationsCount = 0;
        setTurnTimer(40);
        score = 0;
        setScore(score);
        drawScene();
    }

    private void drawScene() {
        drawField();
        enemyFleet.draw(this);
        enemyBullets.forEach(bullet -> bullet.draw(this));
        playerBullets.forEach(bullet -> bullet.draw(this));
        playerShip.draw(this);

    }

    private void drawField() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                setCellValueEx(x, y, Color.BLACK, "");
            }
        }
        stars.forEach(star -> star.draw(this));
    }

    private void createStars() {
        stars = new ArrayList<>();
        Game game = new Game();
        for (int i = 0; i < 8; i++) {
            stars.add(new Star(game.getRandomNumber(63), game.getRandomNumber(63)));
        }
    }

    private void moveSpaceObjects() {
        enemyFleet.move();
        enemyBullets.forEach(Bullet::move);
        playerBullets.forEach(Bullet::move);
        playerShip.move();
    }

    private void removeDeadBullets() {
        enemyBullets.removeIf(bullet -> bullet.y >= (HEIGHT - 1) || !bullet.isAlive);
        playerBullets.removeIf(bullet -> (bullet.y + bullet.height < 0) || !bullet.isAlive);
    }

    private void check() {
        if (!playerShip.isAlive) {stopGameWithDelay();}
        playerShip.verifyHit(enemyBullets);
        enemyFleet.deleteHiddenShips();
        removeDeadBullets();
        if (enemyFleet.getBottomBorder() >= playerShip.y) {playerShip.kill();}
        if (enemyFleet.getShipsCount() == 0) {
            playerShip.win();
            stopGameWithDelay();
        }
        score += enemyFleet.verifyHit(playerBullets);


    }

    private void stopGame(boolean isWin) {
        isGameStopped = true;
        if (isWin) {
            showMessageDialog(Color.NONE, "YOU WIN", Color.GREEN, 75);
        } else showMessageDialog(Color.NONE, "GAME OVER", Color.RED, 75);
        stopTurnTimer();
    }

    private void stopGameWithDelay() {
        animationsCount++;
        if (animationsCount >= 10) {
            stopGame(playerShip.isAlive);
        }
    }
}
