package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* 
Читаем и пишем в файл: JavaRush
*/

public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File yourFile = File.createTempFile("___", ".txt", new File("d:/"));
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user1 = new User();
            user1.setFirstName("Вася");
            user1.setLastName("Васильев");
            user1.setBirthDate(new Date(85, 2, 21));
            user1.setMale(true);
            user1.setCountry(User.Country.OTHER);

            User user2 = new User();
            user2.setFirstName("Гадя");
            user2.setLastName("Хренова");
            user2.setBirthDate(new Date(99, 4, 3));
            user2.setMale(false);
            user2.setCountry(User.Country.RUSSIA);



            javaRush.users.add(user1);
            javaRush.users.add(user2);


            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //here check that the javaRush object is equal to the loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(loadedObject.equals(javaRush));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            if (users.isEmpty()) {return;}
            PrintWriter writer = new PrintWriter(outputStream);
            for (User user:
                 users) {
                writer.println(user.getFirstName());
                writer.println(user.getLastName());
                writer.println(user.getBirthDate().getTime());
                writer.println(user.isMale());
                writer.println(user.getCountry().getDisplayName());
            }
            writer.close();

        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream));

            while (reader.ready()) {
                String firstName = reader.readLine();
                String lastName = reader.readLine();
                long birthDate = Long.parseLong(reader.readLine());
                boolean isMale = Boolean.parseBoolean(reader.readLine());
                User.Country country;
                switch (reader.readLine()) {
                    case "Ukraine": country = User.Country.UKRAINE; break;
                    case "Russia": country = User.Country.RUSSIA; break;
                    case "Other": country = User.Country.OTHER; break;
                    default:country = null;
                }
                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setBirthDate(new Date(birthDate));
                user.setMale(isMale);
                user.setCountry(country);

                users.add(user);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
