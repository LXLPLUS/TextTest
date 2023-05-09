package lib.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TestCollect.class)
public @interface TextTest {
    String value();
}
