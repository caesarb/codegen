package de.bk.codegen.bytebuddy;

import de.bk.codegen.apt.Testclass;

public class AgentExample {

	public static void main(String[] args){
		System.out.println("running AgentExample");
		new Testclass().testmethod();
	}
}
