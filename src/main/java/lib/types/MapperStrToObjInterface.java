package lib.types;

import java.lang.reflect.Type;

/**
 * 接口类
 */
public interface MapperStrToObjInterface {


    boolean check(String str, Type type);

    Object getMessage(String str, Type type);

    String getTypeName();
}
