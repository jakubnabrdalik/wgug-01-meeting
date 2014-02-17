package eu.solidcraft.explicitreturn

import spock.lang.Specification

class ExplicitReturnSpec extends Specification {

    def "this should be trivial, right?"() {
        given:
            String value = new StrangeStringie().getMeSomeStrangeStringie()

        expect:
            value != "oe od aa htee oe od aa ht"
    }

    class StrangeStringie {
        void getMeSomeStrangeStringie() {
            StringBuffer buffer = new StringBuffer()
            buffer.append("some old java shit")
            buffer.append("even more old java shit")
            char[] chars = buffer.toString().toCharArray()
            chars.findAll {it in "oh fuck, what do I get?".toCharArray()}.join("")
        }
    }

    def "that looks strange, doesn't it?"() {
        expect:
            someMethodReturns() == "something"
    }

    String someMethodReturns() {
        String something = "some"
        something += "th"
        something = something << "ing"
        something
    }

    def "do not overdo returns"() {
        given:
            char[] chars = '23W4532@J#@#U$%G@##I#@S@#A^@#W^2E34S23O22M^@E#%@%'.toCharArray()
        expect:
            chars.findAll{ return it.isLetter() && it.isUpperCase() }.join() == "WJUGISAWESOME"
    }

    /*

        Always use explicit return,except for in-line closures

     */
}
