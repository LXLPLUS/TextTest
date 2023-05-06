package lib.chain;

import lib.exception.AnnotationException;
import lib.interfaces.SourceParam;
import lib.interfaces.SourceParams;
import lombok.extern.slf4j.Slf4j;
import utils.MethodUtils;

import java.lang.reflect.Method;

@Slf4j
public class AnnotationPreCheck {

    public AnnotationPreCheck(Class<?> c, Class<? extends SourceParam> startAnnotation,
                              Class <? extends SourceParams> collectAnnotation) throws AnnotationException {
        methodCheck(c, startAnnotation, collectAnnotation);
    }

    public static boolean checkAnnotation(Class<?> c, Class<? extends SourceParam> startAnnotation,
                                          Class <? extends SourceParams> collectAnnotation) {
        return utils.MethodUtils.getMethodWithInterface(c, startAnnotation, collectAnnotation).length > 0;
    }

    void methodCheck(Class<?> c, Class<? extends SourceParam> startAnnotation,
                     Class <? extends SourceParams> collectAnnotation) throws AnnotationException {
        Method[] methods = MethodUtils.getMethodWithInterface(c, startAnnotation, collectAnnotation);

        for (Method method : methods) {
            SourceParam annotation = method.getAnnotation(startAnnotation);
            int parameterCount = method.getParameterCount();
            if (annotation != null && parameterCount != 1) {
                log.warn("注解和函数实际执行的参数数量不符!");
                throw new AnnotationException("注解和函数实际执行的参数数量不符!");
            }

            SourceParams declaredAnnotation = method.getAnnotation(collectAnnotation);
            if (declaredAnnotation != null && declaredAnnotation.value().length != parameterCount) {
                log.warn("注解和函数实际执行的参数数量不符! 注解有 {} 个, 实际方法有 {} 个方法",
                        declaredAnnotation.value().length, parameterCount);
                throw new AnnotationException("注解和函数实际执行的参数数量不符!");
            }
        }
    }
}
