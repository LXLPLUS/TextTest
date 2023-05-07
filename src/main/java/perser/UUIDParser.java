package perser;

import model.JsonMapper;

import java.lang.reflect.Type;
import java.util.UUID;

public class UUIDParser implements ParserInterface {
    @Override
    public Object parser(String str, Class<?> ruler, Type type) throws Exception {
        return JsonMapper.mapper.writeValueAsString(UUID.randomUUID().toString());
    }
}
