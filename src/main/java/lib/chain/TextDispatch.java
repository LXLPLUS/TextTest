package lib.chain;

import lib.interfaces.TestCollect;
import lib.interfaces.TextTest;
import lombok.extern.slf4j.Slf4j;
import model.AnnotationTask;
import org.apache.commons.lang3.tuple.Pair;
import utils.FilesWalkUtils;
import utils.Timer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class TextDispatch {

    List<AnnotationTask> taskList = new ArrayList<>();
    static Timer timer = new Timer();

    public TextDispatch(Class<?> c, Class<? extends TextTest> textTestAnnotation,
                        Class<? extends TestCollect> testCollectAnnotation)
            throws Exception {

        // 进行安全检查，发现是否有不符合条件的数据，如果有的话就直接异常
        // 不进行任何业务，只检查
        new TextPreCheck(c, textTestAnnotation, testCollectAnnotation);

        // 获取所有任务
        getAllTask(c, textTestAnnotation, testCollectAnnotation);
    }

    void getAllTask(Class<?> c, Class<? extends TextTest> textTestAnnotation,
                    Class<? extends TestCollect> testCollectAnnotation) throws IOException {

        List<Pair<Method, TextTest>> methodAnnotationPairList = new ArrayList<>();

        File f = new File("");
        List<Path> fileList = FilesWalkUtils.getFileList(f.getAbsolutePath(), ".ttest");

        // 遍历方法获取所有注解
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            TextTest textTest = method.getAnnotation(textTestAnnotation);
            if (textTest != null) {
                methodAnnotationPairList.add(Pair.of(method, textTest));
            }
            TestCollect testCollect = method.getAnnotation(testCollectAnnotation);
            if (testCollect != null) {
                for (TextTest textTestInCollect : testCollect.value()) {
                    methodAnnotationPairList.add(Pair.of(method, textTestInCollect));
                }
            }
        }

        for (Pair<Method, TextTest> methodAnnotationPair : methodAnnotationPairList) {
            Method method = methodAnnotationPair.getLeft();
            TextTest annotation = methodAnnotationPair.getRight();

            String fileName = annotation.value();
            if (!fileName.endsWith(".ttest")) {
                fileName += ".ttest";
            }

            for (Path path : fileList) {
                if (path.endsWith(Path.of(fileName))) {
                    AnnotationTask annotationTask = new AnnotationTask(c, path, method);
                    annotationTask.setTaskFrom(annotationTask.getFilePath().getFileName().toString());
                    taskList.add(annotationTask);
                }
            }
        }
        // 可达性日志，代表程序成功运行到这个阶段没有报错
        // 所以发现日志不报错是很正常的,但内容是重复的
        log.debug("通过文件采集完成！一共 {} 个任务", taskList.size());
    }

    public void startAllTask() throws Exception {
        for (AnnotationTask annotationTask : taskList) {
            taskList.sort(Comparator.comparing(o -> o.getMethod().getName()));
            try {
                new TaskStarter(annotationTask);
            } catch (Exception e) {
                log.error("任务失败, 失败信息 {} ", e.getMessage());
                throw e;
            }
        }
    }
}
