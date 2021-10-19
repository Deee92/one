package se.wasp.one.runner;

import org.junit.jupiter.api.Test;
import se.wasp.one.util.ConstructEnum;
import se.wasp.one.util.VisibilityEnum;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OneCommandTest {

    static ProjectLauncher launcher;
    static String projectPath = Thread.currentThread().getContextClassLoader().getResource("NeuronString.java").getPath();

    @Test
    public void testThatPublicMethodsAreFound() {
        launcher = new ProjectLauncher(projectPath, ConstructEnum.METHOD, VisibilityEnum.PUBLIC);
        List<String> publicMethodsFound = launcher.processModel();
        assertEquals(3, publicMethodsFound.size(), "There are three public methods in NeuronString.java");
    }

    @Test
    public void testThatPrivateMethodsAreFound() {
        launcher = new ProjectLauncher(projectPath, ConstructEnum.METHOD, VisibilityEnum.PRIVATE);
        List<String> privateMethodsFound = launcher.processModel();
        assertEquals(4, privateMethodsFound.size(), "There are four private methods in NeuronString.java");
    }

    @Test
    public void testThatPublicClassesAreFound() {
        launcher = new ProjectLauncher(projectPath, ConstructEnum.CLASS, VisibilityEnum.PUBLIC);
        List<String> publicClassesFound = launcher.processModel();
        assertEquals(1, publicClassesFound.size(), "There is one public class in NeuronString.java");
    }

    @Test
    public void testThatPrivateClassesAreFound() {
        launcher = new ProjectLauncher(projectPath, ConstructEnum.CLASS, VisibilityEnum.PRIVATE);
        List<String> privateClassesFound = launcher.processModel();
        assertEquals(2, privateClassesFound.size(), "There are two private classes in NeuronString.java");
    }
}
