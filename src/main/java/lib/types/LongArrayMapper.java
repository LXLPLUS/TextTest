package lib.types;

import java.lang.reflect.Type;
import java.util.List;

public class LongArrayMapper implements MapperStrToObjInterface{

    final static String typeName = "java.util.List<java.lang.Double>";

    @Override
    public boolean check(String str, Type type) {
        return new DoubleListMapper().check(str, type);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object getMessage(String str, Type type) {
        List<Long> message = (List<Long>) new LongListMapper().getMessage(str, type);
        return message.toArray();

    }

    @Override
    public String getTypeName() {
        return typeName;
    }
}
