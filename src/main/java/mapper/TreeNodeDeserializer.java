package mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lib.javaCollections.TreeNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TreeNodeDeserializer extends JsonDeserializer<TreeNode> {
    @Override
    public TreeNode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ArrayList<?> arrayList = p.readValueAs(ArrayList.class);
        List<Integer> collect = arrayList.stream().map(x -> (Number) x).map(Number::intValue).collect(Collectors.toList());
        return TreeNode.TreeNodeCreate(collect);
    }
}
