package com.javarush.task.pro.task13.task1313;

import java.util.Random;

public class StringsLinkedList {
    private Node first = new Node();
    private Node last = new Node();

    public void printAll() {
        Node currentElement = first.next;
        while ((currentElement) != null) {
            System.out.println(currentElement.value);
            currentElement = currentElement.next;
        }
    }

    public void add(String value) {
        //напишите тут ваш код
        if (first.next == null) {
            Node node = new Node();
            node.value = value;
            node.prev = first.next;
            first.next = last.prev = node;
        }
        else if (first.next == last.prev) {
            Node node = new Node();
            node.value = value;
            last.prev.next = node;
            last.prev = node;
            node.prev = first.next;

        }
        else {

            Node node = new Node();
            node.prev = last.prev;
            last.prev.next = node;
            last.prev = node;
            node.value = value;

        }

    }

    public static class Node {
        private Node prev;
        private String value;
        private Node next;
    }
}
