package lib.chain;

import lombok.extern.slf4j.Slf4j;
import model.AnnotationTask;
import perser.WorkerParser;
import utils.MethodUtils;
import utils.PrintAllType;
import utils.Timer;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

// 每个任务的启动器
// 带一个简单的计时器，计算代码的运行时间
// 日志结果打印
// 公用一个解析器
@Slf4j
public class TaskStarter {
    static WorkerParser workerParser = new WorkerParser();
    Timer timer = new Timer();
    public TaskStarter(AnnotationTask annotationTask) throws Exception {
        Method method = annotationTask.getMethod();
        log.info("---------------------------===============------------------------------------");
        log.info("---------------------------||task_start ||------------------------------------");
        log.info("---------------------------===============------------------------------------");
        log.info("开始任务, 任务来源 {}, 方法为 {} 的 {}",
                annotationTask.getTaskFrom(),
                annotationTask.getObject(),
                method.getName());

        String[] jsonList = annotationTask.getJsonList();
        Class<?>[] typeArray = annotationTask.getTypeArray();
        Type[] realTypes = annotationTask.getRealTypes();
        Object[] objectList = new Object[jsonList.length];

        for (int i = 0; i < jsonList.length; i++) {
            objectList[i] = workerParser.parser(jsonList[i], typeArray[i], realTypes[i]);
            List<String> dataAndType = PrintAllType.getString(objectList[i]);
            log.info("成功注入, 类型为 {} ,值为 {}", dataAndType.get(1), dataAndType.get(0));
        }
        timer.time();
        Object invoke = MethodUtils.invoke(annotationTask.getObject(), method, objectList);
        timer.time();
        List<String> dataAndType = PrintAllType.getString(invoke);
        log.info("获取结果, 类型为 {} ,值为 {}", dataAndType.get(1), dataAndType.get(0));
        long timer = TextDispatch.timer.getTimer();
        if (timer == 0) {
            log.info("运算时间小于1毫秒");
        }
        else {
            log.info("该方法运行了 {} 毫秒", timer);
        }
    }
}