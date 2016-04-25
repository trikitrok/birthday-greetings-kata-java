package com.dodevjutsu.kata.birthdaygreetings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class MyFileReader {
    private final Path path;

    public MyFileReader(Path path) {
        this.path = path;
    }

    public Iterator<String> skipHeader() throws IOException {
        List<String> lines = Files.readAllLines(path);
        Iterator<String> iterator = lines.iterator();
        iterator.next();
        return iterator;
    }
}
