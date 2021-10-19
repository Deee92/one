package se.wasp.parser.runner;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

public class ParserLauncherTest {
    static String projectPath = Thread.currentThread().getContextClassLoader().getResource("NeuronString.java").getPath();
    static ParserLauncher launcher = new ParserLauncher(projectPath);

    @Test
    public void test_visibilityIsNoted() throws IOException {
        File tmpFile = File.createTempFile("parser", "res");
        launcher.printModel(tmpFile.getPath());
        String result = FileUtils.readFileToString(tmpFile, "UTF-8");
        assertTrue(result.contains("visibility"));
    }
}
