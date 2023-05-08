package parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.JsonMapper;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Type;

public class CharArrayParser implements ParserInterface {

    ObjectMapper objectMapper = JsonMapper.mapper;

    @Override
    public Object parser(String str, Class<?> ruler, Type type) throws Exception {
        if (ruler.isAssignableFrom(char.class)) {
            if (str.isBlank()) {
                return ' ';
            }
            return str.charAt(0);
        }
        if (!str.startsWith("[") && ruler.isAssignableFrom(char[].class)) {
            return str.toCharArray();
        }

        if (ruler.isAssignableFrom(char[].class)) {
            String[] strings = objectMapper.readValue(str, String[].class);
            ArrayUtils.removeAllOccurences(strings, "");
            char[] list = new char[strings.length];
            for (int i = 0; i < list.length; i++) {
                list[i] = strings[i].charAt(0);
            }
            return list;
        }

        if (ruler.isAssignableFrom(char[][].class)) {
            String[][] strings = objectMapper.readValue(str, String[][].class);
            char[][] matrix = new char[strings.length][];
            for (int i = 0; i < strings.length; i++) {
                ArrayUtils.removeAllOccurences(strings[i], "");
                matrix[i] = new char[strings[i].length];
                for (int j = 0; j < strings[i].length; j++) {
                    matrix[i][j] = strings[i][j].charAt(0);
                }
            }
            return matrix;
        }
        return null;
    }
}
