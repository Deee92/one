package se.wasp.one.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtAssert;
import spoon.reflect.code.CtStatement;
import spoon.reflect.code.CtTry;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TryProcessor  extends AbstractProcessor<CtStatement>{
    private static final Logger LOGGER = Logger.getLogger("TargetProcessor");

    private static List<String> tryFound = new ArrayList<>();

    public TryProcessor() {
        super();
    }
    @Override
    public void process(CtStatement aStatement) {
        if (aStatement instanceof CtTry) {
            tryFound.add(aStatement.getPath().toString());
        }

    }
    public List<String> getTryFound() {
        return tryFound;
    }


}
