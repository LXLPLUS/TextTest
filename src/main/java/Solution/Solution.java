package Solution;

import lib.interfaces.MainMethod;
import lib.interfaces.SourceParam;
import lib.interfaces.SourceParams;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Solution {
    @NotNull
    public void Test01(String s) {

    }

    @MainMethod
    @SourceParam(value = "11")
    @SourceParam(value = "11")
    public int run(int a, List<Integer> b) {
        return 0;
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