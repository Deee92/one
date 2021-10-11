## `one`

This unit finds program constructs from a Java source file, based on their visibility.
It currently supports public and private classes or methods.


### Dependencies
- JDK 11+
- [Maven](https://maven.apache.org/)

### Building and running

- `mvn clean install`
- ```
  java -jar target/<name-version-jar-with-dependencies>.jar /path/to/a/java/SourceFile.java
  --find <method (default) OR class>
  --visibility <public (default) OR private>
  ```
