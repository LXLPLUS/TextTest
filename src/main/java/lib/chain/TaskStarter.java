package lib.chain;

import lombok.extern.slf4j.Slf4j;
import mapper.JsonMapperUtils;
import model.AnnotationTask;
import model.Timer;
import parser.WorkerParser;
import utils.MethodUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

// 每个任务的启动器
// 带一个简单的计时器，计算代码的运行时间
// 日志结果打印
// 公用一个解析器
@Slf4j
public class TaskStarter {

    static WorkerParser workerParser;

    static {
        try {
            workerParser = new WorkerParser();
        } catch (Exception e) {
            log.warn("定义函数工具异常，函数解析失败！");
            System.exit(-1);
        }
    }

    Timer timer = new Timer();

    public TaskStarter(AnnotationTask annotationTask) throws Exception {
        Method method = annotationTask.getMethod();
        log.info("------------------------------------------------------------------------------");
        log.info("  任务来源: {}   任务: {}          方法:  {}",
                annotationTask.getTaskFrom(),
                annotationTask.getC().getName(),
                method.getName());
        log.info("------------------------------------------------------------------------------");

        String[] jsonList = annotationTask.getJsonList();
        Class<?>[] typeArray = annotationTask.getTypeArray();
        Type[] realTypes = annotationTask.getRealTypes();
        Object[] objectList = new Object[jsonList.length];

        for (int i = 0; i < jsonList.length; i++) {
            objectList[i] = workerParser.parser(jsonList[i], typeArray[i], realTypes[i]);
            log.info("对第 {} 个参数注入结束, 注入类型为 {} ,值为 {}", i + 1,
                    realTypes[i].getTypeName(),
                    JsonMapperUtils.mapper.writeValueAsString(objectList[i]));
        }
        timer.flushStartTime();
        Object invoke = MethodUtils.invoke(annotationTask.getObject(), method, objectList);
        timer.flushFinishTIme();
        if (invoke == null) {
            log.info("获取结果，返回数据为null");
        }
        else {
            log.info("获取结果, 类型为 {} ,值为 {}",
                    invoke.getClass().getTypeName(),
                    JsonMapperUtils.mapper.writeValueAsString(invoke));
        }

        long during = timer.spendMillis();
        if (during == 0) {
            log.info("运算时间小于1毫秒");
        }
        else {
            log.info("该方法运行了 {} 毫秒", during);
        }
    }
}
