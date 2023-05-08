package lib.chain;

import lib.exception.AnnotationException;
import lib.exception.ParserFailedException;
import lib.interfaces.TestCollect;
import lib.interfaces.TextTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.commons.lang3.tuple.Pair;
import utils.FilesWalkUtils;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TextPreCheck {

    List<Path> fileList;
    List<Pair<Method, TextTest>> methodList = new ArrayList<>();

    public TextPreCheck(Class<?> c,
                        Class<? extends Annotation> singleAnnotation,
                        Class <? extends TestCollect> collectAnnotation)
            throws AnnotationException, IOException, ParserFailedException {
        getPathList();
        checkMainAnnotation(c, singleAnnotation, collectAnnotation);
        getPathFromAnnotation();
    }

    // 检查是否存在可进入的注解对
    // 不需要实例化这个class就可以使用的方法
    public static boolean checkTextAnnotation(Class<?> c,
                                              Class<? extends Annotation> startAnnotation,
                                              Class <? extends TestCollect> collectAnnotation) {
        return utils.MethodUtils.getMethodWithInterface(c, startAnnotation, collectAnnotation).length > 0;
    }

    // 从注解中获取TestText注解
    // 重复注解会被转行为TestCollect,需要进行解包
    void checkMainAnnotation(Class<?> c,
                             Class<? extends Annotation> startAnnotation,
                             Class <? extends TestCollect> collectAnnotation) throws AnnotationException {
        Method[] methodsWithAnnotation = MethodUtils.getMethodsWithAnnotation(c, startAnnotation);
        for (Method method : methodsWithAnnotation) {
            methodList.add(Pair.of(method, (TextTest) method.getAnnotation(startAnnotation)));
        }
        for (Method method : MethodUtils.getMethodsWithAnnotation(c, collectAnnotation)) {
            for (TextTest textTest : method.getAnnotation(collectAnnotation).value()) {
                methodList.add(Pair.of(method, textTest));
            }
        }
        if (methodList.isEmpty()) {
            log.warn("类 {} 没有获取标准的@TextTest注解 或者方法并不是public", c.getName());
            throw new AnnotationException("没有获取标准的@TextTest注解 或者方法并不是public");
        }
    }

    void getPathList() throws IOException {
        File f = new File("");
        fileList = FilesWalkUtils.getFileList(f.getAbsolutePath(), ".ttest");
    }


    void getPathFromAnnotation() throws IOException, ParserFailedException {
        for (Pair<Method, TextTest> methodTextTestPair : methodList) {
            Method method = methodTextTestPair.getLeft();
            String fileName = methodTextTestPair.getRight().value();

            Class<?>[] parameterTypes = method.getParameterTypes();
            if (!fileName.endsWith(".ttest")) {
                fileName += ".ttest";
            }
            int count = 0;
            for (Path path : fileList) {
                if (path.endsWith(Path.of(fileName))) {
                    count++;
                    if (FilesWalkUtils.getRowCountWithoutBlack(path) != parameterTypes.length) {
                        log.warn("发现参数和配置文件指定的路径行数不符！ 方法为{}, 可用的参数为 {}, 实际提供的行数:{}",
                                method.getName(), parameterTypes.length,
                                FilesWalkUtils.getRowCountWithoutBlack(path));
                        throw new ParserFailedException("参数行数不符！");
                    }
                }
            }
            if (count == 0) {
                log.warn("注解表示 {} 方法在有一个ttest文件, 文件名为 {}, 但实际上并没有发现",
                        method.getName(), fileName);
                throw new ParserFailedException("没有可解析的文件！");
            }
            log.debug("方法{} 的{} 配置文件已经找到", method.getName(), fileName);
        }
    }
}

