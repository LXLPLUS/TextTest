package parser;

/*
  @author 青碧凝霜
 * 2023 2023/5/8 3:01 created
 */
import utils.StringBuilderUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 实际的解析器，也是唯一的入口
 * 但是实际解析一言难尽：可能会解析失败，所以需要多个解析规则适配
 */
public class WorkerParser implements ParserInterface {

    static final NotSuitParser quickParser = new NotSuitParser();
    static final CharArrayParser charArrayParser = new CharArrayParser();
    static final DefaultParser defaultParser = new DefaultParser();
    static FuncParser funcParser = null;

    public WorkerParser()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        funcParser = new FuncParser();
    }
    @Override
    public Object parser(String str, Class<?> ruler, Type type) throws Exception {

        str = StringBuilderUtils.toHalfCharacter(str);
        // 优先函数定义，不然的话其他格式根本无法识别

        if (!str.isBlank() && !str.startsWith("[") && !str.startsWith("\"") && funcParser.checkMethodExist(str)) {
            str = (String) funcParser.parser(str, ruler, type);
        }
        // 无格式字符串的解析工具
        if (!str.startsWith("[") && ruler.isAssignableFrom(ArrayList.class)) {
            str = (String) quickParser.parser(str, ArrayList.class, type);
        }
        if (!str.startsWith("[") && ruler.isArray()) {
            str = (String) quickParser.parser(str, ruler, type);
        }

        if (!str.startsWith("\"") && ruler.isAssignableFrom(String.class)) {
            return str;
        }

        // char是按照字符格式写入的，按照字符进行解析
        if (ruler.isAssignableFrom(char.class)) {
            return charArrayParser.parser(str, char.class, type);
        }
        if (ruler.isAssignableFrom(char[].class)) {
            return charArrayParser.parser(str, char[].class, type);
        }
        if (ruler.isAssignableFrom(char[][].class)) {
            return charArrayParser.parser(str, char[][].class, type);
        }

        return defaultParser.parser(str, ruler, type);
    }
}
