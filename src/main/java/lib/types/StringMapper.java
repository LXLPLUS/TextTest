package lib.types;

import java.lang.reflect.Type;

public class StringMapper implements MapperStrToObjInterface{

    final static String typeName = "class java.lang.String";

    @Override
    public boolean check(String str, Type type) {
        return true;
    }

    @Override
    public Object getMessage(String str, Type type) {
        if (str.equalsIgnoreCase("NULL") || str.equalsIgnoreCase("NONE")) {
            return null;
        }
        if (str.startsWith("\"") && str.endsWith("\"")) {
            return str.substring(1, str.length() - 1);
        }
        return str;
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
}
