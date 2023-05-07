package perser;

import java.lang.reflect.Type;

public interface ParserInterface {
    Object parser(String str, Class<?> ruler, Type type) throws Exception;
}
