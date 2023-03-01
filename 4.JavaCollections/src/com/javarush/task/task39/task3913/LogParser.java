package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
    private List<LogEntity> logEntities = new ArrayList<>();
    private DateFormat simpleDateFormat = new SimpleDateFormat("d.M.yyyy H:m:s");

    public LogParser(Path logDir) {
        this.logDir = logDir;
        readLogs();
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                result.add(logEntities.get(i).getIp());
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)) {
                    result.add(logEntities.get(i).getIp());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(event)) {
                    result.add(logEntities.get(i).getIp());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getStatus().equals(status)) {
                    result.add(logEntities.get(i).getIp());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getAllUsers() {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            result.add(logEntities.get(i).getUser());
        }
        return result;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                result.add(logEntities.get(i).getUser());
            }
        }
        return result.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)) {
                    result.add(logEntities.get(i).getEvent());
                }
            }
        }
        return result.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getIp().equals(ip)) {
                    result.add(logEntities.get(i).getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.LOGIN)) {
                    result.add(logEntities.get(i).getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.DOWNLOAD_PLUGIN)) {
                    result.add(logEntities.get(i).getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.WRITE_MESSAGE)) {
                    result.add(logEntities.get(i).getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.SOLVE_TASK)) {
                    result.add(logEntities.get(i).getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.SOLVE_TASK)
                        && logEntities.get(i).getEventAdditionalParameter() == task) {
                    result.add(logEntities.get(i).getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.DONE_TASK)) {
                    result.add(logEntities.get(i).getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        Set<String> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.DONE_TASK)
                        && logEntities.get(i).getEventAdditionalParameter() == task) {
                    result.add(logEntities.get(i).getUser());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)
                        && logEntities.get(i).getEvent().equals(event)) {
                    result.add(logEntities.get(i).getDate());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getStatus().equals(Status.FAILED)) {
                    result.add(logEntities.get(i).getDate());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getStatus().equals(Status.ERROR)) {
                    result.add(logEntities.get(i).getDate());
                }
            }
        }
        return result;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)
                        && logEntities.get(i).getEvent().equals(Event.LOGIN)) {
                    set.add(logEntities.get(i).getDate());
                }
            }
        }
        if (set.size() == 0) {
            return null;
        }
        Date minDate = set.iterator().next();
        for (Date date : set) {
            if (date.getTime() < minDate.getTime())
                minDate = date;
        }
        return minDate;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)
                        && logEntities.get(i).getEvent().equals(Event.SOLVE_TASK)
                        && logEntities.get(i).getEventAdditionalParameter() == task) {
                    set.add(logEntities.get(i).getDate());
                }
            }
        }
        if (set.size() == 0) {
            return null;
        }
        Date minDate = set.iterator().next();
        for (Date date : set) {
            if (date.getTime() < minDate.getTime())
                minDate = date;
        }
        return minDate;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        Set<Date> set = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)
                        && logEntities.get(i).getEvent().equals(Event.DONE_TASK)
                        && logEntities.get(i).getEventAdditionalParameter() == task) {
                    set.add(logEntities.get(i).getDate());
                }
            }
        }
        if (set.size() == 0) {
            return null;
        }
        Date minDate = set.iterator().next();
        for (Date date : set) {
            if (date.getTime() < minDate.getTime())
                minDate = date;
        }
        return minDate;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)
                        && logEntities.get(i).getEvent().equals(Event.WRITE_MESSAGE)) {
                    result.add(logEntities.get(i).getDate());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        Set<Date> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)
                        && logEntities.get(i).getEvent().equals(Event.DOWNLOAD_PLUGIN)) {
                    result.add(logEntities.get(i).getDate());
                }
            }
        }
        return result;
    }

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getAllEvents(after, before).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                result.add(logEntities.get(i).getEvent());
            }
        }
        return result;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getIp().equals(ip)) {
                    result.add(logEntities.get(i).getEvent());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getUser().equals(user)) {
                    result.add(logEntities.get(i).getEvent());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getStatus().equals(Status.FAILED)) {
                    result.add(logEntities.get(i).getEvent());
                }
            }
        }
        return result;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        Set<Event> result = new HashSet<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getStatus().equals(Status.ERROR)) {
                    result.add(logEntities.get(i).getEvent());
                }
            }
        }
        return result;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        int quantity = 0;
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.SOLVE_TASK)
                        && logEntities.get(i).getEventAdditionalParameter() == task) {
                    quantity++;
                }
            }
        }
        return quantity;
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        int quantity = 0;
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.DONE_TASK)
                        && logEntities.get(i).getEventAdditionalParameter() == task) {
                    quantity++;
                }
            }
        }
        return quantity;
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.SOLVE_TASK)) {
                    int task = logEntities.get(i).getEventAdditionalParameter();
                    Integer count = result.containsKey(task) ? result.get(task) : 0;
                    result.put(task, count + 1);
                }
            }
        }
        return result;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < logEntities.size(); i++) {
            if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                if (logEntities.get(i).getEvent().equals(Event.DONE_TASK)) {
                    int task = logEntities.get(i).getEventAdditionalParameter();
                    Integer count = result.containsKey(task) ? result.get(task) : 0;
                    result.put(task, count + 1);
                }
            }
        }
        return result;
    }

    @Override
    public Set<Object> execute(String query) {
        Set<Object> result = new HashSet<>();
        String field1;
        String field2 = null;
        String value1 = null;
        Date after = null;
        Date before = null;
        Pattern pattern = Pattern.compile("get (ip|user|date|event|status)"
                + "( for (ip|user|date|event|status) = \"(.*?)\")?"
                + "( and date between \"(.*?)\" and \"(.*?)\")?");
        Matcher matcher = pattern.matcher(query);
        matcher.find();
        field1 = matcher.group(1);
        if (matcher.group(2) != null) {
            field2 = matcher.group(3);
            value1 = matcher.group(4);
            if (matcher.group(5) != null) {
                try {
                    after = simpleDateFormat.parse(matcher.group(6));
                    before = simpleDateFormat.parse(matcher.group(7));
                } catch (ParseException e) {
                }
            }
        }

        if (field2 != null && value1 != null) {
            for (int i = 0; i < logEntities.size(); i++) {
                if (dateBetweenDates(logEntities.get(i).getDate(), after, before)) {
                    if (field2.equals("date")) {
                        try {
                            if (logEntities.get(i).getDate().getTime() == simpleDateFormat.parse(value1).getTime()) {
                                result.add(getCurrentValue(logEntities.get(i), field1));
                            }
                        } catch (ParseException e) {
                        }
                    } else {
                        if (value1.equals(getCurrentValue(logEntities.get(i), field2).toString())) {
                            result.add(getCurrentValue(logEntities.get(i), field1));
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < logEntities.size(); i++) {
                result.add(getCurrentValue(logEntities.get(i), field1));
            }
        }

        return result;
    }

    private void readLogs() {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(logDir)) {
            for (Path file : directoryStream) {
                if (file.toString().toLowerCase().endsWith(".log")) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            String[] params = line.split("\t");

                            if (params.length != 5) {
                                continue;
                            }

                            String ip = params[0];
                            String user = params[1];
                            Date date = readDate(params[2]);
                            Event event = readEvent(params[3]);
                            int eventAdditionalParameter = -1;
                            if (event.equals(Event.SOLVE_TASK) || event.equals(Event.DONE_TASK)) {
                                eventAdditionalParameter = readAdditionalParameter(params[3]);
                            }
                            Status status = readStatus(params[4]);

                            LogEntity logEntity = new LogEntity(ip, user, date, event, eventAdditionalParameter, status);
                            logEntities.add(logEntity);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Date readDate(String lineToParse) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(lineToParse);
        } catch (ParseException e) {
        }
        return date;
    }

    private Event readEvent(String lineToParse) {
        Event event = null;
        if (lineToParse.contains("SOLVE_TASK")) {
            event = Event.SOLVE_TASK;
        } else if (lineToParse.contains("DONE_TASK")) {
            event = Event.DONE_TASK;
        } else {
            switch (lineToParse) {
                case "LOGIN": {
                    event = Event.LOGIN;
                    break;
                }
                case "DOWNLOAD_PLUGIN": {
                    event = Event.DOWNLOAD_PLUGIN;
                    break;
                }
                case "WRITE_MESSAGE": {
                    event = Event.WRITE_MESSAGE;
                    break;
                }
            }
        }
        return event;
    }

    private int readAdditionalParameter(String lineToParse) {
        if (lineToParse.contains("SOLVE_TASK")) {
            lineToParse = lineToParse.replace("SOLVE_TASK", "").replaceAll(" ", "");
            return Integer.parseInt(lineToParse);
        } else {
            lineToParse = lineToParse.replace("DONE_TASK", "").replaceAll(" ", "");
            return Integer.parseInt(lineToParse);
        }
    }

    private Status readStatus(String lineToParse) {
        Status status = null;
        switch (lineToParse) {
            case "OK": {
                status = Status.OK;
                break;
            }
            case "FAILED": {
                status = Status.FAILED;
                break;
            }
            case "ERROR": {
                status = Status.ERROR;
                break;
            }
        }
        return status;
    }

    private boolean dateBetweenDates(Date current, Date after, Date before) {
        if (after == null) {
            after = new Date(0);
        }
        if (before == null) {
            before = new Date(Long.MAX_VALUE);
        }
        return current.after(after) && current.before(before);
    }

    private Object getCurrentValue(LogEntity logEntity, String field) {
        Object value = null;
        switch (field) {
            case "ip": {
                Command method = new GetIpCommand(logEntity);
                value = method.execute();
                break;
            }
            case "user": {
                Command method = new GetUserCommand(logEntity);
                value = method.execute();
                break;
            }
            case "date": {
                Command method = new GetDateCommand(logEntity);
                value = method.execute();
                break;
            }
            case "event": {
                Command method = new GetEventCommand(logEntity);
                value = method.execute();
                break;
            }
            case "status": {
                Command method = new GetStatusCommand(logEntity);
                value = method.execute();
                break;
            }
        }
        return value;
    }

    private class LogEntity {
        private String ip;
        private String user;
        private Date date;
        private Event event;
        private int eventAdditionalParameter;
        private Status status;

        public LogEntity(String ip, String user, Date date, Event event, int eventAdditionalParameter, Status status) {
            this.ip = ip;
            this.user = user;
            this.date = date;
            this.event = event;
            this.eventAdditionalParameter = eventAdditionalParameter;
            this.status = status;
        }

        public String getIp() {
            return ip;
        }

        public String getUser() {
            return user;
        }

        public Date getDate() {
            return date;
        }

        public Event getEvent() {
            return event;
        }

        public int getEventAdditionalParameter() {
            return eventAdditionalParameter;
        }

        public Status getStatus() {
            return status;
        }
    }

    private abstract class Command {
        protected LogEntity logEntity;

        abstract Object execute();
    }

    private class GetIpCommand extends Command {
        public GetIpCommand(LogEntity logEntity) {
            this.logEntity = logEntity;
        }

        @Override
        Object execute() {
            return logEntity.getIp();
        }
    }

    private class GetUserCommand extends Command {
        public GetUserCommand(LogEntity logEntity) {
            this.logEntity = logEntity;
        }

        @Override
        Object execute() {
            return logEntity.getUser();
        }
    }

    private class GetDateCommand extends Command {
        public GetDateCommand(LogEntity logEntity) {
            this.logEntity = logEntity;
        }

        @Override
        Object execute() {
            return logEntity.getDate();
        }
    }

    private class GetEventCommand extends Command {
        public GetEventCommand(LogEntity logEntity) {
            this.logEntity = logEntity;
        }

        @Override
        Object execute() {
            return logEntity.getEvent();
        }
    }

    private class GetStatusCommand extends Command {
        public GetStatusCommand(LogEntity logEntity) {
            this.logEntity = logEntity;
        }

        @Override
        Object execute() {
            return logEntity.getStatus();
        }
    }
}

//import com.javarush.task.task39.task3913.query.*;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//
//public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
//    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy H:mm:ss");
//    private List<LogLine> logLines;
//
//    public LogParser(Path logDir) {
//        if (!Files.exists(logDir)) {
//            throw new RuntimeException(new FileNotFoundException(logDir.toString()));
//        }
//        logLines = createLogLines(logDir);
//    }
//
//    private List<LogLine> createLogLines(Path logDir) {
//        File[] files = getFiles(logDir);
//        List<String> lines = getLines(files);
//        return getLogLines(lines);
//    }
//
//    private File[] getFiles(Path logDir) {
//        return Arrays.stream(Objects.requireNonNull(logDir.toFile().listFiles())).filter(file -> file.getName().toLowerCase().endsWith(".log")).toArray(File[]::new);
//    }
//
//    private List<String> getLines(File[] files) {
//        List<String> lines = new ArrayList<>();
//        for (File file : files) {
//            try {
//                lines.addAll(Files.readAllLines(file.toPath()));
//            } catch (IOException e) {
//                System.err.printf("File %s was not loaded.%n", file.getName());
//                e.printStackTrace();
//            }
//        }
//        return lines;
//    }
//
//    private List<LogLine> getLogLines(List<String> lines) {
//        List<LogLine> logLines = new ArrayList<>();
//        for (String line : lines) {
//            try {
//                logLines.add(new LogLine(line));
//            } catch (Exception e) {
//                System.err.printf("Line skipped. Invalid data format in line: \"%s\"%n", line);
//                e.printStackTrace();
//            }
//        }
//        return logLines;
//    }
//
//    @Override
//    public int getNumberOfUniqueIPs(Date after, Date before) {
//        return getUniqueIPs(after, before).size();
//    }
//
//    @Override
//    public Set<String> getUniqueIPs(Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().map(l -> l.ip)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getIPsForUser(String user, Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.user.equalsIgnoreCase(user))
//                .map(l -> l.ip)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream()
//                .filter(l -> l.event.equals(event))
//                .map(l -> l.ip).collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.status.equals(status))
//                .map(l -> l.ip)
//                .collect(Collectors.toSet());
//    }
//
//    public List<LogLine> getLogLinesInRange(Date after, Date before) {
//        List<LogLine> linesInRange = new ArrayList<>();
//        if (after == null && before == null) {
//            return logLines;
//        } else if (after != null && before != null) {
//            for (LogLine l : logLines) {
//                if (l.date.compareTo(after) > 0 && l.date.compareTo(before) < 0) {
//                    linesInRange.add(l);
//                }
//            }
//        } else if (after == null) {
//            for (LogLine l : logLines) {
//                if (l.date.compareTo(before) < 0) {
//                    linesInRange.add(l);
//                }
//            }
//        } else {
//            for (LogLine l : logLines) {
//                if (l.date.compareTo(after) > 0) {
//                    linesInRange.add(l);
//                }
//            }
//        }
//        return linesInRange;
//    }
//
//    @Override
//    public Set<String> getAllUsers() {
//        return logLines.stream().map(l -> l.user)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public int getNumberOfUsers(Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().map(l -> l.user)
//                .collect(Collectors.toSet())
//                .size();
//    }
//
//    @Override
//    public int getNumberOfUserEvents(String user, Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.user.equalsIgnoreCase(user))
//                .map(l -> l.event)
//                .collect(Collectors.toSet())
//                .size();
//    }
//
//    @Override
//    public Set<String> getUsersForIP(String ip, Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.ip.equals(ip))
//                .map(l -> l.user)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getLoggedUsers(Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.event.equals(Event.LOGIN))
//                .map(l -> l.user)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.event.equals(Event.DOWNLOAD_PLUGIN))
//                .map(l -> l.user)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getWroteMessageUsers(Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.event.equals(Event.WRITE_MESSAGE))
//                .map(l -> l.user)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getSolvedTaskUsers(Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.event.equals(Event.SOLVE_TASK))
//                .map(l -> l.user)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.event.equals(Event.SOLVE_TASK) && l.taskNumber == task)
//                .map(l -> l.user)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getDoneTaskUsers(Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.event.equals(Event.DONE_TASK))
//                .map(l -> l.user)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.event.equals(Event.DONE_TASK) && l.taskNumber == task)
//                .map(l -> l.user)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.event.equals(event) && l.user.equalsIgnoreCase(user)).map(l -> l.date)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.status.equals(Status.FAILED))
//                .map(l -> l.date)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.status.equals(Status.ERROR))
//                .map(l -> l.date)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
//        Date dateWhenUserLoggedFirstTime = null;
//        for (LogLine l : getLogLinesInRange(after, before)) {
//            if (l.user.equalsIgnoreCase(user) && l.event.equals(Event.LOGIN)) {
//                if (dateWhenUserLoggedFirstTime == null || l.date.before(dateWhenUserLoggedFirstTime)) {
//                    dateWhenUserLoggedFirstTime = l.date;
//                }
//            }
//        }
//        return dateWhenUserLoggedFirstTime;
//    }
//
//    @Override
//    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
//        Date dateWhenUserSolvedTask = null;
//        for (LogLine l : getLogLinesInRange(after, before)) {
//            if (l.user.equalsIgnoreCase(user) && l.event.equals(Event.SOLVE_TASK) && l.taskNumber == task) {
//                if (dateWhenUserSolvedTask == null || l.date.before(dateWhenUserSolvedTask)) {
//                    dateWhenUserSolvedTask = l.date;
//                }
//            }
//        }
//        return dateWhenUserSolvedTask;
//    }
//
//    @Override
//    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
//        Date dateWhenUserDoneTask = null;
//        for (LogLine l : getLogLinesInRange(after, before)) {
//            if (l.user.equalsIgnoreCase(user) && l.event.equals(Event.DONE_TASK) && l.taskNumber == task) {
//                if (dateWhenUserDoneTask == null || l.date.before(dateWhenUserDoneTask)) {
//                    dateWhenUserDoneTask = l.date;
//                }
//            }
//        }
//        return dateWhenUserDoneTask;
//    }
//
//    @Override
//    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.user.equalsIgnoreCase(user) && l.event.equals(Event.WRITE_MESSAGE))
//                .map(l -> l.date)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.user.equalsIgnoreCase(user) && l.event.equals(Event.DOWNLOAD_PLUGIN))
//                .map(l -> l.date).collect(Collectors.toSet());
//    }
//
//    @Override
//    public int getNumberOfAllEvents(Date after, Date before) {
//        return getAllEvents(after, before).size();
//    }
//
//    @Override
//    public Set<Event> getAllEvents(Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().map(l -> l.event)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.ip.equals(ip))
//                .map(l -> l.event)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<Event> getEventsForUser(String user, Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.user.equalsIgnoreCase(user))
//                .map(l -> l.event)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<Event> getFailedEvents(Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.status.equals(Status.FAILED))
//                .map(l -> l.event)
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<Event> getErrorEvents(Date after, Date before) {
//        Set<Event> set = new HashSet<>();
//        for (LogLine l : getLogLinesInRange(after, before)) {
//            if (l.status.equals(Status.ERROR)) {
//                Event event = l.event;
//                set.add(event);
//            }
//        }
//        return set;
//    }
//
//    @Override
//    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
//        int numberOfAttemptToSolveTask = 0;
//        for (LogLine l : getLogLinesInRange(after, before)) {
//            if (l.event.equals(Event.SOLVE_TASK) && l.taskNumber == task) {
//                numberOfAttemptToSolveTask++;
//            }
//        }
//        return numberOfAttemptToSolveTask;
//    }
//
//    @Override
//    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
//        int numberOfSuccessfulAttemptToSolveTask = 0;
//        for (LogLine l : getLogLinesInRange(after, before)) {
//            if (l.event.equals(Event.DONE_TASK) && l.taskNumber == task) {
//                numberOfSuccessfulAttemptToSolveTask++;
//            }
//        }
//        return numberOfSuccessfulAttemptToSolveTask;
//    }
//
//    @Override
//    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.event.equals(Event.SOLVE_TASK) && l.taskNumber != -1)
//                .collect(Collectors.toMap(l -> l.taskNumber, l -> 1, Integer::sum));
//    }
//
//    @Override
//    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
//        return getLogLinesInRange(after, before)
//                .stream().filter(l -> l.event.equals(Event.DONE_TASK) && l.taskNumber != -1)
//                .collect(Collectors.toMap(l -> l.taskNumber, l -> 1, Integer::sum));
//    }
//
//    @Override
//    public Set<Object> execute(String query) {
//        Set<Object> result = new HashSet<>();
//        String command = null;
//        String parameter1 = null;
//        String value1 = null;
//        Date after;
//        Date before;
//
//        try {
//            String[] queryFields = getQueryFields(query);
//            command = queryFields[0];
//            parameter1 = queryFields[1];
//            value1 = queryFields[2];
//            after = queryFields[3] == null ? null : DATE_FORMAT.parse(queryFields[3]);
//            before = queryFields[4] == null ? null : DATE_FORMAT.parse(queryFields[4]);
//
//        } catch (Exception e) {
//            System.err.println("Invalid query format");
//            throw new RuntimeException(e);
//        }
//        for (LogLine logLine : logLines) {
//            Object o = getCurrentValue(logLine, command, parameter1, value1, after, before);
//            if (o != null) {
//                result.add(o);
//            }
//        }
//        return result;
//    }
//
//    private String[] getQueryFields(String query) {
//        String[] fields = new String[5];
//        Pattern pattern;
//        Matcher matcher;
//        if (!query.contains("=")) {
//            pattern = Pattern.compile("get (ip|user|date|event|status)");
//            matcher = pattern.matcher(query);
//            matcher.find();
//            fields[0] = matcher.group(1);
//        } else if (!query.toLowerCase().contains("and date between")){
//            pattern = Pattern.compile("get (ip|user|date|event|status) for (ip|user|date|event|status) = \"(.*)\"");
//            matcher = pattern.matcher(query);
//            matcher.find();
//            fields[0] = matcher.group(1);
//            fields[1] = matcher.group(2);
//            fields[2] = matcher.group(3);
//        } else {
//            String datePattern = "\\d{2}\\.\\d{2}\\.\\d{4} \\d{1,2}:\\d{2}:\\d{2}";
//            pattern = Pattern.compile("get (ip|user|date|event|status) for (ip|user|date|event|status) = \"(.*)\"" +
//                    " and date between \"(" + datePattern +")\" and \"(" + datePattern + ")\"");
//            matcher = pattern.matcher(query);
//            matcher.find();
//            fields[0] = matcher.group(1);
//            fields[1] = matcher.group(2);
//            fields[2] = matcher.group(3);
//            fields[3] = matcher.group(4);
//            fields[4] = matcher.group(5);
//        }
//        return fields;
//    }
//
//    private Object getCurrentValue(LogLine logLine, String command,
//                                   String parameter1, String value1, Date after, Date before) {
//        Object value = null;
//        switch (command) {
//
//            case "ip": {
//                Command method = new GetIpCommand(logLine, parameter1, value1, after, before);
//                value = method.execute();
//                break;
//            }
//            case "user": {
//                Command method = new GetUserCommand(logLine, parameter1, value1, after, before);
//                value = method.execute();
//                break;
//            }
//            case "date": {
//                Command method = new GetDateCommand(logLine, parameter1, value1, after, before);
//                value = method.execute();
//                break;
//            }
//            case "event": {
//                Command method = new GetEventCommand(logLine, parameter1, value1, after, before);
//                value = method.execute();
//                break;
//            }
//            case "status": {
//                Command method = new GetStatusCommand(logLine, parameter1, value1, after, before);
//                value = method.execute();
//                break;
//            }
//        }
//        return value;
//    }
//
//    public class LogLine {
//        String ip;
//        String user;
//        Date date;
//        Event event;
//        int taskNumber = -1;
//        Status status;
//
//        public LogLine(String logLine) throws Exception {
//            String[] data = logLine.split("\t");
//            ip = data[0];
//            user = data[1];
//            date = DATE_FORMAT.parse(data[2]);
//            String[] ev = data[3].split(" ");
//            setEvent(ev[0]);
//            if (ev.length > 1) {
//                taskNumber = Integer.parseInt(ev[1]);
//            }
//            setStatus(data[4]);
//        }
//
//        private void setStatus(String s) throws Exception {
//            switch (s) {
//                case "OK":
//                    status = Status.OK;
//                    break;
//                case "ERROR":
//                    status = Status.ERROR;
//                    break;
//                case "FAILED":
//                    status = Status.FAILED;
//                    break;
//                default:
//                    throw new Exception("Unexpected value: " + s);
//            }
//        }
//
//        private void setEvent(String e) throws Exception {
//            switch (e) {
//                case "LOGIN":
//                    event = Event.LOGIN;
//                    break;
//                case "DOWNLOAD_PLUGIN":
//                    event = Event.DOWNLOAD_PLUGIN;
//                    break;
//                case "WRITE_MESSAGE":
//                    event = Event.WRITE_MESSAGE;
//                    break;
//                case "SOLVE_TASK":
//                    event = Event.SOLVE_TASK;
//                    break;
//                case "DONE_TASK":
//                    event = Event.DONE_TASK;
//                    break;
//                default:
//                    throw new Exception("Unexpected value: " + e);
//            }
//        }
//    }
//
//    protected abstract class Command {
//        protected String parameter1;
//        protected String value1;
//        private final Date after;
//        private final Date before;
//        LogLine logLine;
//
//        protected Command(LogLine logLine, String parameter1, String value1, Date after, Date before) {
//            this.logLine = logLine;
//            this.parameter1 = parameter1;
//            this.value1 = value1;
//            this.after = after;
//            this.before = before;
//        }
//
//        protected boolean isMatch() {
//            boolean match = true;
//            if (parameter1 != null) {
//
//                switch (parameter1) {
//                    case "ip": {
//                        match = logLine.ip.equals(value1);
//                        break;
//                    }
//                    case "user": {
//                        match = logLine.user.equalsIgnoreCase(value1);
//                        break;
//                    }
//                    case "date": {
//                        try {
//                            match = logLine.date.equals(DATE_FORMAT.parse(value1));
//                        } catch (ParseException e) {
//                            System.out.println("Invalid date format");
//                            e.printStackTrace();
//                        }
//                        break;
//                    }
//                    case "event": {
//                        match = logLine.event.name().equalsIgnoreCase(value1);
//                        break;
//                    }
//                    case "status": {
//                        match = logLine.status.name().equalsIgnoreCase(value1);
//                        break;
//                    }
//                }
//            }
//            if (after != null && before != null){
//                match =  logLine.date.after(after) && logLine.date.before(before);
//            }
//            return match;
//        }
//
//        abstract Object execute();
//    }
//
//    private class GetIpCommand extends Command {
//
//        public GetIpCommand(LogLine logLine, String parameter1, String value1, Date after, Date before) {
//            super(logLine, parameter1, value1, after, before);
//        }
//
//        @Override
//        Object execute() {
//            if (isMatch()) {
//                return logLine.ip;
//            } else {
//                return null;
//            }
//        }
//
//
//    }
//
//    private class GetUserCommand extends Command {
//        public GetUserCommand(LogLine logLine, String parameter1, String value1, Date after, Date before) {
//            super(logLine, parameter1, value1, after, before);
//        }
//
//        @Override
//        Object execute() {
//            if (isMatch()) {
//                return logLine.user;
//            } else {
//                return null;
//            }
//        }
//    }
//
//    private class GetDateCommand extends Command {
//        public GetDateCommand(LogLine logLine, String parameter1, String value1, Date after, Date before) {
//            super(logLine, parameter1, value1, after, before);
//        }
//
//        @Override
//        Object execute() {
//            if (isMatch()) {
//                return logLine.date;
//            } else {
//                return null;
//            }
//        }
//    }
//
//    private class GetEventCommand extends Command {
//        public GetEventCommand(LogLine logLine, String parameter1, String value1, Date after, Date before) {
//            super(logLine, parameter1, value1, after, before);
//        }
//
//        @Override
//        Object execute() {
//            if (isMatch()) {
//                return logLine.event;
//            } else {
//                return null;
//            }
//        }
//    }
//
//    private class GetStatusCommand extends Command {
//        public GetStatusCommand(LogLine logLine, String parameter1, String value1, Date after, Date before) {
//            super(logLine, parameter1, value1, after, before);
//        }
//
//        @Override
//        Object execute() {
//            if (isMatch()) {
//                return logLine.status;
//            } else {
//                return null;
//            }
//        }
//    }
//}