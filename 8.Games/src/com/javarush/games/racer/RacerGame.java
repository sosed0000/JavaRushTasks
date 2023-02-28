package com.javarush.games.racer;

import com.javarush.engine.cell.*;
import com.javarush.games.racer.road.RoadManager;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH / 2;
    public static final int ROADSIDE_WIDTH = 14;
    private RoadMarking roadMarking;
    private PlayerCar player;
    private RoadManager roadManager;
    private boolean isGameStopped;
    private FinishLine finishLine;
    private static final int RACE_GOAL_CARS_COUNT = 40;
    private ProgressBar progressBar;
    private int score;

    @Override
    public void initialize() {
        showGrid(false);
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT) {
            super.setCellColor(x, y, color);
        }
    }

    @Override
    public void onTurn(int step) {
        if (roadManager.checkCrush(player)) {
            gameOver();
            drawScene();
            return;
        }
        roadManager.generateNewRoadObjects(this);

        if (roadManager.getPassedCarsCount() >= RACE_GOAL_CARS_COUNT) {finishLine.show();}
        if (finishLine.isCrossed(player)) {
            win();
            drawScene();
            return;
        };
        score-=5;
        setScore(score);
        moveAll();
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if (key == Key.RIGHT) {player.setDirection(Direction.RIGHT);}
        if (key == Key.LEFT) {player.setDirection(Direction.LEFT);}
        if (key == Key.SPACE && isGameStopped) {createGame();}
        if (key == Key.UP) {player.speed = 2;}
    }

    @Override
    public void onKeyReleased(Key key) {
        if (key == Key.RIGHT && player.getDirection() == Direction.RIGHT) {
            player.setDirection(Direction.NONE);
        }
        if (key == Key.LEFT && player.getDirection() == Direction.LEFT) {
            player.setDirection(Direction.NONE);
        }
        if (key == Key.UP) {player.speed = 1;}
    }

    private void createGame() {
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        roadManager = new RoadManager();
        isGameStopped = false;
        finishLine = new FinishLine();
        progressBar = new ProgressBar(RACE_GOAL_CARS_COUNT);
        score = 3500;
        setTurnTimer(40);
        drawScene();
    }
    private void drawScene() {
        drawField();
        roadMarking.draw(this);
        player.draw(this);
        roadManager.draw(this);
        finishLine.draw(this);
        progressBar.draw(this);
    }

    private void drawField() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellColor(x, y, Color.GREEN);
            }
        }
        for (int x = ROADSIDE_WIDTH; x < WIDTH - ROADSIDE_WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellColor(x, y, Color.GRAY);
            }
        }
        for (int y = 0; y < HEIGHT; y++) {
            setCellColor(CENTER_X, y, Color.WHITE);
        }

    }

    private void moveAll() {
        player.move();
        roadMarking.move(player.speed);
        roadManager.move(player.speed);
        finishLine.move(player.speed);
        progressBar.move(roadManager.getPassedCarsCount());
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.NONE, "GAME OVER", Color.HONEYDEW, 75);
        stopTurnTimer();
        player.stop();
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.NONE, "YOU WIN", Color.CORNSILK, 75);
        stopTurnTimer();
    }
}






/*
        for (int x = 0; x < ROADSIDE_WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellColor(x, y, Color.GREEN);
            }
        }
        for (int x = ROADSIDE_WIDTH; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellColor(x, y, Color.GREEN);
            }
        }

        for (int x = ROADSIDE_WIDTH; x < CENTER_X; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellColor(x, y, Color.GRAY);
            }
        }
        for (int x = CENTER_X + 1; x < WIDTH - ROADSIDE_WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellColor(x, y, Color.GRAY);
            }
        }

        for (int y = 0; y < HEIGHT; y++) {
            setCellColor(CENTER_X, y, Color.WHITE);
        }


 */