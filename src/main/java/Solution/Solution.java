package Solution;

import lib.interfaces.TextTest;

import java.util.List;

public class Solution {

    @TextTest("1.ttest")
    @TextTest("2.ttest")
    public int run(String a, List<Integer> b, int[] c, List<Double> d) {
        return (int) (b.get(0) + c[0] + d.get(0));
    }

    public int run2(int a, int b, int c) {
        return a + b + c;
    }

    private void testPrivate() {

    }

    private int testPrivate(int a) {
        return a;
    }
}