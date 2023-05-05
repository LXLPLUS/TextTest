package lib.interfaces;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(SourceParams.class)
public @interface SourceParam {
    String value() default "";
}
