package examples;

import lib.annotation.TextTest;

@TextTest(value = "2")
public class Solution2 {
    public String run(String a, String b) {
        return a + b;
    }
}
