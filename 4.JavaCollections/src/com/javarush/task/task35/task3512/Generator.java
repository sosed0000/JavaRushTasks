package com.javarush.task.task35.task3512;

public class Generator<T> {

    private final Class<T> eventClass;

    public Generator(Class<T> eventClass) throws InstantiationException, IllegalAccessException {

        this.eventClass  = eventClass;
    }


    T newInstance() throws InstantiationException, IllegalAccessException {
        return eventClass.newInstance();
    }
}
