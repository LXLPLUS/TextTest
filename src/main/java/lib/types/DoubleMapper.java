package lib.types;

import org.apache.commons.lang3.math.NumberUtils;

import java.lang.reflect.Type;

public class DoubleMapper implements MapperStrToObjInterface{

    final static String typeName = "double";

    @Override
    public boolean check(String str, Type type) {
        return NumberUtils.isCreatable(str);
    }

    @Override
    public Object getMessage(String str, Type type) {
        return Double.parseDouble(str);
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
}
