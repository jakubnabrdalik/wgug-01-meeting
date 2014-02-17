package eu.solidcraft.defhorror

import spock.lang.Specification

class DefHorrorSpec extends Specification {

    def "so what that compiler understands, when you do not"() {
        when:
            def outcome = ChujowyService.triangulate()

        then:
            outcome instanceof Object
    }

    def "def doesn't protect you"() {
        given:
            def zmienna = "dupa"
            zmienna = 34

        expect:
            zmienna == 34
    }

    /*

    See: http://groovy.codehaus.org/Scoping+and+the+Semantics+of+%22def%22
    "In variable definitions it is used to indicate that you don't care about the type.
     You can think of "def" as an alias of "Object" and you will understand it in an instant."

    where you should use def:
    - names of tests
    - duck typing

    */
}
