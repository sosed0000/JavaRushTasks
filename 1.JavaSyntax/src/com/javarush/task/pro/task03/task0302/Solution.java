package com.javarush.task.pro.task03.task0302;

import java.util.Scanner;

/* 
Призывная кампания
*/

public class Solution {
    public static void main(String[] args) {
        String militaryCommissar = ", явитесь в военкомат";
        //напишите тут ваш код
        Scanner console = new Scanner(System.in);
        String name = console.nextLine();
        int age = console.nextInt();
        if (age >= 18 & age <= 28)
        System.out.println(name + militaryCommissar);

    }
}
