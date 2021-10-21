package se.wasp.one.runner;

import org.junit.jupiter.api.Test;
import se.wasp.one.util.ConstructEnum;
import se.wasp.one.util.SyntacticEnum;
import se.wasp.one.util.VisibilityEnum;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OneCommandTest {

    static ProjectLauncher launcher;
    static String projectPath = Thread.currentThread().getContextClassLoader().getResource("NeuronString.java").getPath();

    @Test
    public void testThatPublicMethodsAreFound() {
        launcher = new ProjectLauncher(projectPath, ConstructEnum.METHOD, VisibilityEnum.PUBLIC, SyntacticEnum.NONE);
        List<String> publicMethodsFound = launcher.processModel();
        assertEquals(3, publicMethodsFound.size(), "There are three public methods in NeuronString.java");
    }

    @Test
    public void testThatPrivateMethodsAreFound() {
        launcher = new ProjectLauncher(projectPath, ConstructEnum.METHOD, VisibilityEnum.PRIVATE, SyntacticEnum.NONE);
        List<String> privateMethodsFound = launcher.processModel();
        assertEquals(4, privateMethodsFound.size(), "There are four private methods in NeuronString.java");
    }

    @Test
    public void testThatPublicClassesAreFound() {
        launcher = new ProjectLauncher(projectPath, ConstructEnum.CLASS, VisibilityEnum.PUBLIC, SyntacticEnum.NONE);
        List<String> publicClassesFound = launcher.processModel();
        assertEquals(1, publicClassesFound.size(), "There is one public class in NeuronString.java");
    }

    @Test
    public void testThatPrivateClassesAreFound() {
        launcher = new ProjectLauncher(projectPath, ConstructEnum.CLASS, VisibilityEnum.PRIVATE, SyntacticEnum.NONE);
        List<String> privateClassesFound = launcher.processModel();
        assertEquals(2, privateClassesFound.size(), "There are two private classes in NeuronString.java");
    }

    @Test
    public void testThatLoopsAreFound() {
        launcher = new ProjectLauncher(projectPath, ConstructEnum.NONE, VisibilityEnum.NONE, SyntacticEnum.LOOP);
        List<String> loopsFound = launcher.processModel();
        assertEquals(6, loopsFound.size(), "There are 6 loop constructs in NeuronString.java");
    }

    @Test
    public void testThatIfAreFound() {
        launcher = new ProjectLauncher(projectPath, ConstructEnum.NONE, VisibilityEnum.NONE, SyntacticEnum.IF);
        List<String> ifFound = launcher.processModel();
        assertEquals(4, ifFound.size(), "There are 4 if constructs in NeuronString.java");
    }

    @Test
    public void testThatAssertAreFound() {
        launcher = new ProjectLauncher(projectPath, ConstructEnum.NONE, VisibilityEnum.NONE, SyntacticEnum.ASSERTION);
        List<String> assertFound = launcher.processModel();
        assertEquals(1, assertFound.size(), "There are 1 assert construct in NeuronString.java");
    }

    @Test
    public void testThatSwitchAreFound() {
        launcher = new ProjectLauncher(projectPath, ConstructEnum.NONE, VisibilityEnum.NONE, SyntacticEnum.SWITCH);
        List<String> switchFound = launcher.processModel();
        assertEquals(1, switchFound.size(), "There are 1 switch construct in NeuronString.java");
    }

    @Test
    public void testThatSynchronizedAreFound() {
        launcher = new ProjectLauncher(projectPath, ConstructEnum.NONE, VisibilityEnum.NONE, SyntacticEnum.SYNCHRONIZED);
        List<String> synchronizedFound = launcher.processModel();
        assertEquals(1, synchronizedFound.size(), "There are 1 synchronized construct in NeuronString.java");
    }
}
