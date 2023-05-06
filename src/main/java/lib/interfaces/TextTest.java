package lib.interfaces;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TestCollect.class)
public @interface TextTest {
    String value();
}
