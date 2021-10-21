package se.wasp.one.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtLoop;
import spoon.reflect.code.CtStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LoopProcessor  extends AbstractProcessor<CtStatement>{
    private static final Logger LOGGER = Logger.getLogger("TargetProcessor");

    private static List<String> loopsFound = new ArrayList<>();

    public LoopProcessor() {
        super();
    }
    @Override
    public void process(CtStatement aStatement) {
        if (aStatement instanceof CtLoop) {
            loopsFound.add(aStatement.getPath().toString());
        }

    }
    public List<String> getLoopssFound() {
        return loopsFound;
    }


}
