package com.codecool.dynamicArrayDojo;

import java.util.Arrays;
import java.util.Objects;

public class DynamicIntArray implements IDynamicIntArray {

    private Integer[] storage;  // use Integer to get null as default content

    DynamicIntArray(int numberOfElements) {
        this.storage = new Integer[numberOfElements+1];
    }

    DynamicIntArray() {
        int DEFAULT_SIZE = 10;
        this.storage = new Integer[DEFAULT_SIZE];
    }

    @Override
    public void add(int num) {
        if( (Arrays.stream(storage).allMatch(Objects::nonNull)) ) {  // array is filled with numbers
            int size = storage.length;
            float sizeMultiplier = 1.5f;
            int newSize = (int) (size * sizeMultiplier);
            extendStorageSize(newSize);
            storage[size] = num;
        } else {
            for(int i=0; i<storage.length; i++) {
                if(storage[i] == null) {
                    storage[i] = num;
                    break;
                }
            }
        }
    }

    @Override
    public void remove(int num) {
        boolean isRemoved = false;
        for(int i=0; i<storage.length; i++) {
            if(storage[i] == num) {
                storage[i] = null;
                isRemoved = true;
                break;
            }
        }
        if(!isRemoved) {
            storage[num] = null;
        }
    }

    @Override
    public void insert(int index, int num) {
        if(index > storage.length-1) {
            extendStorageSize(index+1);
        }
        if(storage[index] == null) {
            storage[index] = num;
        } else {
            storage = concatIntArrays(
                    Arrays.copyOfRange(storage, 0, index),
                    new Integer[]{num},
                    Arrays.copyOfRange(storage, index, storage.length));
        }
    }

    public String toString() {
        int[] toPrimitiveArray = Arrays.stream(storage)
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .toArray();

        return " " + Arrays.toString(toPrimitiveArray)
                .replaceAll("\\[", "")
                .replaceAll("]","")
                .replaceAll(",", "");
    }

    private void extendStorageSize(int newSize) {
        storage = Arrays.copyOf(storage, newSize);
    }

    private Integer[] concatIntArrays(Integer[]... arrays) {
        int outputSize = 0;
        for(Integer[] array : arrays) {
            outputSize += array.length;
        }

        Integer[] first = arrays[0];
        Integer[] output = Arrays.copyOf(arrays[0], outputSize);
        int currentSize = first.length;

        for (int i=1; i<arrays.length; i++) {
            System.arraycopy(arrays[i], 0, output, currentSize, arrays[i].length);
            currentSize += arrays[i].length;
        }
        return output;
    }
}
