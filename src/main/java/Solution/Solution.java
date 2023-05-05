package Solution;

import lib.interfaces.MainMethod;
import lib.interfaces.SourceParam;
import lib.interfaces.SourceParams;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Solution {

    @MainMethod
    @SourceParam(value = "10")
    @SourceParam(value = "[11, 12]")
    @SourceParam(value = "[12, 13]")
    @SourceParam(value = "[13, 14]")
    public int run(int a, List<Integer> b, int[] c, List<Double> d) {
        return (int) (a + b.get(0) + c[0] + d.get(0));
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