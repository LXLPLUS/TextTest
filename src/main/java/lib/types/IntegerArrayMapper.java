package lib.types;

import java.lang.reflect.Type;
import java.util.List;

public class IntegerArrayMapper implements MapperStrToObjInterface{
    @Override
    public boolean check(String str, Type type) {
        return new DoubleListMapper().check(str, type);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object getMessage(String str, Type type) {
        List<Double> doubleList = (List<Double>) new DoubleMapper().getMessage(str, type);
        return doubleList.stream().mapToInt(Double::intValue).toArray();
    }
}
