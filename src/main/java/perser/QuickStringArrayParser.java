package perser;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 简单来说，让实现只写a b c就能解析成为列表成为可能
 */
@Slf4j
public class QuickStringArrayParser implements ParserInterface {

    @Override
    public Object parser(String str, Class<?> ruler, Type type) throws Exception {
        log.debug("青碧凝霜提示：解析字符串要符合Json规范，不要直接a b c,嫌我啰嗦的话日志级别设置为info可以屏蔽所有这种提示");
        str = StringUtils.strip(str, "[]{}() \t\r\t");
        str = str.replace("[,\\s\"]+", " ");
        String[] split = str.split("\\s");
        ArrayUtils.removeAllOccurences(split, "");
        if (ruler.isAssignableFrom(ArrayList.class)) {
            return Arrays.stream(split).collect(Collectors.toCollection(ArrayList::new));
        }
        if (ruler.isAssignableFrom(String[].class)) {
            return split;
        }
        return null;
    }
}
