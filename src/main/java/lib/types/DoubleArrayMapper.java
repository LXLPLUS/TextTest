package lib.types;

import java.lang.reflect.Type;
import java.util.List;

/**
 * steam是真的很繁琐。代码没啥说的
 */
public class DoubleArrayMapper implements MapperStrToObjInterface{

    /**
     * 反射可以获取的实际类名
     */
    final static String typeName = "class [D";

    @Override
    public boolean check(String str, Type type) {
        return new DoubleListMapper().check(str, type);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object getMessage(String str, Type type) {
        List<Double> message = (List<Double>) new DoubleListMapper().getMessage(str, type);
        return message.stream().mapToDouble(Double::doubleValue).toArray();
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
}
