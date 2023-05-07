package perser;

import lib.javaCollections.ListNode;
import lib.javaCollections.TreeNode;
import utils.ListUtils;
import utils.StringBuilderUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 实际的解析器，也是唯一的入口
 * 但是实际解析一言难尽：可能会解析失败，所以需要多个解析规则适配
 * 假如有ABC3个规则，那么A解析失败以后可以换个B解析，提高解析成功率
 */
public class WorkerParser implements ParserInterface {

    ListNodeParser listNodeParser = new ListNodeParser();
    TreeNodeParser treeNodeParser = new TreeNodeParser();
    QuickStringArrayParser quickStringArrayParser = new QuickStringArrayParser();
    RangeArrayParser rangeArrayParser = new RangeArrayParser();
    CharArrayParser charArrayParser = new CharArrayParser();
    RandomIntParser randomIntParser = new RandomIntParser();
    NumberParser numberParser = new NumberParser();
    UUIDParser uuidParser = new UUIDParser();

    @Override
    public Object parser(String str, Class<?> ruler, Type type) throws Exception {

        str = StringBuilderUtils.toHalfCharacter(str);

        final String range = "Range";

        // 优先函数定义，不然的话其他格式根本无法识别
        boolean isRandomInt = str.toLowerCase().startsWith("RandomInt".toLowerCase());
        if (ruler.isAssignableFrom(int[].class) && isRandomInt) {
            return randomIntParser.parser(str, int[].class, type);
        }
        if (ruler.isAssignableFrom(ArrayList.class) && isRandomInt && type.getTypeName().contains(Integer.class.getTypeName())) {
            return randomIntParser.parser(str, ArrayList.class, type);
        }

        if (ruler.isAssignableFrom(String.class) && str.equalsIgnoreCase("UUID")) {
            return uuidParser.parser(str, String.class, type);
        }
        // 注册Range方法
        // 虽然可以用更高级的语法，但是不用的话可读性反而会更强，纯纯的枚举可以让你知道适配了哪些方法
        // 规则是适配不完的，但是知道哪些是适配了的很重要
        // 假如有人不用ArrayList用LinkedList,我算他NB
        if (ruler.isAssignableFrom(int[].class) && str.toLowerCase().startsWith("range")) {
            return rangeArrayParser.parser(str, int[].class, type);
        }
        if (ruler.isAssignableFrom(long[].class) && str.toLowerCase().startsWith("range")) {
            return rangeArrayParser.parser(str, long[].class, type);
        }
        if (ruler.isAssignableFrom(ArrayList.class) && str.toLowerCase().startsWith("range")) {
            return rangeArrayParser.parser(str, ArrayList.class, type);
        }
        if (ruler.isAssignableFrom(LinkedList.class) && str.toLowerCase().startsWith("range")) {
            return rangeArrayParser.parser(str, LinkedList.class, type);
        }

        // 格式转化
        if(ListUtils.checkExistString(type.getTypeName(), List.of(Integer.class.getTypeName(),
                Float.class.getTypeName(), Long.class.getTypeName(),
                Short.class.getTypeName(), Double.class.getTypeName()))) {
            return numberParser.parser(str, ruler, type);
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
        return new DefaultParser().parser(str, ruler, type);
    }
}
