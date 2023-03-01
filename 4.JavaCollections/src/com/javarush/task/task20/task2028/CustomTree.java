package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/* 
Построй дерево(1)
*/

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    Map<Integer, Entry<String>> entryMap = new TreeMap<>();
    private int index;
    private static int parentsCount;


    public boolean remove(Object o) throws UnsupportedOperationException {
        if (!(o instanceof String)) throw new UnsupportedOperationException();
        removeBranch(entryMap.get(findEntryIndexByElementName((String) o)));

        return false;
    }

    private void removeBranch(Entry<String> toRemove) {
        if (toRemove == null) return;
        removeBranch(toRemove.leftChild);
        removeBranch(toRemove.rightChild);
        modCount--;
        entryMap.remove(findEntryIndexByElementName(toRemove.elementName));


    }

    private int findEntryIndexByElementName(String elementName) {
        for (Integer i :
                entryMap.keySet()) {
            Entry<String> e = entryMap.get(i);
            if (e.elementName.equals(elementName)) return i;
        }


        return -1;
    }

    public CustomTree() {
        root = new Entry<>("rootElement");
        index = 0;
        entryMap.put(index, root);
        modCount = 0;
    }

    public String getParent(String elementName) {
        for (Integer i :
                entryMap.keySet()) {
            Entry<String> e = entryMap.get(i);
            if (e.elementName.equals(elementName)) return e.parent.elementName;
        }
        return null;
    }

    private int getIndexToAdd() {
        for (Integer i :
                entryMap.keySet()) {
            Entry<String> e = entryMap.get(i);
            if (e.availableToAddLeftChildren || e.availableToAddRightChildren) {
                return i;
            }
        }
        int maxParents = 0;
        for (Integer i :
                entryMap.keySet()) {
            Entry<String> e = entryMap.get(i);
            parentsCount = 0;
            parentsCount(e);
            maxParents = Math.max(maxParents, parentsCount);
        }
        int minIndexWithMaxParents = Integer.MAX_VALUE;
        for (Integer i :
        entryMap.keySet()) {
            Entry<String> e = entryMap.get(i);
            parentsCount = 0;
            parentsCount(e);
            if (parentsCount == maxParents)
            minIndexWithMaxParents = Math.min(minIndexWithMaxParents, i);
        }

        return minIndexWithMaxParents;
    }

    private void parentsCount(Entry<String> e) {
        if (e.parent == null) return;
        parentsCount++;
        if (e.parent == root) return;
        parentsCount(e.parent);
    }


    @Override
    public boolean add(String s) {
        modCount++;
        index++;
        Entry<String> newEntry = new Entry<>(s);
        Entry<String> parentEntry = entryMap.get(getIndexToAdd());
        if (parentEntry.availableToAddLeftChildren) {
            parentEntry.leftChild = newEntry;
            parentEntry.availableToAddLeftChildren = false;
        } else {
            parentEntry.rightChild = newEntry;
            parentEntry.availableToAddRightChildren = false;
        }
        newEntry.parent = parentEntry;

        entryMap.put(index, newEntry);


        return true;
    }


    @Override
    public String get(int index) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return modCount;
    }

    static class Entry<T> implements Serializable {

        String elementName;
        boolean availableToAddLeftChildren = true;
        boolean availableToAddRightChildren = true;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;

        }

        public boolean isAvailableToAddChildren() {
            return (availableToAddLeftChildren || availableToAddRightChildren);
        }
    }
}
