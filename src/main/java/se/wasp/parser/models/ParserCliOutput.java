package se.wasp.parser.models;

public class ParserCliOutput {
    private String parseResultPath;

    public ParserCliOutput(String parseResultPath){
        this.parseResultPath = parseResultPath;
    }

    public String getParseResultPath() {
        return parseResultPath;
    }

    public void setParseResultPath(String parseResultPath) {
        this.parseResultPath = parseResultPath;
    }
}
