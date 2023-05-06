package examples;

import lib.chain.TestRunSolution;
import lib.chain.TextPreCheck;
import lib.chain.TextTestStarter;
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
    public void run() throws Exception {
        new TextTestStarter(Solution.class);
    }
}
