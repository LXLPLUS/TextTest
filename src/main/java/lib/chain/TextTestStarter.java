package lib.chain;
/*
  @author 青碧凝霜
 * 2023 2023/5/8 3:01 created
 */

import lib.exception.AnnotationException;
import lib.interfaces.SourceParam;
import lib.interfaces.SourceParams;
import lib.interfaces.TestCollect;
import lib.interfaces.TextTest;
import lombok.extern.slf4j.Slf4j;
import model.AnnotationTask;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class TextTestStarter {

    TextDispatch testRunSolution = null;
    AnnotationDispatch annotationDispatch = null;
    List<AnnotationTask> taskList = new ArrayList<>();

    public TextTestStarter(Class<?> c) {
        if (TextPreCheck.checkTextAnnotation(c, TextTest.class, TestCollect.class)) {
            try {
                testRunSolution = new TextDispatch(c, TextTest.class, TestCollect.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (AnnotationPreCheck.checkAnnotation(c, SourceParam.class, SourceParams.class)) {
            try {
                annotationDispatch = new AnnotationDispatch(c, SourceParam.class, SourceParams.class);
            } catch (AnnotationException e) {
                e.printStackTrace();
            }
        }
    }

    public static void startAllTask(List<AnnotationTask> taskList) {
        for (AnnotationTask annotationTask : taskList) {
            try {
                new TaskStarter(annotationTask);
            } catch (Exception e) {
                log.warn("任务失败, 失败原因: {}", e.getMessage());
            }
        }
    }

    /**
     * 按照方法的字典序启动所有任务（ a b bb bbb c cc）
     */
    public void startAllTask() {
        if (testRunSolution != null) {
            taskList.addAll(testRunSolution.taskList);
        }
        if (annotationDispatch != null) {
            taskList.addAll(annotationDispatch.taskList);
        }
        if (taskList.isEmpty()) {
            log.warn("没有可执行的任务！请加入@TextTest或者@Solution注解");
        }
        taskList.sort(Comparator.comparing(o -> o.getMethod().getName()));
        startAllTask(taskList);
    }
}
