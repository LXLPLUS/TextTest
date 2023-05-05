package test;

import Solution.Solution;
import lib.chain.PreCheck;
import lib.exception.AnnotationException;
import lib.interfaces.MainMethod;
import lib.interfaces.SourceParam;
import lib.interfaces.SourceParams;
import lib.types.DoubleArrayMapper;
import lib.types.DoubleListMapper;
import lib.types.LongListMapper;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.Test;

import javax.xml.transform.Source;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;

public class test {

    @Test
    public void run() {
        Method[] methodWithInterface = MethodUtils.getMethodsWithAnnotation(Solution.class, MainMethod.class);
        for (Method method : methodWithInterface) {
            AnnotatedType annotatedReturnType = method.getAnnotatedReturnType();
            Type type = annotatedReturnType.getType();
            System.out.println(type.getTypeName());

        }
        for (Method method : methodWithInterface) {
            System.out.println(method.getName());
        }
    }

    @Test
    public void run01() {
        String data = "[125635, 2, 5, 6, 8, 8.1]";
        System.out.println(Arrays.toString((double[]) new DoubleArrayMapper().getMessage(data, null)));
        System.out.println(new DoubleListMapper().getMessage(data, null));
        System.out.println(new LongListMapper().getMessage(data, null));
    }

    @Test
    public void run02() {
        try {
            PreCheck preCheck = new PreCheck(Solution.class, MainMethod.class, SourceParams.class);
        } catch (AnnotationException e) {
            e.printStackTrace();
        }
    }
}
