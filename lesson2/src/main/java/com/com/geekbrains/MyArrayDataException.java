package com.com.geekbrains;

public class MyArrayDataException extends RuntimeException {

    MyArrayDataException(int i, int j, String s){
        System.out.printf("Элемент %d %d содержит некорректный символ %s\n", i, j, s);
    }

}
