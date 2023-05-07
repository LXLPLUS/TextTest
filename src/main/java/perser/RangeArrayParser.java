package perser;

import lombok.extern.slf4j.Slf4j;
import utils.StringBuilderUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class RangeArrayParser implements ParserInterface {

    @Override
    public Object parser(String str, Class<?> ruler, Type type) throws Exception {
        long[] allInteger = StringBuilderUtils.getAllInteger(str);
        long step = 1;
        long start = allInteger[0];
        long end = allInteger[1];
        if (allInteger.length == 3) {
            step = allInteger[2];
        }

        List<Long> ans = new ArrayList<>();
        if (Math.abs(start - end) > 100000) {
            log.debug("青碧凝霜提示：数据量太大会导致OOM，所以暂时定的是1E5的数量级，刷题可以保证可用，祝好运！");
        }
        for (int i = 0; i < 500000; i++) {
            ans.add(start);
            start += step;
            if (step >= 0 && start >= end) {
                break;
            }
            if (step <= 0 && start <= end) {
                break;
            }
        }

        if (ruler.isAssignableFrom(long[].class)) {
            return ans.stream().mapToLong(x -> x).toArray();
        }
        if (ruler.isAssignableFrom(int[].class)) {
            return ans.stream().mapToInt(Long::intValue).toArray();
        }
        if (ruler.isAssignableFrom(double[].class)) {
            return ans.stream().mapToDouble(Long::doubleValue).toArray();
        }
        if (ruler.isAssignableFrom(ArrayList.class) && type.getTypeName().contains(Integer.class.getTypeName())) {
            return ans.stream().mapToInt(Long::intValue).boxed().collect(Collectors.toCollection(ArrayList::new));
        }
        if (ruler.isAssignableFrom(ArrayList.class) && type.getTypeName().contains(Long.class.getTypeName())) {
            return ans;
        }
        if (ruler.isAssignableFrom(ArrayList.class) && type.getTypeName().contains(Double.class.getTypeName())) {
            return ans.stream().mapToDouble(Double::valueOf)
                    .boxed().collect(Collectors.toCollection(ArrayList::new));
        }

        if (ruler.isAssignableFrom(LinkedList.class) && type.getTypeName().contains(Integer.class.getTypeName())) {
            return ans.stream().mapToInt(Long::intValue).boxed().collect(Collectors.toCollection(LinkedList::new));
        }
        if (ruler.isAssignableFrom(LinkedList.class) && type.getTypeName().contains(Long.class.getTypeName())) {
            return new LinkedList<>(ans);
        }
        if (ruler.isAssignableFrom(LinkedList.class) && type.getTypeName().contains(Double.class.getTypeName())) {
            return ans.stream().mapToDouble(Double::valueOf)
                    .boxed().collect(Collectors.toCollection(LinkedList::new));
        }
        return null;
    }
}
