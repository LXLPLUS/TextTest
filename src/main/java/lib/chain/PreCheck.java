package lib.chain;

import lib.exception.AnnotationException;
import lib.interfaces.SourceParams;
import utils.MethodUtils;
import utils.PrintAllType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class PreCheck {
    public PreCheck(Class<?> c, Class<? extends Annotation> startAnnotation, Class<? extends SourceParams> sourceAnnotation) throws AnnotationException {
    checkMainAnnotation(c, startAnnotation);
    checkSource(c, startAnnotation, sourceAnnotation);
    }

    void checkMainAnnotation(Class<?> c, Class<? extends Annotation> startAnnotation) throws AnnotationException {
        Method[] methodWithInterface = MethodUtils.getMethodWithInterface(c, startAnnotation);
        if (methodWithInterface.length == 0) {
            throw new AnnotationException("没有获取标准的@MainMethod 或者方法并不是public");
        }
    }

    void checkSource(Class<?> c, Class<? extends Annotation> startAnnotation, Class<? extends SourceParams> sourceAnnotation) throws AnnotationException {
        Method[] methodWithInterface = MethodUtils.getMethodWithInterface(c, startAnnotation);
        for (Method method : methodWithInterface) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Annotation[] annotations = method.getAnnotations();

            SourceParams dataAnnotation = null;
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().isAssignableFrom(sourceAnnotation)) {
                     dataAnnotation = (SourceParams) annotation;
                }
            }

            if (dataAnnotation == null) {
                throw new AnnotationException("无法获取到目的的SourceParam注解");
            }

            int countAnnotations = dataAnnotation.value().length;
            int countParameter = parameterTypes.length;
            if (countParameter != countAnnotations) {
                String errorMessage = "获取注解数量和参数不对应！方法的注解为" + countParameter + "个," +
                        " 但是实际的注解为" + countAnnotations + "个";
                errorMessage += "\n实际注解为" + PrintAllType.getInfo(dataAnnotation);
                errorMessage += "\n实际需要填充的方法类型为：" + PrintAllType.getInfo(parameterTypes);
                throw new AnnotationException(errorMessage);
            }
        }
    }

}
