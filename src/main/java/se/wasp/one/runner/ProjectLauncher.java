package se.wasp.one.runner;

import se.wasp.one.processor.ClassProcessor;
import se.wasp.one.processor.MethodProcessor;
import se.wasp.one.util.ConstructEnum;
import se.wasp.one.util.VisibilityEnum;
import spoon.Launcher;
import spoon.reflect.CtModel;

import java.util.List;
import java.util.logging.Logger;

public class ProjectLauncher {

  private Launcher launcher = new Launcher();
  private ConstructEnum programConstruct;
  private VisibilityEnum visibility;
  private CtModel model;

  private final static Logger LOGGER = Logger.getLogger(ProjectLauncher.class.getName());

  ProjectLauncher(String path, ConstructEnum programConstruct, VisibilityEnum visibility) {
    LOGGER.info(String.format("Processing %s", path));
    this.programConstruct = programConstruct;
    this.visibility = visibility;
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
    }
    MethodProcessor methodProcessor = new MethodProcessor(visibility);
    model.processWith(methodProcessor);
    return methodProcessor.getMethodsFound();
  }
}
