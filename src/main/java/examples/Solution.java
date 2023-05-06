package examples;

import lib.interfaces.TextTest;
import lib.javaCollections.ListNode;
import lib.javaCollections.TreeNode;

import java.util.List;

public class Solution {

    @TextTest(value = "1.ttest")
    public int run(String a, List<Integer> b, int[] c, ListNode d, TreeNode e, String[] f) {
        return (int) (b.get(0) + c[0] + d.val + e.val + f[0].charAt(0));
    }
}