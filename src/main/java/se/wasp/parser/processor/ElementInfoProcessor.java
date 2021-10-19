package se.wasp.parser.processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtModifiable;
import spoon.reflect.declaration.ModifierKind;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ElementInfoProcessor extends AbstractProcessor<CtElement> {
    //    private static final String IS_COMMENT = "{is-comment}";
    private static final String ELEMENT_TYPE = "{element-type}";
    private static final String LOCATION = "{location}";
    private static final String EXTRA_INFO = "{extra-info}";
    private static final String PARENT_LOCATION = "{parent-location}";
    private static final String PARENT_ELEMENT_TYPE = "{parent-element-type}";
    private static final String ELEM_STR_TEMPLATE =
            String.format("%s,%s,%s,%s,%s", ELEMENT_TYPE, LOCATION, EXTRA_INFO, PARENT_ELEMENT_TYPE, PARENT_LOCATION);
    private static final String INFO_SEPARATOR = ";";

    private List<String> elementsFoundInfo = new ArrayList<>();

    @Override
    public void process(CtElement element) {
        elementsFoundInfo.add(getElemStr(element));
    }

    private String getElemStr(CtElement element) {
        CtElement parent = element.getParent();
        String parentType = parent == null ? "null" : parent.getClass().getSimpleName(),
                parentLocation = getElemLocation(parent);

        String elemInfo = ELEM_STR_TEMPLATE
//                .replace(IS_COMMENT, isComment + "")
                .replace(ELEMENT_TYPE, element.getClass().getSimpleName())
                .replace(LOCATION, getElemLocation(element))
                .replace(EXTRA_INFO, getExtraInfo(element))
                .replace(PARENT_ELEMENT_TYPE, parentType)
                .replace(PARENT_LOCATION, parentLocation);

        return elemInfo;
    }

    private String getElemLocation(CtElement element) {
        String elemLocation = "null";
        if (element.getPosition().isValidPosition()) {
            SourcePosition elemPos = element.getPosition();
            elemLocation = Stream.of(
                    elemPos.getFile().getAbsolutePath(),
                    elemPos.getLine(),
                    elemPos.getColumn(),
                    elemPos.getEndLine(),
                    elemPos.getEndColumn()).map(Object::toString).collect(Collectors.joining(INFO_SEPARATOR));
        }
        return elemLocation;
    }

    public List<String> getElementsFoundInfo() {
        return elementsFoundInfo;
    }

    private String getExtraInfo(CtElement element) {
        List<String> extraInfoLst = new ArrayList<>();

        if (element instanceof CtModifiable) {
            if (!element.getPath().toString().contains("subPackage[name=java]")) {
                CtModifiable modifiable = (CtModifiable) element;
                Set<ModifierKind> modifiers = modifiable.getModifiers();
                if (modifiers.toString().contains("public"))
                    extraInfoLst.add("visibility=public");
                else if (modifiers.toString().contains("private"))
                    extraInfoLst.add("visibility=private");
                else if (modifiers.toString().contains("protected"))
                    extraInfoLst.add("visibility=protected");
                extraInfoLst.add(element.getPath().toString());
            }
        }
        return extraInfoLst.stream().collect(Collectors.joining(INFO_SEPARATOR));
    }
}
