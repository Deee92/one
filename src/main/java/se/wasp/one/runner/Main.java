package se.wasp.one.runner;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import se.wasp.one.util.ConstructEnum;
import se.wasp.one.util.VisibilityEnum;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

@Command(name = "unit one", mixinStandardHelpOptions = true, version = "1.0",
        description = "Finds instances of a program construct matching input visibility")
public class Main implements Callable<Integer> {
    @Parameters(index = "0", description = "The path to the .java file to analyze")
    private String projectPath;

    @Option(names = {"-f", "--find"}, defaultValue = "method", description = "Program construct to find: method (default), class")
    private String inputProgramConstruct;
    private ConstructEnum programConstruct;

    @Option(names = {"-v", "--visibility"}, defaultValue = "public", description = "Construct visibility: public (default), private")
    private String inputVisibility;
    private VisibilityEnum visibility;

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    private void setProgramConstruct() {
        programConstruct = inputProgramConstruct.equalsIgnoreCase("class") ?
                        ConstructEnum.CLASS : ConstructEnum.METHOD;
    }

    private void setVisibility() {
        visibility = inputVisibility.equalsIgnoreCase("private") ?
                        VisibilityEnum.PRIVATE : VisibilityEnum.PUBLIC;
    }

    @Override
    public Integer call() throws Exception {
        if (!projectPath.endsWith(".java")) {
            System.out.println("Please provide a Java source file (.java). Try --help for help.");
            return 1;
        }
        setProgramConstruct();
        setVisibility();
        LOGGER.info(String.format("You've chosen to find all %s constructs that are %s in %s",
                programConstruct, visibility, projectPath));
        ProjectLauncher launcher = new ProjectLauncher(projectPath, programConstruct, visibility);
        List<String> constructsFound = launcher.processModel();
        LOGGER.info(String.format("Found %s result(s)", constructsFound.size()));
        System.out.println(constructsFound);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }
}
