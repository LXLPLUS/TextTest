package test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import utils.FilesWalkUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class testFree {

    ArrayList<Integer> list = new ArrayList<>(List.of(1, 2, 3));

    @Test
    void check() throws IOException {
        File directory = new File("");
        System.out.println(directory.getAbsolutePath());
        ArrayList<Path> collect = Files.walk(Path.of(directory.getAbsolutePath())).collect(Collectors.toCollection(ArrayList::new));
        collect.removeIf(path -> !path.toFile().isFile());
        System.out.println(collect);
        collect.removeIf(path -> !path.toFile().getAbsolutePath().endsWith("ttest"));
        System.out.println(collect);

    }
}
