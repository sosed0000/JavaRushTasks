package com.javarush.task.task33.task3310.strategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            path.toFile().deleteOnExit();
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public long getFileSize() {
        try {
            return Files.size(path);
        } catch (IOException ignored) {
        }
        return 0;
    }

    public void putEntry(Entry entry) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            while (entry != null) {
                objectOutputStream.writeObject(entry);
                entry = entry.next;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Entry getEntry() {
        Entry entry = null;
        if (getFileSize() != 0) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path))) {
                entry = (Entry) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return entry;
    }

    public void remove(){
        try {
            Files.delete(path);
        } catch (IOException ignored) {

        }
    }
}
