package com.eduproject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntConsumer;


public class Main {
    public static void main(String[] args) {

        int n = 10;


        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(n);


        ExecutorService executor = Executors.newFixedThreadPool(3);


        IntConsumer printNumber = number ->
                System.out.println(Thread.currentThread().getName() + " printed: " + number);


        executor.submit(() -> {
            Thread.currentThread().setName("Zero-Thread");
            try {
                zeroEvenOdd.zero(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executor.submit(() -> {
            Thread.currentThread().setName("Even-Thread");
            try {
                zeroEvenOdd.even(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        executor.submit(() -> {
            Thread.currentThread().setName("Odd-Thread");
            try {
                zeroEvenOdd.odd(printNumber);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });


        executor.shutdown();
    }
}

