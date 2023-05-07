package perser;

import com.fasterxml.jackson.databind.ObjectMapper;
import lib.javaCollections.ListNode;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ListNodeParser implements ParserInterface {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object parser(String str, Class<?> ruler, Type type) throws Exception {
        ArrayList<?> arrayList = objectMapper.readValue(str, ArrayList.class);
        return ListNode.NodeListCreate(arrayList.stream().map(x -> (Integer)x).collect(Collectors.toList()));
    }
}
