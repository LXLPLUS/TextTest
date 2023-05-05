package utils;

import java.util.Random;

public class RandomUtils {
    static Random r = new Random();

    static public int RandomPositiveInteger(int maxNum) {
        return r.nextInt(maxNum);
    }

    static public int RandomPositiveInteger() {
        return r.nextInt(Integer.MAX_VALUE);
    }

    static public long RandomLong() {
        return r.nextLong();
    }
}
