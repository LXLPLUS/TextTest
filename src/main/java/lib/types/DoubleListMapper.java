package lib.types;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import utils.StringBuilderUtils;

import java.lang.reflect.Type;

public class DoubleListMapper implements MapperStrToObjInterface{
    @Override
    public boolean check(String str, Type type) {
        str = StringBuilderUtils.toHalfCharacter(str);
        str = StringUtils.strip("()[]{} \n\t");
        String[] split = str.split("[^\\d]+");
        for (String numCheck : split) {
            if (!NumberUtils.isCreatable(numCheck)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Object getMessage(String str, Type type) {
        return null;
    }
}
