package parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import model.JsonMapper;
import utils.TypeUtils;

import java.lang.reflect.Type;
import java.util.List;

@Slf4j
public class ListParser implements ParserInterface {

    ObjectMapper objectMapper = JsonMapper.mapper;

    @Override
    public Object parser(String str, Class<?> ruler, Type type) throws Exception {

        List<Class<?>> typeList = TypeUtils.getClass(type);

        return objectMapper.readValue(str, objectMapper.getTypeFactory()
                .constructCollectionType(List.class, typeList.get(1)));
    }
}
