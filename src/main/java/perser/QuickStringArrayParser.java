package perser;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class QuickStringArrayParser implements parserInterface{

    @Override
    public Object parser(String str, Class<?> ruler) throws Exception {
        str = StringUtils.strip(str, "[]{}() \t\r\t");
        str = str.replace("[,\\s\"]+", " ");
        String[] split = str.split("\\s");
        return ArrayUtils.removeAllOccurences(split, "");
    }
}
