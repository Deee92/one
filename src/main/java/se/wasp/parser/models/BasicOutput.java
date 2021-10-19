package se.wasp.parser.models;

public class BasicOutput {
    private String parseResultPath;

    public BasicOutput(String parseResultPath){
        this.parseResultPath = parseResultPath;
    }

    public String getParseResultPath() {
        return parseResultPath;
    }

    public void setParseResultPath(String parseResultPath) {
        this.parseResultPath = parseResultPath;
    }
}
