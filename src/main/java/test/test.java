package test;

import Solution.Solution;
import lib.chain.TestRunSolution;
import lib.chain.TextPreCheck;
import lib.exception.AnnotationException;
import lib.exception.ParserFailedException;
import lib.interfaces.TestCollect;
import lib.interfaces.TextTest;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class test {

    @Test
    public void run() {
        Method[] methodWithInterface = MethodUtils.getMethodsWithAnnotation(Solution.class, TextTest.class);
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
        for (Method method : Solution.class.getMethods()) {
            System.out.println(method);
            for (Annotation annotation : method.getAnnotations()) {
                System.out.println(annotation);
            }
        }
    }

    @Test
    public void run03() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, ParserFailedException, IOException, AnnotationException {
        System.out.println(new TextPreCheck(Solution.class, TextTest.class, TestCollect.class));
    }

    @Test
    public void run04() throws Exception {
        new TestRunSolution(Solution.class, TextTest.class, TestCollect.class);
    }
}
