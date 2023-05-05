package lib.types;

import org.apache.commons.lang3.math.NumberUtils;

import java.lang.reflect.Type;

public class IntegerMapper implements MapperStrToObjInterface{

    final static String typeName = "java.util.List<java.lang.Double>";

    @Override
    public boolean check(String str, Type type) {
        return NumberUtils.isCreatable(str);
    }

    @Override
    public Object getMessage(String str, Type type) {
        return (int) Double.parseDouble(str);
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
}
