package demo;

public class Test01 {

    public Test01() {
        System.out.println("test01");
    }

    // 注解
    public Test01(String s) {
        System.out.println("Test01, s = " + s);
    }

    public int run(int a, int b) {
        return a + b;
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
