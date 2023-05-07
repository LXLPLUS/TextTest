package perser;

import com.fasterxml.jackson.databind.ObjectMapper;
import lib.exception.ParserFailedException;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Slf4j
public class NumberParser implements ParserInterface {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object parser(String str, Class<?> ruler, Type type) throws Exception {

        ArrayList<?> arrayList = objectMapper.readValue(str, ArrayList.class);

        if (type.getTypeName().contains(Long.class.getTypeName())) {
            return arrayList.stream().map(x -> (Number) x).map(Number::longValue)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        if (type.getTypeName().contains(Short.class.getTypeName())) {
            return arrayList.stream().map(x -> (Number) x).map(Number::shortValue)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        if (type.getTypeName().contains(Integer.class.getTypeName())) {
            return arrayList.stream().map(x -> (Number) x).map(Number::intValue)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        if (type.getTypeName().contains(Double.class.getTypeName())) {
            return arrayList.stream().map(x -> (Number) x).map(Number::doubleValue)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        if (type.getTypeName().contains(Float.class.getTypeName())) {
            return arrayList.stream().map(x -> (Number) x).map(Number::floatValue)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        log.warn("无法通过数字方式进行解析！");
        throw new ParserFailedException("无法通过数字方式进行解析！");
    }
}
