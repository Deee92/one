# Unit-by-Unit (UBU)
This project performs simple program analysis tasks according to the "do one thing and do it well" rule. 

#### Building
```
mvn clean install
alias ubu='java -jar /abs/path/to/ubu.jar'
```

## Parser unit
This unit parses a directory of Java source files and prints the result into a file.
```
ubu --source /path/to/source/dir --output /path/to/output/file
```

The result is printed int `/path/to/output/file` and looks like this:
```
CtTypeReferenceImpl,null,,CtLiteralImpl,/home/khaes/phd/courses/wasp-4/tmp/Patch-Explainer-Test/src/main/java/NumberAnalyzer.java;5;23;5;23
CtLiteralImpl,/home/khaes/phd/courses/wasp-4/tmp/Patch-Explainer-Test/src/main/java/NumberAnalyzer.java;5;23;5;23,,CtBinaryOperatorImpl,/home/khaes/phd/courses/wasp-4/tmp/Patch-Explainer-Test/src/main/java/NumberAnalyzer.java;5;18;5;23
```
The output follows this format:
```
element-type,element-location,extra-info,parent-type,parent-location
```
The location is given in this format:
```
file;start-line;start-column;end-line;end-column
```
The extra-info is given as follows:
```
attribute1=value1;attribute2=value2
```

The parser unit prints an output with the following format:
```
{"parseResultPath":"/home/khaes/phd/courses/wasp-4/one/../tmp/parsed.res"}
```

## one unit

This unit uses [Spoon](https://spoon.gforge.inria.fr/) to find program constructs from a Java source file, based on their visibility.
It currently supports public and private classes or methods.

### Dependencies
- JDK 11+
- [Maven](https://maven.apache.org/)

#### Running

```
ubu /path/to/a/java/SourceFile.java one \
    --find <method (default) OR class> \
    --visibility <public (default) OR private>
  ```
