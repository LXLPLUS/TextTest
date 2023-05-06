package examples;

import lib.interfaces.TextTest;
import lib.javaCollections.ListNode;

import java.util.List;

public class Solution {

    @TextTest(value = "1.ttest")
    public int run(String a, List<Integer> b, int[] c, ListNode d) {
        return (int) (b.get(0) + c[0] + d.val);
    }
}