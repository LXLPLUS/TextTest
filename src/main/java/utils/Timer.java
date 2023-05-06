package utils;

public class Timer {
    long[] list = new long[2];

    public void time() {
        list[0] = list[1];
        list[1] = System.currentTimeMillis();
    }

    public long getTimer() {
        return list[1] - list[0];
    }
}
