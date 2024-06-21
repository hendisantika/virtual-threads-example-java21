package id.my.hendisantika;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        try (ExecutorService executorThreadPool = Executors.newFixedThreadPool(100)) {
            executeTasks(executorThreadPool);
        }
        try (ExecutorService executorVirtualThread = Executors.newVirtualThreadPerTaskExecutor()) {
            executeTasks(executorVirtualThread);
        }
    }

    private static void executeTasks(ExecutorService executor) throws InterruptedException, ExecutionException {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 1_000; i++) {
            tasks.add(new Task(i));
        }
        long time = System.currentTimeMillis();
        List<Future<Integer>> futures = executor.invokeAll(tasks);
        long sum = 0;
        for (Future<Integer> future : futures) {
            sum += future.get();
        }
        time = System.currentTimeMillis() - time;
        System.out.println("****************************************************");
        System.out.println("sum = " + sum + "; time = " + time + " ms");
        System.out.println("****************************************************\n\n");
        executor.shutdown();
    }
}
