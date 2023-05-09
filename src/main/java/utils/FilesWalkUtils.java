package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilesWalkUtils {
    public static List<Path> getFileList(String filepath, String suffix) throws IOException {
        ArrayList<Path> pathCollect = Files.walk(Path.of(filepath)).collect(Collectors.toCollection(ArrayList::new));
        pathCollect.removeIf(path -> !path.toFile().isFile());
        pathCollect.removeIf(path -> !path.toFile().getAbsolutePath().endsWith(suffix));
        return pathCollect;
    }

    public static int getRowCountWithoutBlack(Path path) throws IOException {
        return getStringListWithOutBlackOrNote(path).size();
    }

    public static List<String> getStringListWithOutBlackOrNote(Path path) throws IOException {
        List<String> stringList = Files.readAllLines(path);
        stringList.removeIf(x -> x == null || x.startsWith("#") || x.startsWith("//") || x.isBlank());
        return stringList;
    }

}
