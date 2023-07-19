package model;

import lib.exception.UndefinedException;

import java.util.Arrays;

public class Timer {
    long[] list = new long[2];

    public void flushStartTime() {
        list[0] = System.currentTimeMillis();
    }

    public void flushFinishTime() {
        list[1] = System.currentTimeMillis();
    }

    public void clear() {
        Arrays.fill(list, 0L);
    }
    public long spendMillis() throws UndefinedException {
        if (list[0] == 0) {
            throw new UndefinedException("开始时间未定义！");
        }
        if (list[1] == 0) {
            throw new UndefinedException("结束时间未定义！");
        }
        return list[1] - list[0];
    }
}
