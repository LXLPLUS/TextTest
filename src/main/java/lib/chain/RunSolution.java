package lib.chain;

import lib.interfaces.SourceParam;
import lib.interfaces.SourceParams;
import utils.MethodUtils;
import utils.PrintAllType;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class RunSolution {

    Object o;
    Method[] methods;
    public RunSolution(Class<?> c, Class<? extends Annotation> startAnnotation, Class<? extends SourceParams> sourceAnnotation) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        newInstance(c);
        getMethod(c, startAnnotation);

        for (Method method : methods) {
            getParams(method, sourceAnnotation);
        }
    }

    void newInstance(Class<?> c) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        o = c.getDeclaredConstructor().newInstance();
    }

    void getMethod(Class<?> c, Class<? extends Annotation> startAnnotation) {
        methods = MethodUtils.getMethodWithInterface(c, startAnnotation);
    }

    void getParams(Method method, Class<? extends SourceParams> sourceAnnotation) {
        Annotation[] annotations = method.getAnnotations();
        SourceParams dataAnnotation = null;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().isAssignableFrom(sourceAnnotation)) {
                dataAnnotation = (SourceParams) annotation;
            }
        }

        if (dataAnnotation == null) {
            return;
        }

        Type[] parameterTypes = method.getGenericParameterTypes();
        SourceParam[] annotationParam = dataAnnotation.value();

        String[] params = new String[parameterTypes.length];
        for (int i = 0; i < annotationParam.length; i++) {
            params[i] = annotationParam[i].value();
        }


        PrintAllType.print(parameterTypes);
        PrintAllType.print(params);

    }
}
