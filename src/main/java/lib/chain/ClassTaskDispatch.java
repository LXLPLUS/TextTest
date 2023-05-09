package lib.chain;

import lib.annotation.TextTest;
import lib.exception.MethodNotExistException;
import lib.exception.ParserFailedException;
import lib.exception.UndefinedException;
import lombok.extern.slf4j.Slf4j;
import model.AnnotationTask;
import model.TextTextEnum;
import org.apache.commons.lang3.tuple.Pair;
import utils.FilesWalkUtils;
import utils.MethodUtils;
import utils.StringBuilderUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ClassTaskDispatch {

    public Map<Pair<String, Integer>, Method> methodMap = new HashMap<>();
    public List<AnnotationTask> taskList = new ArrayList<>();
    Class<?> c;

    ClassTaskDispatch(Class<?> c, Class<? extends TextTest> startAnnotation)
            throws MethodNotExistException, ParserFailedException, UndefinedException, IOException {

        this.c = c;

        new ClassTaskPreCheck(c, startAnnotation);

        getMethodList(c);

        String fileName = c.getAnnotation(startAnnotation).value();

        getTask(fileName);

    }

    void getMethodList(Class<?> c) {
        Method[] metHodNotInObject = MethodUtils.getMetHodNotInObject(c);
        for (Method method : metHodNotInObject) {
            methodMap.put(Pair.of(method.getName(), method.getParameterCount()), method);
        }
    }

    public Method getMethod(String name, int ParamsCount) throws ParserFailedException {
        name = name.strip();
        Method method = methodMap.getOrDefault(Pair.of(name, ParamsCount), null);
        if (method == null) {
            log.warn("带 {} 个参数的方法 {} 不存在", name, ParamsCount);
            throw new ParserFailedException("方法不存在");
        }
        return method;
    }


    void getTask(String fileName)
            throws IOException, ParserFailedException {

        Path path = openTestFile(fileName);
        List<String> stringList = FilesWalkUtils.getStringListWithOutBlackOrNote(path);
        List<List<String>> taskInfoList = new ArrayList<>();
        for (String row : stringList) {
            if (StringBuilderUtils.StartsWithIgnoreCase(row, TextTextEnum.METHOD_SYMBOL)) {
                String[] split = row.split("[:\\s]");
                taskInfoList.add(new ArrayList<>(List.of(split[split.length - 1])));
            }
            else {
                taskInfoList.get(taskInfoList.size() - 1).add(row);
            }
        }

        for (List<String> task : taskInfoList) {
            String[] split = task.get(0).split("[:\\s]");
            String methodName = split[split.length - 1];
            List<String> paramsList = task.subList(1, task.size());
            Method method = getMethod(methodName, paramsList.size());
            AnnotationTask annotationTask = new AnnotationTask(c, paramsList.toArray(String[]::new), method);
            annotationTask.setTaskFrom("类注解");
            taskList.add(annotationTask);
        }
    }

    private Path openTestFile(String fileName) throws IOException {
        if (!fileName.endsWith(TextTextEnum.FILE_SUFFIX)) {
            fileName += TextTextEnum.FILE_SUFFIX;
        }
        List<Path> fileList = FilesWalkUtils.getFileList(new File("").getAbsolutePath(),
                TextTextEnum.FILE_SUFFIX);
        for (Path path : fileList) {
            if (path.endsWith(Path.of(fileName))) {
                return path;
            }
        }
        throw new IOException("文件不存在！");
    }
}