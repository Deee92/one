package se.wasp.one.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtStatement;
import spoon.reflect.code.CtSwitch;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SwitchProcessor  extends AbstractProcessor<CtStatement>{
    private static final Logger LOGGER = Logger.getLogger("TargetProcessor");

    private static List<String> switchFound = new ArrayList<>();

    public SwitchProcessor() {
        super();
    }
    @Override
    public void process(CtStatement aStatement) {
        if (aStatement instanceof CtSwitch) {
            switchFound.add(aStatement.getPath().toString());
        }

    }
    public List<String> getSwitchFound() {
        return switchFound;
    }


}
