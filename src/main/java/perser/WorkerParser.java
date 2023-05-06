package perser;

/**
 * 实际的解析器，也是唯一的入口
 * 但是实际解析一言难尽：可能会解析失败，所以需要多个解析规则适配
 * 假如有ABC3个规则，那么A解析失败以后可以换个B解析，提高解析成功率
 */
public class WorkerParser implements parserInterface{

    @Override
    public Object parser(String str, Class<?> ruler) throws Exception {
        return new DefaultParser().parser(str, ruler);
    }
}
