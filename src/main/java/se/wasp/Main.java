package se.wasp;

import picocli.CommandLine;
import se.wasp.cli.UBUCommand;

public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new UBUCommand()).execute(args);
        System.exit(exitCode);
    }
}
