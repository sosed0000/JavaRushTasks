package com.javarush.task.task38.task3804;

public class ExceptionFactory {
    public static <E extends Enum<E>> Throwable getException(E e) {
        if (e != null) {
            String firstChar = String.valueOf(e.name().charAt(0));
            String message = e.name()
                    .toLowerCase()
                    .replaceFirst(firstChar.toLowerCase(), firstChar.toUpperCase())
                    .replaceAll("_", " ");
            switch (e.getClass().getSimpleName()) {
                case "ApplicationExceptionMessage":
                    return new Exception(message);
                case "DatabaseExceptionMessage":
                    return new RuntimeException(message);
                case "UserExceptionMessage":
                    return new Error(message);
            }
        }
        return new IllegalArgumentException();
    }
}
