package se.wasp.one.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class IfProcessor  extends AbstractProcessor<CtStatement>{
    private static final Logger LOGGER = Logger.getLogger("TargetProcessor");

    private static List<String> ifFound = new ArrayList<>();

    public IfProcessor() {
        super();
    }
    @Override
    public void process(CtStatement aStatement) {
        if (aStatement instanceof CtIf) {
            ifFound.add(aStatement.getPath().toString());
        }

    }
    public List<String> getIfFound() {
        return ifFound;
    }


}
