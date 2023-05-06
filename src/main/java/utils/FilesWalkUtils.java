package utils;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesWalkUtils {
    public static List<Path> getFileList(String filepath, String suffix) throws IOException {
        ArrayList<Path> pathCollect = Files.walk(Path.of(filepath)).collect(Collectors.toCollection(ArrayList::new));
        pathCollect.removeIf(path -> !path.toFile().isFile());
        pathCollect.removeIf(path -> !path.toFile().getAbsolutePath().endsWith(suffix));
        return pathCollect;
    }

    public static int getRowCountwithoutBlack(Path path) throws IOException {
        List<String> stringList = Files.readAllLines(path);
        stringList.removeIf(String::isBlank);
        return stringList.size();
    }
}
