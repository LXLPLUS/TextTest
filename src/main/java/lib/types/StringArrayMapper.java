package lib.types;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

public class StringArrayMapper implements MapperStrToObjInterface{

    final static String typeName = "[java.util.List<java.lang.String>";

    @Override
    public boolean check(String str, Type type) {
        return true;
    }

    @Override
    public Object getMessage(String str, Type type) {
        String[] messageBySplit = getMessageBySplit(str);
        String[] messageByEmpty = getMessageByEmpty(str);
        if (messageBySplit == null) {
            return messageByEmpty;
        }
        return messageBySplit;
    }

    String[] getMessageBySplit(String str) {
        String strip = StringUtils.strip(str, "()[]{} \n\t");
        if (str.startsWith("\"") && str.endsWith("\"")) {
            str = str.replaceAll("[\",]", " ");
            str = str.replaceAll("\\s+", " ");
            return str.split(" ");
        }
        return null;
    }
    String[] getMessageByEmpty(String str) {
        if (StringUtils.strip(str, "()[]{} \n\t").isBlank()) {
            return null;
        }
        str = str.replaceAll("\\s+", " ");
        return str.split(" ");
    }

    @Override
    public String getTypeName() {
        return typeName;
    }
}
