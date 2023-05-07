package utils;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringBuilderUtils {
    /**
     * 全角转半角
     * @param input 需要转化的字符
     * @return 转化后的字符
     */
    public static String toHalfCharacter(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    static Pattern p = Pattern.compile("-*\\d+");

    public static long[] getAllInteger(String str) {
        List<Long> list = new ArrayList<>();
        Matcher m = p.matcher(str);
        while (m.find()) {
            list.add(Long.parseLong(m.group()));
        }
        return list.stream().mapToLong(x -> x).toArray();
    }

    public static String[] getAllStringSplitByBlank(String str) {
        str = str.replaceAll("\\s+", " ");
        String[] sArray = str.split(" ");
        ArrayUtils.removeAllOccurences(sArray, "");
        return sArray;
    }

    public static boolean StartsWithIgnoreCase(String str, String startStr) {
        str = str.toLowerCase();
        startStr = startStr.toLowerCase();
        return str.startsWith(startStr);
    }
}
