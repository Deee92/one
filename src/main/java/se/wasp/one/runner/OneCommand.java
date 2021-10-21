package se.wasp.one.runner;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import se.wasp.one.util.ConstructEnum;
import se.wasp.one.util.VisibilityEnum;
import se.wasp.one.util.SyntacticEnum;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Logger;

@Command(name = "one", mixinStandardHelpOptions = true, version = "1.0",
        description = "Finds instances of a program construct matching input visibility")
public class OneCommand implements Callable<Integer> {
    @Parameters(index = "0", description = "The path to the .java file to analyze")
    private String projectPath;

    // TODO We need to group the both find and visibilty in a same group
    // hint: https://stackoverflow.com/questions/61665865/picocli-required-options-selection-based-on-a-primary-option

    @Option(names = {"-f", "--find"}, fallbackValue = "method", arity="0..1", description = "Program construct to find: method (default), class")
    private String inputProgramConstruct;
    private ConstructEnum programConstruct;

    @Option(names = {"-v", "--visibility"}, fallbackValue = "public",arity="0..1", description = "Construct visibility: public (default), private")
    private String inputVisibility;
    private VisibilityEnum visibility;

    @Option(names = {"-l", "--locate"}, fallbackValue = "loop", arity = "0..1", description = "Locate specified syntactic construct: loop (default)")
    private String inputSyntacticConstruct;
    private SyntacticEnum syntactic;

    private final static Logger LOGGER = Logger.getLogger(OneCommand.class.getName());

    private void setProgramConstruct() {
        if (inputProgramConstruct != null) {
            programConstruct = inputProgramConstruct.equalsIgnoreCase("class") ?
                    ConstructEnum.CLASS : ConstructEnum.METHOD;
        } else {
            programConstruct = ConstructEnum.NONE;
        }
    }

    private void setVisibility() {
        if (inputVisibility != null) {
            visibility = inputVisibility.equalsIgnoreCase("private") ?
                    VisibilityEnum.PRIVATE : VisibilityEnum.PUBLIC;
        } else {
            visibility = VisibilityEnum.NONE;
        }

    }

    private void setSyntactic() {
        if (inputSyntacticConstruct != null){
            switch (inputSyntacticConstruct.toLowerCase()){
                case "loop":
                    syntactic = SyntacticEnum.LOOP;
                    break;
                case "if":
                    syntactic = SyntacticEnum.IF;
                    break;
                case "assertion":
                    syntactic = SyntacticEnum.ASSERTION;
                    break;
                case "switch":
                    syntactic = SyntacticEnum.SWITCH;
                    break;
                case "synchronized":
                    syntactic = SyntacticEnum.SYNCHRONIZED;
                    break;
                case "flow_break":
                    syntactic = SyntacticEnum.FLOW_BREAK;
                    break;
                case "try":
                    syntactic = SyntacticEnum.TRY;
                    break;
                default:
                    syntactic = SyntacticEnum.NONE;
                    break;
            }
        } else {
            syntactic = SyntacticEnum.NONE;
        }

    }

    @Override
    public Integer call() throws Exception {
        if (!projectPath.endsWith(".java")) {
            System.out.println("Please provide a Java source file (.java). Try --help for help.");
            return 1;
        }
        setProgramConstruct();
        setVisibility();
        setSyntactic();
        if ((inputProgramConstruct != null) && (inputVisibility != null) ){
            LOGGER.info(String.format("You've chosen to find all %s constructs that are %s in %s",
                    programConstruct, visibility, projectPath));
        } else if (inputSyntacticConstruct != null) {
            LOGGER.info(String.format("You've chosen to find all %s constructs that are in %s",
                    syntactic, projectPath));
        }


        ProjectLauncher launcher = new ProjectLauncher(projectPath, programConstruct, visibility, syntactic);
        List<String> constructsFound = launcher.processModel();
        LOGGER.info(String.format("Found %s result(s)", constructsFound.size()));
        System.out.println(constructsFound);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new OneCommand()).execute(args);
        System.exit(exitCode);
    }
}
