package com.javarush.task.task21.task2107;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* 
Глубокое клонирование карты
*/

public class Solution implements Cloneable {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.users.put("Hubert", new User(172, "Hubert"));
        solution.users.put("Zapp", new User(41, "Zapp"));
        Solution clone = null;
        try {
            clone = (Solution) solution.clone();
            System.out.println(solution);
            System.out.println(clone);

            System.out.println(solution.users);
            System.out.println(clone.users);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    protected Map<String, User> users = new LinkedHashMap();

    public static class User implements Cloneable {
        int age;
        String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public int hashCode() {
            return age + (name == null ? 0 : name.hashCode());
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof User)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            return age == ((User) obj).age && Objects.equals(name, ((User) obj).name);

        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new User(age, name);
        }
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        for (Map.Entry<String, Solution.User> user : users.entrySet()) {
            hashCode += (user.getKey().hashCode() + user.getValue().hashCode());
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Solution)) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        Solution solution = (Solution) obj;
        Set<String> keySetThis = this.users.keySet();
        Set<String> keySetSolution = solution.users.keySet();
        if (keySetThis.size() == keySetSolution.size()) {
            for (int i = 0; i < keySetThis.size(); i++) {
                if (!(keySetThis.toArray()[i].equals(keySetSolution.toArray()[i]))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        Object[] userSetThis = this.users.values().toArray();
        Object[] userSetSolution = solution.users.values().toArray();
        if (userSetThis.length == userSetSolution.length) {
            for (int i = 0; i < userSetThis.length; i++) {
                if (!(userSetThis[i].equals(userSetSolution[i]))) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Solution solution = new Solution();
        for (String key : users.keySet()) {
        solution.users.put(key, new User(users.get(key).age, users.get(key).name));
        }
        return solution;
    }
}
