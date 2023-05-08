package lib.funcPerser;

import lib.exception.ParserFailedException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.Pair;
import utils.MethodUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class ParamMethod {

    Object o;
    static HashMap<Pair<String, Integer>, Method> map = new HashMap<>();

    public ParamMethod(Class<?> c)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Method[] methods = MethodUtils.getMetHodNotInObject(c);
        o = c.getDeclaredConstructor().newInstance();
        for (Method method : methods) {
            String methodName = method.getName();
            int parameterCount = method.getParameterCount();
            map.put(Pair.of(methodName.toLowerCase(), parameterCount), method);
        }
    }

    static Pair<String, Integer> getMethodPair(String str) {
        List<String> methodParams = new ArrayList<>(getMethodParams(str));
        methodParams.set(0, methodParams.get(0).toLowerCase());
        return Pair.of(methodParams.get(0), methodParams.size() - 1);
    }

    static public boolean checkMethodExist(String str) {
        Pair<String, Integer> pair = getMethodPair(str);
        return map.containsKey(pair);
    }
    /**
     * @param str 传入方法参数字符串，不支持,(), 参数只支持double和string格式
     * @return 方法的结果
     */
    public Object invoke(String str)
            throws ParserFailedException, InvocationTargetException, IllegalAccessException {
        if (!checkMethodExist(str)) {
            throw new ParserFailedException("改方法不存在！");
        }
        Pair<String, Integer> methodPair = getMethodPair(str);
        List<String> methodParams = getMethodParams(str);
        List<Object> objectParams = ParserParams(methodParams);
        Method method = map.get(methodPair);
        return MethodUtils.invoke(o, method, objectParams.toArray());
    }


    static List<String> getMethodParams(String methodString) {
        String[] split = methodString.split("[(),]");
        ArrayUtils.removeAllOccurences(split, "");
        split[0] = split[0].toLowerCase();
        return List.of(split);
    }

    static List<Object> ParserParams(List<String> methodParamsList) {
        List<Object> params = new ArrayList<>();
        for (int i = 1; i < methodParamsList.size(); i++) {
            if (NumberUtils.isCreatable(methodParamsList.get(i).strip())) {
                params.add(Double.parseDouble(methodParamsList.get(i).strip()));
            }
            else {
                params.add(methodParamsList.get(i));
            }
        }
        return params;
    }

}
