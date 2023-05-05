package lib.types;

import org.apache.commons.lang3.math.NumberUtils;

import java.lang.reflect.Type;

public class LongMapper implements MapperStrToObjInterface{

    final static String typeName = "long";

    @Override
    public boolean check(String str, Type type) {
        return NumberUtils.isCreatable(str);
    }

    @Override
    public Object getMessage(String str, Type type) {
        double d = Double.parseDouble(str);
        return (long) d;
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
}
