package utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TypeUtils {
    // 解析类型method.getGenericParameterTypes()
    // java.util.List<java.util.List<java.lang.Long>> 转化为类型列表
    static public List<Class<?>> getClass(Type type) {
        String typeName = type.getTypeName();
        String[] typeList = typeName.split("[<,>\\s]");
        typeList = ArrayUtils.removeAllOccurences(typeList, "");
        return Arrays.stream(typeList).map(TypeUtils::ClassForName).collect(Collectors.toCollection(ArrayList::new));
    }

    static Class<?> ClassForName(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            log.warn("类方法定义失败, 类型为 {} ", str);
            return Object.class;
        }
    }
}
