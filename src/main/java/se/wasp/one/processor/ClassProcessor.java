package se.wasp.one.processor;

import se.wasp.one.util.VisibilityEnum;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.ModifierKind;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ClassProcessor extends AbstractProcessor<CtClass<?>> {
    private VisibilityEnum visibility;
    private static List<String> classesFound = new ArrayList<>();

    public ClassProcessor(VisibilityEnum visibility) {
        this.visibility = visibility;
    }

    private void filterByVisibility(CtClass<?> aClass) {
        Set<ModifierKind> modifiers = aClass.getModifiers();
        if (modifiers.toString().contains(visibility.toString().toLowerCase()) &
                !aClass.getPath().toString().contains("subPackage[name=java]"))
            classesFound.add(aClass.getPath().toString());
    }

    public List<String> getClassesFound() {
        return classesFound;
    }

    @Override
    public void process(CtClass<?> aClass) {
        filterByVisibility(aClass);
    }
}
