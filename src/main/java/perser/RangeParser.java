package perser;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import model.JsonMapper;
import utils.StringBuilderUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class RangeParser implements ParserInterface {

    ObjectMapper objectMapper = JsonMapper.mapper;

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
        return objectMapper.writeValueAsString(ans);
    }
}
