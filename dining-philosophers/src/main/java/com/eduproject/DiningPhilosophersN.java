package com.eduproject;

import java.util.concurrent.Semaphore;

public class DiningPhilosophersN {

    private final int n;
    private final Semaphore roomSemaphore;
    private final Semaphore[] forks;

    public DiningPhilosophersN(int n) {
        if (n < 2) {
            throw new IllegalArgumentException("Number of philosophers must be at least 2");
        }

        this.n = n;
        this.roomSemaphore = new Semaphore(n - 1);
        this.forks = new Semaphore[n];

        for (int i = 0; i < n; i++) {
            forks[i] = new Semaphore(1);
        }
    }

    public void wantsToEat(
            int philosopher,
            Runnable pickLeftFork,
            Runnable pickRightFork,
            Runnable eat,
            Runnable putLeftFork,
            Runnable putRightFork
    ) throws InterruptedException {

        validatePhilosopher(philosopher);

        int left = philosopher;
        int right = (philosopher + 1) % n;

        roomSemaphore.acquire();

        forks[left].acquire();
        pickLeftFork.run();

        forks[right].acquire();
        pickRightFork.run();

        try {
            eat.run();
        } finally {
            putRightFork.run();
            forks[right].release();

            putLeftFork.run();
            forks[left].release();

            roomSemaphore.release();
        }
    }

    private void validatePhilosopher(int philosopher) {
        if (philosopher < 0 || philosopher >= n) {
            throw new IllegalArgumentException("Invalid philosopher index: " + philosopher);
        }
    }}