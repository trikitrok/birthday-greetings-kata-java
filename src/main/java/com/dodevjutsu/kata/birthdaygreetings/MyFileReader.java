package com.dodevjutsu.kata.birthdaygreetings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;

public class MyFileReader {
    private final Path path;

    public MyFileReader(Path path) {
        this.path = path;
    }

    public Iterator<String> skipHeader() throws IOException {
        Iterator<String> iterator = readFile();
        return skipHeader(iterator);
    }

    private Iterator<String> readFile() throws IOException {
        List<String> lines = Files.readAllLines(path);
        return lines.iterator();
    }

    private Iterator<String> skipHeader(Iterator<String> iterator) {
        iterator.next();
        return iterator;
    }
}
