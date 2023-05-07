package perser;

import lib.javaCollections.ListNode;
import lib.javaCollections.TreeNode;
import utils.StringBuilderUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 实际的解析器，也是唯一的入口
 * 但是实际解析一言难尽：可能会解析失败，所以需要多个解析规则适配
 * 假如有ABC3个规则，那么A解析失败以后可以换个B解析，提高解析成功率
 */
public class WorkerParser implements ParserInterface {

    static final ListNodeParser listNodeParser = new ListNodeParser();
    static final TreeNodeParser treeNodeParser = new TreeNodeParser();
    static final QuickStringArrayParser quickStringArrayParser = new QuickStringArrayParser();
    static final RangeParser rangeArrayParser = new RangeParser();
    static final CharArrayParser charArrayParser = new CharArrayParser();
    static final RandomIntParser randomIntParser = new RandomIntParser();
    static final ListParser listParser = new ListParser();
    static final UUIDParser uuidParser = new UUIDParser();
    static final DefaultParser defaultParser = new DefaultParser();
    static final TimeParser timeParser = new TimeParser();

    // 自定义的方法
    static final String RANGE = "Range";
    static final String RANDOM_INT = "RandomInt";
    static final String UUID = "UUID";
    static final String NOW_TIME = "NowTime";

    @Override
    public Object parser(String str, Class<?> ruler, Type type) throws Exception {


        str = StringBuilderUtils.toHalfCharacter(str);

        // 优先函数定义，不然的话其他格式根本无法识别
        if (StringBuilderUtils.StartsWithIgnoreCase(str, RANDOM_INT)) {
            str = (String) randomIntParser.parser(str, ruler, type);
        }
        if (StringBuilderUtils.StartsWithIgnoreCase(str, RANGE)) {
            str = (String) rangeArrayParser.parser(str, ruler, type);
        }
        if (StringBuilderUtils.StartsWithIgnoreCase(str, UUID)) {
            str = (String) uuidParser.parser(str, String.class, type);
        }
        if (StringBuilderUtils.StartsWithIgnoreCase(str, NOW_TIME)) {
            str = (String) timeParser.parser(str, String.class, type);
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

        // 无格式字符串的解析工具
        if (ruler.isAssignableFrom(String[].class) && !str.startsWith("[")) {
            return quickStringArrayParser.parser(str, String[].class, type);
        }

        if (ruler.isAssignableFrom(ArrayList.class) && !str.startsWith("[")) {
            return quickStringArrayParser.parser(str, ArrayList.class, type);
        }


        // 树的序列工具
        if (ruler.isAssignableFrom(TreeNode.class)) {
            return treeNodeParser.parser(str, TreeNode.class, type);
        }
        // 链表的序列化工具
        if (ruler.isAssignableFrom(ListNode.class)) {
            return listNodeParser.parser(str, ListNode.class, type);
        }

        // list的序列化工具
        if (ruler.isAssignableFrom(ArrayList.class)) {
            return listParser.parser(str, ArrayList.class, type);
        }
        return defaultParser.parser(str, ruler, type);
    }
}
