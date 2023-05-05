package lib.types;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IntegerListMapper implements MapperStrToObjInterface{

    final static String typeName = "java.util.List<java.lang.Integer>";

    @Override
    public boolean check(String str, Type type) {
        return new DoubleListMapper().check(str, type);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object getMessage(String str, Type type) {
        List<Double> doubleList = (List<Double>) new DoubleListMapper().getMessage(str, type);
        return doubleList.stream().mapToInt(Double::intValue).boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
}
