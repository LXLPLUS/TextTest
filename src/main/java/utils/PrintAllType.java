package utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class PrintAllType {

    /**]
     * 返回可读的字符
     * @param o 一个可用的类构造器
     * @return 一个解析的结果 + 类型
     */
    static List<String> getString(Object o) {
        if (o == null) {
            return List.of("null", "null");
        }
        if (o instanceof int[]) {
            return List.of(Arrays.toString((int[])(o)), "int[]");
        }
        else if (o instanceof long[]) {
            return List.of(Arrays.toString((long[])(o)), "long[]");
        }
        else if (o instanceof int[][]) {
            return List.of(Arrays.deepToString((int[][])(o)), "int[][]");
        }
        else if (o instanceof long[][]) {
            return List.of(Arrays.deepToString((long[][])(o)), "long[][]");
        }
        else if (o instanceof Object[]) {
            Object[] temp = (Object[]) o;
            if (temp.length > 0) {
                return List.of(Arrays.toString((Object[]) o), temp[0].getClass().toString() + "[]");
            }
            return List.of(Arrays.toString((Object[]) o), "Object[]");
        }
        return List.of(o.toString(), o.getClass().toString());
    }

    static public void print(Object o) {
        List<String> list = getString(o);
        String message = list.get(0);
        String type = list.get(1);
        System.out.println("类型: " + type + ", 数据: " + message);
    }

}
