package perser;

import model.JsonMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeParser implements ParserInterface{

    static final String defaultFormat = "yyyy-MM-dd HH:mm:ss:ZZZ";

    Pattern p = Pattern.compile("\\([^()]+\\)");

    @Override
    public Object parser(String str, Class<?> ruler, Type type) throws Exception {

        Matcher m = p.matcher(str);
        if (m.find()) {
            return JsonMapper.mapper.writeValueAsString(
                    DateFormatUtils.format(new Date(), StringUtils.strip(m.group(), "()")));
        }
        return JsonMapper.mapper.writeValueAsString(
                DateFormatUtils.format(new Date(), defaultFormat));
    }
}
