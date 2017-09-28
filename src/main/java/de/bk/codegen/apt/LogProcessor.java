package de.bk.codegen.apt;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes("de.bk.codegen.apt.Log")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class LogProcessor extends AbstractProcessor {

	public LogProcessor() {
		super();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		for (TypeElement annotation : annotations) {
			Set<? extends Element> annotatedElements = roundEnv
					.getElementsAnnotatedWith(annotation);

			List<Element> annotatedMethods = new ArrayList<>(annotatedElements);

			String className = ((TypeElement) annotatedMethods.get(0)
					.getEnclosingElement()).getQualifiedName().toString();

			String methodName = annotatedMethods.get(0).getSimpleName()
					.toString();

			try {
				writeDelegatorFile(className, methodName);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return true;
	}

	private void writeDelegatorFile(String className, String methodName)
			throws IOException {

		String packageName = null;
		int lastDot = className.lastIndexOf('.');
		if (lastDot > 0) {
			packageName = className.substring(0, lastDot);
		}

		String simpleClassName = className.substring(lastDot + 1);
		String delegatorClassName = className + "Delegator";
		String builderSimpleClassName = delegatorClassName.substring(lastDot + 1);

		//javax.tools : generate source or class decision here
		JavaFileObject builderFile = processingEnv.getFiler().createSourceFile(
				delegatorClassName);

		
		try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {

			if (packageName != null) {
				out.print("package "+packageName+";");
				out.println();
			}

			out.println("public class "+builderSimpleClassName+" {");
			out.println();
			out.println("    private "+simpleClassName+" object = new "+simpleClassName+"();");
			out.println();
			out.println("    public void "+methodName+"() {");
			out.println("        long start = System.currentTimeMillis();");
			out.println("        object."+methodName+"();");
			out.println("        long end = System.currentTimeMillis();");
			out.println("        System.out.println((end-start)+ \"ms\");");
			out.println("    }");
			out.println();

			out.println("}");

		}
	}

}
