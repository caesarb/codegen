package de.bk.codegen.bytebuddy;

import java.lang.instrument.Instrumentation;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class Agent {
	
	public static void premain(String arguments, Instrumentation instrumentation) {
		new AgentBuilder.Default()
				.type(ElementMatchers.nameEndsWith("Example"))
				.transform(
						(builder, type, classLoader, module) -> builder.method(
								ElementMatchers.any()).intercept(
								MethodDelegation.to(TimingInterceptor.class)))
				.installOn(instrumentation);
	}
}
