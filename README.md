# codegen

## Die Idee von der Java Virtual Machine
- Die Java Virtual Machine dient als Abstraktionsschicht zwischen Betriebssystem und der Anwendung.

  `| Quellcode |----> Compiler --->| Bytecode |---> JVM --->| Assembler |`


## Generierung von Quellcode
  Beispielsweise durch: JAXB, Template based e.g. Freemarker, Velocity

### XJC
 - run `mvn clean generate-sources -Pxjc` 
 - view resulting Person.java in `target\generated-sources`

## Generierung von Bytecode
  Beispielsweise durch: ASM, BCEL, CGLib, ByteBuddy
  Bytecode einsehen: http://www.javadecompilers.com/
  
### Annotation Processing (statisch)
 - run `mvn clean install` 
 - run `mvn clean compile -Papt`
 - copy `target/generated sources` in `de.bk.codegen.apt` and run Main class
 
#### Lombok
 - `src\main\java\de\bk\codegen\lombok\Pojo.java` 
 - compile and inspect resulting class file
 
### ByteBuddy (dynamisch)
 - `src\main\java\de\bk\codegen\bytebuddy\ByteBuddy.java` 
 - run main method, see instance getting created and toString method being called
 
#### Java Agent
 - run `mvn clean install`
 - run `mvn clean compile exec:exec -Pagent` and view log for javaagent out print
 
## Fazit
### Quellcode
 `+` Der Quellcode ist einsehbar. leichter zu debuggen Der Generator ist leicht zu schreiben.
 
 `-` Ein Compiler ist erforderlich. Die Übersetzung kostet Zeit und Speicher.
### Bytecode
  `+` Bytecode	sehr performant Es ist kein Java-Compiler nötig. sinnvoll bei Bytecode-zu-Bytecode-Transformationen.
  
  `-` Der Bytecode ist schlecht einsehbar. Das Debugging ist schwierig. Der Generator ist schwer zu schreiben, da Wissen über Bytecode nötig ist.
				
		 

