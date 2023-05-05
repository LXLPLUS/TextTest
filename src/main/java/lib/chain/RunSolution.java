package lib.chain;

import lib.exception.ParserFailedException;
import lib.interfaces.SourceParam;
import lib.interfaces.SourceParams;
import lib.types.AllTypeMapper;
import lombok.extern.slf4j.Slf4j;
import utils.MethodUtils;
import utils.PrintAllType;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

@Slf4j
public class RunSolution {

    Object o;
    Method[] methods;

    static final AllTypeMapper mapper = new AllTypeMapper();


    public RunSolution(Class<?> c, Class<? extends Annotation> startAnnotation, Class<? extends SourceParams> sourceAnnotation) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ParserFailedException {
        newInstance(c);
        getMethod(c, startAnnotation);

        for (Method method : methods) {
            getParams(c, method, sourceAnnotation);
        }
    }

    void newInstance(Class<?> c) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        o = c.getDeclaredConstructor().newInstance();
    }

    void getMethod(Class<?> c, Class<? extends Annotation> startAnnotation) {
        methods = MethodUtils.getMethodWithInterface(c, startAnnotation);
    }

    void getParams(Class<?> c, Method method, Class<? extends SourceParams> sourceAnnotation) throws ParserFailedException, InvocationTargetException, IllegalAccessException {
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

        Object[] values = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            if (!mapper.check(params[i], parameterTypes[i])) {
                throw new ParserFailedException("解析数据失败,是" + method + "的第" + (i + 1) + "个方法失败");
            }
            values[i] = mapper.getMessage(params[i], parameterTypes[i]);
        }
        log.info("成功进入类 {} 并准备执行 {} 方法！", o, method.getName());

        for (Object value : values) {
            List<String> stringList = PrintAllType.getString(value);
            log.info("开始传入参数, 参数的类型为: {} , 数据为 {} ", stringList.get(1), stringList.get(0));
        }

        Object invoke = MethodUtils.invoke(o, method, values);
        log.info("最后的结果为： {}", PrintAllType.getInfo(invoke));
    }


}
