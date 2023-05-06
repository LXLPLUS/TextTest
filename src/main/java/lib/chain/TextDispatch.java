package lib.chain;

import lib.interfaces.TestCollect;
import lib.interfaces.TextTest;
import lombok.extern.slf4j.Slf4j;
import model.AnnotationTask;
import org.apache.commons.lang3.tuple.Pair;
import perser.WorkerParser;
import utils.FilesWalkUtils;
import utils.MethodUtils;
import utils.PrintAllType;
import utils.Timer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TextDispatch {

    List<AnnotationTask> taskList = new ArrayList<>();
    static WorkerParser workerParser = new WorkerParser();
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
        log.debug("采集完成！一共 {} 个任务", taskList.size());
    }

    public void startAllTask() throws Exception {
        for (AnnotationTask annotationTask : taskList) {
            try {
                startTask(annotationTask);
            } catch (Exception e) {
                log.error("任务失败, 失败信息 {} ", e.getMessage());
            }
        }
    }

    static public void startTask(AnnotationTask annotationTask) throws Exception {
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
        Object[] objectList = new Object[jsonList.length];

        for (int i = 0; i < jsonList.length; i++) {
            objectList[i] = workerParser.parser(jsonList[i], typeArray[i]);
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
