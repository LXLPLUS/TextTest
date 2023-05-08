package lib.chain;

import lib.exception.AnnotationException;
import lib.interfaces.SourceParam;
import lib.interfaces.SourceParams;
import lombok.extern.slf4j.Slf4j;
import model.AnnotationTask;
import utils.MethodUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class AnnotationDispatch {

    public List<AnnotationTask> taskList = new ArrayList<>();

    public AnnotationDispatch(Class<?> c, Class<? extends SourceParam> singleAnnotation,
                              Class <? extends SourceParams> collectAnnotation) throws AnnotationException {
        getTask(c, singleAnnotation, collectAnnotation);
    }

    void getTask(Class<?> c, Class<? extends SourceParam> startAnnotation,
                        Class <? extends SourceParams> collectAnnotation){
        Method[] methods = MethodUtils.getMethodWithInterface(c, startAnnotation, collectAnnotation);

        for (Method method : methods) {
            method.setAccessible(true);
            List<String> stringList = new ArrayList<>();
            if (method.getAnnotation(startAnnotation) != null) {
                stringList.add(method.getAnnotation(startAnnotation).value());
            }
            if (method.getAnnotation(collectAnnotation) != null) {
                for (SourceParam sourceParam : method.getAnnotation(collectAnnotation).value()) {
                    stringList.add(sourceParam.value());
                }
            }
            if (stringList.isEmpty()) {
                continue;
            }

            // 当启动无参方式的时候不需要注解信息，只是当成启动工具
            if (method.getParameterCount() == 0) {
                stringList.clear();
            }
            AnnotationTask annotationTask = new AnnotationTask(c, stringList.toArray(String[]::new), method);
            annotationTask.setTaskFrom("注解SourceFrom");
            taskList.add(annotationTask);
        }
        log.debug("通过注解成功采集到 {} 个任务", taskList.size());
    }

    public void startAllTask() {
        taskList.sort(Comparator.comparing(o -> o.getMethod().getName()));
        for (AnnotationTask annotationTask : taskList) {
            try {
                new TaskStarter(annotationTask);
            } catch (Exception e) {
                log.warn("任务失败, 失败信息 {}", e.getMessage());
            }
        }
    }
}
