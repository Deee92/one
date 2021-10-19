package se.wasp.cli;

import picocli.CommandLine;
import se.wasp.one.runner.OneCommand;
import se.wasp.parser.runner.ParserCommand;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "UBU",
        mixinStandardHelpOptions = true,
        subcommands = {OneCommand.class, ParserCommand.class},
        description =
                "The UBU command line, for running different units of the project.",
        synopsisSubcommandLabel = "<COMMAND>")
public class UBUCommand implements Callable<Integer> {

    @Override
    public Integer call() {
        new CommandLine(this).usage(System.out);
        return -1;
    }

}
