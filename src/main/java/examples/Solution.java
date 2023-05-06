package examples;

import lib.interfaces.SourceParam;
import lib.interfaces.TextTest;
import lib.javaCollections.ListNode;
import lib.javaCollections.TreeNode;

import java.util.List;

public class Solution {

    @TextTest(value = "1.ttest")
    public int run(String a, List<Integer> b, int[] c, ListNode d, TreeNode e, String[] f) {
        return (b.get(0) + c[0] + d.val + e.val + f[0].charAt(0));
    }

    @SourceParam(value = "1")
    @SourceParam(value = "[2]")
    @SourceParam(value = "[3]")
    @SourceParam(value = "[4]")
    @SourceParam(value = "[5]")
    @SourceParam(value = "[6]")
    public int run02(String a, List<Integer> b, int[] c, ListNode d, TreeNode e, String[] f) {
        return (b.get(0) + c[0] + d.val + e.val + f[0].charAt(0));
    }


}