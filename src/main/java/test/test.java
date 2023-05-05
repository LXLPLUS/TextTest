package test;

import Solution.Solution;
import lib.interfaces.MainMethod;
import lib.types.CheckType;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.Test;
import utils.StringBuilderUtils;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

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
        CheckType checkType = new CheckType("[1, 2, 3]");
        List<Double> numberList = checkType.getNumberList("[1, 3, 4, 5]");
        System.out.println(checkType.getIntegerList("[1, 3, 4, 5]"));
        System.out.println(numberList);

        System.out.println(StringBuilderUtils.toHalfCharacter("（），"));
    }

    @Test
    public void run02() {
    }
}
