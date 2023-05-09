package lib.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(SourceParams.class)
public @interface SourceParam {
    String value() default "";
}
