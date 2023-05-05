package lib.types;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AllTypeMapper implements MapperStrToObjInterface{

    List<MapperStrToObjInterface> mapperList = new ArrayList<>();
    HashMap<String, MapperStrToObjInterface> mapperMap = new HashMap<>();
    final int FAIL_SYMBOL = -1;
    int successIndex = FAIL_SYMBOL;

    /**
     * 注册一下解析器
     */
    AllTypeMapper() {
        mapperList.add(new StringMapper());
        mapperList.add(new StringListMapper());
        mapperList.add(new StringArrayMapper());
        mapperList.add(new IntegerListMapper());
        mapperList.add(new LongListMapper());
        mapperList.add(new DoubleMapper());
        mapperList.add(new DoubleArrayMapper());
        mapperList.add(new DoubleListMapper());
        mapperList.add(new LongMapper());
        mapperList.add(new LongArrayMapper());
        mapperList.add(new IntegerMapper());
        mapperList.add(new IntegerArrayMapper());

        List<String> collectName = mapperList.stream().map(MapperStrToObjInterface::getTypeName).collect(Collectors.toList());

        for (int i = 0; i < collectName.size(); i++) {
            mapperMap.put(collectName.get(i), mapperList.get(i));
        }
    }


    @Override
    public boolean check(String str, Type type) {
        String typeName = type.getTypeName();
        if (mapperMap.containsKey(typeName)) {
            return mapperMap.get(typeName).check(str, type);
        }
        return false;
    }

    @Override
    public Object getMessage(String str, Type type) {
        if (mapperMap.containsKey(type.getTypeName())) {
            return mapperMap.get(type.getTypeName()).getMessage(str, type);
        }
        return null;
    }

    @Override
    public String getTypeName() {
        return null;
    }
}
