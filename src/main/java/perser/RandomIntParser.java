package perser;

import lombok.extern.slf4j.Slf4j;
import utils.StringBuilderUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class RandomIntParser implements parserInterface{

    Random r = new Random();
    @Override
    public Object parser(String str, Class<?> ruler) throws Exception {
        long[] allInteger = StringBuilderUtils.getAllInteger(str);
        long nums = allInteger[0];
        long left = Integer.MIN_VALUE;
        long right = Integer.MAX_VALUE;
        if (allInteger.length >= 3) {
            left = allInteger[1];
            right = allInteger[2];
        }
        List<Integer> list = new ArrayList<>();
        if (nums > 10000) {
            log.debug("青碧凝霜提示：数据量不应过大，刷题的话5e5就很大了");
        }
        for (int i = 0; i < Math.min(5e5, nums); i++) {
            list.add(r.nextInt((int)(right - left)) + (int) left);
        }
        if (ruler.isAssignableFrom(int[].class)) {
            return list.stream().mapToInt(x -> x).toArray();
        }
        if (ruler.isAssignableFrom(ArrayList.class)) {
            return list;
        }
        return null;
    }
}
