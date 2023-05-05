import Solution.Solution;
import org.junit.jupiter.api.Test;
import utils.MethodUtils;
import utils.PrintAllType;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.Arrays;


public class startText {

    @Test
    public void run() {
        Method[] metHodNotInObject = MethodUtils.getMetHodNotInObject(Solution.class);
        for (Method method : metHodNotInObject) {
            AnnotatedType annotatedReturnType = method.getAnnotatedReturnType();
            PrintAllType.print(annotatedReturnType.getType());
        }
        System.out.println(Arrays.toString(metHodNotInObject));
    }
}
