package util;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class MethodUtils {
    static final Object emptyObject = new Object();
    static final Set<String> defaultMethods = Arrays.stream(emptyObject.getClass().getMethods()).map(Method::toString).collect(Collectors.toSet());;

    static public Method[] getMetHodNotInObject(Class<?> c) {
        List<Method> methodList = new ArrayList<>();
        for (Method method : c.getMethods()) {
            if (!defaultMethods.contains(method.toString())) {
                methodList.add(method);
            }
        }
        return methodList.toArray(Method[]::new);
    }

}
