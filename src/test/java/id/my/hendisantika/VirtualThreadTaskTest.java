package id.my.hendisantika;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * Project : virtual-threads-example-java21
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/21/24
 * Time: 09:50
 * To change this template use File | Settings | File Templates.
 */
public class VirtualThreadTaskTest {
    @Test
    void execute() throws Exception {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
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
            assertTrue(time >= 1000 && time <= 2000);
        }
    }
}
