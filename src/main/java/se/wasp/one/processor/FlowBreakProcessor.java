package se.wasp.one.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCFlowBreak;
import spoon.reflect.code.CtStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FlowBreakProcessor extends AbstractProcessor<CtStatement>{
    private static final Logger LOGGER = Logger.getLogger("TargetProcessor");

    private static List<String> flowBreakFound = new ArrayList<>();

    public FlowBreakProcessor() {
        super();
    }
    @Override
    public void process(CtStatement aStatement) {
        if (aStatement instanceof CtCFlowBreak) {
            flowBreakFound.add(aStatement.getPath().toString());
        }

    }
    public List<String> getFlowBreakFound() {
        return flowBreakFound;
    }


}
