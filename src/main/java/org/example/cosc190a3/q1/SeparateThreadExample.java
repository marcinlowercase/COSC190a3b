package org.example.cosc190a3.q1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SeparateThreadExample {
    public static void main(String[] args) {
        // Create a thread pool for regular tasks
        ExecutorService executorService = Executors.newFixedThreadPool(5);


        // Create a separate thread for a critical task
        Thread criticalThread = new Thread(() -> {
            System.out.println("Starting critical task immediately...");
            // Perform critical task here
            // ...
            System.out.println("Critical task completed.");
        });



        for (int he = 0;  he < 2 ; he++){
            // Start the critical thread immediately
            criticalThread.start();

            // Submit regular tasks to the thread pool
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                executorService.execute(() -> {
                    System.out.println("Executing regular task from pool: " + finalI);
                });
                try {
                    executorService.awaitTermination(1, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        // Shutdown the thread pool (after all tasks are completed)
        executorService.shutdown();
    }
}