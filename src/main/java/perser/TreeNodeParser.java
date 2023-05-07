package perser;

import com.fasterxml.jackson.databind.ObjectMapper;
import lib.javaCollections.TreeNode;
import model.JsonMapper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TreeNodeParser implements ParserInterface {

    ObjectMapper objectMapper = JsonMapper.mapper;

    @Override
    public Object parser(String str, Class<?> ruler, Type type) throws Exception {
        ArrayList<?> o = objectMapper.readValue(str, ArrayList.class);
        return TreeNode.TreeNodeCreate(o.stream().map(x -> (Integer)x).collect(Collectors.toList()));
    }
}
