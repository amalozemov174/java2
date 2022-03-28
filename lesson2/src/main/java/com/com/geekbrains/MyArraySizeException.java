package com.com.geekbrains;

public class MyArraySizeException extends RuntimeException {
    MyArraySizeException(int size){
//        super(size);
        System.out.printf("Массив некорректного размера. %d != 4\n", size);
    }
}
