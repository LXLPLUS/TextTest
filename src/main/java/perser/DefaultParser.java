package perser;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultParser implements parserInterface{

    static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object parser(String str, Class<?> ruler) throws Exception {
        Object o = null;
        try {
            o = objectMapper.readValue(str, ruler);
        } catch (Exception e) {
            log.debug("jackJson解析失败,报错 {}", e.getMessage());
        }
        return o;
    }
}
