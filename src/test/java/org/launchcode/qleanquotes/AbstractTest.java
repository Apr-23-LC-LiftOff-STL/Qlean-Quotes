package org.launchcode.qleanquotes;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class AbstractTest {


    protected static String getFileContents(String fileName) throws IOException {
        Path path = FileSystems.getDefault().getPath(fileName);
        return Files.readString(path);
    }


    protected Class getClassByName(String className) throws ClassNotFoundException{
        return Class.forName("org.launchcode.qleanquotes" + className);
    }
}