package lib.chain;

import lib.exception.AnnotationException;
import lib.interfaces.SourceParam;
import lib.interfaces.SourceParams;
import lombok.extern.slf4j.Slf4j;
import model.AnnotationTask;
import utils.MethodUtils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AnnotationDispatch {

    List<AnnotationTask> taskList = new ArrayList<>();

    public AnnotationDispatch(Class<?> c, Class<? extends SourceParam> startAnnotation,
                              Class <? extends SourceParams> collectAnnotation) throws IOException, AnnotationException {
        new AnnotationPreCheck(c, startAnnotation, collectAnnotation);
        getTask(c, startAnnotation, collectAnnotation);
    }

    void getTask(Class<?> c, Class<? extends SourceParam> startAnnotation,
                        Class <? extends SourceParams> collectAnnotation){
        Method[] methods = MethodUtils.getMethodWithInterface(c, startAnnotation, collectAnnotation);

        for (Method method : methods) {
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
            AnnotationTask annotationTask = new AnnotationTask(c, stringList.toArray(String[]::new), method);
            annotationTask.setTaskFrom("注解SourceFrom");
            taskList.add(annotationTask);
        }
        log.debug("通过注解成功采集到 {} 个任务", taskList.size());
    }

    public void startAllTask() throws Exception {
        for (AnnotationTask annotationTask : taskList) {
            new TaskStarter(annotationTask);
        }
    }
}
