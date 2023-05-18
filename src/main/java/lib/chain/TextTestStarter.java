package lib.chain;
/*
  @author 青碧凝霜
 * 2023 2023/5/8 3:01 created
 */

import lib.annotation.SourceParam;
import lib.annotation.SourceParams;
import lib.annotation.TestCollect;
import lib.annotation.TextTest;
import lib.exception.AnnotationException;
import lombok.extern.slf4j.Slf4j;
import model.AnnotationTask;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class TextTestStarter {

    TextDispatch testRunSolution = null;
    AnnotationDispatch annotationDispatch = null;
    ClassTaskDispatch classTaskDispatch = null;
    List<AnnotationTask> taskList = new ArrayList<>();

    public TextTestStarter(Class<?> c) {
        if (TextPreCheck.checkTextAnnotation(c, TextTest.class, TestCollect.class)) {
            try {
                testRunSolution = new TextDispatch(c, TextTest.class, TestCollect.class);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (AnnotationPreCheck.checkAnnotation(c, SourceParam.class, SourceParams.class)) {
            try {
                annotationDispatch = new AnnotationDispatch(c, SourceParam.class, SourceParams.class);
            }
            catch (AnnotationException e) {
                e.printStackTrace();
            }
        }
        if (ClassTaskPreCheck.checkAnnotationExist(c, TextTest.class)) {
            try {
                classTaskDispatch = new ClassTaskDispatch(c, TextTest.class);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void startAllTask(List<AnnotationTask> taskList) {
        for (AnnotationTask annotationTask : taskList) {
            try {
                new TaskStarter(annotationTask);
            } catch (Exception e) {
                log.warn("任务失败!");
                e.printStackTrace();
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
        taskList.sort(Comparator.comparing(o -> o.getMethod().getName()));
        startAllTask(taskList);

        // 为什么这么做呢，是因为之前的任务需要按照字典序混合，而这个方法他有序！！
        // 没辙，只能对前两个进行排序，然后第三个按照定义的顺序进行执行
        if (classTaskDispatch != null) {
            taskList.addAll(classTaskDispatch.taskList);
            startAllTask(classTaskDispatch.taskList);
        }
        if (taskList.isEmpty()) {
            log.warn("没有可执行的任务！请加入@TextTest或者@Solution注解");
        }
    }
}
