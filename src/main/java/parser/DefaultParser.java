package parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import utils.JsonMapperUtils;

import java.lang.reflect.Type;

@Slf4j
public class DefaultParser implements ParserInterface {

    static ObjectMapper objectMapper = JsonMapperUtils.mapper;

    @Override
    public Object parser(String str, Class<?> ruler, Type type) throws Exception {
        Object o = null;
        try {
            o = objectMapper.readValue(str, ruler);
        } catch (Exception e) {
            log.debug("jackJson解析失败, 解析的数据为 {},报错 {}", str, e.getMessage());
        }
        return o;
    }
}
