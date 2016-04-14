package com.archfiery.zoo.bear;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static spark.Spark.*;

/**
 * HelloWorld class
 */
public class App {

    public void loadStaticFiles() {
        staticFileLocation(Consts.BOOTSTRAP_DIR);
    }

    public void serveIndex() {
        StringBuilder sb = new StringBuilder();
        try (Stream<String> lines =
                     Files.lines(Paths.get("src/main/resources/public/startbootstrap/index.html"), Charset.defaultCharset())) {
            lines.forEachOrdered(line -> sb.append(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        get("/", (req, res) -> sb.toString());
        get("/index", (req, res) -> sb.toString());
    }

    public static void main(String[] args) {
        App app = new App();
        app.loadStaticFiles();
        app.serveIndex();
    }
}