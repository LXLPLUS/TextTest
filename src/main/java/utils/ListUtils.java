package utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListUtils {
    public static List<Object> boxedByType(List<Double> list, Type type) {
        list.removeIf(Objects::isNull);
        if (type.getTypeName().contains(Long.class.getTypeName())) {
            return list.stream().mapToLong(Double::longValue).boxed().collect(Collectors.toCollection(ArrayList::new));
        }
        if (type.getTypeName().contains(Integer.class.getTypeName())) {
            return list.stream().mapToInt(Double::intValue).boxed().collect(Collectors.toCollection(ArrayList::new));
        }
        return list.stream().map(x -> (Object)x).collect(Collectors.toCollection(ArrayList::new));
    }

    public static boolean checkExistString(String str, Collection<String> collection) {
        for (String s : collection) {
            if (str.contains(s)) {
                return true;
            }
        }
        return false;
    }
}
