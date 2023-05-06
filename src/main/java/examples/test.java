package examples;

import lib.chain.TextTestStarter;
import org.junit.jupiter.api.Test;

public class test {
    @Test
    public void run() throws Exception {
        new TextTestStarter(Solution.class).starkAllTask();
    }
}
