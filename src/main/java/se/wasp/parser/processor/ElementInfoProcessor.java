package se.wasp.parser.processor;

import se.wasp.parser.models.SerializedAST;
import spoon.processing.AbstractProcessor;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtModifiable;
import spoon.reflect.declaration.ModifierKind;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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

    private SerializedAST serializedAST;

    public ElementInfoProcessor(){
        serializedAST = new SerializedAST();
        serializedAST.setElements(new ArrayList<>());
    }

    @Override
    public void process(CtElement element) {
        serializedAST.getElements().add(getASTElement(element));
    }

    private SerializedAST.ASTElement getASTElement(CtElement element) {
        CtElement parent = element.getParent();
        String parentType = parent == null ? "null" : parent.getClass().getSimpleName();

        return new SerializedAST.ASTElement(element.getClass().getSimpleName(), parentType, getElemLocation(element),
                getElemLocation(parent), getExtraInfo(element));
    }

    private SerializedAST.ASTElement.Location getElemLocation(CtElement element) {
        SerializedAST.ASTElement.Location elemLocation = null;
        if (element.getPosition().isValidPosition()) {
            SourcePosition elemPos = element.getPosition();
            elemLocation = new SerializedAST.ASTElement.Location(
                    elemPos.getLine(),
                    elemPos.getEndLine(),
                    elemPos.getColumn(),
                    elemPos.getEndColumn(),
                    elemPos.getFile().getAbsolutePath()
            );
        }
        return elemLocation;
    }

    private SerializedAST.ASTElement.ExtraInfo getExtraInfo(CtElement element) {
        SerializedAST.ASTElement.ExtraInfo extraInfo = new SerializedAST.ASTElement.ExtraInfo();
        extraInfo.setPairs(new HashMap<>());

        if (element instanceof CtModifiable) {
            if (!element.getPath().toString().contains("subPackage[name=java]")) {
                CtModifiable modifiable = (CtModifiable) element;
                Set<ModifierKind> modifiers = modifiable.getModifiers();
                if (modifiers.toString().contains("public"))
                    extraInfo.getPairs().put("visibility", "public");
                else if (modifiers.toString().contains("private"))
                    extraInfo.getPairs().put("visibility", "private");
                else if (modifiers.toString().contains("protected"))
                    extraInfo.getPairs().put("visibility", "protected");
                extraInfo.getPairs().put("path", element.getPath().toString());
            }
        }

        return extraInfo;
    }

    public SerializedAST getSerializedAST() {
        return serializedAST;
    }
}
