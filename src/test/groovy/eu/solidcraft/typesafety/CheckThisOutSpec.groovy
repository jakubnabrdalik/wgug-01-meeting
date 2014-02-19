package eu.solidcraft.typesafety
import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode
import spock.lang.Specification

class CheckThisOutSpec extends Specification {
    /*
    see: ttp://melix.github.io/talks/s2gx-typechecking/slides.html#slide-1

    Goal

    - Find errors at compile time (fail early)
    - because lots of code do not use dynamic features of Groovy
    - so many bugs can be discovered before production
    - Make java developers even happier

    Turn the compiler grumpy

    - Report typos
    - missing method/property
    - Extension methods (aka DefaultGroovyMethods)
    - Type check assignments
    - Perform type inference
    - method/closure return type inference
    - generics type inference

     */

    //@TypeChecked
    class BadCodeExample {
        void foo() {}
        void bar() {}

        void method() {
            foo()
            baz()
            Set set = 'Bar'
            List<Number> list = ['a','b','c']
            println "taki fajny ten $gString"
        }
    }

    def "let's test it"() {
        given:
            BadCodeExample badCodeExample = new BadCodeExample()
        expect:
            badCodeExample != null

            //but you cannot call this: Groovy is strongly typed
            //see: http://chriswongdevblog.blogspot.com/2013/03/is-your-language-strongly-weakly.html
            //badCodeExample.method()
    }

    // it is better than your IDE
    //@TypeChecked
    class ReturnTypePropagation {
        boolean test() { true }
        void m() {
            int x = 1 + test() // return type checking
        }
    }

    //we can set it on class
    @TypeChecked
    class MyClassWithNoErrors {

        //and we can exclude it
        @TypeChecked(TypeCheckingMode.SKIP)
        int butThisMethodIsNot() {"badumTssss!"}

    }

    //we can set it on methods
    @TypeChecked
    void methodWithNoErrors() {
    }

    /*
    So what do you lose?
    metaclass
    categories
    missing methods etc.
    duck typing

    So should I?
    Use it as DEFAULT, and skip it where duck typing is necessary.
    Yes, you can set it up as default for the whole project, but I've never done that.
    Or... you can edit Groovy class template in IDEA
    */


    /*
    Also, you can add your own rules (extensions) to type checker
    http://groovy.codehaus.org/Type+checking+extensions
     */
}
