package model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utils.FilesWalkUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;

@Getter
@Setter
@ToString
public class AnnotationTask {
    Object object;
    Class<?> c;
    Path filePath;
    Method method;
    Class<?>[] TypeArray;
    String[] JsonList;


    public AnnotationTask(Class<?> c, Path filePath, Method method) throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.c = c;
        this.object = c.getDeclaredConstructor().newInstance();
        this.filePath = filePath;
        this.method = method;
        TypeArray = method.getParameterTypes();
        JsonList = FilesWalkUtils.getStringListWithOutBlackOrNote(filePath).toArray(String[]::new);
    }

}
