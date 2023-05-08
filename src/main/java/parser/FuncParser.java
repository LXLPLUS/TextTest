package parser;

import lib.funcPerser.ParamMethod;
import lib.funcPerser.TextFuncUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public class FuncParser implements ParserInterface{

    static ParamMethod paramMethod;

    public FuncParser()
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
         paramMethod = new ParamMethod(TextFuncUtils.class);
    }

    public boolean checkMethodExist(String str) {
        return ParamMethod.checkMethodExist(str);
    }

    @Override
    public Object parser(String str, Class<?> ruler, Type type) throws Exception {
        return paramMethod.invoke(str);
    }
}
