package mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lib.javaCollections.ListNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListNodeDeserializer extends JsonDeserializer<ListNode> {
    @Override
    public ListNode deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ArrayList<?> arrayList = p.readValueAs(ArrayList.class);
        List<Integer> collect = arrayList.stream().map(x -> (Number) x).map(Number::intValue).collect(Collectors.toList());
        return ListNode.NodeListCreate(collect);
    }
}
