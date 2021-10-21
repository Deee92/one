package se.wasp.one.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtAssert;
import spoon.reflect.code.CtStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class AssertionProcessor  extends AbstractProcessor<CtStatement>{
    private static final Logger LOGGER = Logger.getLogger("TargetProcessor");

    private static List<String> assertionFound = new ArrayList<>();

    public AssertionProcessor() {
        super();
    }
    @Override
    public void process(CtStatement aStatement) {
        if (aStatement instanceof CtAssert) {
            assertionFound.add(aStatement.getPath().toString());
        }

    }
    public List<String> getAssertionFound() {
        return assertionFound;
    }


}
