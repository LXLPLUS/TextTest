package lib.types;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import utils.StringBuilderUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class DoubleListMapper implements MapperStrToObjInterface{

    final static String typeName = "java.util.List<java.lang.Double>";

    /**
     * 字符串切割的规则
     * @param str 被切割的字符串
     * @return 字符串数组
     */
    String[] cutString(String str) {
        str = StringBuilderUtils.toHalfCharacter(str);
        str = StringUtils.strip(str, "()[]{} \n\t");
        String[] split = str.split("[,\\s]+");
        split = ArrayUtils.removeAllOccurences(split, "");
        return split;
    }

    @Override
    public boolean check(String str, Type type) {
        String[] split = cutString(str);
        for (String numCheck : split) {
            if (!NumberUtils.isCreatable(numCheck)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object getMessage(String str, Type type) {
        String[] split = cutString(str);
        return Arrays.stream(split).map(Double::parseDouble).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
}
