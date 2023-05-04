import demo.Test01;
import org.junit.jupiter.api.Test;
import util.MethodUtils;
import util.PrintAllType;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.Arrays;


public class startText {

    @Test
    public void run() {
        Method[] metHodNotInObject = MethodUtils.getMetHodNotInObject(Test01.class);
        for (Method method : metHodNotInObject) {
            AnnotatedType annotatedReturnType = method.getAnnotatedReturnType();
            PrintAllType.print(annotatedReturnType.getType());
        }
        System.out.println(Arrays.toString(metHodNotInObject));
    }
}
