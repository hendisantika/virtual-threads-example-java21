package id.my.hendisantika;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    void thatCallReturnsResult() throws Exception {
        Task task = new Task(5);
        Integer result = task.call();
        assertNotNull(result);
    }
}
