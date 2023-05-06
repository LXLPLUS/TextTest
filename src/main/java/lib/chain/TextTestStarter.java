package lib.chain;

import examples.Solution;
import lib.interfaces.TestCollect;

public class TextTestStarter {
    public TextTestStarter(Class<?> c) throws Exception {
        new TestRunSolution(Solution.class, lib.interfaces.TextTest.class, TestCollect.class);
    }
}
