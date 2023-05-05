package utils;

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
}
