package lib.types;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StringListMapper implements MapperStrToObjInterface{
    @Override
    public boolean check(String str, Type type) {
        return true;
    }

    @Override
    public Object getMessage(String str, Type type) {
        String[] stringArray = (String[]) new StringArrayMapper().getMessage(str, type);
        return new ArrayList<>(List.of(stringArray));
    }
}