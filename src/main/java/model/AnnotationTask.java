package model;

import lombok.Getter;
import lombok.Setter;
import utils.FilesWalkUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;

@Getter
@Setter
public class AnnotationTask {
    Object object;
    Class<?> c;
    Path filePath = null;
    Method method;
    Class<?>[] TypeArray;
    String[] jsonList;
    String taskFrom = "undefined";


    public AnnotationTask(Class<?> c, Path filePath, Method method) throws IOException {
        this.c = c;
        this.filePath = filePath;
        this.method = method;
        TypeArray = method.getParameterTypes();
        jsonList = FilesWalkUtils.getStringListWithOutBlackOrNote(filePath).toArray(String[]::new);
    }

    public AnnotationTask(Class<?> c, String[] jsonList, Method method){
        this.c = c;
        this.method = method;
        TypeArray = method.getParameterTypes();
        this.jsonList = jsonList;
    }

    /**
     * 为了保证每次获取的都是最新的实例，不然就跟一个线程调用两次start方法一样不可预知
     */
    public Object getObject() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        object = c.getDeclaredConstructor().newInstance();
        return object;
    }
}
