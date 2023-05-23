package mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lib.javaCollections.ListNode;

import java.io.IOException;
import java.util.List;

public class ListNodeSerializer extends JsonSerializer<ListNode> {
    @Override
    public void serialize(ListNode value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        List<Integer> integerList = ListNode.NodeListRead(value);
        String str = JsonMapperUtils.mapper.writeValueAsString(integerList);
        gen.writeString(str);
    }
}
