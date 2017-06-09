package de.bk.codegen.apt;

public class Testclass {

	@Log
    public void testmethod(){
		
		String a = "";
    	for(int i=0; i<2000; i++){
    		a+="a";
    	}
    	a=a.replace("a", "");
    }
	

}
