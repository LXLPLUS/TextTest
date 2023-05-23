package mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lib.javaCollections.TreeNode;

import java.io.IOException;
import java.util.List;

public class TreeNodeSerializer extends JsonSerializer<TreeNode> {

    @Override
    public void serialize(TreeNode treeNode, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        List<Integer> integerList = TreeNode.treeNodeToList(treeNode);
        gen.writeString(JsonMapperUtils.mapper.writeValueAsString(integerList));
    }
}