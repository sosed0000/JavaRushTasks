package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BotClient extends Client {

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
//            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            BotClient.this.sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            String[] dividedMessage = message.split(": ");
            if (dividedMessage.length != 2) return;
            String name = dividedMessage[0];
            String request = dividedMessage[1];
            String pattern;

            switch (request) {
                case "дата":
                    pattern = "d.MM.YYYY";
                    break;
                case "день":
                    pattern = "d";
                    break;
                case "месяц":
                    pattern = "MMMM";
                    break;
                case "год":
                    pattern = "YYYY";
                    break;
                case "время":
                    pattern = "H:mm:ss";
                    break;
                case "час":
                    pattern = "H";
                    break;
                case "минуты":
                    pattern = "m";
                    break;
                case "секунды":
                    pattern = "s";
                    break;
                default:
                    pattern = null;
            }
            if (pattern != null)
                BotClient.this.sendTextMessage("Информация для " + name + ": " + new SimpleDateFormat(pattern).format(Calendar.getInstance().getTime()));

        }
    }
}
