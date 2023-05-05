package utils;

import java.lang.annotation.Annotation;
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

    /**
     * 检查类上存在某注解的方法
     * @param c 一个需要分析的类
     * @param annotation 加入的注解
     * @return method数组
     */
    static public Method[] getMethodWithInterface(Class<?> c, Class<? extends Annotation> annotation) {
        List<Method> methodWithAnnotationList = new ArrayList<>();
        for (Method method : c.getMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                methodWithAnnotationList.add(method);
            }
        }
        return methodWithAnnotationList.toArray(Method[]::new);
    }

}
