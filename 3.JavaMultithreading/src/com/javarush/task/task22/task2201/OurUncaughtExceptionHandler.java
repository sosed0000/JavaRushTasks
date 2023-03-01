package com.javarush.task.task22.task2201;

public class OurUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        final String string = "%s : %s : %s";
        if (Solution.FIRST_THREAD_NAME.equals(t.getName())) {
            System.out.println(getFormattedStringForFirstThread(t, e, string));
        } else if (Solution.SECOND_THREAD_NAME.equals(t.getName())) {
            System.out.println(getFormattedStringForSecondThread(t, e, string));
        } else {
            System.out.println(getFormattedStringForOtherThread(t, e, string));
        }
    }

    protected String getFormattedStringForOtherThread(Thread t, Throwable e, String string) {
        StringBuffer message = new StringBuffer();
        message.append(e.getClass().getSimpleName()).
                append(" : ").
                append(e.getMessage()).
                append(" : ").
                append(t.getName());

        return message.toString();
    }

    protected String getFormattedStringForSecondThread(Thread t, Throwable e, String string) {
        StringBuffer message = new StringBuffer();
        message.append(e.getMessage()).
                append(" : ").
                append(e.getClass().getSimpleName()).
                append(" : ").
                append(t.getName());
        return message.toString();
    }

    protected String getFormattedStringForFirstThread(Thread t, Throwable e, String string) {
        StringBuffer message = new StringBuffer();
        message.append(t.getName()).
                append(" : ").
                append(e.getClass().getSimpleName()).
                append(" : ").
                append(e.getMessage());
        return message.toString();
    }
}

