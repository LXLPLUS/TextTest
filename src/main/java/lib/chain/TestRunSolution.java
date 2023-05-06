package lib.chain;

import lib.exception.AnnotationException;
import lib.exception.ParserFailedException;
import lib.interfaces.TestCollect;
import lib.interfaces.TextTest;
import lombok.extern.slf4j.Slf4j;
import model.AnnotationTask;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import perser.WorkerParser;
import utils.FilesWalkUtils;
import utils.MethodUtils;
import utils.PrintAllType;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class TestRunSolution {

    List<AnnotationTask> taskList = new ArrayList<>();

    public TestRunSolution(Class<?> c, Class<? extends TextTest> textTestAnnotation,
                           Class<? extends TestCollect> testCollectAnnotation)
            throws Exception {

        // 进行安全检查，发现是否有不符合条件的数据，如果有的话就直接异常
        // 不进行任何业务，只检查
        new TextPreCheck(c, textTestAnnotation, testCollectAnnotation);

        // 获取所有任务
        getAllTask(c, textTestAnnotation, testCollectAnnotation);

        // 启动所有任务
        startAllTask();
    }

    void getAllTask(Class<?> c, Class<? extends TextTest> textTestAnnotation,
                    Class<? extends TestCollect> testCollectAnnotation)
            throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

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
                    taskList.add(new AnnotationTask(c, path, method));
                }
            }
        }
        log.debug("采集完成！一共 {} 个任务", taskList.size());
    }

    void startAllTask() throws Exception {
        for (AnnotationTask annotationTask : taskList) {
            startTask(annotationTask);
        }
    }

    void startTask(AnnotationTask annotationTask) throws Exception {

        WorkerParser workerParser = new WorkerParser();
        Method method = annotationTask.getMethod();
        log.info("开始任务, 配置文件{}, 方法为{} 的 {}",
                annotationTask.getFilePath().getFileName().toString(),
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

        Object invoke = MethodUtils.invoke(annotationTask.getObject(), method, objectList);
        List<String> dataAndType = PrintAllType.getString(invoke);
        log.info("获取结果, 类型为 {} ,值为 {}", dataAndType.get(1), dataAndType.get(0));


    }
}
