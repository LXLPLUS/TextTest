package model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import utils.FilesWalkUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.file.Path;

@Getter
@Setter
@Slf4j
public class AnnotationTask {
    Object object;
    Class<?> c;
    Path filePath = null;
    Method method;
    Class<?>[] TypeArray;
    String[] jsonList;
    String taskFrom = "undefined";
    Type[] realTypes;


    public AnnotationTask(Class<?> c, Path filePath, Method method) throws IOException {
        this.c = c;
        this.filePath = filePath;
        this.method = method;
        TypeArray = method.getParameterTypes();
        realTypes = method.getGenericParameterTypes();
        jsonList = FilesWalkUtils.getStringListWithOutBlackOrNote(filePath).toArray(String[]::new);
    }

    public AnnotationTask(Class<?> c, String[] jsonList, Method method){
        this.c = c;
        this.method = method;
        realTypes = method.getGenericParameterTypes();
        TypeArray = method.getParameterTypes();
        this.jsonList = jsonList;
    }

    /**
     * 为了让每次获取的进程都是新的，保证线程安全
     * @return 新建的线程
     */
    public Object getObject()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        try {
            object = c.getDeclaredConstructor().newInstance();
        } catch (IllegalAccessException e) {
            log.warn("创建任务失败，请检查执行的class是否为Public , 报错：{}", e.getMessage());
            throw e;
        }

        return object;
    }
}
