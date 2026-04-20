package com.eduproject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        int n = 20; // You can change this to test with different ranges
        FizzBuzz fizzBuzz = new FizzBuzz(n);

        // Create a fixed thread pool with 4 threads for each task
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // Create and run the fizz thread
        executorService.submit(() -> {
            try {
                fizzBuzz.fizz(() -> {
                    System.out.println("Fizz (" + Thread.currentThread().getName() + ") ");
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Create and run the buzz thread
        executorService.submit(() -> {
            try {
                fizzBuzz.buzz(() -> {
                    System.out.println("Buzz (" + Thread.currentThread().getName() + ") ");
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Create and run the fizzbuzz thread
        executorService.submit(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> {
                    System.out.println("FizzBuzz (" + Thread.currentThread().getName() + ") ");
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Create and run the number thread
        executorService.submit(() -> {
            try {
                fizzBuzz.number(num -> {
                    System.out.println(num + " (" + Thread.currentThread().getName() + ") ");
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Shutdown the executor service after the tasks are completed
        executorService.shutdown();

    }
}

