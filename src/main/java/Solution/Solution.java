package Solution;

import lib.interfaces.SourceParam;
import lib.interfaces.TextTest;

import java.util.List;

public class Solution {

    @TextTest("1.ttest")
    public int run(String a, List<Integer> b, int[] c, List<Double> d) {
        return (int) (b.get(0) + c[0] + d.get(0));
    }

    @SourceParam(value = "1")
    @SourceParam(value = "1")
    public int run2(int a, int b, int c) {
        return a + b + c;
    }

    private int testPrivate(int a) {
        return a;
    }
}