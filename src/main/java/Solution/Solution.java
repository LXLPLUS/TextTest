package Solution;

import lib.interfaces.MainMethod;

import javax.validation.constraints.NotNull;

public class Solution {
    @NotNull
    public void Test01(String s) {
        System.out.println("Test01, s = " + s);
    }

    @MainMethod
    public int run(int a, int b) {
        return a + b;
    }

    @MainMethod
    public int run2(int a, int b, int c) {
        return a + b + c;
    }

    private void testPrivate() {

    }

    private int testPrivate(int a) {
        return a;
    }
}