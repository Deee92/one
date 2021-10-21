package se.wasp.one.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtStatement;
import spoon.reflect.code.CtSynchronized;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SynchronizedProcessor  extends AbstractProcessor<CtStatement>{
    private static final Logger LOGGER = Logger.getLogger("TargetProcessor");

    private static List<String> synchronizedFound = new ArrayList<>();

    public SynchronizedProcessor() {
        super();
    }
    @Override
    public void process(CtStatement aStatement) {
        if (aStatement instanceof CtSynchronized) {
            synchronizedFound.add(aStatement.getPath().toString());
        }

    }
    public List<String> getSynchronizedFound() {
        return synchronizedFound;
    }


}
