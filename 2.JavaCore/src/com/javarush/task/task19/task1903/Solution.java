package com.javarush.task.task19.task1903;

import java.util.HashMap;
import java.util.Map;

/* 
Адаптация нескольких интерфейсов
*/

public class Solution {
    public static Map<String, String> countries = new HashMap<String, String>();

    static
    {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {


        String num = String.format("%010d", 501234567);
        System.out.println(num);
        String phoneNumber = String.format("+%d(%s)%s-%s-%s", 38, num.substring(0, 3), num.substring(3,6), num.substring(6,8), num.substring(8,10));
        System.out.println(phoneNumber);


    }

    public static class IncomeDataAdapter implements Customer, Contact {
        private IncomeData data;

        public IncomeDataAdapter(IncomeData incomeData) {
            this.data = incomeData;
        }


        @Override
        public String getCompanyName() {
            return data.getCompany();
        }

        @Override
        public String getCountryName() {
            return countries.get(data.getCountryCode());
        }

        @Override
        public String getName() {
            String name = data.getContactLastName() + ", " + data.getContactFirstName();
            return name;
        }

        @Override
        public String getPhoneNumber() {

            String num = String.format("%010d", data.getPhoneNumber());
            String phoneNumber = String.format("+%d(%s)%s-%s-%s", this.data.getCountryPhoneCode(),
                    num.substring(0, 3),
                    num.substring(3,6),
                    num.substring(6,8),
                    num.substring(8));
            return phoneNumber;
        }
    }


    public interface IncomeData {
        String getCountryCode();        //For example: UA

        String getCompany();            //For example: JavaRush Ltd.

        String getContactFirstName();   //For example: Ivan

        String getContactLastName();    //For example: Ivanov

        int getCountryPhoneCode();      //For example: 38

        int getPhoneNumber();           //For example1: 501234567, For example2: 71112233
    }

    public interface Customer {
        String getCompanyName();        //For example: JavaRush Ltd.

        String getCountryName();        //For example: Ukraine
    }

    public interface Contact {
        String getName();               //For example: Ivanov, Ivan

        String getPhoneNumber();        //For example1: +38(050)123-45-67, For example2: +38(007)111-22-33
    }
}