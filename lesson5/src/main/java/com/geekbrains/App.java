package com.geekbrains;

import java.util.Arrays;
import java.util.List;

public class App
{
    public static void main(String[] args) {
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        float[] arrSecond = new float[size];
        checkTimeStraightStream(arr);
        checkTimeMultiStreams(arrSecond);
    }

    public static void checkTimeStraightStream(float[] arr){
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++){
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.out.println("Время на зполнение массива в одном потоке " + (System.currentTimeMillis() - a));
    }

    public static void checkTimeMultiStreams(float[] arr){
        long b = System.currentTimeMillis();
        Arrays.fill(arr, 1);
        float arr1[] = Arrays.copyOfRange(arr, 0, arr.length/2);
        float arr2[] = Arrays.copyOfRange(arr, arr.length/2, arr.length);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr1.length; i++) {
                    arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 50; i < arr2.length; i++) {
                    arr2[i] = (float)(arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        float[] arrComposite = Arrays.copyOf(arr1, arr.length);
        System.arraycopy(arr2, 0, arrComposite, arr1.length, arr2.length);

        System.out.println("Время на зполнение массива в параллельных потоках " + (System.currentTimeMillis() - b));
    }
}
