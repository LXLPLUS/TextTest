package examples;

import lib.annotation.SourceParam;
import lib.annotation.TextTest;
import lib.javaCollections.ListNode;
import lib.javaCollections.TreeNode;

import java.util.List;

public class Solution {

    @TextTest(value = "1.ttest")
    public int run(String a, List<Integer> b, char[] c, ListNode d, TreeNode e, String[] f) {
        return (b.get(0) + c[0] + d.val + e.val + f[0].charAt(0));
    }

    @SourceParam(value = "[1, 3, 4]")
    @SourceParam(value = "Range(1, 100, 2)")
    @SourceParam(value = "Range(1, 100, 2)")
    @SourceParam(value = "[1]")
    @SourceParam(value = "[\"b\"]")
    @SourceParam(value = "[6, 7]")
    public double run02(List<Integer> a, List<Long> b, int[] c, ListNode d, char[] e, String[] f) {
        return (b.get(0) + c[0] + d.val + e[0] + f[0].charAt(0));
    }

    @SourceParam(value = "RandomInt(20, 1, 13)")
    @SourceParam(value = "1, 2")
    @SourceParam(value = "[1, 5, 5]")
    @SourceParam(value = "[1]")
    @SourceParam(value = "[\"b\"]")
    @SourceParam(value = "UUID")
    public String run03(int[] a, List<Long> b, int[] c, ListNode d, char[] e, String f) {
        return f + (b.get(0) + c[0] + d.val + e[0]);
    }

    @SourceParam(value = "NowTime(yyyy-MM-dd)")
    @SourceParam(value = "a b c")
    @SourceParam(value = "a b c")
    @SourceParam(value = "abcd")
    public String run04(String str1, String[] b, List<String> c, char[] d) {
        return str1;
    }

    @SourceParam
    public String run05() {
        return "hello world";
    }

    @SourceParam(value = "RandomString(3)")
    @SourceParam(value = "RandomChar(10)")
    public String run06(String name, String id) {
        return name + id;
    }
}