package se.wasp.one.processor;

import se.wasp.one.util.VisibilityEnum;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.ModifierKind;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MethodProcessor extends AbstractProcessor<CtMethod<?>> {
    private VisibilityEnum visibility;
    private List<String> methodsFound = new ArrayList<>();

    public MethodProcessor(VisibilityEnum visibility) {
        this.visibility = visibility;
    }

    private void filterByVisibility(CtMethod<?> method) {
        Set<ModifierKind> modifiers = method.getModifiers();
        if (modifiers.toString().contains(visibility.toString().toLowerCase()) &
                !method.getPath().toString().contains("subPackage[name=java]"))
            methodsFound.add(method.getPath().toString());
    }

    public List<String> getMethodsFound() {
        return methodsFound;
    }

    @Override
    public void process(CtMethod<?> method) {
        filterByVisibility(method);
    }
}
