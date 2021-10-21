package se.wasp.one.runner;

import se.wasp.one.processor.*;
import se.wasp.one.util.ConstructEnum;
import se.wasp.one.util.VisibilityEnum;
import se.wasp.one.util.SyntacticEnum;
import spoon.Launcher;
import spoon.reflect.CtModel;

import java.util.List;
import java.util.logging.Logger;
import java.util.ArrayList;

public class ProjectLauncher {

  private Launcher launcher = new Launcher();
  private ConstructEnum programConstruct;
  private VisibilityEnum visibility;
  private SyntacticEnum syntacticConstruct;
  private CtModel model;

  private final static Logger LOGGER = Logger.getLogger(ProjectLauncher.class.getName());

  ProjectLauncher(String path, ConstructEnum programConstruct, VisibilityEnum visibility, SyntacticEnum syntacticConstruct) {
    LOGGER.info(String.format("Processing %s", path));
    this.programConstruct = programConstruct;
    this.visibility = visibility;
    this.syntacticConstruct = syntacticConstruct;
    launcher.getEnvironment().setCommentEnabled(true);
    launcher.addInputResource(path);
    launcher.buildModel();
    model = launcher.getModel();
  }

  public List<String> processModel() {
    if (programConstruct.equals(ConstructEnum.CLASS)) {
      ClassProcessor classProcessor = new ClassProcessor(visibility);
      model.processWith(classProcessor);
      return classProcessor.getClassesFound();
    } else if (programConstruct.equals(ConstructEnum.METHOD)) {
      MethodProcessor methodProcessor = new MethodProcessor(visibility);
      model.processWith(methodProcessor);
      return methodProcessor.getMethodsFound();
    } else if (syntacticConstruct.equals(SyntacticEnum.LOOP)) {
      LoopProcessor loopProcessor = new LoopProcessor();
      model.processWith(loopProcessor);
      return loopProcessor.getLoopssFound();
    } else if (syntacticConstruct.equals(SyntacticEnum.IF)) {
      IfProcessor ifProcessor = new IfProcessor();
      model.processWith(ifProcessor);
      return ifProcessor.getIfFound();
    } else if (syntacticConstruct.equals(SyntacticEnum.ASSERTION)) {
      AssertionProcessor assertionProcessor = new AssertionProcessor();
      model.processWith(assertionProcessor);
      return assertionProcessor.getAssertionFound();
    }


    return null;

  }
}
