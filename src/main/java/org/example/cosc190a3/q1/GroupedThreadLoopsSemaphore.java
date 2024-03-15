package org.example.cosc190a3.q1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class GroupedThreadLoopsSemaphore {
    public static void main(String[] args) throws InterruptedException {

        // Define critical task logic
        Runnable criticalTask = () -> {
            System.out.println("Starting critical task...");

            System.out.println("Critical task completed.");
        };

        // Define regular task logic
        Runnable regularTask = () -> {
            System.out.println("Executing regular task from pool...");
            // Perform regular task logic here
            // ...
        };

        // Create a thread pool for regular tasks
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // Semaphore for critical task execution
        Semaphore semaphore = new Semaphore(1);

        // Loop for both critical and regular thread groups
        for (int i = 0; i < 2; i++) {
            // Critical task acquires permit before execution
            semaphore.acquire();

            // Start critical thread using thread pool
            executorService.execute(() -> {
                criticalTask.run();
                semaphore.release(); // Release permit after critical task finishes
            });

            // Submit regular tasks to thread pool
            for (int j = 0; j < 10; j++) {
                executorService.execute(() -> {
                    try {
                        semaphore.acquire(); // Regular tasks wait for permit
                        regularTask.run();
                        semaphore.release(); // Release permit after regular task finishes
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

            // Wait for all tasks in the pool to finish before next loop iteration
            executorService.awaitTermination(1, java.util.concurrent.TimeUnit.SECONDS);
            System.out.println("Done 1 ground");
        }

        // Shutdown the thread pool (after all loops are completed)
        executorService.shutdown();
    }
}
