package utils;

import lib.exception.ParserFailedException;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class MethodUtils {
    static final Object emptyObject = new Object();
    static final Set<String> defaultMethods = Arrays.stream(emptyObject.getClass().getMethods()).map(Method::toString).collect(Collectors.toSet());

    /**
     * 检查所有方法，去除Object的方法
     * @param c 检查方法
     * @return 方法数组
     */
    static public Method[] getMetHodNotInObject(Class<?> c) {
        List<Method> methodList = new ArrayList<>();
        for (Method method : c.getMethods()) {
            if (!defaultMethods.contains(method.toString())) {
                methodList.add(method);
            }
        }
        return methodList.toArray(Method[]::new);
    }

    @SafeVarargs
    static public Method[] getMethodWithInterface(Class<?> c, Class<? extends Annotation>... annotation) {
        List<Method> methodList = new ArrayList<>();
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            for (Class<? extends Annotation> checkAnnotation : annotation) {
                if (method.getAnnotation(checkAnnotation) != null) {
                    methodList.add(method);
                    break;
                }
            }
        }
        return methodList.toArray(Method[]::new);
    }


    static public Object invoke(Object o, Method method, Object[] params)
            throws InvocationTargetException, IllegalAccessException, ParserFailedException {
        if (params.length == 0) {
            return method.invoke(o);
        }
        if (params.length == 1) {
            return method.invoke(o, params[0]);
        }
        if (params.length == 2) {
            return method.invoke(o, params[0], params[1]);
        }
        if (params.length == 3) {
            return method.invoke(o, params[0], params[1], params[2]);
        }
        if (params.length == 4) {
            return method.invoke(o, params[0], params[1], params[2], params[3]);
        }
        if (params.length == 5) {
            return method.invoke(o, params[0], params[1], params[2], params[3], params[4]);
        }
        if (params.length == 6) {
            return method.invoke(o, params[0], params[1], params[2], params[3], params[4], params[5]);
        }
        if (params.length == 7) {
            return method.invoke(o, params[0], params[1], params[2], params[3], params[4], params[5], params[6]);
        }
        throw new ParserFailedException("参数大于7个,超过最大限制(真的有必要那么多参数嘛)");
    }

}
