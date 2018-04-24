package com.codecool.dynamicArrayDojo;

import java.util.Arrays;

// put your code here!
public class DynamicIntArray implements IDynamicIntArray {

    private final Integer[] storage;  // use Integer to get null as default content

    DynamicIntArray(int numberOfElements) {
        this.storage = new Integer[numberOfElements];
    }

    DynamicIntArray() {
        int DEFAULT_SIZE = 10;
        this.storage = new Integer[DEFAULT_SIZE];
    }

    @Override
    public boolean add(int num) {
        return false;
    }

    @Override
    public boolean remove(int num) {
        return false;
    }

    @Override
    public boolean insert(int index, int num) {
        return false;
    }

    public String toString() {
        return Arrays.toString(storage);
    }
}
