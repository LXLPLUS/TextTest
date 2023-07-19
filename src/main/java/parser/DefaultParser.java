package parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import mapper.JsonMapperUtils;
import utils.TypeUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Slf4j
public class DefaultParser implements ParserInterface {

    static ObjectMapper objectMapper = JsonMapperUtils.mapper;

    @Override
    public Object parser(String str, Class<?> ruler, Type type){
        Object o = null;
        try {
            if (ruler.isAssignableFrom(List.class)) {
                List<Class<?>> typeList = TypeUtils.getClass(type);
                return objectMapper.readValue(str, objectMapper.getTypeFactory()
                        .constructCollectionType(List.class, typeList.get(1)));
            }
            else if (ruler.isAssignableFrom(Map.class)) {
                List<Class<?>> typeList = TypeUtils.getClass(type);
                return objectMapper.readValue(str, objectMapper.getTypeFactory()
                        .constructMapType(Map.class, typeList.get(1), typeList.get(2)));
            }
            o = objectMapper.readValue(str, ruler);
        } catch (Exception e) {
            log.debug("jackJson解析失败, 解析的数据为 {},报错 {}", str, e.getMessage());
        }
        return o;
    }
}
