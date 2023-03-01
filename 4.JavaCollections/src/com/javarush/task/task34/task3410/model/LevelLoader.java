package com.javarush.task.task34.task3410.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        level = level % 60 == 0 ? 60 : level % 60;
        Set<Wall> walls = new HashSet<>();
        Set<Box> boxes = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = null;
        List<String> levelsStr;
        try {
            levelsStr = Files.readAllLines(levels);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Iterator<String> iterator = levelsStr.listIterator();
        String regex = String.format("Maze: %d", level);
        String line;
        do {
            line = iterator.next();
        } while (!line.matches(regex));
        iterator.next();
        int j = Integer.parseInt(iterator.next().split(": ")[1]);
        int i = Integer.parseInt(iterator.next().split(": ")[1]);
        iterator.next();
        iterator.next();
        iterator.next();
        char[][] fieldArray = new char[i][j];
        for (int ii = 0; ii < i; ii++){
            fieldArray[ii] = iterator.next().toCharArray();
        }
        for (int ii = 0; ii < i; ii++){
            for (int jj = 0; jj < j; jj++) {
                switch (fieldArray[ii][jj]){
                    case 'X':
                        walls.add(new Wall(Model.FIELD_CELL_SIZE / 2 + jj * Model.FIELD_CELL_SIZE,
                                Model.FIELD_CELL_SIZE / 2 + ii * Model.FIELD_CELL_SIZE));
                        break;
                    case '*':
                        boxes.add(new Box(Model.FIELD_CELL_SIZE / 2 + jj * Model.FIELD_CELL_SIZE,
                                Model.FIELD_CELL_SIZE / 2 + ii * Model.FIELD_CELL_SIZE));
                        break;
                    case '.':
                        homes.add(new Home(Model.FIELD_CELL_SIZE / 2 + jj * Model.FIELD_CELL_SIZE,
                                Model.FIELD_CELL_SIZE / 2 + ii * Model.FIELD_CELL_SIZE));
                        break;
                    case '&':
                        homes.add(new Home(Model.FIELD_CELL_SIZE / 2 + jj * Model.FIELD_CELL_SIZE,
                                Model.FIELD_CELL_SIZE / 2 + ii * Model.FIELD_CELL_SIZE));
                        boxes.add(new Box(Model.FIELD_CELL_SIZE / 2 + jj * Model.FIELD_CELL_SIZE,
                                Model.FIELD_CELL_SIZE / 2 + ii * Model.FIELD_CELL_SIZE));
                        break;
                    case '@':
                        player = new Player(Model.FIELD_CELL_SIZE / 2 + jj * Model.FIELD_CELL_SIZE,
                                Model.FIELD_CELL_SIZE / 2 + ii * Model.FIELD_CELL_SIZE);
                        break;
                }
            }
        }



        return new GameObjects(walls, boxes, homes, player);
    }


}
