# codegen

## Die Idee von der Java Virtual Machine
- Die Java Virtual Machine dient als Abstraktionsschicht zwischen Betriebssystem und der Anwendung.

  `| Quellcode |----> Compiler --->| Bytecode |---> JVM --->| Assembler |`


## Generierung von Quellcode
  Beispielsweise durch: JAXB, Template based e.g. Freemarker, Velocity

## Generierung von Bytecode
  Beispielsweise durch: ASM, BCEL, CGLib, ByteBuddy
  Bytecode einsehen: http://www.javadecompilers.com/
  
### Annotation Processing (statisch)
 -
#### Lombok
 - src\main\java\de\bk\codegen\lombok\Pojo.java (compile and inspect resulting class file)
 
### ByteBuddy (dynamisch)
 - src\main\java\de\bk\codegen\bytebuddy\ByteBuddy.java (run main method)
 
## Fazit
 Vor- und Nachteile vom Compiler und von direkter Bytecode-Erzeugung 
### Quellcode
 - Vorteil: Der Quellcode ist einsehbar. leichter zu debuggen Der Generator ist leicht zu schreiben.
 - Nachteil: Ein Compiler ist erforderlich. Die Übersetzung kostet Zeit und Speicher.
### Bytecode
 - Vorteil: Bytecode	sehr performant Es ist kein Java-Compiler nötig. sinnvoll bei Bytecode-zu-Bytecode-Transformationen.
 - Nachteil: Der Bytecode ist schlecht einsehbar. Das Debugging ist schwierig. Der Generator ist schwer zu schreiben, da Wissen über Bytecode nötig ist.
				
		 

