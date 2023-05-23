package mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonMapperUtils {
    public static ObjectMapper mapper = new ObjectMapper();

    public static String writeValueAsString(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (Exception e) {
            log.warn("json反序列化失败(空指针代替), 类为 {}, 方法为 {}", o, e.getMessage());
            return null;
        }
    }

    public static Object readValue(String str, Class<?> c) {
        try {
            return mapper.readValue(str, c);
        } catch (Exception e) {
            log.warn("json序列化失败(空指针代替), 为 {}, 方法为 {}", str, e.getMessage());
            return null;
        }
    }
}