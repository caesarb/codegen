package de.bk.codegen.bytebuddy;

import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 
 * @see http://bytebuddy.net/#/
 * @author Benny.Kaiser
 *
 */
public class HelloWorld {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        HelloWorld b = new HelloWorld();
        b.greet();
    }

    public void greet() throws InstantiationException, IllegalAccessException {
        Class<?> dynamicType = new net.bytebuddy.ByteBuddy()//
                .subclass(Object.class)//
                .method(ElementMatchers.named("toString"))//
                .intercept(FixedValue.value("Hello World!"))//
                .make()//
                .load(getClass().getClassLoader())//
                .getLoaded();

        System.out.println(dynamicType.newInstance().toString());
        assertThat(dynamicType.newInstance().toString(), is("Hello World!"));
    }

}
