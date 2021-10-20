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

The result is printed into `/path/to/output/file`.

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
