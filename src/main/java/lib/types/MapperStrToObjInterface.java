package lib.types;

import java.lang.reflect.Type;

/**
 * 实习
 */
public interface MapperStrToObjInterface {
    public boolean check(String str, Type type);
    
    public Object getMessage(String str, Type type);
}
