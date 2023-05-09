package lib.chain;

import lib.annotation.TextTest;
import lib.exception.MethodNotExistException;
import lib.exception.ParserFailedException;
import lib.exception.UndefinedException;
import lombok.extern.slf4j.Slf4j;
import model.TextTextEnum;
import org.apache.commons.lang3.tuple.Pair;
import utils.FilesWalkUtils;
import utils.MethodUtils;
import utils.StringBuilderUtils;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.*;

@Slf4j
public class ClassTaskPreCheck {

    public Map<Pair<String, Integer>, Method> methodMap = new HashMap<>();

    public ClassTaskPreCheck(Class<?> c, Class<? extends TextTest> startAnnotation)
            throws UndefinedException, IOException, MethodNotExistException, ParserFailedException {
        if (!checkAnnotationExist(c, startAnnotation)) {
            log.warn("类 {} 不存在注解 {}", c.getName(), startAnnotation.getName());
            throw new UndefinedException("方法不存在");
        }

        getMethodList(c);
        checkMethodAndParamsCount(c.getAnnotation(startAnnotation).value());
    }

    static public boolean checkAnnotationExist(Class<?> c, Class<? extends Annotation> startAnnotation) {
        return c.getDeclaredAnnotationsByType(startAnnotation).length > 0;
    }

    void getMethodList(Class<?> c) {
        Method[] metHodNotInObject = MethodUtils.getMetHodNotInObject(c);
        for (Method method : metHodNotInObject) {
            methodMap.put(Pair.of(method.getName(), method.getParameterCount()), method);
        }
    }

    /**
     * 检查方法的时候忽略大小写
     * @param name 方法名
     * @param ParamsCount  参数表
     * @return 方法数量
     */
    public boolean checkMethodExit(String name, int ParamsCount) {
        return methodMap.containsKey(Pair.of(name.toLowerCase(), ParamsCount));
    }


    Path openTestFile(String fileName) throws IOException {
        if (!fileName.endsWith(TextTextEnum.FILE_SUFFIX)) {
            fileName += TextTextEnum.FILE_SUFFIX;
        }
        String absolutePath = new File("").getAbsolutePath();
        List<Path> fileList = FilesWalkUtils.getFileList(absolutePath, TextTextEnum.FILE_SUFFIX);
        for (Path path : fileList) {
            if (path.endsWith(Path.of(fileName))) {
                return path;
            }
        }
        log.warn("文件名为 {} 的文件不存在", fileName);
        throw new IOException();
    }

    void checkMethodAndParamsCount(String fileName)
            throws IOException, ParserFailedException, MethodNotExistException {
        Path path = openTestFile(fileName);
        List<String> stringList = FilesWalkUtils.getStringListWithOutBlackOrNote(path);
        List<List<String>> taskInfoList = new ArrayList<>();
        for (String row : stringList) {
            if (StringBuilderUtils.StartsWithIgnoreCase(row, TextTextEnum.METHOD_SYMBOL)) {
                String[] split = row.split("[:\\s]");
                taskInfoList.add(new ArrayList<>(List.of(split[split.length - 1])));
            }
            else if (taskInfoList.isEmpty()) {
                log.warn("解析任务的时候没有获取方法，或者方法前有异常信息！");
                throw new ParserFailedException("解析失败");
            }
            else {
                taskInfoList.get(taskInfoList.size() - 1).add(row);
            }
        }

        for (List<String> task : taskInfoList) {
            String[] split = task.get(0).split("[:\\s]");
            String methodName = split[split.length - 1];
            int paramsCount = task.size() - 1;
            if (!checkMethodExit(methodName, paramsCount)) {
                log.warn("{} 个参数的方法 {}不存在！", methodName, paramsCount);
                log.info("可用的public方法为 {}" , methodMap.keySet());
                throw new MethodNotExistException();
            }
        }
    }
}
