package lib.chain;

import lib.exception.AnnotationException;
import lib.interfaces.SourceParam;
import lib.interfaces.SourceParams;
import lombok.extern.slf4j.Slf4j;
import utils.MethodUtils;

import java.lang.reflect.Method;

@Slf4j
public class AnnotationPreCheck {

    public AnnotationPreCheck(Class<?> c, Class<? extends SourceParam> singleAnnotation,
                              Class <? extends SourceParams> collectAnnotation) throws AnnotationException {
        methodCheck(c, singleAnnotation, collectAnnotation);
    }

    /**
     * 检查注解是否存在
     * @param c 类的方法
     * @param singleAnnotation  单注解
     * @param collectAnnotation 重复注解
     * @return 两个注解是否存在
     */
    public static boolean checkAnnotation(Class<?> c, Class<? extends SourceParam> singleAnnotation,
                                          Class <? extends SourceParams> collectAnnotation) {
        return utils.MethodUtils.getMethodWithInterface(c, singleAnnotation, collectAnnotation).length > 0;
    }

    /**
     * 检查注解和方法是否数量完全一致，如果不一致会抛出异常，结束class构建操作
     */
    void methodCheck(Class<?> c, Class<? extends SourceParam> sigleAnnotation,
                     Class <? extends SourceParams> collectAnnotation) throws AnnotationException {
        Method[] methods = MethodUtils.getMethodWithInterface(c, sigleAnnotation, collectAnnotation);

        for (Method method : methods) {
            SourceParam annotation = method.getAnnotation(sigleAnnotation);
            int parameterCount = method.getParameterCount();
            if (annotation != null && parameterCount > 1) {
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
