package examples;

import lib.interfaces.SourceParam;
import lib.interfaces.TextTest;
import lib.javaCollections.ListNode;
import lib.javaCollections.TreeNode;

import java.util.List;

public class Solution {

    @TextTest(value = "1.ttest")
    public int run(String a, List<Integer> b, char[] c, ListNode d, TreeNode e, String[] f) {
        return (b.get(0) + c[0] + d.val + e.val + f[0].charAt(0));
    }

    @SourceParam(value = "RandomInt(100, 1, 100)")
    @SourceParam(value = "Range(1, 100, 2)")
    @SourceParam(value = "Range(1, 100, 2)")
    @SourceParam(value = "[1]")
    @SourceParam(value = "[\"b\"]")
    @SourceParam(value = "[6, 7]")
    public double run02(List<Integer> a, List<Long> b, int[] c, ListNode d, char[] e, String[] f) {
        return (b.get(0) + c[0] + d.val + e[0] + f[0].charAt(0));
    }

    @SourceParam(value = "RandomInt(100, 1, 100)")
    @SourceParam(value = "[1, 2]")
    @SourceParam(value = "[1, 5, 5]")
    @SourceParam(value = "[1]")
    @SourceParam(value = "[\"b\"]")
    @SourceParam(value = "UUID")
    public String run03(int[] a, List<Long> b, int[] c, ListNode d, char[] e, String f) {
        return f + (b.get(0) + c[0] + d.val + e[0]);
    }


}